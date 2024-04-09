/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna
 * <p/>
 * This program is free software: you can redistribute it and/or modify it under the terms of
 * the GNU Affero General Public License as published by the Free Software Foundation,
 * either version 3 of the License, or (at your option) any later version.
 * <p/>
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * See the GNU Affero General Public License for more details.
 * <p/>
 * You should have received a copy of the GNU Affero General Public License along with this program.
 * If not, see <https://www.gnu.org/licenses/>.
 */

package net.datasiel.simpaweb.actionbeans;

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.StringUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.fields.SelectField;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.forms.SearchForm;
import com.manydesigns.elements.messages.SessionMessages;
import com.manydesigns.elements.options.DefaultSelectionProvider;
import com.manydesigns.elements.options.SelectionProvider;

import it.eng.parer.simparer.security.ClientUser;
import it.eng.spagoLite.SessionManager;
import net.datasiel.simpaweb.beans.StrutturaInfo;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.common.shiro.CustomRealm;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.pojo.VOrgStrut;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.simpaweb.elements.ElementsHelper;
import net.datasiel.webapp.AuthorizationException;
import net.datasiel.webapp.BaseAction;
import net.datasiel.webapp.DtsActionBeanContext;
import net.datasiel.webapp.elements.search.SearchCriteriaOracleImpl;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * @author reisoli
 * 
 */
@UrlBinding("/pro/strut/{idStrut}")
public class HomePrivata extends BaseAction {
    public static final int ITEM_PER_PAGINA = 5;
    protected String idutente; // ID user IAM
    private String idente;
    protected Long idStrut;
    private boolean ricercaEseguita;

    private List<ParUnitadocVO> udTrovateBozze;
    private List<ParUnitadocVO> udPaginaBozze;
    private int paginaBozze;

    private List<ParUnitadocVO> udTrovateVersamenti;
    private List<ParUnitadocVO> udPaginaVersamenti;
    private int paginaVersamenti;

    private Element enteStruttura;

    private SearchForm searchFormVersamenti;
    private DefaultSelectionProvider selTipoUnitaDoc = null;

    /***********************************************************************************
     * Proprieta' per sincronizzazioni tipo unita' documentaria / registro
     ***********************************************************************************/
    private String relName;
    private int selectionProviderIndex;
    private String jsonOutput;
    private String labelSearch;
    private String passwordVersatore = null;
    private String msgUtenteRicondotto;

    @Override
    public String getRelName() {
        return relName;
    }

    @Override
    public void setRelName(String relName) {
        this.relName = relName;
    }

    public String getMsgUtenteRicondotto() {
        return msgUtenteRicondotto;
    }

    public void setMsgUtenteRicondotto(String msgUtenteRicondotto) {
        this.msgUtenteRicondotto = msgUtenteRicondotto;
    }

    @Override
    public int getSelectionProviderIndex() {
        return selectionProviderIndex;
    }

    @Override
    public void setSelectionProviderIndex(int selectionProviderIndex) {
        this.selectionProviderIndex = selectionProviderIndex;
    }

    @Override
    public String getJsonOutput() {
        return jsonOutput;
    }

    @Override
    public void setJsonOutput(String jsonOutput) {
        this.jsonOutput = jsonOutput;
    }

    @Override
    public String getLabelSearch() {
        return labelSearch;
    }

    @Override
    public void setLabelSearch(String labelSearch) {
        this.labelSearch = labelSearch;
    }

    protected void initListaTipoUnitaDoc(Long idStrut, Long idUser, Connection con) throws SQLException {
        selTipoUnitaDoc = ElementsHelper.getTipiRegUniDoc(idStrut, idUser, con);
    }

    @Before
    public Resolution checkPermissions() throws AuthorizationException, SQLException {
        String permission = String.format(CustomRealm.PERMESSO_PER_STRUTTURA_LEGGI_D, idStrut);
        if (!CustomRealm.isPermitted(permission, idStrut, getContext().getRequest().getSession(), getConnection())) {
            return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN, "Struttura non accessibile dall'utente.");
        }
        return null;
    }

    @DefaultHandler
    public Resolution executeDefault() throws NumberFormatException, SQLException {

        setTitoloPaginaGenerico();
        setTitoloPagina(webappProperties.getProperty("titoloPagina"));

        // per scelta ente/struttura da combo
        String idStrutturaParam = context.getRequest().getParameter("enteStruttura_idStrut");
        if (StringUtils.isNotEmpty(idStrutturaParam)) {
            idStrut = Long.parseLong(idStrutturaParam);
        }
        if (idStrutturaParam != null && idStrutturaParam.equals("")) {
            SessionMessages.addInfoMessage("Bisognerebbe scegliere la struttura");
        }

        if (SessionMessages.getErrorQueue().isEmpty() && SessionMessages.getInfoQueue().isEmpty()
                && SessionMessages.getWarningQueue().isEmpty()) {
            messaggiPresenti = false;
        } else {
            messaggiPresenti = true;
        }
        ClientUser utente = getUser();
        setIdutente(String.valueOf(utente.getIdUtente()));
        DefaultSelectionProvider selEntiStrutture = new DefaultSelectionProvider("entiStrutture");
        List<StrutturaInfo> strutture = utente.getUtenteStrutture().getStrutture();
        boolean isUserAuthForCurrStrut = false;
        for (StrutturaInfo struttura : strutture) {
            String label = struttura.getNomeStruttura();
            Long value = struttura.getIdStrut();
            selEntiStrutture.appendRow(value, label, true);
            if (value.equals(idStrut)) {
                isUserAuthForCurrStrut = true;
            }
        }

        if (!isUserAuthForCurrStrut) {
            SessionMessages.addErrorMessage("Utente non autorizzato alla struttura.");
            return new RedirectResolution(HomePubblica.class);
        }

        VOrgStrut vOrgStrut = new VOrgStrut();
        vOrgStrut.setIdStrut(idStrut);
        enteStruttura = prepareEnteStruttura(selEntiStrutture, strutture.size() == 1, Mode.EDIT);
        enteStruttura.readFromObject(vOrgStrut);

        if (searchFormVersamenti == null) {
            initListaTipoUnitaDoc(idStrut, Long.parseLong(idutente), getConnection());
            searchFormVersamenti = (SearchForm) ElementsHelper.buildSearchFormVersamenti(selTipoUnitaDoc);
        }

        return new ForwardResolution("/pages/homeprivata.jsp");
    }

    private ClientUser getUser() {

        return (ClientUser) SessionManager.getUser(context.getRequest().getSession());

    }

    @HandlesEvent("doConfermaPassword")
    public Resolution doConfermaPassword() {
        RedirectResolution resolution = new RedirectResolution("/pages/homeprivata.jsp");
        if (idStrut != null && idStrut > 0) {
            resolution.addParameter("idStrut", idStrut);
        }
        if (passwordVersatore == null || "".equals(passwordVersatore)) {
            SessionMessages.addErrorMessage("Inserire la password di versatore");
        } else {

            context.getRequest().getSession().setAttribute("passwordVersatore", passwordVersatore);

        }

        return resolution;
    }

    @HandlesEvent("doEliminaPassword")
    public Resolution doEliminaPassword() {
        RedirectResolution resolution = new RedirectResolution("/pages/homeprivata.jsp");
        if (idStrut != null && idStrut > 0) {
            resolution.addParameter("idStrut", idStrut);
        }

        context.getRequest().getSession().removeAttribute("passwordVersatore");

        return resolution;
    }

    private void setTitoloPaginaGenerico() {
        String titolo = "";
        try (InputStream is = DtsActionBeanContext.class.getClassLoader()
                .getResourceAsStream("/MessageResources.properties")) {
            Properties props = new Properties();
            props.load(is);
            titolo = props.getProperty("titoloPagina");
        } catch (Exception e) {
            log.error("errore lettura testo help", e);
        }
        webappProperties.setProperty("titoloPagina", titolo);
    }

    public Resolution listaBozze() throws SQLException {
        ParUnitadocVO parDAO = new ParUnitadocVO();
        String localIdUtente = getIdutente();
        if (StringUtils.isBlank(localIdUtente)) {
            ClientUser ute = getUser();
            setIdutente("" + ute.getIdUtente());
            localIdUtente = getIdutente();

        }
        udTrovateBozze = parDAO.retrieveBozzeUtente(Long.parseLong(localIdUtente), idStrut, getConnection());
        int numeroBozze = udTrovateBozze.size();
        if (numeroBozze == 0) {
            log.debug("Unita' documentarie non trovate per l'utente");
            return new ForwardResolution("/pages/listaVuotaBozzeHome.jsp");
        } else {
            log.debug("Trovate numero {} unita' documentarie", numeroBozze);
        }
        int fromIndex = paginaBozze * ITEM_PER_PAGINA;

        int toIndex = (paginaBozze + 1) * ITEM_PER_PAGINA;

        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (toIndex < 0) {
            toIndex = 0;
        }
        if (toIndex > numeroBozze) {
            toIndex = numeroBozze;
        }
        if (fromIndex > numeroBozze) {
            fromIndex = numeroBozze;
        }

        udPaginaBozze = udTrovateBozze.subList(fromIndex, toIndex);

        return new ForwardResolution("/pages/listaBozzeHome.jsp");
    }

    @HandlesEvent("listaVersamenti")
    public Resolution listaVersamenti() throws SQLException {
        boolean cambiaPagina = context.getRequest().getParameter("cambiaPagina") != null;
        if (!ricercaEseguita && !cambiaPagina || ricercaEseguita && udTrovateVersamenti == null && !cambiaPagina) {
            ParUnitadocVO parDAO = new ParUnitadocVO();
            udTrovateVersamenti = parDAO.retrieveUltimi5VersamentiStruttura(idStrut, Long.parseLong(idutente),
                    getConnection());
        } else if (cambiaPagina) {
            HttpServletRequest request = context.getRequest();
            paginaVersamenti = Integer.parseInt(request.getParameter("paginaVersamenti"));
            initListaTipoUnitaDoc(idStrut, Long.parseLong(idutente), getConnection());
            searchFormVersamenti = (SearchForm) ElementsHelper.buildSearchFormVersamenti(selTipoUnitaDoc);
            searchFormVersamenti.readFromRequest(request);
            SearchCriteriaOracleImpl criteria = new SearchCriteriaOracleImpl("U");
            searchFormVersamenti.configureCriteria(criteria);
            String strWhere = String.format("%s", criteria.getStrWhereCondition());
            Collection<?> listaParametri = criteria.getLstParametri();
            Connection con = null;
            try {
                con = getConnection();
                ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
                udTrovateVersamenti = parUnitadocVO.cercaVersamenti(con, strWhere, idStrut, Long.parseLong(idutente),
                        listaParametri);
            } catch (Exception e) {
                log.error("Error in read udTrovateVersamenti", e);
            } finally {
                DbUtils.closeQuietly(con);
            }

        }
        int numeroVersamenti = udTrovateVersamenti.size();
        int fromIndex = paginaVersamenti * ITEM_PER_PAGINA;

        int toIndex = (paginaVersamenti + 1) * ITEM_PER_PAGINA;

        if (fromIndex < 0) {
            fromIndex = 0;
        }
        if (toIndex < 0) {
            toIndex = 0;
        }
        if (toIndex > numeroVersamenti) {
            toIndex = numeroVersamenti;
        }
        if (fromIndex > numeroVersamenti) {
            fromIndex = numeroVersamenti;
        }

        udPaginaVersamenti = udTrovateVersamenti.subList(fromIndex, toIndex);

        return new ForwardResolution("/pages/listaVersamentiHome.jsp");
    }

    public String getIdente() {
        return idente;
    }

    public void setIdente(String idente) {
        this.idente = idente;
    }

    public String getIdutente() {
        return idutente;
    }

    public void setIdutente(String idutente) {
        this.idutente = idutente;
    }

    public Long getIdStrut() {
        return idStrut;
    }

    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    public boolean isRicercaEseguita() {
        return ricercaEseguita;
    }

    public void setRicercaEseguita(boolean ricercaEseguita) {
        this.ricercaEseguita = ricercaEseguita;
    }

    public Element prepareEnteStruttura(DefaultSelectionProvider selEnteStruttura, boolean soloUnaStruttura,
            Mode mode) {
        reloadElementsThreadLocals();
        return ElementsHelper.buildEnteStruttura(selEnteStruttura, soloUnaStruttura, "enteStruttura_", mode);
    }

    public Element getEnteStruttura() {
        return enteStruttura;
    }

    public void setEnteStruttura(Element enteStruttura) {
        this.enteStruttura = enteStruttura;
    }

    /**
     * @return the udPaginaBozze
     */
    public List<ParUnitadocVO> getUdPaginaBozze() {
        return udPaginaBozze;
    }

    /**
     * @param udPaginaBozze
     *            the udPaginaBozze to set
     */
    public void setUdPaginaBozze(List<ParUnitadocVO> udPaginaBozze) {
        this.udPaginaBozze = udPaginaBozze;
    }

    /**
     * @return the paginaBozze
     */
    public int getPaginaBozze() {
        return paginaBozze;
    }

    /**
     * @param paginaBozze
     *            the paginaBozze to set
     */
    public void setPaginaBozze(int paginaBozze) {
        this.paginaBozze = paginaBozze;
    }

    /**
     * @return the udTrovateBozze
     */
    public List<ParUnitadocVO> getUdTrovateBozze() {
        return udTrovateBozze;
    }

    /**
     * @param udTrovateBozze
     *            the udTrovateBozze to set
     */
    public void setUdTrovateBozze(List<ParUnitadocVO> udTrovateBozze) {
        this.udTrovateBozze = udTrovateBozze;
    }

    public List<ParUnitadocVO> getUdTrovateVersamenti() {
        return udTrovateVersamenti;
    }

    public void setUdTrovateVersamenti(List<ParUnitadocVO> udTrovateVersamenti) {
        this.udTrovateVersamenti = udTrovateVersamenti;
    }

    public List<ParUnitadocVO> getUdPaginaVersamenti() {
        return udPaginaVersamenti;
    }

    public void setUdPaginaVersamenti(List<ParUnitadocVO> udPaginaVersamenti) {
        this.udPaginaVersamenti = udPaginaVersamenti;
    }

    public int getPaginaVersamenti() {
        return paginaVersamenti;
    }

    public void setPaginaVersamenti(int paginaVersamenti) {
        this.paginaVersamenti = paginaVersamenti;
    }

    public Resolution jsonSelectFieldSearchOptions() throws NumberFormatException, SQLException {
        return jsonSelectFieldSearchOptions(true);
    }

    @SuppressWarnings("unchecked")
    public Resolution jsonSelectFieldSearchOptions(Boolean includeSelectPrompt)
            throws NumberFormatException, SQLException {

        String[] fieldNames;
        String prefix;
        SelectionProvider selectionProvider;
        if (Constants.SELPROVIDER_TIPOLOGIAUNIDOC.equals(getRelName())) {
            // Gestione sincronizzazione per inserimento e modifica ul

            // individuazione del prefix
            Enumeration<String> parametriReq = context.getRequest().getParameterNames();
            prefix = "";
            while (parametriReq.hasMoreElements()) {
                String parametro = parametriReq.nextElement();
                if (parametro.endsWith("idRegistroUnitaDoc")) {
                    prefix = StringUtils.substringBefore(parametro, "idRegistroUnitaDoc");
                }
            }

            if (idutente == null)
                idutente = "" + getUser().getIdUtente();

            initListaTipoUnitaDoc(idStrut, Long.parseLong(idutente), getConnection());
            selectionProvider = selTipoUnitaDoc;
            fieldNames = new String[] { "idTipoUnitaDoc", "idRegistroUnitaDoc" };
        } else {
            log.error("Relname non trovato");
            return new StreamingResolution("text/plain");
        }

        /*
         * Costruzione form ridotto con i soli select da sincronizzare
         */

        Form searchForm = new FormBuilder(ParUnitadoc.class).configFields(fieldNames).configPrefix(prefix)
                .configSelectionProvider(selectionProvider, fieldNames).build();
        searchForm.readFromRequest(context.getRequest());

        /*
         * SelectionProviderIndex e' la posizione, all'interno dell'elenco di select che voglio sincronizzare in cascata
         * (di solito 2, ma possono essere di piu') dell'elemento che devo ricaricare con i valori dipendenti dalla
         * selezione nella select precedente
         */
        SelectField targetField = (SelectField) searchForm.get(0).get(getSelectionProviderIndex());

        /*
         * Il parametro passato discrimina tra combo in cascata (true) e autocomplete (false) NOTA: combo sincronizzate
         * e campo ad autocompletamento sono mutuamente esclusivi
         */
        targetField.setLabelSearch(getLabelSearch());
        String text = targetField.jsonSelectFieldOptions(includeSelectPrompt);

        setJsonOutput(text);

        StreamingResolution streamingResolution = new StreamingResolution("text/plain", text);
        streamingResolution.setCharacterEncoding(StandardCharsets.UTF_8.name());
        return streamingResolution;
    }

    public Resolution cerca() throws Exception {

        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);

        HttpServletRequest request = context.getRequest();

        if (idutente == null)
            idutente = "" + getUser().getIdUtente();

        initListaTipoUnitaDoc(idStrut, Long.parseLong(idutente), getConnection());
        searchFormVersamenti = (SearchForm) ElementsHelper.buildSearchFormVersamenti(selTipoUnitaDoc);
        searchFormVersamenti.readFromRequest(request);

        SearchCriteriaOracleImpl criteria = new SearchCriteriaOracleImpl("U");
        searchFormVersamenti.configureCriteria(criteria);
        String strWhere = String.format("%s", criteria.getStrWhereCondition());
        Collection<?> listaParametri = criteria.getLstParametri();

        Connection con = null;
        try {
            con = getConnection();
            ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
            udTrovateVersamenti = parUnitadocVO.cercaVersamenti(con, strWhere, idStrut, Long.parseLong(idutente),
                    listaParametri);

            int numeroVersamenti = udTrovateVersamenti.size();
            if (numeroVersamenti == 0) {
                log.debug("Unita' documentarie non trovate per l'utente");
                udTrovateVersamenti = new ArrayList<>();
                SessionMessages.addInfoMessage("Con i criteri impostati non e' stato trovato alcun risultato");
            } else {
                log.debug("Trovate numero {} unita' documentarie", numeroVersamenti);
                int fromIndex = paginaVersamenti * ITEM_PER_PAGINA;
                int toIndex = (paginaVersamenti + 1) * ITEM_PER_PAGINA;
                if (fromIndex < 0) {
                    fromIndex = 0;
                }
                if (toIndex < 0) {
                    toIndex = 0;
                }
                if (toIndex > numeroVersamenti) {
                    toIndex = numeroVersamenti;
                }
                if (fromIndex > numeroVersamenti) {
                    fromIndex = numeroVersamenti;
                }
                udPaginaVersamenti = udTrovateVersamenti.subList(fromIndex, toIndex);
            }

        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            DbUtils.closeQuietly(con);
        }
        if (udPaginaVersamenti != null && !udPaginaVersamenti.isEmpty()) {
            log.debug("Query eseguita. Trovati: {}", udPaginaVersamenti.size());
        }
        resolution.addParameter("ricercaEseguita", true);
        return resolution.flash(this);
    }

    public SearchForm getSearchFormVersamenti() {
        return searchFormVersamenti;
    }

    public void setSearchFormVersamenti(SearchForm searchFormVersamenti) {
        this.searchFormVersamenti = searchFormVersamenti;
    }

    public String getPasswordVersatore() {
        return passwordVersatore;
    }

    public void setPasswordVersatore(String passwordVersatore) {
        this.passwordVersatore = passwordVersatore;
    }

    @Override
    public String getTitoloPagina() {
        // TODO Auto-generated method stub
        return "Homepage Verso";
    }

}

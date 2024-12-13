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

package net.datasiel.simpaweb.actionbeans.crudud;

import it.eng.spagoLite.SessionManager;
import it.eng.spagoLite.security.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import net.datasiel.simpaweb.beans.DatiSpecifici;
import net.datasiel.simpaweb.beans.EnumEntitaDatiSpecifici;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.common.crud.SimpaMonoCrudAction;
import net.datasiel.simpaweb.common.shiro.CustomRealm;
import net.datasiel.simpaweb.db.DbConstants;
import net.datasiel.simpaweb.db.EnumStatoUD;
import net.datasiel.simpaweb.db.dao.DbUtil;
import net.datasiel.simpaweb.db.dao.ParValoredatispecificiDAO;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.pojo.ParValoredatispecifici;
import net.datasiel.simpaweb.db.pojo.VDecAttribDatiSpec;
import net.datasiel.simpaweb.db.vo.ParDatispecificiVO;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.simpaweb.db.vo.VDecAttribDatiSpecVO;
import net.datasiel.simpaweb.elements.ElementsHelper;
import net.datasiel.webapp.AuthorizationException;
import net.datasiel.webapp.InfoToolbar;
import net.datasiel.webapp.crud.CrudUI;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.util.UrlBuilder;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.FormElement;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.fields.SelectField;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.messages.SessionMessages;
import com.manydesigns.elements.options.DefaultSelectionProvider;
import com.manydesigns.elements.options.SelectionProvider;
import com.manydesigns.elements.reflection.JavaClassAccessor;
import com.manydesigns.elements.reflection.PropertyAccessor;
import it.eng.parer.simparer.security.ClientUser;

import net.sourceforge.stripes.action.ErrorResolution;

/**
 * @author reisoli
 *
 */
@UrlBinding("/pro/editud/{idrecord}")
@InfoToolbar(titolo = "Unità documentaria", accelerator = "U", breadcrumbs = "Home")
public class UnitaDocumentaria extends SimpaMonoCrudAction {

    public static final String TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE = "Tentativo di aggiornamento di domanda non modificabile";

    private static final String CH_DTS_ACA_RESULT = "DtsAcaResult";

    private static final String CH_VALIDATION_ERROR = "validationError";

    private static final String CH_INSERT_OK = "insertOk";

    private static final String EL_NEW_RIGA = "elNewRiga";

    public UnitaDocumentaria() {
        super();
        this.pgm = getClass().getSimpleName();
        setTitoloPagina(getTitoloFromAnnotation());
    }

    private static String[][] campiEditUnitaDoc = { { "anno", "numero", "oggetto", "data", "cdVersioneXSD" },
            { "tipoconservazione", "flgforzaconservazione", "flgforzaaccettazione", "flgforzacollegamento" } };
    public DefaultSelectionProvider selTipoUnitaDoc = null;
    protected DefaultSelectionProvider selCDVersione = null;
    protected DatiSpecifici datiForm;
    protected long idDatiSpecifici;
    protected List<VDecAttribDatiSpecVO> listaCampi;

    /**
     * Contiene i dati della riga di dettaglio da inserire (dovrebbe servire per entrambi i dettagli).
     */
    protected Object dettaglioModel;

    /**
     * Form per inserimento riga
     */
    protected Element elNewRiga;

    /**
     * Indica se le chiamate devono essere gestite in modalità ajax o no.
     */
    protected boolean ajaxEnabled;

    /**
     * Indice della riga su cui si fanno le operazioni.
     */
    protected Integer indiceRiga;

    /**
     * Contiene l'indice della riga da eliminare
     */
    protected String delete;

    private String insertDettaglio1;

    private String insertDettaglio2;

    private String createDettaglio1;

    private String createDettaglio2;

    protected void initListaTipoUnitaDoc(Long idStrut, Long idTipoUnitaDoc, Long idUser, Connection con)
            throws SQLException {
        selTipoUnitaDoc = ElementsHelper.getTipiRegUniDoc(idStrut, idUser, con);
        if (idTipoUnitaDoc != null) {
            selCDVersione = ElementsHelper.getCDVersioneUniDoc(idStrut, EnumEntitaDatiSpecifici.UNI_DOC.name(),
                    idTipoUnitaDoc, con);
        } else {
            selCDVersione = new DefaultSelectionProvider("cdVersioneXSD");
        }
    }

    @Before
    public Resolution caricaDatiUnitaDoc() throws AuthorizationException, SQLException, NoSuchFieldException {
        // Se sono in inserimento ho idrecord=0 e idStrut arrivato dai parametri
        // Se sono in edit ho idrecord e devo ricavare idStrut
        Connection connection = getConnection();
        String permission;
        if (idrecord != 0) {
            leggiDatiUnitaDoc(idrecord, connection);
            idStrut = datiUnitaDoc.getIdStrut();
            permission = String.format(CustomRealm.PERMESSO_PER_UD_LEGGI_D, idrecord);
        } else {
            permission = String.format(CustomRealm.PERMESSO_PER_STRUTTURA_LEGGI_D, idStrut);
        }

        if (!CustomRealm.isPermitted(permission, idStrut, getContext().getRequest().getSession(), getConnection())) {
            return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN, CustomRealm.CUSTOM_FORBIDDEN_MESSAGE);
        }
        Long idTipoUnitaDoc = datiUnitaDoc == null ? null : datiUnitaDoc.getIdTipoUnitaDoc();

        if (datiUnitaDoc != null)
            initListaTipoUnitaDoc(idStrut, idTipoUnitaDoc, datiUnitaDoc.getIdutente(), connection);
        else
            initListaTipoUnitaDoc(idStrut, idTipoUnitaDoc, getUser().getIdUtente(), connection);

        ParDatispecificiVO parDsDAO = new ParDatispecificiVO();
        VDecAttribDatiSpecVO vdecAttribDAO = new VDecAttribDatiSpecVO();
        /*
         * UNI_DOC("idunitadoc"), DOC("iddocumento"), COMP("idcomponente"), SUB_COMP("idcomponente")
         */
        if (idrecord != 0) {
            idDatiSpecifici = parDsDAO.loadOrCreateDatiSpecifici(idrecord, EnumEntitaDatiSpecifici.UNI_DOC, connection);
            String versioneXsd = datiUnitaDoc.getCdVersioneXSD();
            if (tipoDato == null)
                tipoDato = EnumEntitaDatiSpecifici.UNI_DOC.name();
            listaCampi = vdecAttribDAO.getValoriSpecificiPerVersione(idDatiSpecifici, idStrut, tipoDato,
                    datiUnitaDoc.getIdTipoUnitaDoc(), versioneXsd, connection);

            datiForm = new DatiSpecifici();
            int index = 1;
            JavaClassAccessor jca = JavaClassAccessor.getClassAccessor(DatiSpecifici.class);
            for (VDecAttribDatiSpecVO current : listaCampi) {
                PropertyAccessor field = jca.getProperty(String.format("campo%s", index));
                field.set(datiForm, current.getValore());
                index++;
            }
        }

        return null;
    }

    // TO IMPROVE
    private ClientUser getUser() {
        return (ClientUser) SessionManager.getUser(context.getRequest().getSession());
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#prepareUiRO()
     */
    @Override
    public Element prepareUiROInsert() {
        // in inserimento nessuna form di sola lettura
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#prepareUiRW(com.manydesigns .elements.Mode)
     */
    @Override
    public Element prepareUiRWInsert(Mode mode) {
        return new FormBuilder(ParUnitadoc.class).configFieldSetNames("Intestazione")
                .configFields("idTipoUnitaDoc", "idRegistroUnitaDoc", "anno", "numero", "oggetto", "data")
                .configSelectionProvider(selTipoUnitaDoc, "idTipoUnitaDoc", "idRegistroUnitaDoc")
                .configPrefix("datiUnitaDoc_").configMode(mode).build();
    }

    @Override
    public Element prepareUiROEdit() {
        Mode modo = (isReadOnly() || idrecord != 0) ? Mode.PREVIEW : Mode.EDIT;
        Form pagina = new FormBuilder(ParUnitadoc.class).configFields("idTipoUnitaDoc", "idRegistroUnitaDoc")
                .configFieldSetNames("Tipo unità documentaria")
                .configSelectionProvider(selTipoUnitaDoc, "idTipoUnitaDoc", "idRegistroUnitaDoc")
                .configPrefix("datiUnitaDoc_").configMode(modo).build();

        return pagina;
    }

    @Override
    public Element prepareUiRWEdit(Mode mode) {

        Form pagina = new FormBuilder(ParUnitadoc.class).configFieldSetNames("Intestazione", "Configurazione")
                .configFields(campiEditUnitaDoc).configSelectionProvider(selCDVersione, "cdVersioneXSD")
                .configPrefix("datiUnitaDoc_").configMode(mode).build();

        return pagina;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#loadModel()
     */
    @Override
    public void loadModel() throws Exception {
        modelRW = datiUnitaDoc;
        modelRO = datiUnitaDoc;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#updateModel()
     */
    @Override
    public void updateModel() throws Exception {
        ParUnitadoc datiUnitaDoc = (ParUnitadoc) modelRW;
        ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
        datiUnitaDoc.setPgm(pgm);
        datiUnitaDoc.setId(RandomUtils.nextLong());
        datiUnitaDoc.setStato(EnumStatoUD.BOZZA.getValore());
        parUnitadocVO.updateByIndex(datiUnitaDoc, getConnection());
        log.info(getClass().getName() + " - Aggiornata Unità Documentaria: " + datiUnitaDoc.getIdunitadoc());

    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#createModel()
     */
    @Override
    public void createModel() {
        modelRW = new ParUnitadoc();
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#saveModel()
     */
    @Override
    protected void saveModel() throws Exception {
        idrecord = DbUtil.getSequenceValue("PAR_SEQ_IDUNITADOC", getConnection());
        ParUnitadoc datiUnitaDoc = (ParUnitadoc) modelRW;
        datiUnitaDoc.setIdunitadoc(idrecord);

        User utente = (User) SessionManager.getUser(context.getRequest().getSession());
        Long idUtente = utente.getIdUtente();
        datiUnitaDoc.setIdStrut(idStrut);
        datiUnitaDoc.setIdutente(idUtente);
        datiUnitaDoc.setVersione(Constants.VERSIONE_VERSAMENTO);

        datiUnitaDoc.setTipoconservazione("S");
        datiUnitaDoc.setStato(EnumStatoUD.BOZZA.getValore());
        datiUnitaDoc.setFlgstato(0L);
        datiUnitaDoc.setPgm(pgm);
        datiUnitaDoc.setId(0L);
        ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
        parUnitadocVO.insertPrepared(datiUnitaDoc, getConnection());
        log.info("{} - Inserita Unita' documentaria: {}", getClass().getName(), datiUnitaDoc.getIdunitadoc());
        SessionMessages.addInfoMessage("Unità Documentaria " + datiUnitaDoc.getAnno() + "/" + datiUnitaDoc.getNumero()
                + " creata con successo");
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#businessValidate()
     */
    @Override
    public boolean businessValidate() {
        // TODO implementare validazione
        log.info("Validazione di business...");
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#commitTransaction()
     */
    @Override
    public void commitTransaction() throws Exception {
        getConnection().commit();

    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#beginTransaction()
     */
    @Override
    public void beginTransaction() throws Exception {
        getConnection().setAutoCommit(false);
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#endTransaction()
     */
    @Override
    public void endTransaction() throws Exception {
        getConnection().rollback();
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#getCreatedPath()
     */
    @Override
    protected String getCreatedPath() {
        UrlBuilder urlBuilder = new UrlBuilder(Locale.getDefault(), UnitaDocumentaria.class, false);
        urlBuilder.addParameter("idrecord", idrecord);
        return urlBuilder.toString();
    }

    @Override
    protected boolean isSaveEnabled() {
        // TODO inserire eventuali controlli su condizioni che possono
        // impedire l'inserimento.
        return true;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class[] getToolbarActions() {
        return UdToolbar.TOOLBAR_ACTIONS;
    }

    @Override
    public Long getIdStrut() {
        return idStrut;
    }

    @Override
    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    /**
     * Necessario perchè con l'url binding definito non si riesce a passare null (viene eseguita la validazione)
     */
    @Override
    protected boolean isCreate() {
        if (idrecord == null || idrecord.compareTo(0L) == 0) {
            return true;
        }
        return false;
    }

    public Resolution jsonSelectFieldOptions() throws Exception {
        return jsonSelectFieldOptions(true);
    }

    @SuppressWarnings("unchecked")
    public Resolution jsonSelectFieldOptions(Boolean includeSelectPrompt) throws Exception {
        reloadElementsThreadLocals();

        String[] fieldNames;
        String prefix;
        SelectionProvider selectionProvider;
        Class<?> clazz;
        String realName = getRelName();
        if (Constants.SELPROVIDER_TIPOLOGIAUNIDOC.equals(realName)) {
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

            selectionProvider = selTipoUnitaDoc;
            fieldNames = new String[] { "idTipoUnitaDoc", "idRegistroUnitaDoc" };
            clazz = ParUnitadoc.class;
        } else {
            log.error("Relname non trovato");
            return new StreamingResolution("text/plain");
        }

        /*
         * Costruzione form ridotto con i soli select da sincronizzare
         */
        Form elfoForm = new FormBuilder(clazz).configFields(fieldNames).configPrefix(prefix)
                .configSelectionProvider(selectionProvider, fieldNames).build();

        elfoForm.readFromRequest(context.getRequest());

        /*
         * SelectionProviderIndex è la posizione, all'interno dell'elenco di select che voglio sincronizzare in cascata
         * (di solito 2, ma possono essere di più) dell'elemento che devo ricaricare con i valori dipendenti dalla
         * selezione nella select precedente
         */
        SelectField targetField = (SelectField) elfoForm.get(0).get(getSelectionProviderIndex());

        /*
         * Il parametro passato discrimina tra combo in cascata (true) e autocomplete (false) NOTA: combo sincronizzate
         * e campo ad autocompletamento sono mutuamente esclusivi
         */
        targetField.setLabelSearch(getLabelSearch());
        String text = targetField.jsonSelectFieldOptions(includeSelectPrompt);

        setJsonOutput(text);

        StreamingResolution streamingResolution = new StreamingResolution("text/plain", text);
        streamingResolution.setCharacterEncoding("UTF-8");
        return streamingResolution;
    }

    public Resolution createDettaglio2() {

        // if (isReadOnly()) {
        // SessionMessages
        // .addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
        // if (ajaxEnabled) {
        // context.getResponse().setHeader(CH_DTS_ACA_RESULT,
        // CH_VALIDATION_ERROR);
        // return new ForwardResolution(getDatiSpecificiView());
        //
        // } else {
        // return new RedirectResolution(getOriginalPath());
        // }
        // }

        String strIndice = "1";
        // createDettaglio2.substring(CrudUI.CREA_DETTAGLIO.length());
        indiceRiga = Integer.parseInt(strIndice);

        // Dato che è un inserimento dobbiamo creare
        // il model corrispondente alla riga da inserire
        dettaglioModel = createDettaglio2Model();

        // Preparazione per generazione oggetti di interfaccia
        // Parte di setupUI relativa solamente all'inserimento di
        // una nuova riga
        // Si usa lo stesso form di inserimento per riga e dettagli
        elNewRiga = prepareElDettaglio2(EL_NEW_RIGA, isReadOnly() ? Mode.VIEW : Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(dettaglioModel);
        return new ForwardResolution(getDatiSpecificiView());

        /*
         * if (ajaxEnabled) { } else { return new ForwardResolution(getDettaglio1CreatoNoJSView()); }
         */
    }

    /**
     * Hook per la restituzione della jsp da usare per la creazione di una riga del primo dettaglio.
     *
     * @return
     */
    public String getDatiSpecificiView() {
        return "/pages/crud/datiSpecificiPoUp.jsp";
    }

    /**
     * Hook per la restituzione della jsp da usare per la creazione di una riga del primo dettaglio nel caso JS
     * disabilitato.
     *
     * @return
     */
    public String getDettaglio1CreatoNoJSView() {
        return "/pages/crud/dettaglio1CreatoNoJS.jsp";
    }

    public boolean dettaglio2Validate(Object dettaglio) {
        // Non usato
        return true;
    }

    public Object createDettaglio2Model() {
        // Vuoto non ci sono dettagli 2
        return datiForm;
    }

    public Element prepareElDettaglio2(String prefix, Mode mode) {

        FormBuilder builder = new FormBuilder(DatiSpecifici.class).configMode(mode);
        String[] fieldNames = new String[listaCampi.size()];
        int index = 0;
        for (VDecAttribDatiSpec current : listaCampi) {
            fieldNames[index] = String.format("campo%d", index + 1);

            index++;
        }
        builder.configFields(fieldNames);

        Form formDati = builder.build();
        index = 0;
        for (VDecAttribDatiSpec current : listaCampi) {
            FormElement field = formDati.get(0).get(index);
            field.setLabel(current.getNmAttribDatiSpec());
            field.setHelp(current.getDsAttribDatiSpec());

            index++;
        }

        return formDati;
    }

    public Resolution insertDettaglio2() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
            return new ForwardResolution(getDatiSpecificiView());
        }

        if (indiceRiga == null) {
            String strIndice = insertDettaglio2.substring(CrudUI.CREA_DETTAGLIO.length());
            indiceRiga = Integer.parseInt(strIndice);
        }

        // Dato che è un inserimento dobbiamo creare
        // il model corrispondente alla riga da inserire
        dettaglioModel = createDettaglio2Model();

        // Preparazione per generazione oggetti di interfaccia
        // Parte di setupUI relativa solamente all'inserimento di
        // una nuova riga
        elNewRiga = prepareElDettaglio2(EL_NEW_RIGA, Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(dettaglioModel);

        // Aggiornamento dati da request
        elNewRiga.readFromRequest(context.getRequest());

        // Validazione dati
        if (elNewRiga.validate()) {
            // Scarico i dati negli oggetti
            elNewRiga.writeToObject(dettaglioModel);

            if (dettaglio2Validate(dettaglioModel)) {
                try {
                    beginTransaction();
                    insertDettaglio2Model(indiceRiga - 1, dettaglioModel);
                    commitTransaction();
                    SessionMessages.addInfoMessage(String.format("Dettaglio aggiunto a riga %d", indiceRiga));
                    context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_INSERT_OK);
                    // Uscita fittizia, tanto la pagina viene ricaricata.
                    // Così non passo da una jsp che svuoterebbe i messaggi senza visualizzarli
                    return new StreamingResolution("text/html", "output");

                } catch (Exception e) {
                    Throwable rootCause = ExceptionUtils.getRootCause(e);
                    log.error("Generic error {}", rootCause, e);
                    SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore generico");
                    context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                    return new ForwardResolution(getDatiSpecificiView());
                } finally {
                    safeEndTransaction();
                }
            } else {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getDatiSpecificiView());
            }

        } else {
            context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
            return new ForwardResolution(getDatiSpecificiView());
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#insertDettaglio2Model(int, java.lang.Object)
     */

    public void insertDettaglio2Model(int indiceRiga, Object dettaglio) throws Exception {
        DatiSpecifici datiDaAggiornare = (DatiSpecifici) dettaglio;
        JavaClassAccessor jca = JavaClassAccessor.getClassAccessor(DatiSpecifici.class);
        int index = 0;
        ParValoredatispecificiDAO parDsDAO = new ParValoredatispecificiDAO();
        Connection connection = getConnection();
        for (VDecAttribDatiSpecVO current : listaCampi) {
            PropertyAccessor field = jca.getProperty(String.format("campo%s", index + 1));
            String valDaInserire = (String) field.get(datiDaAggiornare);
            ParValoredatispecifici objToInsUpdOrDel = new ParValoredatispecifici();
            objToInsUpdOrDel.setIddatispecifici(idDatiSpecifici);
            objToInsUpdOrDel.setFlgstato(0L);
            objToInsUpdOrDel.setId(0L);
            objToInsUpdOrDel.setIdAttribDatiSpec(current.getIdAttribDatiSpec());
            objToInsUpdOrDel.setIdStrut(idStrut);
            objToInsUpdOrDel.setPgm(this.getClass().getSimpleName());
            objToInsUpdOrDel.setValore(valDaInserire);
            objToInsUpdOrDel.setCdVersioneXSD(datiUnitaDoc.getCdVersioneXSD());
            // Se inserire prendere da sequence
            Long currentIdValoreDS = current.getIdvaloredatispecifici();
            if (StringUtils.isNotEmpty(valDaInserire)) {
                if (currentIdValoreDS.compareTo(0L) == 0) {
                    // inserire
                    Long newIdValoreDS = DbUtil.getSequenceValue(DbConstants.SEQ_ID_GENERALI, connection);
                    objToInsUpdOrDel.setIdvaloredatispecifici(newIdValoreDS);
                    parDsDAO.insertPrepared(objToInsUpdOrDel, connection);
                } else {
                    // aggiornare
                    objToInsUpdOrDel.setIdvaloredatispecifici(currentIdValoreDS);
                    parDsDAO.updateByIndex(objToInsUpdOrDel, connection);
                }
            } else {
                if (!(currentIdValoreDS.compareTo(0L) == 0)) {
                    objToInsUpdOrDel.setIdvaloredatispecifici(currentIdValoreDS);
                    parDsDAO.deleteByIndex(objToInsUpdOrDel, connection);
                }
            }
            index++;
        }

        // Aggiornamento dati unità documentaria
        ParUnitadocVO parUDDao = new ParUnitadocVO();
        int righe = parUDDao.aggiornaUnitaDoc(EnumStatoUD.BOZZA.ordinal(), null, null, idrecord, null, connection);
        if (righe == 0) {
            log.debug("Nessuna riga aggiornata: problema di concorrenza");
            endTransaction();
        }
        caricaDatiUnitaDoc();

    }

    public long getIdDatiSpecifici() {
        return idDatiSpecifici;
    }

    public void setIdDatiSpecifici(long idDatiSpecifici) {
        this.idDatiSpecifici = idDatiSpecifici;
    }

    public Element getElNewRiga() {
        return elNewRiga;
    }

    public void setElNewRiga(Element elNewRiga) {
        this.elNewRiga = elNewRiga;
    }

    public boolean isAjaxEnabled() {
        return ajaxEnabled;
    }

    public void setAjaxEnabled(boolean ajaxEnabled) {
        this.ajaxEnabled = ajaxEnabled;
    }

    public Integer getIndiceRiga() {
        return indiceRiga;
    }

    public void setIndiceRiga(Integer indiceRiga) {
        this.indiceRiga = indiceRiga;
    }

    public String getInsertDettaglio1() {
        return insertDettaglio1;
    }

    public void setInsertDettaglio1(String insertDettaglio1) {
        this.insertDettaglio1 = insertDettaglio1;
    }

    public String getInsertDettaglio2() {
        return insertDettaglio2;
    }

    public void setInsertDettaglio2(String insertDettaglio2) {
        this.insertDettaglio2 = insertDettaglio2;
    }

    public String getCreateDettaglio1() {
        return createDettaglio1;
    }

    public void setCreateDettaglio1(String createDettaglio1) {
        this.createDettaglio1 = createDettaglio1;
    }

    public String getCreateDettaglio2() {
        return createDettaglio2;
    }

    public void setCreateDettaglio2(String createDettaglio2) {
        this.createDettaglio2 = createDettaglio2;
    }

    @Override
    public boolean isReadOnly() {
        if (datiUnitaDoc != null && datiUnitaDoc.getStato() == EnumStatoUD.VERSATA.getValore()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public String getTitoloPagina() {
        // TODO Auto-generated method stub
        return "Verso: unità Documentaria";
    }

    @Override
    public String getCreaDettaglio2Title() {
        // TODO Auto-generated method stub
        return "Inserimento Dati Specifici";
    }

}

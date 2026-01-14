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

package net.datasiel.simpaweb.common.crud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.datasiel.par.jaxb.esito.ECEsitoExtType;
import net.datasiel.par.service.VerificaVersamentoUD;
import net.datasiel.simpaweb.actionbeans.HomePrivata;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.common.EnumOperazione;
import net.datasiel.simpaweb.common.InfoEsito;
import net.datasiel.simpaweb.common.Utils;
import net.datasiel.simpaweb.db.EnumStatoUD;
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.pojo.ParDatispecifici;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParCollegamentoVO;
import net.datasiel.simpaweb.db.vo.ParComponenteVO;
import net.datasiel.simpaweb.db.vo.ParDatispecificiVO;
import net.datasiel.simpaweb.db.vo.ParDocumentoVO;
import net.datasiel.simpaweb.db.vo.ParFascicoloVO;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.simpaweb.db.vo.ParValoredatispecificiVO;
import net.datasiel.webapp.AuthorizationException;
import net.datasiel.webapp.monocrud.MonoCrudAction;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.messages.SessionMessages;
import com.manydesigns.elements.options.DefaultSelectionProvider;

public class SimpaMonoCrudAction extends MonoCrudAction {

    protected Long idStrut;
    protected Integer daHome;
    protected Integer comunica;
    private String enteStrutturaLabel;
    protected ParUnitadoc datiUnitaDoc;
    protected DefaultSelectionProvider selCDVersione = null;
    private final Integer NumericTrue = 1;
    @SpringBean
    private CsrfTokenRepository tokenRepos;

    private static final String PATH_NODO_ESITO = "/EsitoVersamento/EsitoGenerale";
    private static final String NODO_CODICE_ESITO = "CodiceEsito";
    private static final String NODO_MESSAGGIO_ERRORE = "MessaggioErrore";

    public Long getIdStrut() {
        return idStrut;
    }

    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    public String getEnteStrutturaLabel() {
        String label = "";
        try {
            label = Utils.getLabelEnteStruttura(getIdStrut(), getConnection());
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            log.error("Errore estrazione label ente/struttura: " + rootCause, e);
        }
        return label;
    }

    public void setEnteStrutturaLabel(String enteStrutturaLabel) {
        this.enteStrutturaLabel = enteStrutturaLabel;
    }

    @Override
    public Element prepareUiROInsert() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareUiRWInsert(Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareUiROEdit() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareUiRWEdit(Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void loadModel() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateModel() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void createModel() {
        // TODO Auto-generated method stub

    }

    @Override
    protected void saveModel() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean businessValidate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void commitTransaction() throws Exception {
        getConnection().commit();

    }

    @Override
    public void beginTransaction() throws Exception {
        getConnection().setAutoCommit(false);

    }

    @Override
    public void endTransaction() throws Exception {
        getConnection().rollback();

    }

    @Override
    protected String getCreatedPath() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Resolution goHome() {
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);
        return resolution;
    }

    /**
     * @throws SQLException
     * @throws AuthorizationException
     */
    protected void leggiDatiUnitaDoc(Long idrecord, Connection con) throws SQLException, AuthorizationException {
        ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
        datiUnitaDoc = parUnitadocVO.retrieveByKey(idrecord, con);
        if (datiUnitaDoc == null) {
            throw new AuthorizationException("Unita documentaria inesistente o accesso negato.");
        }
    }

    /**
     * TODO da capire come unificare con quello in SimpaMonoCrudAction che è uguale...
     *
     * @see net.datasiel.webapp.BaseAction#isTabEnabled(int)
     *
     * @Override public boolean isTabEnabled(int indiceTab) throws SQLException { int indiceInTipoDoc=0; //corrisponde
     *           al doc principale che qui non è gestito switch (indiceTab) { case 4: case 5: case 6: //Tab allegati,
     *           annessi e annotazioni //Calcolare indiceInTipoDoc e recuperare EnumTipodocumento indiceInTipoDoc =
     *           indiceTab - 3; int numTipiDoc = VDecTipoDocVO.getNumeroTipiDoc(datiUnitaDoc.getIdTipoUnitaDoc(),
     *           EnumTipoDocumento.values()[indiceInTipoDoc].getValoreInDb(), getConnection()); if (numTipiDoc == 0) {
     *           return false; } else { return true; } case 7: return
     *           VDecXsdDatiSpecVO.sonoPresentiDatiSpecificiUD(datiUnitaDoc.getIdStrut(),
     *           datiUnitaDoc.getIdTipoUnitaDoc(), getConnection()); default: return true; } }
     */
    public Resolution elimina() throws Exception {
        if (!checkCSRFToken(getContext().getRequest())) {
            getContext().getResponse().sendError(HttpServletResponse.SC_FORBIDDEN, "Richiesta non autorizzata");
            // throw new AuthorizationException("Richiesta non autorizzata");
            return null;
        }
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);
        getConnection().setAutoCommit(false);
        deleteUD();
        getConnection().commit();
        SessionMessages.addInfoMessage("Eliminazione dell'Unità Documentaria effettuata");
        return resolution;
    }

    private boolean checkCSRFToken(HttpServletRequest request) {
        CsrfToken csrfToken = tokenRepos.loadToken(request);
        final boolean missingToken = csrfToken == null;
        if (missingToken) {
            return false;
        }

        String actualToken = request.getHeader(csrfToken.getHeaderName());
        if (actualToken == null) {
            actualToken = request.getParameter(csrfToken.getParameterName());
        }
        if (!csrfToken.getToken().equals(actualToken)) {
            return false;
        }
        return true;
    }

    private void deleteUD() throws Exception {

        log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - INIZIO CANCELLAZIONE");

        try {

            // cancellazione dati specifici
            ParDatispecificiVO paDatispecificiVO = new ParDatispecificiVO();
            List<ParDatispecifici> parDatispecificiList = paDatispecificiVO
                    .getParDatispecificisByIdunitadoc(datiUnitaDoc.getIdunitadoc(), getConnection());
            if (parDatispecificiList != null && parDatispecificiList.size() == 1) {
                ParDatispecifici parDatispecifici = parDatispecificiList.get(0);
                Long idDatiSpecifici = parDatispecifici.getIddatispecifici();

                ParValoredatispecificiVO parValoredatispecificiVO = new ParValoredatispecificiVO();
                int recordCancellati = parValoredatispecificiVO.deleteByIdDatiSpec(idDatiSpecifici, getConnection());
                log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - Cancellati " + recordCancellati
                        + " record da PAR_VALOREDATISPECIFICI");
                recordCancellati = paDatispecificiVO.delete(parDatispecifici, getConnection());
                log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - Cancellati " + recordCancellati
                        + " record da PAR_DATISPECIFICI");
            }

            // cancellazione componenti
            int recordCancellati = 0;
            ParComponenteVO parComponenteVO = new ParComponenteVO();
            List<ParComponente> componentiDaCancellare = parComponenteVO
                    .getParComponentesByIdunitadocNoBlob(datiUnitaDoc.getIdunitadoc(), getConnection());
            for (ParComponente componenteDaCancellare : componentiDaCancellare) {
                parComponenteVO.delete(componenteDaCancellare, getConnection());
                String codiceBlob = componenteDaCancellare.getCodallegato();
                cleanBlobFiles(codiceBlob);
                recordCancellati++;
            }
            log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - Cancellati " + recordCancellati
                    + " record da PAR_COMPONENTE");

            // cancellazione documenti
            ParDocumentoVO parDocumentoVO = new ParDocumentoVO();
            recordCancellati = parDocumentoVO.deleteByIdUd(datiUnitaDoc.getIdunitadoc(), getConnection());
            log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - Cancellati " + recordCancellati
                    + " record da PAR_DOCUMENTO");

            // cancellazione fascicoli
            ParFascicoloVO parFascicoloVO = new ParFascicoloVO();
            recordCancellati = parFascicoloVO.deleteByIdUd(datiUnitaDoc.getIdunitadoc(), getConnection());
            log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - Cancellati " + recordCancellati
                    + " record da PAR_FASCICOLO");

            // cancellazione collegamenti
            ParCollegamentoVO parCollegamentoVO = new ParCollegamentoVO();
            recordCancellati = parCollegamentoVO.deleteByIdUd(datiUnitaDoc.getIdunitadoc(), getConnection());
            log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - Cancellati " + recordCancellati
                    + " record da PAR_COLLEGAMENTO");

            // cancellazione unità documentaria
            ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
            recordCancellati = parUnitadocVO.deleteByIdUd(datiUnitaDoc.getIdunitadoc(), getConnection());
            log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - Cancellati " + recordCancellati
                    + " record da PAR_UNITADOC");

        } catch (Exception e) {
            getConnection().rollback();
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            log.error("Errore cancellazione UD: " + rootCause, e);
            SessionMessages.addErrorMessage("Errore nell'eliminazione dell'unità Documentaria");
            throw e;
        } finally {
            log.debug("UD: " + datiUnitaDoc.getIdunitadoc() + " - FINE CANCELLAZIONE");
        }

    }

    public Resolution verifica() throws Exception {
        Resolution redirect;
        // controllo se la richiesta è avvenuta sulla home
        // if(NumericTrue.equals(daHome)){
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);
        redirect = resolution;
        // } else {
        // RedirectResolution resol=new RedirectResolution(this.getClass());
        // resol.addParameter("idrecord", idrecord);
        // redirect=resol;
        // }
        String isPasswordPresente = (String) context.getRequest().getSession().getAttribute(Constants.PASSWORD_ATTR);
        String isComunicazionePresente = (String) context.getRequest().getSession().getAttribute("Comunicazione");

        if (StringUtils.isNotBlank(isPasswordPresente) && "true".equals(isPasswordPresente)) {
            InfoEsito infoEsitoVerifica = VerificaVersamentoUD.esegui(
                    "true".equals(isComunicazionePresente) ? EnumOperazione.COMUNICAZIONE : EnumOperazione.VERIFICA,
                    datiUnitaDoc.getIdunitadoc(), getConnection(), context.getRequest());
            String esitoOrdinal = infoEsitoVerifica.getEsito();

            ECEsitoExtType esito = ECEsitoExtType.fromValue(esitoOrdinal);

            String messaggio = infoEsitoVerifica.getMessaggio();

            Utils.VisualizzaMessaggioEsito(esito, messaggio, EnumOperazione.VERIFICA);
            context.getRequest().getSession().removeAttribute("Comunicazione");

        } else {
            String redirectUrl = "/pro/password/";
            RedirectResolution resol = new RedirectResolution(redirectUrl);
            resol.addParameter("idStrut", idStrut);
            resol.addParameter("idrecord", idrecord);
            resol.addParameter("azione", "verifica");
            redirect = resol;
        }

        return redirect;
    }

    public Resolution versa() throws Exception {
        if (!checkCSRFToken(getContext().getRequest())) {
            getContext().getResponse().sendError(HttpServletResponse.SC_FORBIDDEN, "Richiesta non autorizzata");
            // throw new AuthorizationException("Richiesta non autorizzata");
            return null;
        }
        context.getRequest().getSession().removeAttribute("Comunicazione");
        // Si esce sempre in edit
        RedirectResolution redirect = null;
        // controllo se la richiesta è avvenuta sulla home
        // if(NumericTrue.equals(daHome)){
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);
        redirect = resolution;
        // } else {
        // RedirectResolution resol=new RedirectResolution(this.getClass());
        // resol.addParameter("idrecord", idrecord);
        // redirect=resol;
        // }
        String isPasswordPresente = (String) context.getRequest().getSession().getAttribute(Constants.PASSWORD_ATTR);
        // String isComunicazionePresente = (String) context.getRequest().getSession()
        // .getAttribute("Comunicazione");

        if (StringUtils.isNotBlank(isPasswordPresente) && "true".equals(isPasswordPresente)) {
            if (VerificaVersamentoUD.isVersata(datiUnitaDoc.getIdunitadoc(), getConnection())) {
                SessionMessages.addErrorMessage("Unita documentaria già versata in Sacer");
            } else {
                InfoEsito infoEsitoVerifica = VerificaVersamentoUD.esegui(EnumOperazione.VERSAMENTO,
                        datiUnitaDoc.getIdunitadoc(), getConnection(), context.getRequest());
                String esitoOrdinal = infoEsitoVerifica.getEsito();
                ECEsitoExtType esito = ECEsitoExtType.fromValue(esitoOrdinal);
                String messaggio = infoEsitoVerifica.getMessaggio();

                Utils.VisualizzaMessaggioEsito(esito, messaggio, EnumOperazione.VERSAMENTO);
            }
        } else {
            String redirectUrl = "/pro/password/";
            RedirectResolution resol = new RedirectResolution(redirectUrl);
            resol.addParameter("idStrut", idStrut);
            resol.addParameter("idrecord", idrecord);
            resol.addParameter("azione", "versa");
            redirect = resol;
        }
        return redirect;
    }

    public ParUnitadoc getDatiUnitaDoc() {
        return datiUnitaDoc;
    }

    @Override
    public boolean isReadOnly() {
        if (datiUnitaDoc != null && datiUnitaDoc.getStato().equals(EnumStatoUD.VERSATA.getValore())) {
            return true;
        } else {
            return false;
        }
    }

    public Integer getDaHome() {
        return daHome;
    }

    public void setDaHome(Integer daHome) {
        this.daHome = daHome;
    }

    public Integer getComunica() {
        return comunica;
    }

    public void setComunica(Integer comunicazione) {
        if (comunicazione != null) {
            context.getRequest().getSession().setAttribute("Comunicazione", "true");

        }
        this.comunica = comunicazione;
    }

}

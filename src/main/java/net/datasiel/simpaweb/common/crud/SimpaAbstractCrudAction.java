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

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.exception.ExceptionUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.forms.TableForm;
import com.manydesigns.elements.options.DefaultSelectionProvider;

import net.datasiel.par.jaxb.esito.ECEsitoExtType;
import net.datasiel.par.service.VerificaVersamentoUD;
import net.datasiel.simpaweb.actionbeans.HomePrivata;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.common.EnumOperazione;
import net.datasiel.simpaweb.common.InfoEsito;
import net.datasiel.simpaweb.common.Utils;
import net.datasiel.simpaweb.db.EnumStatoUD;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.webapp.AuthorizationException;
import net.datasiel.webapp.crud.AbstractCrudAction;
import net.datasiel.webapp.crud.RigaModel;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

public class SimpaAbstractCrudAction extends AbstractCrudAction {

    protected Long idStrut;
    private String enteStrutturaLabel;
    protected ParUnitadoc datiUnitaDoc;
    protected DefaultSelectionProvider selCDVersione = null;

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

    public Resolution verifica() throws Exception {

        // Si esce sempre in edit
        RedirectResolution redirect = null;// new RedirectResolution(this.getClass());
        // redirect.addParameter("idrecord", idrecord);
        // controllo se la richiesta è avvenuta sulla home
        // if(NumericTrue.equals(daHome)){
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);
        redirect = resolution;

        String isPasswordPresente = (String) context.getRequest().getSession().getAttribute(Constants.PASSWORD_ATTR);
        if (StringUtils.isNotBlank(isPasswordPresente) && "true".equals(isPasswordPresente)) {

            InfoEsito infoEsitoVerifica = VerificaVersamentoUD.esegui(EnumOperazione.VERIFICA,
                    datiUnitaDoc.getIdunitadoc(), getConnection(), context.getRequest());
            String esitoOrdinal = infoEsitoVerifica.getEsito();

            ECEsitoExtType esito = ECEsitoExtType.fromValue(esitoOrdinal);

            String messaggio = infoEsitoVerifica.getMessaggio();

            Utils.VisualizzaMessaggioEsito(esito, messaggio, EnumOperazione.VERIFICA);

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

        // Si esce sempre in edit
        RedirectResolution redirect = new RedirectResolution(this.getClass());
        // controllo se la richiesta è avvenuta sulla home
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);
        redirect = resolution;

        InfoEsito infoEsitoVerifica = VerificaVersamentoUD.esegui(EnumOperazione.VERSAMENTO,
                datiUnitaDoc.getIdunitadoc(), getConnection(), context.getRequest());
        String esitoOrdinal = infoEsitoVerifica.getEsito();

        ECEsitoExtType esito = ECEsitoExtType.fromValue(esitoOrdinal);

        String messaggio = infoEsitoVerifica.getMessaggio();

        Utils.VisualizzaMessaggioEsito(esito, messaggio, EnumOperazione.VERSAMENTO);

        return redirect;
    }

    /**
     * TODO da capire come unificare con quello in SimpaMonoCrudAction che è uguale...
     * 
     * @see net.datasiel.webapp.BaseAction#isTabEnabled(int)
     * 
     * @Override public boolean isTabEnabled(int indiceTab) throws SQLException { int indiceInTipoDoc = 0; //
     *           corrisponde al doc principale che qui non è // gestito switch (indiceTab) { case 4: case 5: case 6: //
     *           Tab allegati, annessi e annotazioni // Calcolare indiceInTipoDoc e recuperare EnumTipodocumento
     *           indiceInTipoDoc = indiceTab - 3; int numTipiDoc =
     *           VDecTipoDocVO.getNumeroTipiDoc(datiUnitaDoc.getIdTipoUnitaDoc(),
     *           EnumTipoDocumento.values()[indiceInTipoDoc].getValoreInDb(), getConnection()); if (numTipiDoc == 0) {
     *           return false; } else { return true; } case 7: return
     *           VDecXsdDatiSpecVO.sonoPresentiDatiSpecificiUD(datiUnitaDoc.getIdStrut(),
     *           datiUnitaDoc.getIdTipoUnitaDoc(), getConnection()); default: return true; } }
     */
    @Override
    public Resolution goHome() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareIntestazioneUiRO() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareIntestazioneUiRW(Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareElRiga(String prefix, Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TableForm prepareEltafoDettaglio1(String prefix, int nRows, Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public TableForm prepareEltafoDettaglio2(String prefix, int nRows, Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareElDettaglio1(String prefix, Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Element prepareElDettaglio2(String prefix, Mode mode) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void loadCrudModel() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public boolean businessValidate() {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean rigaValidate(RigaModel rigaModel) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean dettaglio1Validate(Object dettaglio) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public boolean dettaglio2Validate(Object dettaglio) {
        // TODO Auto-generated method stub
        return false;
    }

    @Override
    public void deleteRiga(int indiceRiga) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void updateCrudModel() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertRigaModel(RigaModel rigaModel) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public RigaModel createRigaModel() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void insertDettaglio1Model(int indiceRiga, Object dettaglio) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Object createDettaglio1Model() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteDettaglio1Model(int indiceRiga2, int indiceDettaglio) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void insertDettaglio2Model(int indiceRiga, Object dettaglio) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public Object createDettaglio2Model() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void deleteDettaglio2Model(int indiceRiga2, int indiceDettaglio) throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void commitTransaction() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void beginTransaction() throws Exception {
        // TODO Auto-generated method stub

    }

    @Override
    public void endTransaction() throws Exception {
        // TODO Auto-generated method stub

    }

    public ParUnitadoc getDatiUnitaDoc() {
        return datiUnitaDoc;
    }

    @Override
    public boolean isReadOnly() {
        return datiUnitaDoc != null && datiUnitaDoc.getStato().compareTo(EnumStatoUD.VERSATA.getValore()) == 0;
    }

}

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

/**
 * 
 */
package net.datasiel.simpaweb.actionbeans.crudud;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.datasiel.simpaweb.actionbeans.HomePrivata;
import net.datasiel.simpaweb.common.crud.SimpaAbstractCrudAction;
import net.datasiel.simpaweb.common.shiro.CustomRealm;
import net.datasiel.simpaweb.db.EnumStatoUD;
import net.datasiel.simpaweb.db.dao.DbUtil;
import net.datasiel.simpaweb.db.pojo.ParCollegamento;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParCollegamentoVO;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.simpaweb.elements.ElementsHelper;
import net.datasiel.webapp.AuthorizationException;
import net.datasiel.webapp.InfoToolbar;
import net.datasiel.webapp.crud.RigaModel;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.action.ErrorResolution;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.forms.TableForm;
import com.manydesigns.elements.options.DefaultSelectionProvider;

/**
 * @author saba
 *
 */
@UrlBinding("/pro/collegamenti/{idrecord}")
@InfoToolbar(titolo = "Collegamenti", accelerator = "C", breadcrumbs = "Home")
public class Collegamenti extends SimpaAbstractCrudAction {
    public Collegamenti() {
        super();
        this.pgm = getClass().getSimpleName();
        setTitoloPagina(getTitoloFromAnnotation());
    }

    public DefaultSelectionProvider selTipoUnitaDoc = null;
    public DefaultSelectionProvider selRegistroUnitaDoc = null;

    protected void initListeSelectionProv(Long idStrut, Long idutente, Connection con) throws SQLException {
        selTipoUnitaDoc = ElementsHelper.getTipiRegUniDoc(idStrut, idutente, con);
        selRegistroUnitaDoc = ElementsHelper.getRegistriUnitaDoc(idStrut, idutente, con);
    }

    @Before
    public Resolution caricaDatiUnitaDoc() throws AuthorizationException, SQLException {

        leggiDatiUnitaDoc(idrecord, getConnection());
        idStrut = datiUnitaDoc.getIdStrut();
        String permission = String.format(CustomRealm.PERMESSO_PER_UD_LEGGI_D, idrecord);
        if (!CustomRealm.isPermitted(permission, idStrut, getContext().getRequest().getSession(), getConnection())) {
            return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN, CustomRealm.CUSTOM_FORBIDDEN_MESSAGE);
        }

        initListeSelectionProv(idStrut, datiUnitaDoc.getIdutente(), getConnection());

        reloadElementsThreadLocals();
        return null;

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#goHome()
     */
    @Override
    public Resolution goHome() {
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        resolution.addParameter("idStrut", idStrut);
        return resolution;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareIntestazioneUiRO()
     */
    @Override
    public Element prepareIntestazioneUiRO() {
        reloadElementsThreadLocals();
        Form intestazioneForm = new FormBuilder(ParUnitadoc.class).configPrefix("datiUnitaDoc_")
                .configFields("idTipoUnitaDoc", "idRegistroUnitaDoc", "anno", "numero", "oggetto", "data")
                .configSelectionProvider(selTipoUnitaDoc, "idTipoUnitaDoc", "idRegistroUnitaDoc")
                .configFieldSetNames("Unita Documentaria").configMode(Mode.PREVIEW).build();
        return intestazioneForm;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareElRiga(java.lang.String, com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareElRiga(String prefix, Mode mode) {
        reloadElementsThreadLocals();
        return ElementsHelper.buildCollegamento(null, selRegistroUnitaDoc, prefix, mode);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareEltafoDettaglio1(java.lang.String, int,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public TableForm prepareEltafoDettaglio1(String prefix, int nRows, Mode mode) {
        // Non ci sono dettagli
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareEltafoDettaglio2(java.lang.String, int,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public TableForm prepareEltafoDettaglio2(String prefix, int nRows, Mode mode) {
        // Non ci sono dettagli
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareElDettaglio1(java.lang.String,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareElDettaglio1(String prefix, Mode mode) {
        // Non ci sono dettagli
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareElDettaglio2(java.lang.String,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareElDettaglio2(String prefix, Mode mode) {
        // NOn ci sono dettagli
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#loadCrudModel()
     */
    @Override
    public void loadCrudModel() throws Exception {
        CollegamentiCrudModel collegamentiModel = new CollegamentiCrudModel();
        crudModel = collegamentiModel;
        collegamentiModel.intestazione = datiUnitaDoc;
        ParCollegamentoVO parCollegamentoVO = new ParCollegamentoVO();
        List<ParCollegamento> collegamenti = parCollegamentoVO
                .getParCollegamentosByIdunitadoc(datiUnitaDoc.getIdunitadoc(), getConnection());
        for (ParCollegamento parCollegamento : collegamenti) {
            RigaModel riga = new CollegamentiRigaModel(parCollegamento, null, null);
            crudModel.getRighe().add(riga);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#businessValidate()
     */
    @Override
    public boolean businessValidate() {
        boolean isValid = true;
        List<RigaModel> righe = crudModel.getRighe();
        // Connection connection = getConnection();
        for (RigaModel riga : righe) {
            CollegamentiRigaModel rigaCollegamentoModel = (CollegamentiRigaModel) riga;
            ParCollegamento collegamento = (ParCollegamento) rigaCollegamentoModel.getRiga();
            if (!isChiaveCompilata(collegamento)) {
                isValid = false;
                com.manydesigns.elements.messages.SessionMessages
                        .addErrorMessage("Attenzione : Uno o più collegamenti non sono validi per l'inserimento.");
            }
        }
        return isValid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#rigaValidate(net.datasiel.webapp.crud.RigaModel)
     */
    @Override
    public boolean rigaValidate(RigaModel rigaModel) {

        boolean isValid = true;
        ParCollegamento collegamento = (ParCollegamento) rigaModel.getRiga();
        isValid = isChiaveCompilata(collegamento);
        if (!isValid)
            com.manydesigns.elements.messages.SessionMessages.addErrorMessage(
                    "Attenzione : Il collegamento per essere inserito deve essere completo in ogni sua parte");
        return isValid;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#dettaglio1Validate(java.lang.Object)
     */
    @Override
    public boolean dettaglio1Validate(Object dettaglio) {
        // Non ci sono dettagli
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#dettaglio2Validate(java.lang.Object)
     */
    @Override
    public boolean dettaglio2Validate(Object dettaglio) {
        // Non ci sono dettagli
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#deleteRiga(int)
     */
    @Override
    public void deleteRiga(int indiceRiga) throws Exception {
        loadCrudModel();
        ParCollegamento rigaDaCancellare = (ParCollegamento) crudModel.getRighe().get(indiceRiga).getRiga();
        ParCollegamentoVO parCollegamentoVO = new ParCollegamentoVO();
        parCollegamentoVO.delete(rigaDaCancellare, getConnection());
        log.debug(getClass().getName() + " - Cancellato Collegamento: " + rigaDaCancellare.getIdcollegamento());
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#updateCrudModel()
     */
    @Override
    public void updateCrudModel() throws Exception {
        ParCollegamentoVO parCollegamentoVO = new ParCollegamentoVO();
        List<RigaModel> righe = crudModel.getRighe();
        Connection connection = getConnection();
        for (RigaModel riga : righe) {
            CollegamentiRigaModel rigaCollegamentoModel = (CollegamentiRigaModel) riga;
            ParCollegamento collegamento = (ParCollegamento) rigaCollegamentoModel.getRiga();
            collegamento.setIdStrut(datiUnitaDoc.getIdStrut());
            collegamento.setFlgstato(0L);
            collegamento.setPgm(pgm);
            collegamento.setId(RandomUtils.nextLong());
            parCollegamentoVO.updateByIndex(collegamento, connection);
            log.debug(getClass().getName() + " - Aggiornato Collegamento: " + collegamento.getIdcollegamento());
        }
        // Aggiornamento dati unità documentaria
        ParUnitadocVO parUDDao = new ParUnitadocVO();
        int righeAggiornate = parUDDao.aggiornaUnitaDoc(EnumStatoUD.BOZZA.ordinal(), null, null, idrecord, null,
                connection);
        if (righeAggiornate == 0) {
            log.debug("Nessuna riga aggiornata: problema di concorrenza");
            endTransaction();
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#insertRigaModel(net.datasiel.webapp.crud.RigaModel)
     */
    @Override
    public void insertRigaModel(RigaModel rigaModel) throws Exception {
        ParCollegamentoVO parCollegamentoVO = new ParCollegamentoVO();
        ParCollegamento collegamento = (ParCollegamento) rigaModel.getRiga();
        collegamento.setFlgstato(0L);
        collegamento.setPgm(pgm);
        collegamento.setId(0L);
        collegamento.setIdStrut(datiUnitaDoc.getIdStrut());
        collegamento.setIdcollegamento(DbUtil.getSequenceValue("PAR_SEQ_IDGENERALI ", getConnection()));
        parCollegamentoVO.insertPrepared(collegamento, getConnection());
        log.debug(getClass().getName() + " - Inserito collegamento: " + collegamento.getIdcollegamento());
    }

    @Override
    public String getTitoloFieldsetRiga() {
        return "Unità documentaria collegata ";
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#createRigaModel()
     */
    @Override
    public RigaModel createRigaModel() {
        ParCollegamento riga = new ParCollegamento();
        riga.idunitadoc = datiUnitaDoc.getIdunitadoc();
        return new CollegamentiRigaModel(riga, null, null);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#insertDettaglio1Model(int, java.lang.Object)
     */
    @Override
    public void insertDettaglio1Model(int indiceRiga, Object dettaglio) throws Exception {
        // Non ci sono dettagli

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#createDettaglio1Model()
     */
    @Override
    public Object createDettaglio1Model() {
        // Non ci sono dettagli
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#deleteDettaglio1Model(int, int)
     */
    @Override
    public void deleteDettaglio1Model(int indiceRiga2, int indiceDettaglio) throws Exception {
        // Non ci sono dettagli

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#insertDettaglio2Model(int, java.lang.Object)
     */
    @Override
    public void insertDettaglio2Model(int indiceRiga, Object dettaglio) throws Exception {
        // Non ci sono dettagli

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#createDettaglio2Model()
     */
    @Override
    public Object createDettaglio2Model() {
        // Non ci sono dettagli
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#deleteDettaglio2Model(int, int)
     */
    @Override
    public void deleteDettaglio2Model(int indiceRiga2, int indiceDettaglio) throws Exception {
        // Non ci sono dettagli

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

    @SuppressWarnings("rawtypes")
    @Override
    public Class[] getToolbarActions() {
        return UdToolbar.TOOLBAR_ACTIONS;
    }

    @Override
    public Element prepareIntestazioneUiRW(Mode mode) {
        // Non c'è intestazione rw
        return null;
    }

    @Override
    public String getTitoloPagina() {
        // TODO Auto-generated method stub
        return "Verso: Collegamenti";
    }

    private boolean isChiaveCompilata(ParCollegamento collegamento) {
        boolean result = true;
        if (!(collegamento.getAnno() != null && collegamento.getAnno().longValue() > 0)) {
            result = false;
        }
        if (StringUtils.isBlank(collegamento.getNumero())) {
            result = false;
        }
        if (StringUtils.isBlank(collegamento.getDescrizione())) {
            result = false;
        }
        if (!(collegamento.getIdRegistroUnitaDoc() != null && collegamento.getIdRegistroUnitaDoc().longValue() > 0)) {
            result = false;
        }
        return result;
    }
}

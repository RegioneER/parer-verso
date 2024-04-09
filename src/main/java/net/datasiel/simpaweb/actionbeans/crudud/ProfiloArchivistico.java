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
import net.datasiel.simpaweb.db.pojo.ParFascicolo;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParFascicoloVO;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.simpaweb.elements.ElementsHelper;
import net.datasiel.simpaweb.elements.FascicoloForm;
import net.datasiel.webapp.AuthorizationException;
import net.datasiel.webapp.InfoToolbar;
import net.datasiel.webapp.crud.RigaModel;
import net.datasiel.webapp.crud.RigaUI;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.forms.TableForm;
import com.manydesigns.elements.options.DefaultSelectionProvider;

import net.sourceforge.stripes.action.ErrorResolution;

/**
 * @author reisoli
 *
 */
@UrlBinding("/pro/proarch/{idrecord}")
@InfoToolbar(titolo = "Profilo archivistico", accelerator = "P", breadcrumbs = "Home")
public class ProfiloArchivistico extends SimpaAbstractCrudAction {
    public ProfiloArchivistico() {
        super();
        this.pgm = getClass().getSimpleName();
        setTitoloPagina(getTitoloFromAnnotation());
    }

    public DefaultSelectionProvider selTipoUnitaDoc = null;

    protected void initListaTipoUnitaDoc(Long idStrut, Long idUtente, Connection con) throws SQLException {
        selTipoUnitaDoc = ElementsHelper.getTipiRegUniDoc(idStrut, idUtente, con);
    }

    @Before
    public Resolution caricaDatiUnitaDoc() throws AuthorizationException, SQLException {

        leggiDatiUnitaDoc(idrecord, getConnection());
        idStrut = datiUnitaDoc.getIdStrut();
        String permission = String.format(CustomRealm.PERMESSO_PER_UD_LEGGI_D, idrecord);
        if (!CustomRealm.isPermitted(permission, idStrut, getContext().getRequest().getSession(), getConnection())) {
            return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN, CustomRealm.CUSTOM_FORBIDDEN_MESSAGE);
        }

        initListaTipoUnitaDoc(idStrut, datiUnitaDoc.getIdutente(), getConnection());
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
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareIntestazioneUiRW(com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareIntestazioneUiRW(Mode mode) {
        reloadElementsThreadLocals();

        FascicoloForm fascicoloForm = new FascicoloForm("datiFascicolo_", mode);
        return fascicoloForm;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareElRiga(java.lang.String, com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareElRiga(String prefix, Mode mode) {
        reloadElementsThreadLocals();

        return ElementsHelper.buildFascicolo(null, prefix, mode);
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
        ProfiloArchivisticoCrudModel profiloCM = new ProfiloArchivisticoCrudModel();
        crudModel = profiloCM;
        profiloCM.intestazione = datiUnitaDoc;

        ParFascicoloVO parFascicoloVO = new ParFascicoloVO();

        // lettura o creazione dati fascicolo principale

        List<ParFascicolo> fascicoloPrincipaleList = parFascicoloVO
                .retrieveByIdUdCodTipoFascicoloP(datiUnitaDoc.getIdunitadoc(), getConnection());
        ParFascicolo fascicoloPrincipale = new ParFascicolo();
        if (fascicoloPrincipaleList != null && fascicoloPrincipaleList.size() > 0) {
            fascicoloPrincipale = fascicoloPrincipaleList.get(0);
        } else {

            // in caso di nuovo fascicolo l'id viene impostato a 1 per superare la validazione del form;
            // in fase di salvataggio su db tale id conterrà il valore del sequence PAR_SEQ_IDFASCICOLO
            fascicoloPrincipale.setIdfascicolo(-1L);

        }
        profiloCM.fascicoloPrincipale = fascicoloPrincipale;

        // lettura dati eventuali fascicoli secondari
        List<ParFascicolo> fascicoloSecondarioList = parFascicoloVO
                .retrieveByIdUdCodTipoFascicoloS(datiUnitaDoc.getIdunitadoc(), getConnection());
        for (ParFascicolo fascicoloSecondario : fascicoloSecondarioList) {
            RigaModel riga = new ProfiloArchivisticoRigaModel(fascicoloSecondario);
            profiloCM.righe.add(riga);
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

        // INIZIO VALIDAZIONE FASCICOLO PRINCIPALE

        Form elfoRW = ((FascicoloForm) crudUi.getIntestazioneUiRW()).elfoFormRW;
        ParFascicolo datiFascicolo = (ParFascicolo) crudModel.getIntestazioneRW();

        // validazione classifica
        String classifica = datiFascicolo.getClassifica();
        boolean isClassificaValid = validaClassifica(classifica, elfoRW);
        if (!isClassificaValid) {
            isValid = false;
        }

        // validazione identificativo
        String identificativo = datiFascicolo.getIdentificativo();
        boolean isIdentificativoValid = validaIdentificativo(identificativo, isClassificaValid, classifica, elfoRW);
        if (!isIdentificativoValid) {
            isValid = false;
        }

        // validazione identificativo sottofascicolo
        String idSottofascicolo = datiFascicolo.getIdsottofascicolo();
        boolean isIdSottofascicoloValid = validaIdSottofascicolo(idSottofascicolo, isClassificaValid, classifica,
                isIdentificativoValid, identificativo, elfoRW);
        if (!isIdSottofascicoloValid) {
            isValid = false;
        }

        // i campi identificativo ed oggetto devono essere entrambi compilati oppure entrambi vuoti
        boolean identificativoInserito = StringUtils.isNotEmpty(datiFascicolo.getIdentificativo()) ? true : false;
        boolean oggettoInserito = StringUtils.isNotEmpty(datiFascicolo.getOggetto()) ? true : false;
        boolean isIdentificativoOggettoValid = validaIdentificativoOggetto(elfoRW, identificativoInserito,
                oggettoInserito);
        if (!isIdentificativoOggettoValid) {
            isValid = false;
        }

        // FINE VALIDAZIONE FASCICOLO PRINCIPALE

        // INIZIO VALIDAZIONE SOTTOFASCICOLI

        List<RigaUI> righe = crudUi.getRigheUI();
        for (RigaUI riga : righe) {
            Form elfoSottofascicolo = (Form) riga.getElRiga();
            ParFascicolo sottofascicolo = new ParFascicolo();
            elfoSottofascicolo.writeToObject(sottofascicolo);

            // validazione classifica
            String classificaSF = sottofascicolo.getClassifica();
            boolean isClassificaSFValid = validaClassifica(classificaSF, elfoSottofascicolo);
            if (!isClassificaSFValid) {
                isValid = false;
            }

            // validazione identificativo
            String identificativoSF = sottofascicolo.getIdentificativo();
            boolean isIdentificativoSFValid = validaIdentificativo(identificativoSF, isClassificaSFValid, classificaSF,
                    elfoSottofascicolo);
            if (!isIdentificativoSFValid) {
                isValid = false;
            }

            // validazione identificativo sottofascicolo
            String idSottofascicoloSF = sottofascicolo.getIdsottofascicolo();
            boolean isIdSottofascicoloSFValid = validaIdSottofascicolo(idSottofascicoloSF, isClassificaSFValid,
                    classificaSF, isIdentificativoSFValid, identificativoSF, elfoSottofascicolo);
            if (!isIdSottofascicoloSFValid) {
                isValid = false;
            }

            // i campi identificativo ed oggetto devono essere entrambi compilati oppure entrambi vuoti
            boolean identificativoSFInserito = StringUtils.isNotEmpty(sottofascicolo.getIdentificativo()) ? true
                    : false;
            boolean oggettoSFInserito = StringUtils.isNotEmpty(sottofascicolo.getOggetto()) ? true : false;
            boolean isIdentificativoOggettoSFValid = validaIdentificativoOggetto(elfoSottofascicolo,
                    identificativoSFInserito, oggettoSFInserito);
            if (!isIdentificativoOggettoSFValid) {
                isValid = false;
            }

        }

        // FINE VALIDAZIONE SOTTOFASCICOLI

        return isValid;
    }

    /**
     * @param isValid
     * @param elfoRW
     * @param identificativoInserito
     * @param oggettoInserito
     * 
     * @return
     */
    protected boolean validaIdentificativoOggetto(Form elfo, boolean identificativoInserito, boolean oggettoInserito) {
        boolean isValid = true;
        if ((identificativoInserito && !oggettoInserito) || (!identificativoInserito && oggettoInserito)) {
            isValid = false;
            if (!oggettoInserito) {
                elfo.findFieldByPropertyName("oggetto").getErrors()
                        .add("campo 'Oggetto' obbligatorio se si inserisce il campo 'Identificativo'");
            }
            if (!identificativoInserito) {
                elfo.findFieldByPropertyName("identificativo").getErrors()
                        .add("campo 'Identificativo' obbligatorio se si inserisce il campo 'Oggetto'");
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

        ParFascicolo riga = ((ParFascicolo) rigaModel.getRiga());

        // validazione classifica
        String classifica = riga.getClassifica();
        boolean isClassificaValid = validaClassifica(classifica, (Form) elNewRiga);
        if (!isClassificaValid) {
            isValid = false;
        }

        // validazione identificativo
        String identificativo = riga.getIdentificativo();
        boolean isIdentificativoValid = validaIdentificativo(identificativo, isClassificaValid, classifica,
                (Form) elNewRiga);
        if (!isIdentificativoValid) {
            isValid = false;
        }

        // validazione identificativo sottofascicolo
        String idSottofascicolo = riga.getIdsottofascicolo();
        boolean isIdSottofascicoloValid = validaIdSottofascicolo(idSottofascicolo, isClassificaValid, classifica,
                isIdentificativoValid, identificativo, (Form) elNewRiga);
        if (!isIdSottofascicoloValid) {
            isValid = false;
        }

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
        return false;
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
        ParFascicolo rigaDaCancellare = (ParFascicolo) crudModel.getRighe().get(indiceRiga).getRiga();
        ParFascicoloVO parFascicoloVO = new ParFascicoloVO();
        parFascicoloVO.delete(rigaDaCancellare, getConnection());
        log.debug(getClass().getName() + " - Cancellato Sottofascicolo: " + rigaDaCancellare.getIdfascicolo());
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#updateCrudModel()
     */
    @Override
    public void updateCrudModel() throws Exception {

        ParFascicolo datiFascicolo = (ParFascicolo) crudModel.getIntestazioneRW();
        ParFascicoloVO parFascicoloVO = new ParFascicoloVO();
        datiFascicolo.setPgm(pgm);
        datiFascicolo.setFlgstato(0L);

        // se idunitadoc è null si tratta di un nuovo fascicolo principale da inserire
        Connection connection = getConnection();
        if (datiFascicolo.getIdunitadoc() == null) {
            datiFascicolo.setIdfascicolo(DbUtil.getSequenceValue("PAR_SEQ_IDFASCICOLO", connection));
            datiFascicolo.setIdunitadoc(datiUnitaDoc.getIdunitadoc());
            datiFascicolo.setCodtipofascicolo("P");
            datiFascicolo.setId(0L);
            parFascicoloVO.insertPrepared(datiFascicolo, connection);
            log.debug(getClass().getName() + " - Inserito Fascicolo: " + datiFascicolo.getIdfascicolo());
        } else {

            datiFascicolo.setId(RandomUtils.nextLong());
            parFascicoloVO.updateByIndex(datiFascicolo, connection);
            log.debug(getClass().getName() + " - Aggiornato Fascicolo: " + datiFascicolo.getIdfascicolo());

            List<RigaModel> righe = crudModel.getRighe();
            for (RigaModel riga : righe) {
                ProfiloArchivisticoRigaModel rigaSottoFascicoloModel = (ProfiloArchivisticoRigaModel) riga;
                ParFascicolo sottoFascicolo = (ParFascicolo) rigaSottoFascicoloModel.getRiga();
                sottoFascicolo.setFlgstato(0L);
                sottoFascicolo.setPgm(pgm);
                sottoFascicolo.setId(RandomUtils.nextLong());
                parFascicoloVO.updateByIndex(sottoFascicolo, connection);
                log.debug(getClass().getName() + " - Aggiornato Sottofascicolo: " + sottoFascicolo.getIdfascicolo());
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

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#insertRigaModel(net.datasiel.webapp.crud.RigaModel)
     */
    @Override
    public void insertRigaModel(RigaModel rigaModel) throws Exception {
        ParFascicoloVO parFascicoloVO = new ParFascicoloVO();
        ParFascicolo sottoFascicolo = (ParFascicolo) rigaModel.getRiga();
        sottoFascicolo.setFlgstato(0L);
        sottoFascicolo.setPgm(pgm);
        sottoFascicolo.setId(0L);
        sottoFascicolo.setIdfascicolo(DbUtil.getSequenceValue("PAR_SEQ_IDFASCICOLO", getConnection()));
        parFascicoloVO.insertPrepared(sottoFascicolo, getConnection());
    }

    @Override
    public String getTitoloFieldsetRiga() {
        return "Fascicolo secondario ";
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.AbstractCrudAction#createRigaModel()
     */
    @Override
    public RigaModel createRigaModel() {
        ParFascicolo riga = new ParFascicolo();
        riga.idunitadoc = datiUnitaDoc.getIdunitadoc();
        riga.codtipofascicolo = "S";
        return new ProfiloArchivisticoRigaModel(riga);
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

    /*
     * verifica la validità del campo "classifica" rispetto alla relativa regular expression
     */
    private boolean validaClassifica(String classifica, Form elfo) {
        boolean isValid = true;
        /*
         * String errorMsg = "formato non valido; formato corretto: categoria.classe.sottoclasse"; if
         * (StringUtils.isNotEmpty(classifica) && !classifica.matches(".+\\..+\\..+")) {
         * elfo.findFieldByPropertyName("classifica").getErrors().add(errorMsg); isValid = false; }
         */
        return isValid;
    }

    /*
     * verifica la validità del campo "identificativo" rispetto alla relativa regular expression e la sua coerenza con
     * l'eventuale valore del campo 'classifica'
     */
    private boolean validaIdentificativo(String identificativo, boolean isClassificaValid, String classifica,
            Form elfoRW) {
        boolean isValid = true;
        /*
         * String errorMsg = new String(); if (StringUtils.isNotEmpty(identificativo)) { if
         * (!identificativo.matches(".+\\/[0-9]{4}\\/[0-9]+")) { isValid = false; errorMsg =
         * "formato non valido; formato corretto: categoria.classe.sottoclasse/anno/numero"; } else { // se è stato
         * valorizzato anche il campo classifica occorre verificare che il suo valore sia stato riportato nella prima
         * parte del campo identificativo if (StringUtils.isNotEmpty(classifica) && isClassificaValid) { int slashIdx =
         * identificativo.indexOf("/"); String classificaSub = identificativo.substring(0, slashIdx); if
         * (!classificaSub.equals(classifica)) { isValid = false; errorMsg =
         * "valore incoerente con quanto inserito nel campo 'Classifica'"; } } } } if (!isValid) {
         * elfoRW.findFieldByPropertyName("identificativo").getErrors().add(errorMsg); isValid = false; }
         */
        return isValid;
    }

    private boolean validaIdSottofascicolo(String idSottofascicolo, boolean isClassificaValid, String classifica,
            boolean isIdentificativoValid, String identificativo, Form elfoRW) {
        boolean isValid = true;
        /*
         * String errorMsg = new String(); if (StringUtils.isNotEmpty(idSottofascicolo)) { if
         * (!idSottofascicolo.matches(".+\\/[0-9]{4}\\/[0-9]+\\/[0-9]+")) { isValid = false; errorMsg =
         * "formato non valido; formato corretto: categoria.classe.sottoclasse/anno/numero/numero"; } else {
         * 
         * // se è stato valorizzato anche il campo identificativo occorre verificare che il suo valore sia stato
         * riportato nella prima parte del campo identificativo sottofascicolo if
         * (StringUtils.isNotEmpty(identificativo) && isIdentificativoValid) { int slashIdx =
         * idSottofascicolo.lastIndexOf("/"); String identificativoSub = idSottofascicolo.substring(0, slashIdx); if
         * (!identificativoSub.equals(identificativo)) { isValid = false; errorMsg =
         * "valore incoerente con quanto inserito nel campo 'Identificativo'"; } }
         * 
         * // se è stato valorizzato anche il campo classifica occorre verificare che il suo valore sia stato riportato
         * nella prima parte del campo identificativo sottofascicolo if (StringUtils.isNotEmpty(classifica) &&
         * isClassificaValid) { int slashIdx = idSottofascicolo.indexOf("/"); String classificaSub =
         * idSottofascicolo.substring(0, slashIdx); if (!classificaSub.equals(classifica)) { isValid = false; errorMsg =
         * "valore incoerente con quanto inserito nel campo 'Classifica'"; } }
         * 
         * } } if (!isValid) { elfoRW.findFieldByPropertyName("idsottofascicolo").getErrors().add(errorMsg); isValid =
         * false; }
         */
        return isValid;
    }

    @Override
    public String getTitoloPagina() {
        // TODO Auto-generated method stub
        return "Verso: Profilo Archivistico";
    }

}

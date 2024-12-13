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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.datasiel.simpaweb.actionbeans.HomePrivata;
import net.datasiel.simpaweb.beans.DatiSpecifici;
import net.datasiel.simpaweb.beans.DatoSpecifico;
import net.datasiel.simpaweb.beans.EnumEntitaDatiSpecifici;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.common.crud.SimpaAbstractCrudAction;
import net.datasiel.simpaweb.common.shiro.CustomRealm;
import net.datasiel.simpaweb.db.DbConstants;
import net.datasiel.simpaweb.db.EnumStatoUD;
import net.datasiel.simpaweb.db.dao.DbUtil;
import net.datasiel.simpaweb.db.dao.ParComponenteDAO;
import net.datasiel.simpaweb.db.dao.ParDatispecificiDAO;
import net.datasiel.simpaweb.db.dao.ParDocumentoDAO;
import net.datasiel.simpaweb.db.dao.ParValoredatispecificiDAO;
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.pojo.ParDocumento;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.pojo.ParValoredatispecifici;
import net.datasiel.simpaweb.db.pojo.VDecAttribDatiSpec;
import net.datasiel.simpaweb.db.vo.ParComponenteVO;
import net.datasiel.simpaweb.db.vo.ParDatispecificiVO;
import net.datasiel.simpaweb.db.vo.ParDocumentoVO;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.simpaweb.db.vo.VDecAttribDatiSpecVO;
import net.datasiel.simpaweb.db.vo.VDecTipoDocVO;
import net.datasiel.simpaweb.elements.ElementsHelper;
import net.datasiel.simpaweb.elements.RigaDocumentoForm;
import net.datasiel.webapp.AuthorizationException;
import net.datasiel.webapp.crud.RigaModel;
import net.datasiel.webapp.crud.RigaUI;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

import org.apache.commons.lang.StringUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.ElementsThreadLocals;
import com.manydesigns.elements.FormElement;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.blobs.Blob;
import com.manydesigns.elements.blobs.BlobManager;
import com.manydesigns.elements.fields.Field;
import com.manydesigns.elements.fields.SelectField;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.forms.TableForm;
import com.manydesigns.elements.forms.TableForm.Row;
import com.manydesigns.elements.forms.TableFormBuilder;
import com.manydesigns.elements.messages.SessionMessages;
import com.manydesigns.elements.options.DefaultSelectionProvider;
import com.manydesigns.elements.options.SelectionProvider;
import com.manydesigns.elements.reflection.JavaClassAccessor;
import com.manydesigns.elements.reflection.PropertyAccessor;

/**
 * @author reisoli
 *
 */
public abstract class Documenti extends SimpaAbstractCrudAction {

    public Documenti() {
        super();
        this.pgm = getClass().getSimpleName();
        setTitoloPagina(getTitoloFromAnnotation());
    }

    protected String[] campiDettaglio = { "idTipoCompDoc", "idFormatoFileDoc", "dsHashFileVers", "flgFirmaPerRifTemp",
            "dataRifTemp", "descRifTemp", "codallegato" };
    protected EnumEntitaDatiSpecifici entitaDatiSpecifici = null;
    private DatiSpecifici datiForm;
    private List<VDecAttribDatiSpecVO> listaCampi;
    /**
     * Selection provider per decodifica registro in intestazione
     */
    public DefaultSelectionProvider selTipoUnitaDoc = null;
    public DefaultSelectionProvider selTipoDoc = null;
    /**
     * Selection provider per decodifica in aggiunta componenti.
     */
    public DefaultSelectionProvider selRegistroUnitaDoc = null;

    /**
     * Selection provider per decodifica in aggiunta componenti.
     */
    private DefaultSelectionProvider selFormatoFileAmmesso = null;
    private DefaultSelectionProvider seltipoStruttura = null;
    private long idDatiSpecifici;
    private long idDocumento = -1;
    private String verXsd = null;

    protected void initSelProviders(Long idStrut, Long idTipoDoc, Long idTipoStrutDoc, Long idUser, Connection con)
            throws SQLException {
        selTipoUnitaDoc = ElementsHelper.getTipiRegUniDoc(idStrut, idUser, con);
        selRegistroUnitaDoc = ElementsHelper.getRegistriUnitaDoc(idStrut, idUser, con);
        seltipoStruttura = ElementsHelper.getTipoStruttura(idStrut, con);

        // TO-DO: USARE RIFERIMENTO A ID_TIPO_STRUT_DOC
        selFormatoFileAmmesso = ElementsHelper.getFormatiFile(idStrut, con);
        if (idTipoDoc != null) {
            selCDVersione = ElementsHelper.getCDVersione(idStrut, EnumEntitaDatiSpecifici.DOC.name(), idTipoDoc, con);
        } else {
            selCDVersione = new DefaultSelectionProvider("cdVersioneXSD");
        }
    }

    protected void initListaTipoDoc(Long idStrut, String strTiDoc, Long idUserIam, Connection con) throws SQLException {
        boolean isPrincipale = "PRINC".equalsIgnoreCase(getTipoTab());
        selTipoDoc = VDecTipoDocVO.getTipiDoc(idStrut, strTiDoc, idUserIam, con, isPrincipale);
    }

    @Before
    public Resolution caricaDatiUnitaDoc() throws AuthorizationException, SQLException {

        Connection connection = getConnection();
        leggiDatiUnitaDoc(idrecord, connection);
        // Il controllo sull'abilitazione del tab ha bisogno di datiUnitaDoc
        // quindi va eseguito dopo la lettura

        idStrut = datiUnitaDoc.getIdStrut();

        // Long idTipoUnitaDoc = datiUnitaDoc.getIdTipoUnitaDoc();
        String strTiDOC = getValoreTiDoc(getTipoTab());

        String permission = String.format(CustomRealm.PERMESSO_PER_UD_LEGGI_D, idrecord);
        if (!CustomRealm.isPermitted(permission, idStrut, getContext().getRequest().getSession(), getConnection())) {
            return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN, CustomRealm.CUSTOM_FORBIDDEN_MESSAGE);
        }

        inizializzaDocPrincipale(idrecord, connection);
        ParDocumentoVO parDoc = new ParDocumentoVO();
        ParDocumento docum = null;
        Long idTipoDoc = null;
        Long idTipoStrutDoc = null;
        String cdVersioneXSD = null;
        String tipoTab = getTipoTab();
        if (tipoTab != null) {

            if (!"PRINC".equalsIgnoreCase(tipoTab)) {

                Long idDocum = (Long) getFromSession("idDocumento");
                if (idDocum != null) {
                    idDocumento = idDocum;
                }

            } else {

                parDoc = parDoc.getDocPrincipale(idrecord, connection);
                /*
                 * idTipoDoc=parDoc!=null?parDoc.getIdTipoDoc():null;
                 * idTipoStrutDoc=parDoc!=null?parDoc.getIdTipoStrutDoc():null;
                 * cdVersioneXSD=parDoc!=null?parDoc.getCdVersioneXSD():null;
                 */
                idDocumento = parDoc.getIddocumento();
            }

        }
        // if ( "PRINC".equalsIgnoreCase(getTipoTab())){
        if (idDocumento > 0) {
            docum = parDoc.retrieveByIndex(idDocumento, idrecord, connection);
            if (docum != null) {
                idTipoDoc = docum.getIdTipoDoc();
                idTipoStrutDoc = 0L;
                cdVersioneXSD = docum.getCdVersioneXSD();
            }
        }
        initSelProviders(idStrut, idTipoDoc, idTipoStrutDoc, datiUnitaDoc.getIdutente(), connection);

        initListaTipoDoc(idStrut, strTiDOC, datiUnitaDoc.getIdutente(), connection);
        log.debug("Leggo o creo se non c'è il record di PAR_DATISPECIFICI");
        ParDatispecificiVO parDsDAO = new ParDatispecificiVO();
        idDatiSpecifici = parDsDAO.loadOrCreateDatiSpecifici(idDocumento, EnumEntitaDatiSpecifici.DOC, connection);

        /* DONE: CAMBIATO RIFERIMENTO AL TIPO DATO DI ATTRIBUTI SPECIFICI */
        VDecAttribDatiSpecVO vdecAttribDAO = new VDecAttribDatiSpecVO();
        listaCampi = vdecAttribDAO.getValoriSpecificiPerVersione(idDatiSpecifici, idStrut, EnumEntitaDatiSpecifici.DOC,
                idTipoDoc, cdVersioneXSD, connection);
        if (cdVersioneXSD != null) {
            try {
                datiForm = new DatiSpecifici();
                int index = 1;
                JavaClassAccessor jca = JavaClassAccessor.getClassAccessor(DatiSpecifici.class);
                for (VDecAttribDatiSpecVO current : listaCampi) {
                    PropertyAccessor field;

                    field = jca.getProperty(String.format("campo%s", index));
                    field.set(datiForm, current.getValore());
                    index++;
                }
            } catch (NoSuchFieldException e) {
                log.error("Generic error", e);
            }
        }
        return null;
    }

    /**
     * @param strTiDOC
     * @param strTipoTab
     *
     * @return
     */
    protected String getValoreTiDoc(String strTipoTab) {
        String strTiDOC;
        if ("ALLE".equals(strTipoTab)) {
            strTiDOC = "ALLEGATO";
        } else if ("ANNE".equals(strTipoTab)) {
            strTiDOC = "ANNESSO";
        } else if ("ANNO".equals(strTipoTab)) {
            strTiDOC = "ANNOTAZIONE";
        } else {
            strTiDOC = "PRINCIPALE";
        }
        return strTiDOC;
    }

    /**
     * Fare override solo in documento principale Inizializza (inserisce) il documento principale se non c'è ancora.
     * Nelle gestioni diverse da doc principale non deve fare niente.
     *
     * @param idrecord
     * @param connection
     *
     * @throws SQLException
     */
    protected void inizializzaDocPrincipale(Long idrecord, Connection connection) throws SQLException {
    }

    /**
     * @return
     */
    protected abstract String getTipoTab();

    protected abstract EnumEntitaDatiSpecifici getEntitaDatiSpecifici();

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

        Form intestazioneForm = null;

        FormBuilder formBuilder = new FormBuilder(ParUnitadoc.class).configPrefix("datiUnitaDoc_")
                .configFields("idTipoUnitaDoc", "idRegistroUnitaDoc", "anno", "numero", "oggetto", "data",
                        "cdVersioneXSD") // ,"idTipoStrutDoc"
                .configFieldSetNames("Unita Documentaria")
                .configSelectionProvider(selTipoUnitaDoc, "idTipoUnitaDoc", "idRegistroUnitaDoc");
        if ("PRINC".equals(getTipoTab())) {
            formBuilder = formBuilder.configSelectionProvider(selCDVersione, "cdVersioneXSD");
        }
        formBuilder = formBuilder.configMode(Mode.PREVIEW);
        intestazioneForm = formBuilder.build();
        return intestazioneForm;

    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareIntestazioneUiRW(com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareIntestazioneUiRW(Mode mode) {
        // Non c'è intestazione rw
        return null;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareElRiga(java.lang.String, com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareElRiga(String prefix, Mode mode) {
        boolean isPrincipale = "PRINC".equalsIgnoreCase(getTipoTab());
        return new RigaDocumentoForm(selTipoDoc, selCDVersione, seltipoStruttura, "Dati " + getTitoloFieldsetRiga(),
                mode, prefix, isPrincipale);
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareEltafoDettaglio1(java.lang.String, int,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public TableForm prepareEltafoDettaglio1(String prefix, int nRows, Mode mode) {
        TableForm eltafoDettaglio = new TableFormBuilder(ParComponente.class).configPrefix(prefix)
                .configFields(campiDettaglio)
                .configSelectionProvider(selFormatoFileAmmesso, "idTipoCompDoc", "idFormatoFileDoc")
                .configMode(Mode.PREVIEW).configNRows(nRows).build();

        eltafoDettaglio.setCaption("Lista file");
        return eltafoDettaglio;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareEltafoDettaglio2(java.lang.String, int,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public TableForm prepareEltafoDettaglio2(String prefix, int nRows, Mode mode) {
        TableForm formDati = null;
        if ("PRINC".equals(getTipoTab())) {
            if (datiForm != null) {
                String[] campiData = { "dato", "valore" };
                TableFormBuilder builder = new TableFormBuilder(DatoSpecifico.class).configPrefix(prefix)
                        .configFields(campiData).configNRows(nRows).configMode(Mode.PREVIEW);

                formDati = builder.build();
                formDati.setCaption("Dati Specifici");

            }
        }

        return formDati;

    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareElDettaglio1(java.lang.String,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareElDettaglio1(String prefix, Mode mode) {
        Form elfoDettaglio = new FormBuilder(ParComponente.class).configPrefix(prefix).configFields(campiDettaglio)
                .configSelectionProvider(selFormatoFileAmmesso, "idTipoCompDoc", "idFormatoFileDoc").configMode(mode)
                .build();
        /*
         * "idTipoCompDoc","idFormatoFileDoc", "flgFirmaPerRifTemp", "dataRifTemp","descRifTemp","codallegato"
         */
        Field idTipoCompDoc = elfoDettaglio.findFieldByPropertyName("idTipoCompDoc");
        idTipoCompDoc.setHelp("Individua la tipologia di componente, generalmente è \"Contenuto\".");

        Field idFormatoFileDoc = elfoDettaglio.findFieldByPropertyName("idFormatoFileDoc");
        idFormatoFileDoc.setHelp("Inserire il formato del file da trasmettere. "
                + "Nel caso di file con più estensioni P7M, inserirne una sola "
                + "(ad esempio, se file ha estensione pdf.p7m.p7m, inserire come formato pdf.p7m). "
                + "Se il formato del file non rientra tra quelli selezionabili, "
                + "segnalare il problema via email a helpdeskparer@regione.emilia-romagna.it");

        Field hashFileDoc = elfoDettaglio.findFieldByPropertyName("dsHashFileVers");
        hashFileDoc.setHelp(
                "Inserire l'hash del file calcolato con algoritmo SHA-1 (esempio: 5f0b5ca2e075a100c815ed83424dfaf79cb4469d).");

        Field flgFirmaPerRifTemp = elfoDettaglio.findFieldByPropertyName("flgFirmaPerRifTemp");
        flgFirmaPerRifTemp.setHelp(
                "Selezionare SI se si vuole che il sistema di conservazione verifichi la firma alla data contenuta nella firma stessa.");

        Field dataRifTemp = elfoDettaglio.findFieldByPropertyName("dataRifTemp");
        dataRifTemp.setHelp("Compilare solo se il file è firmato. "
                + "Indica la data alla quale il sistema di conservazione verificherà le firme digitali apposte sul documento al momento del versamento");

        Field descRifTemp = elfoDettaglio.findFieldByPropertyName("descRifTemp");
        descRifTemp.setHelp("Compilare obbligatoriamente se si è compilato il campo Riferimento temporale. "
                + "Descrive il riferimento temporale inserito nel campo precedente (es.: Data di registrazione di protocollo, Data di pubblicazione, Data di firma, ecc.)");

        return elfoDettaglio;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#prepareElDettaglio2(java.lang.String,
     * com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareElDettaglio2(String prefix, Mode mode) {
        Form formDati = null;
        if ("princ".equalsIgnoreCase(getTipoTab())) {
            FormBuilder builder = new FormBuilder(DatiSpecifici.class).configMode(mode);
            String[] fieldNames = new String[listaCampi.size()];
            int index = 0;
            for (VDecAttribDatiSpec current : listaCampi) {
                fieldNames[index] = String.format("campo%d", index + 1);

                index++;
            }
            builder.configFields(fieldNames);

            formDati = builder.build();
            index = 0;
            for (VDecAttribDatiSpec current : listaCampi) {
                FormElement field = formDati.get(0).get(index);
                field.setLabel(current.getNmAttribDatiSpec());
                field.setHelp(current.getDsAttribDatiSpec());

                index++;
            }
        }
        // FieldSet messaggioDate= new FieldSet("Attenzione", 1, Mode.VIEW);
        // LabelField avvertenza=new LabelField(null, mode, null,
        // "I campi data devono essere compilati devono essere compilati secondo il formato : AAAA-MM-GG ");
        // messaggioDate.add(avvertenza);
        // formDati.add(messaggioDate);
        return formDati;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#loadCrudModel()
     */
    @Override
    public void loadCrudModel() throws Exception {
        DocumentiCrudModel documentiModel = new DocumentiCrudModel();
        crudModel = documentiModel;
        documentiModel.intestazione = datiUnitaDoc;
        ParDocumentoVO parDAO = new ParDocumentoVO();

        Connection con = getConnection();
        List<ParDocumento> documenti = parDAO.getListadocumenti(idrecord, getTipoTab(), con);
        ParComponenteDAO parComponenteDAO = new ParComponenteDAO();
        for (ParDocumento parDocumento : documenti) {
            List<ParComponente> fileAllegati = parComponenteDAO
                    .getParComponentesByIddocumentoIdunitadocOrdered(parDocumento.getIddocumento(), idrecord, con);
            // Per mantenere intatta la lista dei codici allegato in modo da poter confrontare
            // vecchio codice con il nuovo e poter decidere se fare pulizia dei file vecchi
            // ci vuole un altra lista con oggetti ParComponenete nuovi altrimenti la writeToObject dopo
            // la readFromRequest sovrascrive i vecchi valori
            List<String> listaVecchiCodiciAllegato = new ArrayList<String>();
            for (ParComponente parComponente : fileAllegati) {
                listaVecchiCodiciAllegato.add(StringUtils.defaultString(parComponente.getCodallegato()));
            }

            ParDatispecificiVO parDsDAO = new ParDatispecificiVO();
            long idDatiSpecifici = parDsDAO.loadOrCreateDatiSpecifici(parDocumento.getIddocumento(),
                    EnumEntitaDatiSpecifici.DOC, con);

            VDecAttribDatiSpecVO vdecAttribDAO = new VDecAttribDatiSpecVO();
            /*
             * UNI_DOC("idunitadoc"), DOC("iddocumento"), COMP("idcomponente"), SUB_COMP("idcomponente")
             */
            if (tipoDato == null) {
                tipoDato = EnumEntitaDatiSpecifici.DOC.name();
            }
            listaCampi = vdecAttribDAO.getValoriSpecificiPerVersione(idDatiSpecifici, idStrut, getEntitaDatiSpecifici(),
                    parDocumento.getIdTipoDoc(), parDocumento.getCdVersioneXSD(),
                    con);/*
                          * (idDatiSpecifici, idStrut, tipoDato, datiUnitaDoc.getIdTipoUnitaDoc(), con);
                          */

            List<DatoSpecifico> listaDatiSpecifici = new ArrayList<>();
            for (VDecAttribDatiSpecVO attributo : listaCampi) {
                DatoSpecifico dato = new DatoSpecifico();
                dato.setDato(attributo.getNmAttribDatiSpec());
                dato.setValore(attributo.getValore());
                listaDatiSpecifici.add(dato);
            }
            boolean isPrincipale = "PRINC".equals(getTipoTab());
            RigaModel riga = new DocumentiRigaModel(parDocumento, fileAllegati, listaVecchiCodiciAllegato,
                    listaDatiSpecifici, isPrincipale);
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
        // Implementare validazione di business a partire dal model
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#rigaValidate(net.datasiel.webapp.crud.RigaModel)
     */
    @Override
    public boolean rigaValidate(RigaModel rigaModel) {
        // Ininfluente perchè non c'è inserimento vero e proprio:
        // c'è solo una riga inserita manualmente. Per la validazione in
        // aggiornamento c'è businessValidate()
        return true;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#dettaglio1Validate(java.lang.Object)
     */
    @Override
    public boolean dettaglio1Validate(Object dettaglio) {
        // TODO implementare validazione... (controllo su tipologie file?)
        boolean ret = true;
        ParComponente obj = (ParComponente) dettaglio;
        if (!StringUtils.isAlphanumeric(obj.getCodallegato())) {
            SessionMessages.addErrorMessage("Non è stato inserito il file!!");
            ret = false;
        }
        // if()
        return ret;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#dettaglio2Validate(java.lang.Object)
     */
    @Override
    public boolean dettaglio2Validate(Object dettaglio) {
        // Non usato
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
        Connection con = getConnection();
        ParDocumento rigaDaCancellare = (ParDocumento) crudModel.getRighe().get(indiceRiga).getRiga();
        ParComponenteVO parComponenteVO = new ParComponenteVO();

        int componentiCancellati = 0;
        List<ParComponente> componentiDaCancellare = parComponenteVO.getParComponentesByIddocumentoIdunitadocNoBlob(
                rigaDaCancellare.getIddocumento(), rigaDaCancellare.getIdunitadoc(), con);
        for (ParComponente componenteDaCancellare : componentiDaCancellare) {
            parComponenteVO.delete(componenteDaCancellare, con);
            String codiceBlob = componenteDaCancellare.getCodallegato();
            cleanBlobFiles(codiceBlob);
            componentiCancellati++;
        }

        ParDocumentoVO parDocumentoVO = new ParDocumentoVO();
        int righeCancellate = parDocumentoVO.delete(rigaDaCancellare, con);
        if (righeCancellate > 0) {
            log.debug(getClass().getName() + " - Cancellata riga documento: " + rigaDaCancellare.getIddocumento()
                    + " - cancellati " + componentiCancellati + " componenti");
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#updateCrudModel()
     */
    @Override
    public void updateCrudModel() throws Exception {
        // crudModel è aggiornato con i dati di request
        // mentre listaFileAllegati contiene ancora i dati sul db
        log.debug("Aggiornamento del documento :" + idDocumento);
        ParDocumentoDAO objDAO = new ParDocumentoDAO();
        Connection connection = getConnection();
        for (RigaModel current : crudModel.getRighe()) {
            ParDocumento riga = (ParDocumento) current.getRiga();
            controllaDatiSpecificiPerTipologia(riga, objDAO);
            String pgm = riga.getPgm();
            objDAO.update(riga, connection);
            if ("DocPrincipale".equalsIgnoreCase(pgm)) {
                verXsd = riga.getCdVersioneXSD();

            }
            log.debug("Aggiornamento righe dettaglio (componenti)");
            ParComponenteDAO compDAO = new ParComponenteDAO();
            int index = 0;
            List<String> vecchiCodici = ((DocumentiRigaModel) current).getListaCodiciAllegati();
            for (Object currentDettaglio : current.getDettagli1()) {
                ParComponente currentComponente = (ParComponente) currentDettaglio;

                compDAO.update(currentComponente, connection);
                // Se è stato modificato o cancellato il file allegato ripuliamo del vecchio file
                // Se il vecchio file non c'era non si deve fare niente
                String strOldCodAllegato = vecchiCodici.get(index);
                log.debug("codallegato in lista: {}", strOldCodAllegato);
                log.debug("codallegato in model: {}", currentComponente.getCodallegato());
                if (StringUtils.isNotEmpty(strOldCodAllegato)) {
                    if (!strOldCodAllegato.equals(currentComponente.getCodallegato())) {
                        cleanBlobFiles(strOldCodAllegato);
                    }
                }
                index++;
            }

        }

        // Aggiornamento dati unità documentaria
        ParUnitadocVO parUDDao = new ParUnitadocVO();
        int righe = parUDDao.aggiornaUnitaDoc(EnumStatoUD.BOZZA.ordinal(), null, null, idrecord, null, connection);
        if (righe == 0) {
            log.debug("Nessuna riga aggiornata: problema di concorrenza");
            endTransaction();
        }

    }

    private void controllaDatiSpecificiPerTipologia(ParDocumento riga, ParDocumentoDAO objDAO) {

        ParDocumento docFromDb;
        try {
            docFromDb = objDAO.retrieveByKey(riga.getIddocumento(), getConnection());
            Long idTipoDocFromView = riga.getIdTipoDoc();
            Long idTipoDocFromDb = docFromDb.getIdTipoDoc();
            if ((idTipoDocFromView != null) && !idTipoDocFromView.equals(idTipoDocFromDb)) {
                beginTransaction();
                // Vengono cancellati i record della tabella dei valori
                // referenziati dalla variabile : idDatiSpecifici.
                deleteDettaglio2Model(0, 0);
                commitTransaction();

            }
        } catch (Exception e) {
            log.error("Generic error", e);
            try {
                endTransaction();
            } catch (Exception e1) {
                log.error(e1.toString());
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
        ParDocumento riga = (ParDocumento) rigaModel.getRiga();
        String xsdVersione = riga.getCdVersioneXSD();
        // Long idTipoStrutDoc=riga.getIdTipoStrutDoc();
        ParDocumentoVO parDocVO = new ParDocumentoVO();
        ParDocumentoVO datiDocAllegato = new ParDocumentoVO();
        datiDocAllegato.setIdunitadoc(idrecord);
        datiDocAllegato.setTipologia(getTipoTab());
        datiDocAllegato.setIddocumento(DbUtil.getSequenceValue("PAR_SEQ_IDCOMPONENTE", getConnection()));
        datiDocAllegato.setIdTipoDoc(riga.getIdTipoDoc());
        datiDocAllegato.setProfiloautoredoc(riga.getProfiloautoredoc());
        datiDocAllegato.setProfilodescrizionedoc(riga.getProfilodescrizionedoc());
        datiDocAllegato.setPgm(pgm);
        datiDocAllegato.setId(0L);
        datiDocAllegato.setFlgstato(0L);
        if (xsdVersione != null && !"".equals(xsdVersione)) {
            datiDocAllegato.setCdVersioneXSD(xsdVersione);

        }
        // if(idTipoStrutDoc!=null && idTipoStrutDoc>0L){
        // datiDocAllegato.setIdTipoStrutDoc(idTipoStrutDoc);
        // }
        parDocVO.insertPrepared(datiDocAllegato, getConnection());
        log.debug("{} - Inserita riga: {}", getClass().getName(), datiDocAllegato.getIddocumento());
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#createRigaModel()
     */
    @Override
    public RigaModel createRigaModel() {
        ParDocumento riga = new ParDocumento();
        riga.setIdunitadoc(datiUnitaDoc.getIdunitadoc());

        // assegnazione necessaria ai fini di validazione
        riga.setIddocumento(1L);

        return new DocumentiRigaModel(riga, null, null, null, false);
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#insertDettaglio1Model(int, java.lang.Object)
     */
    @Override
    public void insertDettaglio1Model(int indiceRiga, Object dettaglio) throws Exception {
        Connection con = getConnection();
        Long idDoc = ((DocumentiRigaModel) crudModel.getRighe().get(indiceRiga)).riga.getIddocumento();
        ParComponenteDAO parCompDAO = new ParComponenteDAO();
        ParComponente objToInsert = (ParComponente) dettaglio;
        objToInsert.setId(0L);
        objToInsert.setIdunitadoc(idrecord);
        objToInsert.setIddocumento(idDoc);
        objToInsert.setIdcomponente(DbUtil.getSequenceValue("PAR_SEQ_IDCOMPONENTE", con));
        objToInsert.setPgm("Documenti");
        objToInsert.setFlgstato(0L);
        if (!StringUtils.isAlphanumeric(objToInsert.getCodallegato())) {
            throw new IllegalArgumentException("Non è stato inserito il file!!");
        }
        Blob blob = getBlobFromFileSystem(objToInsert.getCodallegato());
        if (Objects.nonNull(blob)) {
            blob.loadMetaProperties();
        }
        parCompDAO.insertPrepared(objToInsert, blob, con);
        cleanBlobFiles(objToInsert.getCodallegato());

    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#createDettaglio1Model()
     */
    @Override
    public Object createDettaglio1Model() {
        return new ParComponente();
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#deleteDettaglio1Model(int, int)
     */
    @Override
    public void deleteDettaglio1Model(int indiceRiga2, int indiceDettaglio) throws Exception {

        loadCrudModel();
        ParComponente objDaEliminare = (ParComponente) crudModel.getRighe().get(indiceRiga2).getDettagli1()
                .get(indiceDettaglio);
        ParComponenteDAO objDAO = new ParComponenteDAO();
        objDAO.deleteByIndex(objDaEliminare, getConnection());
        String codiceBlob = objDaEliminare.getCodallegato();
        cleanBlobFiles(codiceBlob);

    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#insertDettaglio2Model(int, java.lang.Object)
     */
    @Override
    public void insertDettaglio2Model(int indiceRiga, Object dettaglio) throws Exception {
        DatiSpecifici datiDaAggiornare = (DatiSpecifici) dettaglio;
        JavaClassAccessor jca = JavaClassAccessor.getClassAccessor(DatiSpecifici.class);
        int index = 0;
        ParValoredatispecificiDAO parDsDAO = new ParValoredatispecificiDAO();
        ParDatispecificiDAO parDatSpecDao = new ParDatispecificiDAO();
        Connection connection = getConnection();
        ParDocumento parDoc = (ParDocumento) parDatSpecDao.getParObjectByIdDatiSpecificiAndEntitaSacer(idDatiSpecifici,
                "DOC", connection);
        String valCDXSD = parDoc.getCdVersioneXSD();

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
            objToInsUpdOrDel.setCdVersioneXSD(valCDXSD);
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
                if (currentIdValoreDS.compareTo(0L) != 0) {
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

    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#createDettaglio2Model()
     */
    @Override
    public Object createDettaglio2Model() {
        // Vuoto non ci sono dettagli 2
        if ("PRINC".equals(getTipoTab())) {
            return datiForm;
        } else {
            return null;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see net.datasiel.webapp.crud.AbstractCrudAction#deleteDettaglio2Model(int, int)
     */
    @Override
    public void deleteDettaglio2Model(int indiceRiga2, int indiceDettaglio) throws Exception {
        ParValoredatispecificiDAO parDatSpecDAO = new ParValoredatispecificiDAO();
        parDatSpecDAO.deleteByIdDatiSpec(idDatiSpecifici, getConnection());

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
    public int getMaxRighe() {
        // Il documento principale è al massimo 1
        return 1;
    }

    @Override
    public String getTitoloFieldsetRiga() {
        // Non voglio il fieldset
        return null;
    }

    @Override
    public Long getIdStrut() {
        return idStrut;
    }

    @Override
    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    @Override
    public void setHRef() {
        List<RigaModel> righeModel = crudModel.getRighe();
        List<RigaUI> righeUI = crudUi.getRigheUI();
        for (int j = 0; j < righeUI.size(); j++) {
            RigaUI rigaUI = righeUI.get(j);
            TableForm tableForm = rigaUI.getEltafoDettaglio1();
            Row[] rows = tableForm.getRows();
            for (int i = 0; i < rows.length; i++) {
                Row row = rows[i];
                RigaModel rigaModel = righeModel.get(j);
                if (rigaModel.getDettagli1() != null && rigaModel.getDettagli1().size() > 0) {
                    ParComponente componente = (ParComponente) rigaModel.getDettagli1().get(i);
                    if (componente.getCodallegato() != null && StringUtils.isNotEmpty(componente.getCodallegato())) {
                        Field campoFile = row.findFieldByPropertyName("codallegato");
                        Long idComponente = componente.getIdcomponente();
                        campoFile.setHref("?vediAllegato&idComponente=" + idComponente);
                    }
                }
            }
        }
    }

    public Resolution jsonSelectFieldOptions() throws Exception {
        return jsonSelectFieldOptions(true);
    }

    @SuppressWarnings("unchecked")
    public Resolution jsonSelectFieldOptions(Boolean includeSelectPrompt) {
        reloadElementsThreadLocals();

        String[] fieldNames;
        String prefix;
        SelectionProvider selectionProvider;
        Class<?> clazz;
        if (Constants.SELPROVIDER_FORMATIFILE.equals(getRelName())) {
            // Gestione sincronizzazione per inserimento e modifica ul

            // individuazione del prefix
            Enumeration<String> parametriReq = context.getRequest().getParameterNames();
            prefix = "";
            while (parametriReq.hasMoreElements()) {
                String parametro = parametriReq.nextElement();
                if (parametro.endsWith("idFormatoFileDoc")) {
                    prefix = StringUtils.substringBefore(parametro, "idFormatoFileDoc");
                }
            }

            selectionProvider = selFormatoFileAmmesso;
            fieldNames = new String[] { "idTipoCompDoc", "idFormatoFileDoc" };
            clazz = ParComponente.class;
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

    @Override
    public Resolution createRiga() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            if (ajaxEnabled) {
                context.getResponse().setHeader("DtsAcaResult", "validationError");
                return new ForwardResolution(getRigaCreataView());

            } else {
                return new RedirectResolution(getOriginalPath());
            }
        }
        // Dato che è un inserimento dobbiamo creare
        // il model corrispondente alla riga da inserire
        rigaModel = createRigaModel();

        // Preparazione per generazione oggetti di interfaccia
        // Parte di setupUI relativa solamente all'inserimento di
        // una nuova riga
        elNewRiga = prepareElRiga("elNewRiga", Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(rigaModel.getRiga());

        if (ajaxEnabled) {// && "PRINC".equals(tipoTab)
            return new ForwardResolution(getRigaCreataView());
        } else {
            return new ForwardResolution(getRigaCreataNoJSView());
        }
    }

    @SuppressWarnings("unchecked")
    public Resolution optionsVersioneXSD() {

        StreamingResolution resolution = null;//

        Enumeration<String> parametriReq = context.getRequest().getParameterNames();
        String idTipoDoc = null;
        while (parametriReq.hasMoreElements()) {
            String parametro = parametriReq.nextElement();
            if (parametro.endsWith("elNewRigaidTipoDoc") | (parametro.equalsIgnoreCase("idTipoDocSelezionato"))) {
                idTipoDoc = context.getRequest().getParameter(parametro);
            }
        }
        if (idTipoDoc != null) {
            List<String> listaOpzioni = null;
            StringBuilder jsonOpzioni = new StringBuilder();
            try {
                listaOpzioni = ElementsHelper.getOptionsXSDVersione(idStrut, "DOC", Long.parseLong(idTipoDoc),
                        getConnection());
                if (listaOpzioni != null && !listaOpzioni.isEmpty()) {
                    jsonOpzioni.append("{\"opzioni\":[{");
                    for (String id : listaOpzioni) {
                        jsonOpzioni.append("\"" + id + "\":\"" + id + "\",");
                    }
                    jsonOpzioni.append("}]}");

                    String risultato = jsonOpzioni.toString();
                    risultato = StringUtils.replace(risultato, ",}", "}");
                    resolution = new StreamingResolution("text/plain", risultato);
                }
            } catch (NumberFormatException | SQLException e) {
                log.error("Generic error", e);
            }
        }
        return resolution;

    }

    public long getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(long idDocumento) {
        this.idDocumento = idDocumento;
        if (idDocumento > 0) {
            insertInSession("idDocumento", new Long(idDocumento));
        }
    }

    private void insertInSession(String label, Object value) {

        HttpSession session = context.getRequest().getSession(true);
        if (session.getAttribute(label) != null) {

            session.removeAttribute(label);

        }
        session.setAttribute(label, value);

    }

    private Object getFromSession(String label) {

        Object result = null;

        HttpSession session = context.getRequest().getSession(true);
        result = session.getAttribute(label);

        return result;
    }

    private Blob getBlobFromFileSystem(String codallegato) {
        Blob blobFile = null;
        try {
            BlobManager blobManager = ElementsThreadLocals.getBlobManager();
            if (blobManager == null) {
                blobManager = BlobManager.createDefaultBlobManager();
            }

            blobFile = blobManager.loadBlob(codallegato);

        } catch (IOException e) {
            log.error("Generic error", e);
        }
        return blobFile;
    }
}

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

import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import net.datasiel.simpaweb.beans.DatiSpecifici;
import net.datasiel.simpaweb.beans.EnumEntitaDatiSpecifici;
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
import net.datasiel.webapp.DtsActionBeanContext;
import net.datasiel.webapp.InfoToolbar;
import net.sourceforge.stripes.action.Before;
import net.sourceforge.stripes.action.ErrorResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.FormElement;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.options.DefaultSelectionProvider;
import com.manydesigns.elements.reflection.JavaClassAccessor;
import com.manydesigns.elements.reflection.PropertyAccessor;

/**
 * @author reisoli
 *
 */
@UrlBinding("/pro/datispec/{idrecord}/{tipoDato}")
@InfoToolbar(titolo = "Dati specifici UD", accelerator = "S", breadcrumbs = "Home")
public class DatiSpecificiUd extends SimpaMonoCrudAction {

    public DatiSpecificiUd() {
        super();
        this.pgm = getClass().getSimpleName();
        setTitoloPagina(getTitoloFromAnnotation());
    }

    protected DatiSpecifici datiForm;
    protected long idDatiSpecifici;
    protected List<VDecAttribDatiSpecVO> listaCampi;

    public DefaultSelectionProvider selTipoUnitaDoc = null;

    protected void initListeSelectionProv(Long idStrut, Long idUser, Connection con) throws SQLException {
        selTipoUnitaDoc = ElementsHelper.getTipiRegUniDoc(idStrut, idUser, con);
    }

    @Before
    public Resolution caricaDatiUnitaDoc() throws AuthorizationException, SQLException, NoSuchFieldException {
        Connection connection = getConnection();
        leggiDatiUnitaDoc(idrecord, connection);
        idStrut = datiUnitaDoc.getIdStrut();
        String permission = String.format(CustomRealm.PERMESSO_PER_UD_LEGGI_D, idrecord);
        if (!CustomRealm.isPermitted(permission, idStrut, getContext().getRequest().getSession(), getConnection())) {
            return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN, CustomRealm.CUSTOM_FORBIDDEN_MESSAGE);
        }
        log.debug("Controllo se il tab è abilitato...");
        int indiceTab = 0;
        for (Class<?> tabClass : UdToolbar.TOOLBAR_ACTIONS) {
            if (this.getClass() == tabClass) {
                break;
            }
            indiceTab++;
        }
        // Il controllo sull'abilitazione del tab ha bisogno di datiUnitaDoc
        // quindi va eseguito dopo la lettura
        if (!isTabEnabled(indiceTab)) {
            return new ErrorResolution(HttpServletResponse.SC_FORBIDDEN, "Tentativo di accesso a pagina non abilitata");
        }

        initListeSelectionProv(idStrut, datiUnitaDoc.getIdutente(), connection);

        log.debug("Leggo o creo se non c'è il record di PAR_DATISPECIFICI");
        ParDatispecificiVO parDsDAO = new ParDatispecificiVO();
        idDatiSpecifici = parDsDAO.loadOrCreateDatiSpecifici(idrecord, EnumEntitaDatiSpecifici.UNI_DOC, connection);

        VDecAttribDatiSpecVO vdecAttribDAO = new VDecAttribDatiSpecVO();
        /*
         * UNI_DOC("idunitadoc"), DOC("iddocumento"), COMP("idcomponente"), SUB_COMP("idcomponente")
         */
        if (tipoDato == null)
            tipoDato = EnumEntitaDatiSpecifici.UNI_DOC.name();
        listaCampi = vdecAttribDAO.getValoriSpecifici(idDatiSpecifici, idStrut, tipoDato,
                datiUnitaDoc.getIdTipoUnitaDoc(), null, connection);

        datiForm = new DatiSpecifici();
        int index = 1;
        JavaClassAccessor jca = JavaClassAccessor.getClassAccessor(DatiSpecifici.class);
        for (VDecAttribDatiSpecVO current : listaCampi) {
            PropertyAccessor field = jca.getProperty(String.format("campo%s", index));
            field.set(datiForm, current.getValore());
            index++;
        }
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#prepareUiROInsert()
     */
    @Override
    public Element prepareUiROInsert() {
        // Non c'è vera e propria insert
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#prepareUiRWInsert(com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareUiRWInsert(Mode mode) {
        return prepareUiRO();
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#prepareUiROEdit()
     */
    @Override
    public Element prepareUiROEdit() {
        return prepareUiRO();
    }

    private Element prepareUiRO() {
        Form intestazioneForm = new FormBuilder(ParUnitadoc.class).configPrefix("datiUnitaDoc_")
                .configFields("idTipoUnitaDoc", "idRegistroUnitaDoc", "anno", "numero", "oggetto", "data")
                .configFieldSetNames("Unita Documentaria")
                .configSelectionProvider(selTipoUnitaDoc, "idTipoUnitaDoc", "idRegistroUnitaDoc")
                .configMode(Mode.PREVIEW).build();

        return intestazioneForm;

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#prepareUiRWEdit(com.manydesigns.elements.Mode)
     */
    @Override
    public Element prepareUiRWEdit(Mode mode) {
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

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#loadModel()
     */
    @Override
    public void loadModel() throws Exception {
        modelRO = datiUnitaDoc;
        modelRW = datiForm;
        String testo = "";
        InputStream is = null;
        try {
            is = DtsActionBeanContext.class.getClassLoader().getResourceAsStream("/resources/ContrattoPubblico.txt");
            testo = IOUtils.toString(is, StandardCharsets.UTF_8);
        } catch (Exception e) {
            log.error("errore lettura testo help", e);
        } finally {
            if (is != null) {
                is.close();
            }
        }
        setTestoHelp(testo);
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#updateModel()
     */
    @Override
    public void updateModel() throws Exception {
        // "Srotolare" listaCampi e recuperando i dati da aggiornare da modelRW inserire
        // o aggiornare i valori.
        DatiSpecifici datiDaAggiornare = (DatiSpecifici) modelRW;
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

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#createModel()
     */
    @Override
    public void createModel() {
        // Non c'è creazione

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#saveModel()
     */
    @Override
    protected void saveModel() throws Exception {
        // Non c'è inserimento

    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#businessValidate()
     */
    @Override
    public boolean businessValidate() {
        return true;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.monocrud.MonoCrudAction#getCreatedPath()
     */
    @Override
    protected String getCreatedPath() {
        // Non c'è path dopo la creazione
        return null;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public Class[] getToolbarActions() {
        return UdToolbar.TOOLBAR_ACTIONS;
    }

    public Long getIdStrut() {
        return idStrut;
    }

    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    public ParUnitadoc getDatiUniDoc() {
        return datiUnitaDoc;
    }

    public void setDatiUniDoc(ParUnitadoc datiUniDoc) {
        this.datiUnitaDoc = datiUniDoc;
    }

    public DatiSpecifici getDatiForm() {
        return datiForm;
    }

    public void setDatiForm(DatiSpecifici datiForm) {
        this.datiForm = datiForm;
    }

}

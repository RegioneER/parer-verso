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
package net.datasiel.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.ElementsThreadLocals;
import com.manydesigns.elements.blobs.Blob;
import com.manydesigns.elements.blobs.BlobManager;
import com.manydesigns.elements.messages.SessionMessages;

import it.eng.exceptions.VersoException;
import it.eng.exceptions.ErrorCategory.VersoErrorCategory;
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.vo.ParComponenteVO;
import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 * @author reisoli
 *
 */
public abstract class BaseAction implements ActionBean {
    protected static final Logger log = LoggerFactory.getLogger(BaseAction.class);

    protected DtsActionBeanContext context;
    protected Properties webappProperties = WebappProperties.getProperties();
    protected String originalPath;
    protected String absoluteOriginalPath;
    @SpringBean("dataSource")
    private DataSource dataSource;
    private Connection connection;

    protected boolean messaggiPresenti;
    /**
     * Chiave del record gestito.
     */
    protected Long idrecord;

    /**
     * Nome della relazione di sincronizzazione combo
     */
    private String relName;

    /**
     * Parametro che contiene l'output json per la sincronizzazione combo
     */
    private String jsonOutput;

    /**
     * Indice della seconda combo
     */
    private int selectionProviderIndex;

    /**
     * Etichetta della combo
     */
    private String labelSearch;

    protected String testoHelp;
    protected String titoloPagina;
    protected String tipoDato;

    /**
     * Elimina i files associati ad un allegato elements.
     *
     * @param codiceBlob
     *            Il codice del blob da eliminare.
     *
     * @throws IOException
     */
    protected void cleanBlobFiles(String codiceBlob) throws IOException {
        if (codiceBlob != null) {
            log.debug("Eliminazione file {}", codiceBlob);
            BlobManager blManager = BlobManager.createDefaultBlobManager();
            try {
                Blob blob = blManager.loadBlob(codiceBlob);
                blob.getDataFile().delete();
                blob.getMetaFile().delete();
            } catch (FileNotFoundException e) {
                log.error(String.format("File da eliminare %s non trovato.", codiceBlob));
            }
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see net.sourceforge.stripes.action.ActionBean#getContext()
     */

    public DtsActionBeanContext getContext() {
        return context;
    }

    /*
     * (non-Javadoc)
     *
     * @see net.sourceforge.stripes.action.ActionBean#setContext(net.sourceforge. stripes.action.ActionBeanContext)
     */

    public void setContext(ActionBeanContext arg0) {
        this.context = (DtsActionBeanContext) arg0;
        originalPath = context.getRequest().getServletPath();
        absoluteOriginalPath = context.getRequest().getContextPath() + originalPath;
    }

    /**
     * Se necessario crea la connessione altrimenti restituisce quella che ha.
     *
     * @return
     *
     * @throws SQLException
     */
    public Connection getConnection() throws SQLException {
        if (connection == null) {

            connection = dataSource.getConnection();// context.getDataSource().getConnection();
        }
        return connection;
    }

    public void closeConnection() {
        if (connection != null) {
            DbUtils.closeQuietly(connection);
        }
        connection = null;
    }

    public String getAbsoluteOriginalPath() {
        return absoluteOriginalPath;
    }

    public String getOriginalPath() {
        return originalPath;
    }

    /********************************
     * Hooks
     *******************************/

    /**
     * Hook per gestione toolbar
     */
    @SuppressWarnings("rawtypes")
    public Class[] getToolbarActions() {
        /**
         * Implementazione di default
         */
        return new Class[] { this.getClass() };
    }

    /**
     * Hook per gestione abilitazione/disabilitazione tabs Fare override se necessario cioè se nella gestione
     * dell'intero set di tabs c'è la necessità di gestire l'abilitazione dei tab.
     *
     * @param indiceTab
     *            indice del tab da controllare
     *
     * @return true se il tab deve essere abilitato false altrimenti.
     *
     * @throws SQLException
     */
    public boolean isTabEnabled(int indiceTab) throws SQLException {
        /**
         * Implementazione di default
         */
        return true;
    }

    /**
     * @return the idrecord
     */
    public Long getIdrecord() {
        return idrecord;
    }

    /**
     * @param idrecord
     *            the idrecord to set
     */
    public void setIdrecord(Long idrecord) {
        this.idrecord = idrecord;
    }

    /**
     * Aggiunta ulteriori parametri alla resolution.
     *
     * @param resolution
     */
    protected void addCustomParams(RedirectResolution resolution) {

    }

    /**
     * @param updateResult
     *
     * @return
     *
     * @throws SQLException
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
    protected Resolution handleSalvaEContinua(Resolution updateResult) throws SQLException {
        if (updateResult instanceof ForwardResolution) {
            log.debug("errori in update quindi rimaniamo dove siamo");
            return updateResult;
        }
        /**
         * Recuperare tab successivo TODO controllare che il tab successivo sia abilitato...
         */
        Class[] toolbarActions = getToolbarActions();
        int tabCorrente = ArrayUtils.indexOf(toolbarActions, this.getClass());

        if (tabCorrente == ArrayUtils.INDEX_NOT_FOUND) {
            log.warn("Incredibilmente indice non trovato: restiamo dove siamo");
            return updateResult;
        }

        log.debug("Si controlla se il tab successivo è abilitato");
        int tabSuccessivo = tabCorrente + 1;
        while (tabSuccessivo < toolbarActions.length) {
            if (isTabEnabled(tabSuccessivo)) {
                break;
            }
            log.debug("Il tab {} non è abilitato", tabSuccessivo);
            tabSuccessivo++;
        }

        if (tabSuccessivo >= toolbarActions.length) {
            log.debug("non ci sono tab successivi: restiamo dove siamo");
            SessionMessages.addInfoMessage("Ultima sezione raggiunta: per procedere tornare all'indice.");
            return updateResult;
        }

        log.debug("Troviamo l'url del tab successivo");
        Class<? extends ActionBean> clazz = toolbarActions[tabSuccessivo];
        RedirectResolution returnValue = new RedirectResolution(clazz);
        returnValue.addParameter("idrecord", getIdrecord());
        addCustomParams(returnValue);
        return returnValue;
    }

    public Resolution vediAllegato() throws Exception {
        StreamingResolution sr = null;
        String idComponente = context.getRequest().getParameter("idComponente");
        if (idComponente != null && checkPermission(idComponente)) {
            ParComponenteVO parComponenteVO = new ParComponenteVO();
            List<ParComponente> componenteList = parComponenteVO.getParComponentesByIdcomponenteIdunitadocNoBlob(
                    Long.parseLong(idComponente), idrecord, getConnection());
            if (componenteList != null && componenteList.size() > 0) {
                if (componenteList.size() == 1) {
                    ParComponente componente = componenteList.get(0);
                    String codAllegato = componente.getCodallegato();

                    BlobManager blManager = BlobManager.createDefaultBlobManager();
                    try {
                        InputStream datiBlob = parComponenteVO.getBlobWhereByIdCompIdUD(Long.parseLong(idComponente),
                                idrecord, getConnection());
                        // blobAll = blManager.loadBlob(codAllegato);
                        // File fileAll = blobAll.getDataFile();
                        // FileInputStream fis = new FileInputStream(fileAll);
                        sr = new StreamingResolution("application/octet-stream", datiBlob);
                        sr.setFilename(componente.getNome());
                        // sr.setLength(blobAll.getSize());
                        sr.setLastModified(componente.getDtagg().getTime());
                    } catch (Exception e) {
                        Throwable rootCause = ExceptionUtils.getRootCause(e);
                        log.error("Generic error {}", rootCause, e);
                        SessionMessages.addErrorMessage("Si è verificato un errore durante l'apertura del file");
                        throw e;
                    }
                } else {
                    String err = "Errore nel recupero dell'allegato: più allegati con lo stesso codiceallegato";
                    log.error(err);
                    SessionMessages.addErrorMessage(err);
                    throw new Exception(err);
                }
            } else {
                String err = "Errore nel recupero dell'allegato: allegato non presente";
                log.error(err);
                SessionMessages.addErrorMessage(err);
                throw new Exception(err);
            }
        }
        return sr;
    }

    public Resolution vediAllegatoNoDB() throws Exception {
        StreamingResolution sr = null;
        String idComponente = context.getRequest().getParameter("idComponente");
        if (idComponente != null && checkPermission(idComponente)) {
            ParComponenteVO parComponenteVO = new ParComponenteVO();
            List<ParComponente> componenteList = parComponenteVO.getParComponentesByIdcomponenteIdunitadocNoBlob(
                    Long.parseLong(idComponente), idrecord, getConnection());
            if (componenteList != null && !componenteList.isEmpty()) {
                if (componenteList.size() == 1) {
                    ParComponente componente = componenteList.get(0);
                    String codAllegato = componente.getCodallegato();
                    BlobManager blManager = BlobManager.createDefaultBlobManager();
                    Blob blobAll = null;
                    try {
                        blobAll = blManager.loadBlob(codAllegato);
                        File fileAll = blobAll.getDataFile();
                        FileInputStream fis = new FileInputStream(fileAll);
                        sr = new StreamingResolution(blobAll.getContentType(), fis);
                        sr.setFilename(blobAll.getFilename());
                        sr.setLength(blobAll.getSize());
                        sr.setLastModified(blobAll.getCreateTimestamp().getMillis());
                    } catch (Exception e) {
                        Throwable rootCause = ExceptionUtils.getRootCause(e);
                        log.error("Generic error {}", rootCause, e);
                        SessionMessages.addErrorMessage("Si è verificato un errore durante l'apertura del file");
                        throw e;
                    }
                } else {
                    String err = "Errore nel recupero dell'allegato: più allegati con lo stesso codiceallegato";
                    log.error(err);
                    SessionMessages.addErrorMessage(err);
                    throw new VersoException(err, VersoErrorCategory.INTERNAL_ERROR);
                }
            } else {
                String err = "Errore nel recupero dell'allegato: allegato non presente";
                log.error(err);
                SessionMessages.addErrorMessage(err);
                throw new VersoException(err, VersoErrorCategory.INTERNAL_ERROR);
            }
        }
        return sr;
    }

    public boolean checkPermission(String idComponente) throws Exception {
        return true;
    }

    public boolean isMessaggiPresenti() {
        return messaggiPresenti;
    }

    public void setMessaggiPresenti(boolean messaggiPresenti) {
        this.messaggiPresenti = messaggiPresenti;
    }

    /**
     * @return the relName
     */
    public String getRelName() {
        return relName;
    }

    /**
     * @param relName
     *            the relName to set
     */
    public void setRelName(String relName) {
        this.relName = relName;
    }

    /**
     * @return the jsonOutput
     */
    public String getJsonOutput() {
        return jsonOutput;
    }

    /**
     * @param jsonOutput
     *            the jsonOutput to set
     */
    public void setJsonOutput(String jsonOutput) {
        this.jsonOutput = jsonOutput;
    }

    /**
     * @return the selectionProviderIndex
     */
    public int getSelectionProviderIndex() {
        return selectionProviderIndex;
    }

    /**
     * @param selectionProviderIndex
     *            the selectionProviderIndex to set
     */
    public void setSelectionProviderIndex(int selectionProviderIndex) {
        this.selectionProviderIndex = selectionProviderIndex;
    }

    /**
     * @return the labelSearch
     */
    public String getLabelSearch() {
        return labelSearch;
    }

    /**
     * @param labelSearch
     *            the labelSearch to set
     */
    public void setLabelSearch(String labelSearch) {
        this.labelSearch = labelSearch;
    }

    public String getTestoHelp() {
        return testoHelp;
    }

    public void setTestoHelp(String testoHelp) {
        this.testoHelp = testoHelp;
    }

    public String getTitoloPagina() {
        return titoloPagina;
    }

    public void setTitoloPagina(String titoloPagina) {
        this.titoloPagina = titoloPagina;
    }

    public String getTipoDato() {
        return tipoDato;
    }

    public void setTipoDato(String tipoDato) {
        this.tipoDato = tipoDato;
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void reloadElementsThreadLocals() {
        ElementsThreadLocals.setupDefaultElementsContext();
        ElementsThreadLocals.setHttpServletRequest(getContext().getRequest());
        ElementsThreadLocals.setHttpServletResponse(getContext().getResponse());
        ElementsThreadLocals.setServletContext(getContext().getServletContext());
    }
}

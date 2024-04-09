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
package net.datasiel.webapp.monocrud;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.messages.SessionMessages;

import net.datasiel.webapp.BaseAction;
import net.datasiel.webapp.InfoToolbar;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;

/**
 * @author reisoli
 *
 */
public abstract class MonoCrudAction extends BaseAction {

    protected final Logger log = LoggerFactory.getLogger(MonoCrudAction.class);

    public static final String TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE = "Tentativo di aggiornamento di domanda non modificabile";

    protected String pgm = "";
    // ****************************************************
    // dichiarazione fields
    // ****************************************************
    /**
     * Rappresenta la modalità di visualizzazione.
     */
    protected String modalita;

    /**
     * Oggetti della UI.
     */
    /**
     * Parte di form non modificabile (intestazione).
     */
    protected Element uiRO;
    /**
     * Parte di form modificabile.
     */
    protected Element uiRW;

    /**
     * Model dei dati non modificabili.
     */
    protected Object modelRO;

    /**
     * Model dei dati modificabili.
     */
    protected Object modelRW;

    /**
     * Indica se le chiamate devono essere gestite in modalità ajax o no.
     */
    protected boolean ajaxEnabled;

    // ****************************************************
    // metodi web application
    // ****************************************************

    /**
     * Metodo eseguito di default.
     * 
     * @throws Eccezione
     *             in caricamento model
     * 
     * @return Forward di edit
     */
    @DefaultHandler
    public Resolution executeDefault() throws Exception {
        if (isCreate()) {
            return create();
        } else {
            return edit();
        }
    }

    public Resolution create() throws Exception {
        if (!isSaveEnabled()) {
            throw new Exception("Inserimento non consentito.");
        }

        // TODO Auto-generated method stub
        createModel();

        setupUIInsert();

        // Caricamento dati nell'interfaccia
        if (uiRO != null) {
            uiRO.readFromObject(modelRO);
        }
        if (uiRW != null) {
            uiRW.readFromObject(modelRW);
        }

        return new ForwardResolution(getCreateView());
    }

    /**
     * @return
     * 
     * @throws Exception
     */
    public Resolution edit() throws Exception {
        // Caricamento del modello
        loadModel();

        // Preparazione per generazione oggetti di interfaccia
        setupUIEdit();

        // Caricamento dati nell'interfaccia
        if (uiRO != null) {
            uiRO.readFromObject(modelRO);
        }
        if (uiRW != null) {
            uiRW.readFromObject(modelRW);
        }

        return new ForwardResolution(getEditView());
    }

    /**
     * Impostazione delle possibili componenti dell'ui in edit.
     * 
     */
    public void setupUIEdit() {
        reloadElementsThreadLocals();
        // ElementsThreadLocals.setupDefaultElementsContext();

        Mode mode;
        if (isReadOnly()) {
            mode = Mode.VIEW;
        } else {
            mode = Mode.EDIT;
        }

        /**
         * Preparazione per intestazione.
         */
        uiRO = prepareUiROEdit();

        uiRW = prepareUiRWEdit(mode);

    }

    /**
     * Impostazione delle possibili componenti dell'ui in insert.
     * 
     */
    public void setupUIInsert() {
        reloadElementsThreadLocals();
        // ElementsThreadLocals.setupDefaultElementsContext();

        Mode mode;
        if (isReadOnly()) {
            mode = Mode.VIEW;
        } else {
            mode = Mode.EDIT;
        }

        /**
         * Preparazione per intestazione.
         */
        uiRO = prepareUiROInsert();

        uiRW = prepareUiRWInsert(mode);

    }

    /**
     * Metodo per l'azione di aggiornamento.
     * 
     * @throws Eccezione
     *             in caricamento model
     * 
     * @return
     */
    public Resolution update() throws Exception {

        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            return new RedirectResolution(getOriginalPath());
        }

        // Caricamento del modello
        loadModel();

        // Preparazione per generazione oggetti di interfaccia
        setupUIEdit();

        // Caricamento dati già presenti nell'interfaccia
        uiRW.readFromObject(modelRW);

        // Aggiornamento dati da request
        uiRW.readFromRequest(context.getRequest());

        // Validazione dati
        if (uiRW.validate()) {
            // Scarico i dati negli oggetti
            uiRW.writeToObject(modelRW);

            if (businessValidate()) {
                try {
                    beginTransaction();
                    updateModel();
                    commitTransaction();
                    SessionMessages.addInfoMessage("Salvataggio eseguito");

                    return new RedirectResolution(getOriginalPath());
                } catch (Exception e) {
                    Throwable rootCause = ExceptionUtils.getRootCause(e);
                    log.error("Generic error {}", rootCause, e);
                    SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore generico");
                    return new ForwardResolution(getEditView());
                } finally {
                    safeEndTransaction();
                }

            } else {
                // Le implementazioni di businessValidate devono
                // occuparsi di segnalare gli errori.
                SessionMessages.addErrorMessage("Salvataggio non eseguito.");
                return new ForwardResolution(getEditView());
            }

        } else {
            // TODO estrarre gli errori da crudUi
            SessionMessages.addErrorMessage("Dati non validi.");
            return new ForwardResolution(getEditView());
        }

    }

    /**
     * Metodo per aggiornamento e passaggio a tab successivo.
     */
    public Resolution updateAndContinue() throws Exception {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            return new RedirectResolution(getOriginalPath());
        }

        Resolution updateResult = update();

        return handleSalvaEContinua(updateResult);
    }

    /**
     * Metodo per inserimento nuovo record.
     * 
     * @return
     * 
     * @throws Exception
     */
    public Resolution save() throws Exception {
        if (!isSaveEnabled()) {
            throw new Exception("Inserimento non consentito.");
        }

        // Caricamento del modello
        createModel();

        // Preparazione per generazione oggetti di interfaccia
        setupUIInsert();

        // Caricamento dati già presenti nell'interfaccia
        uiRW.readFromObject(modelRW);

        // Aggiornamento dati da request
        uiRW.readFromRequest(context.getRequest());

        // Validazione dati
        if (uiRW.validate()) {
            // Scarico i dati negli oggetti
            uiRW.writeToObject(modelRW);

            if (businessValidate()) {
                try {
                    beginTransaction();
                    saveModel();
                    commitTransaction();
                    SessionMessages.addInfoMessage("Salvataggio eseguito");

                    // TODO rimandare in edit
                    return new RedirectResolution(getCreatedPath());
                } catch (Exception e) {
                    Throwable rootCause = ExceptionUtils.getRootCause(e);
                    log.error("Generic error {}", rootCause, e);
                    SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore generico");
                    return new ForwardResolution(getCreateView());
                } finally {
                    safeEndTransaction();
                }

            } else {
                // Le implementazioni di businessValidate devono
                // occuparsi di segnalare gli errori.
                SessionMessages.addErrorMessage("Salvataggio non eseguito.");
                return new ForwardResolution(getCreateView());
            }

        } else {
            // TODO estrarre gli errori da crudUi
            SessionMessages.addErrorMessage("Dati non validi.");
            return new ForwardResolution(getCreateView());
        }
    }

    /**
     * Hook per capire se si deve entrare in modalità create o update. Fare override se la condizione non è
     * semplicemente il controllo sulla presenza di idrecord.
     * 
     * @return true se si deve creare il record.
     */
    protected boolean isCreate() {
        if (idrecord == null) {
            return true;
        } else {
            return false;
        }

    }

    /**
     * L'override deve essere eseguito solo nelle action che supportano l'inserimento e deve restituire true se ci sono
     * le condizioni per inserire un nuovo record.
     * 
     * @return
     */
    protected boolean isSaveEnabled() {
        return false;
    }

    /**
     * Metodo per inserimento nuovo recordo e passaggio a tab successivo.
     * 
     * @return
     * 
     * @throws Exception
     */
    public Resolution saveAndContinue() throws Exception {
        if (!isSaveEnabled()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            return new RedirectResolution(getOriginalPath());
        }
        Resolution updateResult = save();
        return handleSalvaEContinua(updateResult);
    }

    /**
     * 
     */
    public void safeEndTransaction() {
        try {
            endTransaction();
        } catch (Exception e) {
            log.info("Generic error {}", ExceptionUtils.getRootCause(e), e);
        }
    }

    // ************************************
    // * Hooks
    // ************************************/
    protected String getEditView() {
        return "/pages/monocrud/edit.jsp";
    }

    public String getCreateView() {
        return "/pages/monocrud/create.jsp";
    }

    /**
     * Hook per determinazione modificabilità dato
     */
    public boolean isReadOnly() {
        log.debug("Default: readOnly false");
        return false;
    }

    /**
     * Hook per la preparazione della gui parte non modificabile in inserimento.
     * 
     * @return TODO
     * @return
     */
    public abstract Element prepareUiROInsert();

    /**
     * Hook per la preparazione gui parte modificabile in inserimento.
     * 
     * @param mode
     *            TODO
     */
    public abstract Element prepareUiRWInsert(Mode mode);

    /**
     * Hook per la preparazione della gui parte non modificabile fase di edit.
     * 
     * @return TODO
     * @return
     */
    public abstract Element prepareUiROEdit();

    /**
     * Hook per la preparazione gui parte modificabile in fase di edit.
     * 
     * @param mode
     *            TODO
     */
    public abstract Element prepareUiRWEdit(Mode mode);

    /**
     * Hook per caricamento del model.
     * 
     * @throws Eccezione
     *             generica a seconda della persistenza usata.
     */
    public abstract void loadModel() throws Exception;

    /**
     * Hook per il salvataggio dell'intero model su database.
     * 
     * @throws Exception
     */
    public abstract void updateModel() throws Exception;

    /**
     * Hook per la preparazione del model per inserimento nuovo record
     */
    public abstract void createModel();

    /**
     * Hook per l'inserimento di un nuovo record
     */
    protected abstract void saveModel() throws Exception;

    /**
     * Hook per validazione complessiva.
     */
    public abstract boolean businessValidate();

    /**
     * Hook per il commit. Lanciamo eccezioni generiche perchè potremmo avere db jdbc come altre cose
     */
    public abstract void commitTransaction() throws Exception;

    /**
     * Hook per l'inizio transazione
     */
    public abstract void beginTransaction() throws Exception;

    /**
     * Hook per il rollback
     * 
     */
    public abstract void endTransaction() throws Exception;

    /**
     * Hook per la gestione del redirect dopo l'inserimento.
     * 
     * @return
     */
    protected abstract String getCreatedPath();

    /**
     * Metodo di ritorno alla "home"
     */
    public abstract Resolution goHome();

    // *********************************************
    // * Getters/Setter
    // *********************************************
    /**
     * @return the uiRO
     */
    public Element getUiRO() {
        return uiRO;
    }

    /**
     * @param uiRO
     *            the uiRO to set
     */
    public void setUiRO(Element uiRO) {
        this.uiRO = uiRO;
    }

    /**
     * @return the uiRW
     */
    public Element getUiRW() {
        return uiRW;
    }

    /**
     * @param uiRW
     *            the uiRW to set
     */
    public void setUiRW(Element uiRW) {
        this.uiRW = uiRW;
    }

    /**
     * @return the modelRO
     */
    public Object getModelRO() {
        return modelRO;
    }

    /**
     * @param modelRO
     *            the modelRO to set
     */
    public void setModelRO(Object modelRO) {
        this.modelRO = modelRO;
    }

    /**
     * @return the modelRW
     */
    public Object getModelRW() {
        return modelRW;
    }

    /**
     * @param modelRW
     *            the modelRW to set
     */
    public void setModelRW(Object modelRW) {
        this.modelRW = modelRW;
    }

    public String getTitoloFromAnnotation() {
        String titolo = "";
        try {
            String titoloGenerico = webappProperties.getProperty("titoloPagina");
            InfoToolbar infoToolbar = this.getClass().getAnnotation(InfoToolbar.class);
            titolo = infoToolbar.titolo() + " | " + titoloGenerico;
        } catch (Exception e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            log.error("Generic error {}", rootCause, e);
        }
        return titolo;
    }

    public boolean isAjaxEnabled() {
        return ajaxEnabled;
    }

    public void setAjaxEnabled(boolean ajaxEnabled) {
        this.ajaxEnabled = ajaxEnabled;
    }

    public String getCreaDettaglio2Title() {
        return "Creazione riga dettaglio 2";
    }

}

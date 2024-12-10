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
package net.datasiel.webapp.crud;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import net.datasiel.webapp.BaseAction;
import net.datasiel.webapp.InfoToolbar;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.StreamingResolution;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.forms.TableForm;
import com.manydesigns.elements.messages.SessionMessages;

/**
 * @author reisoli
 *
 */
public abstract class AbstractCrudAction extends BaseAction {

    public static final String TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE = "Tentativo di aggiornamento di domanda non modificabile";

    private static final String CH_DTS_ACA_RESULT = "DtsAcaResult";

    private static final String CH_VALIDATION_ERROR = "validationError";

    private static final String CH_INSERT_OK = "insertOk";

    private static final String EL_NEW_RIGA = "elNewRiga";

    public final Logger log = LoggerFactory.getLogger(AbstractCrudAction.class);

    protected String pgm = "";

    // ****************************************************
    // dichiarazione costanti
    // ****************************************************

    public static final String EDIT_FORWARD = "edit";
    public static final String RIGA_CREATA_FORWARD = "rigaCreata";
    public static final String RIGA_CREATA_NOJS_FORWARD = "rigaCreataNoJS";
    public static final String DETTAGLIO1_CREATO_NOJS_FORWARD = "dettaglio1CreatoNoJS";
    public static final String DETTAGLIO2_CREATO_NOJS_FORWARD = "dettaglio2CreatoNoJS";

    // ****************************************************
    // dichiarazione fields
    // ****************************************************
    /**
     * Rappresenta la modalità di visualizzazione
     */
    protected String modalita;

    /**
     * Rappresenta tutta l'interfaccia utente.
     */
    protected CrudUI crudUi;

    /**
     * Form per inserimento riga
     */
    protected Element elNewRiga;

    /**
     * Contiene tutti i dati da gestire.
     */
    protected CrudModel crudModel;

    /**
     * Contiene i dati della nuova riga da inserire
     */
    protected RigaModel rigaModel;

    /**
     * Contiene i dati della riga di dettaglio da inserire (dovrebbe servire per entrambi i dettagli).
     */
    protected Object dettaglioModel;

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

    private String deleteDettaglio1;

    private String deleteDettaglio2;

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
        // Caricamento del modello
        loadCrudModel();

        // Preparazione per generazione oggetti di interfaccia
        setupUI();

        // Caricamento dati nell'interfaccia
        crudUi.readFromObject(crudModel);

        // Imposta il link per il download di eventuali file allegati
        setHRef();

        return new ForwardResolution(getEditView());
    }

    public void setHRef() {
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
        loadCrudModel();

        // Preparazione per generazione oggetti di interfaccia
        setupUI();

        // Caricamento dati già presenti nell'interfaccia
        crudUi.readFromObject(crudModel);

        // Aggiornamento dati da request
        crudUi.readFromRequest(context.getRequest());

        // Validazione dati
        if (crudUi.validate()) {
            // Scarico i dati negli oggetti
            crudUi.writeToObject(crudModel);

            if (businessValidate()) {
                try {
                    beginTransaction();
                    updateCrudModel();
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
     * Metodo di annullamento azione corrente
     */
    public Resolution cancel() {
        return new RedirectResolution(getOriginalPath());
    }

    /**
     * Metodo per la visualizzazione della form di inserimento di una nuova riga
     */
    public Resolution createRiga() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
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
        elNewRiga = prepareElRiga(EL_NEW_RIGA, Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(rigaModel.getRiga());

        if (ajaxEnabled) {
            return new ForwardResolution(getRigaCreataView());
        } else {
            return new ForwardResolution(getRigaCreataNoJSView());
        }
    }

    /**
     * Metodo per l'inserimento di una nuova riga.
     *
     * @return
     */
    public Resolution insertRiga() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
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
        elNewRiga = prepareElRiga(EL_NEW_RIGA, Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(rigaModel.getRiga());

        // Aggiornamento dati da request
        elNewRiga.readFromRequest(context.getRequest());

        // Validazione dati
        if (elNewRiga.validate()) {
            // Scarico i dati negli oggetti
            elNewRiga.writeToObject(rigaModel.getRiga());

            if (rigaValidate(rigaModel)) {
                try {
                    beginTransaction();
                    insertRigaModel(rigaModel);
                    commitTransaction();
                    SessionMessages.addInfoMessage("Inserimento eseguito");
                    if (ajaxEnabled) {
                        context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_INSERT_OK);
                        // Uscita fittizia, tanto la pagina viene ricaricata.
                        // Così non passo da una jsp che svuoterebbe i messaggi senza visualizzarli
                        return new StreamingResolution("text/html", "output");
                    } else {
                        return new RedirectResolution(getOriginalPath());
                    }
                } catch (Exception e) {
                    Throwable rootCause = ExceptionUtils.getRootCause(e);
                    log.error("Generic error {}", rootCause, e);
                    SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore genrico");
                    if (ajaxEnabled) {
                        context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                        return new ForwardResolution(getRigaCreataView());
                    } else {
                        return new ForwardResolution(getRigaCreataNoJSView());
                    }
                } finally {
                    safeEndTransaction();
                }

            } else {
                if (ajaxEnabled) {
                    context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                    return new ForwardResolution(getRigaCreataView());
                } else {
                    return new ForwardResolution(getRigaCreataNoJSView());
                }
            }

        } else {
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getRigaCreataView());
            } else {
                return new ForwardResolution(getRigaCreataNoJSView());
            }
        }
    }

    /**
     * Eliminazione riga indicata dal value di delete che inseriamo come proprietà della classe.
     *
     * @return
     */
    public Resolution delete() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            return new RedirectResolution(getOriginalPath());
        }

        // Parsing delete
        String testoPulsanteCancella = String.format(CrudUI.CANCELLA_RIGA, getTitoloFieldsetRiga());
        String strIndice = delete.substring(testoPulsanteCancella.length());
        int indice = Integer.parseInt(strIndice) - 1;
        try {
            beginTransaction();
            deleteRiga(indice);
            commitTransaction();
            SessionMessages.addInfoMessage("Cancellazione effettuata correttamente");
        } catch (Exception e) {
            String rootCause = ExceptionUtils.getRootCauseMessage(e);
            log.error(rootCause, e);
            SessionMessages.addErrorMessage("Cancellazione fallita a causa di un errore generico");
        } finally {
            safeEndTransaction();
        }

        return new RedirectResolution(getOriginalPath());
    }

    /**
     * Metodo di ritorno alla "home"
     */
    public abstract Resolution goHome();

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

    public Resolution createDettaglio1() {

        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getDettaglio1CreatoView());

            } else {
                return new RedirectResolution(getOriginalPath());
            }
        }

        String strIndice = createDettaglio1.substring(CrudUI.CREA_DETTAGLIO.length());
        indiceRiga = Integer.parseInt(strIndice);

        // Dato che è un inserimento dobbiamo creare
        // il model corrispondente alla riga da inserire
        dettaglioModel = createDettaglio1Model();

        // Preparazione per generazione oggetti di interfaccia
        // Parte di setupUI relativa solamente all'inserimento di
        // una nuova riga
        // Si usa lo stesso form di inserimento per riga e dettagli
        elNewRiga = prepareElDettaglio1(EL_NEW_RIGA, Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(dettaglioModel);

        if (ajaxEnabled) {
            return new ForwardResolution(getDettaglio1CreatoView());
        } else {
            return new ForwardResolution(getDettaglio1CreatoNoJSView());
        }
    }

    public Resolution insertDettaglio1() {

        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getDettaglio1CreatoView());

            } else {
                return new RedirectResolution(getOriginalPath());
            }
        }

        if (indiceRiga == null) {
            String strIndice = insertDettaglio1.substring(CrudUI.CREA_DETTAGLIO.length());
            indiceRiga = Integer.parseInt(strIndice);
        }

        // Dato che è un inserimento dobbiamo creare
        // il model corrispondente alla riga da inserire
        dettaglioModel = createDettaglio1Model();

        // Preparazione per generazione oggetti di interfaccia
        // Parte di setupUI relativa solamente all'inserimento di
        // una nuova riga
        elNewRiga = prepareElDettaglio1(EL_NEW_RIGA, Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(dettaglioModel);

        // Aggiornamento dati da request
        elNewRiga.readFromRequest(context.getRequest());

        // Validazione dati
        if (elNewRiga.validate()) {
            // Scarico i dati negli oggetti
            elNewRiga.writeToObject(dettaglioModel);

            if (dettaglio1Validate(dettaglioModel)) {
                try {
                    loadCrudModel();

                    beginTransaction();
                    insertDettaglio1Model(indiceRiga - 1, dettaglioModel);
                    commitTransaction();
                    SessionMessages.addInfoMessage(String.format("Dettaglio aggiunto a riga %d", indiceRiga));
                    if (ajaxEnabled) {
                        context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_INSERT_OK);
                        // Uscita fittizia, tanto la pagina viene ricaricata.
                        // Così non passo da una jsp che svuoterebbe i messaggi senza visualizzarli
                        return new StreamingResolution("text/html", "output");
                    } else {
                        return new RedirectResolution(getOriginalPath());
                    }
                } catch (Exception e) {
                    Throwable rootCause = ExceptionUtils.getRootCause(e);
                    log.error("Generic error {}", rootCause, e);
                    SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore generico");
                    if (ajaxEnabled) {
                        context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                        return new ForwardResolution(getDettaglio1CreatoView());
                        // return new StreamingResolution("text/html","output");
                    } else {
                        return new ForwardResolution(getDettaglio1CreatoNoJSView());
                        // return new StreamingResolution("text/html","output");
                    }
                    // return null;
                } finally {
                    safeEndTransaction();
                }
            } else {
                if (ajaxEnabled) {
                    context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                    return new RedirectResolution(getOriginalPath());
                    // return new ForwardResolution(getDettaglio1CreatoView());
                } else {
                    return new RedirectResolution(getOriginalPath());
                    // return new ForwardResolution(getDettaglio1CreatoNoJSView());
                }
            }

        } else {
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getDettaglio1CreatoView());
            } else {
                return new ForwardResolution(getDettaglio1CreatoNoJSView());
            }
        }
    }

    public Resolution deleteDettaglio1() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            return new RedirectResolution(getOriginalPath());
        }

        // Parsing deleteDettaglio1
        String strIndice = deleteDettaglio1.substring(CrudUI.ELIMINA_RIGHE_DETTAGLIO.length());
        indiceRiga = Integer.parseInt(strIndice) - 1;

        String nomeParametro = String.format("dett1_%dselection", indiceRiga);
        String[] selezione = context.getRequest().getParameterValues(nomeParametro);
        if (selezione != null) {
            // Appoggiamo su un array di interi per fare l'ordinamento
            List<Integer> appoggioIndici = new ArrayList<Integer>(selezione.length);
            for (String strIndiceDettaglio : selezione) {
                int indiceDettaglio = Integer.parseInt(strIndiceDettaglio);
                appoggioIndici.add(indiceDettaglio);
            }

            // Ordiniamo la roba da eliminare
            Collections.sort(appoggioIndici);
            Collections.reverse(appoggioIndici);

            try {

                // Ora posso eliminare
                beginTransaction();
                for (Integer indiceDettaglio : appoggioIndici) {
                    deleteDettaglio1Model(indiceRiga, indiceDettaglio);
                }
                commitTransaction();
                if (selezione.length == 1) {
                    SessionMessages
                            .addInfoMessage(String.format("Un dettaglio cancellato dalla riga %d", indiceRiga + 1));
                } else {
                    SessionMessages.addInfoMessage(
                            String.format("Cancellati %d dettagli dalla riga %d", selezione.length, indiceRiga + 1));
                }
            } catch (Exception e) {
                Throwable rootCause = ExceptionUtils.getRootCause(e);
                log.error("Generic error {}", rootCause, e);
                SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore generico");
            } finally {
                safeEndTransaction();
            }
        } else {
            SessionMessages.addInfoMessage("Nessun dettaglio cancellato");
        }
        return new RedirectResolution(getOriginalPath());
    }

    public Resolution createDettaglio2() {

        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getDettaglio1CreatoView());

            } else {
                return new RedirectResolution(getOriginalPath());
            }
        }

        String strIndice = createDettaglio2.substring(CrudUI.CREA_DETTAGLIO.length());
        indiceRiga = Integer.parseInt(strIndice);

        // Dato che è un inserimento dobbiamo creare
        // il model corrispondente alla riga da inserire
        dettaglioModel = createDettaglio2Model();

        // Preparazione per generazione oggetti di interfaccia
        // Parte di setupUI relativa solamente all'inserimento di
        // una nuova riga
        // Si usa lo stesso form di inserimento per riga e dettagli
        elNewRiga = prepareElDettaglio2(EL_NEW_RIGA, Mode.CREATE);

        // Caricamento dati già presenti nell'interfaccia
        elNewRiga.readFromObject(dettaglioModel);

        if (ajaxEnabled) {
            return new ForwardResolution(getDettaglio2CreatoView());
        } else {
            return new ForwardResolution(getDettaglio2CreatoNoJSView());
        }
    }

    public Resolution insertDettaglio2() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getDettaglio2CreatoView());

            } else {
                return new RedirectResolution(getOriginalPath());
            }
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
                    if (ajaxEnabled) {
                        context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_INSERT_OK);
                        // Uscita fittizia, tanto la pagina viene ricaricata.
                        // Così non passo da una jsp che svuoterebbe i messaggi senza visualizzarli
                        return new StreamingResolution("text/html", "output");
                    } else {
                        return new RedirectResolution(getOriginalPath());
                    }
                } catch (Exception e) {
                    Throwable rootCause = ExceptionUtils.getRootCause(e);
                    log.error("Generic error {}", rootCause, e);
                    SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore generico");
                    if (ajaxEnabled) {
                        context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                        return new ForwardResolution(getDettaglio2CreatoView());
                    } else {
                        return new ForwardResolution(getDettaglio2CreatoNoJSView());
                    }
                } finally {
                    safeEndTransaction();
                }
            } else {
                if (ajaxEnabled) {
                    context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                    return new ForwardResolution(getDettaglio2CreatoView());
                } else {
                    return new ForwardResolution(getDettaglio2CreatoNoJSView());
                }
            }

        } else {
            if (ajaxEnabled) {
                context.getResponse().setHeader(CH_DTS_ACA_RESULT, CH_VALIDATION_ERROR);
                return new ForwardResolution(getDettaglio2CreatoView());
            } else {
                return new ForwardResolution(getDettaglio2CreatoNoJSView());
            }
        }
    }

    public Resolution deleteDettaglio2() {
        if (isReadOnly()) {
            SessionMessages.addErrorMessage(TENTATIVO_DI_AGGIORNAMENTO_DI_DOMANDA_NON_MODIFICABILE);
            return new RedirectResolution(getOriginalPath());
        }
        // Parsing deleteDettaglio2
        String strIndice = deleteDettaglio2.substring(CrudUI.ELIMINA_RIGHE_DETTAGLIO.length());
        indiceRiga = Integer.parseInt(strIndice) - 1;

        String nomeParametro = String.format("dett2_%dselection", indiceRiga);
        String[] selezione = context.getRequest().getParameterValues(nomeParametro);
        if (selezione != null) {
            // Appoggiamo su un array di interi per fare l'ordinamento
            List<Integer> appoggioIndici = new ArrayList<Integer>(selezione.length);
            for (String strIndiceDettaglio : selezione) {
                int indiceDettaglio = Integer.parseInt(strIndiceDettaglio);
                appoggioIndici.add(indiceDettaglio);
            }

            // Ordiniamo la roba da eliminare
            Collections.sort(appoggioIndici);
            Collections.reverse(appoggioIndici);

            // Ora posso eliminare
            try {
                beginTransaction();
                for (Integer indiceDettaglio : appoggioIndici) {
                    deleteDettaglio2Model(indiceRiga, indiceDettaglio);
                }
                commitTransaction();
                if (selezione.length == 1) {
                    SessionMessages.addInfoMessage(String.format("Un dettaglio cancellato dalla riga %d", indiceRiga));
                } else {
                    SessionMessages.addInfoMessage(
                            String.format("Cancellati %d dettagli dalla riga %d", selezione.length, indiceRiga));
                }
            } catch (Exception e) {
                Throwable rootCause = ExceptionUtils.getRootCause(e);
                log.error("Generic error {}", rootCause, e);
                SessionMessages.addErrorMessage("Salvataggio fallito a causa di un errore generico");
            } finally {
                safeEndTransaction();
            }
        } else {
            SessionMessages.addInfoMessage("Nessun dettaglio cancellato");
        }
        return new RedirectResolution(getOriginalPath());
    }

    // ****************************************************
    // metodi utilità
    // ****************************************************

    private int safeSize(Collection<?> collection) {
        if (collection == null) {
            return 0;
        } else {
            return collection.size();
        }
    }

    /**
     * Impostazione delle possibili componenti dell'ui.
     *
     */
    public void setupUI() {

        crudUi = new CrudUI(isReadOnly());
        Mode mode;
        if (isReadOnly()) {
            mode = Mode.VIEW;
        } else {
            mode = Mode.EDIT;
        }

        /**
         * Preparazione per intestazione.
         */
        crudUi.intestazioneUiRO = prepareIntestazioneUiRO();
        crudUi.intestazioneUiRW = prepareIntestazioneUiRW(mode);

        crudUi.maxRighe = getMaxRighe();
        crudUi.labelFieldset = getTitoloFieldsetRiga();

        if (crudModel == null) {
            log.debug("crudModel null.");
            return;
        }

        /**
         * Preparazione per righe e dettagli
         */
        List<RigaModel> righe = crudModel.getRighe();

        if (righe == null) {
            log.debug("Righe null.");
            return;
        }
        int numRighe = righe.size();
        for (int i = 0; i < numRighe; i++) {
            RigaModel riga = righe.get(i);
            int numRows1 = safeSize(riga.getDettagli1());
            int numRows2 = safeSize(riga.getDettagli2());
            String prefixRiga = "riga_" + i;
            String prefix1 = "dett1_" + i;
            String prefix2 = "dett2_" + i;
            RigaUI rigaUI = new RigaUI();

            rigaUI.elRiga = prepareElRiga(prefixRiga, mode);
            TableForm eltafoDettaglio1 = prepareEltafoDettaglio1(prefix1, numRows1, mode);
            rigaUI.eltafoDettaglio1 = eltafoDettaglio1;
            if (eltafoDettaglio1 != null) {
                eltafoDettaglio1.setSelectable(!isReadOnly());
                // String canonicalNameOfClass=riga.getClass().getCanonicalName();
                // if ("Lista file".equals(eltafoDettaglio1.getCaption()) &&
                // "net.datasiel.simpaweb.actionbeans.crudud.DocumentiRigaModel".equals(canonicalNameOfClass)){
                // eltafoDettaglio1.setSelectable(false);
                // }
            }

            TableForm eltafoDettaglio2 = prepareEltafoDettaglio2(prefix2, numRows2, mode);
            rigaUI.eltafoDettaglio2 = eltafoDettaglio2;

            if (eltafoDettaglio2 != null) {
                eltafoDettaglio2.setSelectable(!isReadOnly());
                // eltafoDettaglio2.setSelectable(false);
            }
            crudUi.righeUI.add(rigaUI);
        } // Fine for su righe

    }

    // ****************************************************
    // hook a metodi da implementare
    // ****************************************************

    // Non vengono fatti abstract per poter dare un'implementazione
    // "vuota" di default

    /**
     * Prepara gli elements per intestazione read only.
     */
    public abstract Element prepareIntestazioneUiRO();

    /**
     * Prepara gli elements per intestazione read/write.
     *
     * @param mode
     *            TODO
     */
    public abstract Element prepareIntestazioneUiRW(Mode mode);

    /**
     * Prepara gli elements per la riga.
     */
    public abstract Element prepareElRiga(String prefix, Mode mode);

    /**
     * Prepara gli elements per il primo dettaglio.
     */
    public abstract TableForm prepareEltafoDettaglio1(String prefix, int nRows, Mode mode);

    /**
     * Prepara gli elements per il secondo dettaglio.
     */
    public abstract TableForm prepareEltafoDettaglio2(String prefix, int nRows, Mode mode);

    /**
     * Prepara form inserimento nuovo dettaglio1
     */
    public abstract Element prepareElDettaglio1(String prefix, Mode mode);

    /**
     * Prepara form inserimento nuovo dettaglio2
     */
    public abstract Element prepareElDettaglio2(String prefix, Mode mode);

    /**
     * Hook per caricamento del model.
     *
     * @throws Eccezione
     *             generica a seconda della persistenza usata.
     */
    public abstract void loadCrudModel() throws Exception;

    /**
     * Hook per validazione complessiva.
     */
    public abstract boolean businessValidate();

    /**
     * Hook per validazione singola riga.
     *
     * @param rigaModel
     *            Riga da validare
     *
     * @return esito validazione
     */
    public abstract boolean rigaValidate(RigaModel rigaModel);

    /**
     * Hook per la validazione business di una riga della tabella dettaglio 1.
     *
     * @param dettaglio
     *            Riga da validare
     *
     * @return Esito validazione
     */
    public abstract boolean dettaglio1Validate(Object dettaglio);

    /**
     * Hook per la validazione business di una riga della tabella dettaglio 2.
     *
     * @param dettaglio
     *            Riga da validare
     *
     * @return Esito validazione
     */
    public abstract boolean dettaglio2Validate(Object dettaglio);

    /**
     * Hook per l'eliminazine di una riga intera (testata e dettagli).
     *
     * @param indiceRiga
     *            indice 0 based della riga da eliminare.
     */
    public abstract void deleteRiga(int indiceRiga) throws Exception;

    /**
     * Hook per il salvataggio dell'intero model su database.
     *
     * @throws Exception
     */
    public abstract void updateCrudModel() throws Exception;

    /**
     * Hook per inserimento di una singola riga.
     *
     * @param rigaModel
     *            Riga da inserire
     *
     * @throws Exception
     */
    public abstract void insertRigaModel(RigaModel rigaModel) throws Exception;

    public abstract RigaModel createRigaModel();

    /**
     * Hook per inserimento riga dettaglio 1.
     *
     * @param indiceRiga
     *            Indice della riga a cui aggiungere la riga dettaglio.
     * @param dettaglio
     *            Oggetto da inserire
     */
    public abstract void insertDettaglio1Model(int indiceRiga, Object dettaglio) throws Exception;

    public abstract Object createDettaglio1Model();

    public abstract void deleteDettaglio1Model(int indiceRiga2, int indiceDettaglio) throws Exception;

    /**
     * Hook per inserimento riga dettaglio 2.
     *
     * @param indiceRiga
     *            Indice della riga a cui aggiungere la riga dettaglio.
     * @param dettaglio
     *            Oggetto da inserire
     */
    public abstract void insertDettaglio2Model(int indiceRiga, Object dettaglio) throws Exception;

    public abstract Object createDettaglio2Model();

    public abstract void deleteDettaglio2Model(int indiceRiga2, int indiceDettaglio) throws Exception;

    /**
     * Hook per l'impostazione di title (da ripetere per altri meta)
     */
    public String getEditTitle() {
        /**
         * titolo di default
         */
        return "Edit";
    }

    public String getCreaRigaTitle() {
        return "Creazione riga";
    }

    public String getCreaDettaglio1Title() {
        return "Creazione riga dettaglio 1";

    }

    public String getCreaDettaglio2Title() {
        return "Creazione riga dettaglio 2";
    }

    /**
     * Hooks per la personalizzazione dell'interfaccia
     */
    /**
     * Restituisce il numero massimo di righe. Fare restituire 1 per caso estremo di una sola riga non eliminabile. Se
     * non c'è limite restituire 0.
     *
     * @return
     */
    public int getMaxRighe() {
        return 0;
    }

    /**
     * Restituisce il titolo del fieldset della riga Restiuire null per non avere il fieldset
     *
     * @return
     */
    public String getTitoloFieldsetRiga() {
        return "Riga ";
    }

    /**
     * Hook per la restituzione della jsp da usare per la creazione di una riga del primo dettaglio.
     *
     * @return
     */
    public String getDettaglio1CreatoView() {
        return "/pages/crud/dettaglio1Creato.jsp";
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

    /**
     * Hook per la restituzione della jsp da usare per la creazione di una riga del secondo dettaglio.
     *
     * @return
     */
    public String getDettaglio2CreatoView() {
        return "/pages/crud/dettaglio2Creato.jsp";
    }

    /**
     * Hook per la restituzione della jsp da usare per la creazione di una riga del secondo dettaglio caso JS
     * disabilitato.
     *
     * @return
     */
    public String getDettaglio2CreatoNoJSView() {
        return "/pages/crud/dettaglio2CreatoNoJS.jsp";
    }

    /**
     * Hook per la restituzione della jsp da creare per la creazione di una nuova riga nel caso JS disabilitato.
     *
     * @return
     */
    public String getRigaCreataNoJSView() {
        return "/pages/crud/rigaCreataNoJS.jsp";
    }

    /**
     * Hook per la restituzione della jsp da creare per la creazione di una nuova riga.
     *
     * @return
     */
    public String getRigaCreataView() {
        return "/pages/crud/rigaCreata.jsp";
    }

    /**
     * Hook per la restituzione della jsp vista principale.
     *
     * @return
     */
    public String getEditView() {
        return "/pages/crud/edit.jsp";
    }

    /*********************************************
     * Hooks per la gestione delle transazioni
     ********************************************/

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
     * Hook per determinazione modificabilità dato
     */
    public boolean isReadOnly() {
        log.debug("Default: readOnly false");
        return false;
    }

    // ****************************************************
    // getters/setters
    // ****************************************************

    /**
     * @return the modalita
     */
    public String getModalita() {
        return modalita;
    }

    /**
     * @param modalita
     *            the modalita to set
     */
    public void setModalita(String modalita) {
        this.modalita = modalita;
    }

    /**
     * @return the crudUi
     */
    public CrudUI getCrudUi() {
        return crudUi;
    }

    /**
     * @param crudUi
     *            the crudUi to set
     */
    public void setCrudUi(CrudUI crudUi) {
        this.crudUi = crudUi;
    }

    /**
     * @return the crudModel
     */
    public CrudModel getCrudModel() {
        return crudModel;
    }

    /**
     * @param crudModel
     *            the crudModel to set
     */
    public void setCrudModel(CrudModel crudModel) {
        this.crudModel = crudModel;
    }

    /**
     * @return the elNewRiga
     */
    public Element getElNewRiga() {
        return elNewRiga;
    }

    /**
     * @param elNewRiga
     *            the elNewRiga to set
     */
    public void setElNewRiga(Element elNewRiga) {
        this.elNewRiga = elNewRiga;
    }

    /**
     * @return the rigaModel
     */
    public RigaModel getRigaModel() {
        return rigaModel;
    }

    /**
     * @param rigaModel
     *            the rigaModel to set
     */
    public void setRigaModel(RigaModel rigaModel) {
        this.rigaModel = rigaModel;
    }

    /**
     * @return the ajaxEnabled
     */
    public boolean isAjaxEnabled() {
        return false;// ajaxEnabled;
    }

    /**
     * @param ajaxEnabled
     *            the ajaxEnabled to set
     */
    public void setAjaxEnabled(boolean ajaxEnabled) {
        this.ajaxEnabled = ajaxEnabled;
    }

    /**
     * @return the delete
     */
    public String getDelete() {
        return delete;
    }

    /**
     * @param methodDelete
     *            the delete to set
     */
    public void setDelete(String methodDelete) {
        delete = methodDelete;
    }

    /**
     * @return the insertDettaglio1
     */
    public String getInsertDettaglio1() {
        return insertDettaglio1;
    }

    /**
     * @param methodInsertDettaglio1
     *            the insertDettaglio1 to set
     */
    public void setInsertDettaglio1(String methodInsertDettaglio1) {
        insertDettaglio1 = methodInsertDettaglio1;
    }

    /**
     * @return the createDettaglio1
     */
    public String getCreateDettaglio1() {
        return createDettaglio1;
    }

    /**
     * @param methodCreateDettaglio1
     *            the createDettaglio1 to set
     */
    public void setCreateDettaglio1(String methodCreateDettaglio1) {
        createDettaglio1 = methodCreateDettaglio1;
    }

    /**
     * @return the indiceRiga
     */
    public Integer getIndiceRiga() {
        return indiceRiga;
    }

    /**
     * @param indiceRiga
     *            the indiceRiga to set
     */
    public void setIndiceRiga(Integer indiceRiga) {
        this.indiceRiga = indiceRiga;
    }

    /**
     * @return the createDettaglio2
     */
    public String getCreateDettaglio2() {
        return createDettaglio2;
    }

    /**
     * @param methodCreateDettaglio2
     *            the createDettaglio2 to set
     */
    public void setCreateDettaglio2(String methodCreateDettaglio2) {
        createDettaglio2 = methodCreateDettaglio2;
    }

    /**
     * @return the insertDettaglio2
     */
    public String getInsertDettaglio2() {
        return insertDettaglio2;
    }

    /**
     * @param methodInsertDettaglio2
     *            the insertDettaglio2 to set
     */
    public void setInsertDettaglio2(String methodInsertDettaglio2) {
        insertDettaglio2 = methodInsertDettaglio2;
    }

    /**
     * @return the deleteDettaglio1
     */
    public String getDeleteDettaglio1() {
        return deleteDettaglio1;
    }

    /**
     * @param methodDeleteDettaglio1
     *            the deleteDettaglio1 to set
     */
    public void setDeleteDettaglio1(String methodDeleteDettaglio1) {
        deleteDettaglio1 = methodDeleteDettaglio1;
    }

    /**
     * @return the deleteDettaglio2
     */
    public String getDeleteDettaglio2() {
        return deleteDettaglio2;
    }

    /**
     * @param methodDeleteDettaglio2
     *            the deleteDettaglio2 to set
     */
    public void setDeleteDettaglio2(String methodDeleteDettaglio2) {
        deleteDettaglio2 = methodDeleteDettaglio2;
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

}

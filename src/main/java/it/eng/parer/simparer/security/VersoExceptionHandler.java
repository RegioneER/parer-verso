/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

/**
 *
 */
package it.eng.parer.simparer.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.messages.SessionMessages;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.controller.FileUploadLimitExceededException;
import net.sourceforge.stripes.controller.StripesConstants;
import net.sourceforge.stripes.exception.DefaultExceptionHandler;
import net.sourceforge.stripes.exception.StripesRuntimeException;
import net.sourceforge.stripes.validation.SimpleError;

/**
 * @author Parucci_M
 *
 */
public class VersoExceptionHandler extends DefaultExceptionHandler {

    private static final String ERROR_HANDLING_ATTR = VersoExceptionHandler.class.getName()
            + ".ERROR_HANDLING";

    private final Logger log = LoggerFactory.getLogger(VersoExceptionHandler.class);

    /**
     *
     */
    public VersoExceptionHandler() {
        // TODO Auto-generated constructor stub
    }

    /**
     * Evita il loop di errori: se la gestione di un errore e' gia' in corso per la stessa richiesta
     * (es. la pagina di errore fallisce a sua volta), non si logga ne' si inoltra nuovamente.
     *
     * @return true se la richiesta e' gia' in gestione errore
     */
    private boolean isReentrant(HttpServletRequest request, HttpServletResponse response,
            Exception exc) {
        if (request.getAttribute(ERROR_HANDLING_ATTR) != null) {
            log.warn("Errore durante la gestione di un errore ({}), interrompo la catena",
                    exc.getClass().getName());
            if (!response.isCommitted()) {
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
            return true;
        }
        request.setAttribute(ERROR_HANDLING_ATTR, Boolean.TRUE);
        return false;
    }

    public void handleDatabaseException(StripesRuntimeException exc, HttpServletRequest request,
            HttpServletResponse response) {
        if (isReentrant(request, response, exc)) {
            return;
        }
        log.error("ERRORE VersoException: ", exc);
        // message= message.contains("null")?"Errore non riconosciuto, Contattare
        // l'assistenza":message;
        String message = "errore critico nella comunicazione con il database, contattare l'assistenza tecnica.";

        // }
        SessionMessages
                .addErrorMessage("Attenzione si è verificato un errore imprevisto: " + message);
        if (response.isCommitted()) {
            log.warn("Response already committed, cannot forward to error page");
            return;
        }
        try {
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            log.error("Generic error", e);
        }
    }

    // net.sourceforge.stripes.exception.StripesRuntimeException
    public void handleGeneric(Exception exc, HttpServletRequest request,
            HttpServletResponse response) {
        if (isReentrant(request, response, exc)) {
            return;
        }
        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);
        // String message= exc.getLocalizedMessage();
        // if ( message!=null){

        // message= message==null ||message.toLowerCase().contains("null")?"Errore non riconosciuto,
        // Contattare
        // l'assistenza":message;
        String message = "Errore critico sulla funzionalita, contattare l'assistenza tecnica.";
        // }
        log.error("Generic error", exc);

        try {
            if (bean != null) {
                bean.getContext().getValidationErrors()
                        .addGlobalError(new SimpleError("Errore imprevisto: " + message));
            }
            if (response.isCommitted()) {
                log.warn("Response already committed, cannot forward to error page");
                return;
            }
            request.getRequestDispatcher("/pages/error.jsp").forward(request, response);
        } catch (Exception e) {
            log.error("Generic error", e);

        }

    }
    // FileUploadLimitExceededException

    public void handleFileUploadLimitExceeded(FileUploadLimitExceededException exc,
            HttpServletRequest request, HttpServletResponse response) {
        if (isReentrant(request, response, exc)) {
            return;
        }
        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);
        String message = "Errore critico sulla funzionalita, contattare l'assistenza tecnica.";

        // }
        log.error("Generic error", exc);

        try {
            if (bean != null) {
                bean.getContext().getValidationErrors()
                        .addGlobalError(new SimpleError("Errore imprevisto: " + message));
            }
            if (response.isCommitted()) {
                log.warn("Response already committed, cannot forward to error page");
                return;
            }
        } catch (Exception e) {
            log.error("Generic error", e);

        }

    }

}

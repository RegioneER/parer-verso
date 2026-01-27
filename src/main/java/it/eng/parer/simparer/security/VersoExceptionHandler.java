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
package it.eng.parer.simparer.security;

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

    private final Logger log = LoggerFactory.getLogger(VersoExceptionHandler.class);

    /**
     *
     */
    public VersoExceptionHandler() {
        // TODO Auto-generated constructor stub
    }

    public void handleStripesRuntimeException(StripesRuntimeException exc, HttpServletRequest request,
            HttpServletResponse response) {
        // String message= exc.getLocalizedMessage();
        // if ( message!=null){
        //
        // message= message.contains("null")?"Errore non riconosciuto, Contattare l'assistenza":message;
        String message = "Errore critico sulla funzionalita, contattare l'assistenza tecnica.";

        // }
        SessionMessages.addErrorMessage(message);
        try {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            log.error("Generic error", e);
        }
    }

    public void handleFileUploadLimitExceeded(FileUploadLimitExceededException exc, HttpServletRequest request,
            HttpServletResponse response) {
        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);
        // String message= exc.getLocalizedMessage();
        // if ( message!=null){

        // message= message==null ||message.toLowerCase().contains("null")?"Errore non riconosciuto, Contattare
        // l'assistenza":message;
        String message = "Errore critico sulla funzionalita, contattare l'assistenza tecnica.";
        // }
        log.error("Generic error", exc);

        try {
            if (bean != null) {
                bean.getContext().getValidationErrors()
                        .addGlobalError(new SimpleError("Errore imprevisto: " + message));
            }

            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);

        } catch (Exception e) {
            log.error("Generic error", e);

        }

    }
    // FileUploadLimitExceededException
    
    public void handleGeneric(Exception exc, HttpServletRequest request, HttpServletResponse response) {
        ActionBean bean = (ActionBean) request.getAttribute(StripesConstants.REQ_ATTR_ACTION_BEAN);
        // String message= exc.getLocalizedMessage();
        // if ( message!=null){

        // message= message==null ||message.toLowerCase().contains("null")?"Errore non riconosciuto, Contattare
        // l'assistenza":message;
        String message = "Errore critico sulla funzionalita, contattare l'assistenza tecnica.";

        // }
        log.error("Generic error", exc);

        try {
            if (bean != null) {
                bean.getContext().getValidationErrors()
                        .addGlobalError(new SimpleError("Errore imprevisto: " + message));
            }
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, message);

        } catch (Exception e) {
            log.error("Generic error", e);

        }

    }

}

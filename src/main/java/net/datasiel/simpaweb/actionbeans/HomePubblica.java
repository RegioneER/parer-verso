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
package net.datasiel.simpaweb.actionbeans;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import net.datasiel.webapp.BaseAction;
import net.datasiel.webapp.DtsActionBeanContext;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;

import com.manydesigns.elements.messages.SessionMessages;

/**
 * @author reisoli
 *
 */
@UrlBinding("/welcome")
public class HomePubblica extends BaseAction {

    @SuppressWarnings("unchecked")
    @DefaultHandler
    public Resolution executeDefault() {

        HttpServletRequest request = getContext().getRequest();
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement().toString();
            String headerValue = request.getHeader(headerName).toString();
            log.debug("Name - {} - Valore {}", headerName, headerValue);
        }

        if (SessionMessages.getErrorQueue().isEmpty() && SessionMessages.getInfoQueue().isEmpty()
                && SessionMessages.getWarningQueue().isEmpty()) {
            messaggiPresenti = false;
        } else {
            messaggiPresenti = true;
        }

        return new ForwardResolution("/pages/NotAuthorized.jsp");
    }

    private static String getBuildInfo() {
        String buildInfo = "";
        try (InputStream is = DtsActionBeanContext.class.getClassLoader()
                .getResourceAsStream("/resources/buildinfo.properties")) {
            Properties buildProps = new Properties();
            buildProps.load(is);
            String numero = buildProps.getProperty("build.number");
            String data = buildProps.getProperty("build.date");
            String revisione = buildProps.getProperty("build.revision");
            buildInfo = String.format("SimpaWeb build numero %s a partire dalla revision %s creata il %s", numero,
                    revisione, data);
        } catch (Exception e) {
            // Le informazioni di build potrebbero non essere disponibili se il build non è stato fatto in maniera
            // opportuna.
            // Non blocchiamo l'esecuzione
            buildInfo = "Informazioni di build non trovate";
            log.warn(buildInfo);
            log.error("Generic error", e);
        }
        return buildInfo;
    }

}

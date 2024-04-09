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

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import com.manydesigns.elements.ElementsThreadLocals;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

/**
 * @author reisoli
 *
 */
@Intercepts(LifecycleStage.RequestInit)
public class ApplicationInterceptor implements Interceptor {

    /*
     * (non-Javadoc)
     * 
     * @see
     * net.sourceforge.stripes.controller.Interceptor#intercept(net.sourceforge.stripes.controller.ExecutionContext)
     */
    public Resolution intercept(ExecutionContext executionContext) throws Exception {

        ActionBeanContext actionBeanContext = executionContext.getActionBeanContext();
        ServletContext context = actionBeanContext.getServletContext();
        HttpServletResponse response = actionBeanContext.getResponse();

        ElementsThreadLocals.setupDefaultElementsContext();
        ElementsThreadLocals.setHttpServletRequest(actionBeanContext.getRequest());
        ElementsThreadLocals.setHttpServletResponse(response);
        ElementsThreadLocals.setServletContext(context);

        /**
         * Preparazione context log
         */
        // UserInfo userInfo = (UserInfo) SecurityUtils.getSubject().getPrincipal();
        // User utente = (User) SessionManager.getUser(actionBeanContext.getRequest().getSession());
        // if (utente != null) {
        // MDC.put("username", utente.getUsername());
        // }
        // else {
        // MDC.put("username", "guest");
        // }

        /**
         * Controllo cache
         */
        response.setHeader("Cache-Control", "no-cache");
        response.addHeader("Cache-Control", "no-store");
        response.addHeader("Cache-Control", "must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        return executionContext.proceed();
    }

}

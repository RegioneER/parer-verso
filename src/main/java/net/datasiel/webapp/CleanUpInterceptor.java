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

package net.datasiel.webapp;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.ElementsThreadLocals;

import net.sourceforge.stripes.action.ActionBean;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.controller.ExecutionContext;
import net.sourceforge.stripes.controller.Interceptor;
import net.sourceforge.stripes.controller.Intercepts;
import net.sourceforge.stripes.controller.LifecycleStage;

@Intercepts(LifecycleStage.RequestComplete)
public class CleanUpInterceptor implements Interceptor {

    private final Logger log = LoggerFactory.getLogger(CleanUpInterceptor.class);

    public Resolution intercept(ExecutionContext arg0) throws Exception {
        ElementsThreadLocals.removeElementsContext();
        log.debug("Pulizia....");
        /*
         * Chiusura connessione al db
         */
        ActionBean action = arg0.getActionBean();
        if (action instanceof BaseAction) {
            log.debug("Chiusura connessioni...");
            ((BaseAction) action).closeConnection();
        }

        // Pulizia context log

        Resolution r = arg0.proceed();

        log.debug("Post chiamata....");
        return r;

    }

}

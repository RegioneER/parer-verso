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

import it.eng.parer.simparer.security.SimparerLoginLog;
import it.eng.spagoLite.SessionManager;
import it.eng.spagoLite.security.IUser;
import javax.servlet.http.HttpSession;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.webapp.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;

/**
 * @author reisoli
 *
 */
@UrlBinding("/pro/logout")
public class Logout extends BaseAction {

    @SpringBean("simparerLoginLog")
    private SimparerLoginLog loginLog;

    @DefaultHandler
    public Resolution executeDefault() {
        HttpSession httpSession = getContext().getRequest().getSession();
        IUser<?> user = SessionManager.getUser(httpSession);
        String ipVers = (String) httpSession.getAttribute(Constants.SESS_PARAM_WEB_CLIENT_IP_ADDRESS);
        String serverName = (String) httpSession.getAttribute(Constants.SESS_PARAM_WEB_SERVER_ADDRESS);
        loginLog.writeLogEvento(user, ipVers, serverName, SimparerLoginLog.TipiEvento.LOGOUT);
        Resolution resolution = null;
        // Se l'utente è FEDERA SPID allora non fa il GLOBAL LOGOUT che FEDERA NON GESTISCE
        if (user.getUserType() != null && user.getUserType().equals(IUser.UserType.SPID_FEDERA)) {
            resolution = new RedirectResolution("/saml/logout?local=true");
        } else {
            resolution = new RedirectResolution("/saml/logout?local=false");
        }
        return resolution;
    }
}

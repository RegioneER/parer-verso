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

package it.eng.parer.simparer.security;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.parer.simparer.common.SpringContext;
import it.eng.spagoLite.SessionManager;
import it.eng.spagoLite.security.User;
import it.eng.spagoLite.security.auth.Authenticator;
import net.datasiel.simpaweb.db.vo.VUsrAbilApplicVO;

public class SimparerAuthenticator extends Authenticator {
    private final Logger logger = LoggerFactory.getLogger(SimparerAuthenticator.class);
    private String appName;

    @Override
    public String getAppName() {
        return appName;
    }

    @Override
    public User recuperoAutorizzazioni(HttpSession httpSession) {
        User utente = (User) SessionManager.getUser(httpSession);
        logger.info(SimparerAuthenticator.class.getSimpleName() + " --- Recupero autorizzazioni da parte di "
                + utente.getUsername());

        SessionManager.setUser(httpSession, utente);
        return utente;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    /**
     * Verifica se l'utente di cui viene passato l'<code>username</code> possiede l'autorizzazione per accedere
     * all'applicazione
     * 
     * @param username
     * 
     * @return <code>true</code> se possiede l'autorizzazione, altrimenti <code>false</code>
     */
    public boolean isAuthorized(String username) {
        boolean res = false;
        Connection conn = null;
        logger.info(SimparerAuthenticator.class.getSimpleName() + " --- Verifica se utente \"" + username
                + "\" possiede le autorizzazione su " + getAppName());

        DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);

        if (datasource != null) {
            try {
                conn = datasource.getConnection();

                VUsrAbilApplicVO usrAbilVO = new VUsrAbilApplicVO();
                res = usrAbilVO.userAuthorized(username, getAppName(), conn);
            } catch (SQLException e) {
                logger.warn("Cannot create connection with db", e);
            } finally {
                if (conn != null)
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
            }
        }

        return res;

        // if(username.compareTo(resultQuery) == 0)
        // return true;
        //
        // return false;
    }
}

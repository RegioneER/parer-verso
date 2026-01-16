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

package net.datasiel.simpaweb.db.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VUsrAbilApplic;

/**
 *
 * @author Moretti_Lu
 */
public class VUsrAbilApplicDAO extends VUsrAbilApplic {

    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idAmbiente", "nmAmbiente" };
    }

    private Logger logger = LoggerFactory.getLogger(VUsrAbilApplicDAO.class.getName());

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public VUsrAbilApplicDAO() {
        super();
    }

    public String getUsernameByUsernameAndAppname(String username, String appName, Connection conn)
            throws SQLException {
        String res = null;

        String query = "SELECT u.NM_USERID " + "FROM SACER_IAM.USR_V_ABIL_APPLIC u "
                + "WHERE u.NM_USERID = ? AND u.NM_APPLIC = ?";

        ResultSet r = null;
        try (PreparedStatement st = conn.prepareStatement(query)) {
            logger.debug(query);
            st.setString(1, username);
            st.setString(2, appName);
            r = st.executeQuery();

            if (r.next()) {
                if (r.getObject("NM_USERID") != null) {
                    res = r.getString("NM_USERID");

                    if (r.next() != false) {
                        logger.error("Presenti due utenti con stesso username!!");
                        res = null;
                    }
                }
            }

            return res;

        } finally {
            if (r != null) {
                r.close();
            }
        }
    }
}

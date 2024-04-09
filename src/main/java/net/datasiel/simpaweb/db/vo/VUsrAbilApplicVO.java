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

package net.datasiel.simpaweb.db.vo;

import java.sql.Connection;
import java.sql.SQLException;

import net.datasiel.simpaweb.db.dao.VUsrAbilApplicDAO;

/**
 *
 * @author Moretti_Lu
 */
public class VUsrAbilApplicVO extends VUsrAbilApplicDAO {

    private static final long serialVersionUID = 1L;

    public VUsrAbilApplicVO() {
        super();
    }

    public boolean userAuthorized(String username, String appName, Connection conn) throws SQLException {
        String res = getUsernameByUsernameAndAppname(username, appName, conn);

        if (res != null) {
            if (username.compareTo(res) == 0) {
                return true;
            }
        }

        return false;
    }
}

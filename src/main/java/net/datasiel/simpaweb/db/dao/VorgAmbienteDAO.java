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

import java.sql.ResultSet;
import java.sql.SQLException;

import net.datasiel.simpaweb.db.pojo.VOrgAmbiente;

public class VorgAmbienteDAO extends VOrgAmbiente {

    public static String[] fieldNames = null;
    static {
        fieldNames = new String[] { "idAmbiente", "nmAmbiente" };
    }

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public VorgAmbienteDAO() {
        super();
    }

    public void getFromResultSet(VOrgAmbiente obj, ResultSet r) throws SQLException {

        if (r.getObject("ID_AMBIENTE") == null) {
            obj.setIdAmbiente(null);
        } else {
            obj.setIdAmbiente(r.getLong("ID_AMBIENTE"));
        }
        obj.setNmAmbiente(r.getString("NM_AMBIENTE"));

    }

}

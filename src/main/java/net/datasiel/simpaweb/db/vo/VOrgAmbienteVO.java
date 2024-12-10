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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.dao.VorgAmbienteDAO;

public class VOrgAmbienteVO extends VorgAmbienteDAO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VOrgAmbienteVO.class);

    public VOrgAmbienteVO() {
        super();
    }

    public static String getNomeAmbiente(Long idStrut, Connection con) throws SQLException {
        StringBuffer sb = new StringBuffer();
        String nomeAmbiente = "";
        sb.append("SELECT DISTINCT A.NM_AMBIENTE ");
        sb.append("FROM V_USR_IAM A ");
        sb.append("WHERE A.ID_STRUT     =? ");
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sb.toString());
            pst.setLong(1, idStrut);

            rs = pst.executeQuery();

            while (rs.next()) {
                nomeAmbiente = rs.getString("NM_AMBIENTE");
            }
        } catch (SQLException e) {
            log.error("Failed query: " + sb.toString());
            throw e;

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }

        return nomeAmbiente;
    }

}

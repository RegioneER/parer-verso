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

/**
 * V_dec_tipo_comp_doc
 *
 * WARNING! Automatically generated file!
 * Do not edit!
 * Code Generator by J.A.Carter
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;

import net.datasiel.simpaweb.db.dao.VDecTipoCompDocDAO;

public class VDecTipoCompDocVO extends VDecTipoCompDocDAO {
    private static final long serialVersionUID = 1L;

    public VDecTipoCompDocVO() {
        super();
    }

    public String getNmTipoCompDoc(Long idTipoCompDoc, Connection con) throws SQLException {
        String result = null;
        StringBuilder sQuery = new StringBuilder(
                "SELECT NM_TIPO_COMP_DOC FROM V_DEC_TIPO_COMP_DOC WHERE ID_TIPO_COMP_DOC=?");
        ResultSet r = null;
        java.sql.PreparedStatement st = null;
        try {
            st = con.prepareStatement(sQuery.toString());
            st.setLong(1, idTipoCompDoc);
            r = st.executeQuery();
            if (r.next()) {

                result = r.getString("NM_TIPO_COMP_DOC");

            }
        } finally {
            DbUtils.closeQuietly(r);
            DbUtils.closeQuietly(st);
        }

        return result;

    }
}

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

/**
 * VDecTipoStrutUnitaDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecTipoStrutUnitaDoc;

public class VDecTipoStrutUnitaDocDAO extends VDecTipoStrutUnitaDoc {

    private final Logger log = LoggerFactory.getLogger(VDecTipoStrutUnitaDocDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idTipoStrutUnitaDoc", "idTipoUnitaDoc", "nmTipoStrutUnitaDoc",
                "dsTipoStrutUnitaDoc", "dtIstituz", "dtSoppres" };
    }

    public VDecTipoStrutUnitaDocDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecTipoStrutUnitaDoc obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_TIPO_STRUT_UNITA_DOC") == null) {
            obj.setIdTipoStrutUnitaDoc(null);
        } else {
            obj.setIdTipoStrutUnitaDoc(r.getLong("ID_TIPO_STRUT_UNITA_DOC"));
        }
        if (r.getObject("ID_TIPO_UNITA_DOC") == null) {
            obj.setIdTipoUnitaDoc(null);
        } else {
            obj.setIdTipoUnitaDoc(r.getLong("ID_TIPO_UNITA_DOC"));
        }
        obj.setNmTipoStrutUnitaDoc(r.getString("NM_TIPO_STRUT_UNITA_DOC"));
        obj.setDsTipoStrutUnitaDoc(r.getString("DS_TIPO_STRUT_UNITA_DOC"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecTipoStrutUnitaDoc obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_TIPO_STRUT_UNITA_DOC ( ID_TIPO_STRUT_UNITA_DOC,ID_TIPO_UNITA_DOC,NM_TIPO_STRUT_UNITA_DOC,DS_TIPO_STRUT_UNITA_DOC,DT_ISTITUZ,DT_SOPPRES ) values (? ,? ,? ,? ,? ,?   )";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIdTipoStrutUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoStrutUnitaDoc());
        }
        if (obj.getIdTipoUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoUnitaDoc());
        }
        pst.setString(indice++, obj.getNmTipoStrutUnitaDoc());
        pst.setString(indice++, obj.getDsTipoStrutUnitaDoc());
        if (obj.getDtIstituz() != null) {
            pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getDtIstituz()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }
        if (obj.getDtSoppres() != null) {
            pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getDtSoppres()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        try {
            log.debug(prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + prepQuery);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }
}

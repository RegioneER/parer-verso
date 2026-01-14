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
 * VDecTipoCompDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecTipoCompDoc;

public class VDecTipoCompDocDAO extends VDecTipoCompDoc {

    private final Logger log = LoggerFactory.getLogger(VDecTipoCompDocDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idTipoCompDoc", "idTipoStrutDoc", "nmTipoCompDoc", "dsTipoCompDoc", "tiUsoCompDoc",
                "dtIstituz", "dtSoppres" };
    }

    public VDecTipoCompDocDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecTipoCompDoc obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_TIPO_COMP_DOC") == null) {
            obj.setIdTipoCompDoc(null);
        } else {
            obj.setIdTipoCompDoc(r.getLong("ID_TIPO_COMP_DOC"));
        }
        if (r.getObject("ID_TIPO_STRUT_DOC") == null) {
            obj.setIdTipoStrutDoc(null);
        } else {
            obj.setIdTipoStrutDoc(r.getLong("ID_TIPO_STRUT_DOC"));
        }
        obj.setNmTipoCompDoc(r.getString("NM_TIPO_COMP_DOC"));
        obj.setDsTipoCompDoc(r.getString("DS_TIPO_COMP_DOC"));
        obj.setTiUsoCompDoc(r.getString("TI_USO_COMP_DOC"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecTipoCompDoc obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_TIPO_COMP_DOC ( ID_TIPO_COMP_DOC,ID_TIPO_STRUT_DOC,NM_TIPO_COMP_DOC,DS_TIPO_COMP_DOC,TI_USO_COMP_DOC,DT_ISTITUZ,DT_SOPPRES ) values (? ,? ,? ,? ,? ,? ,?   )";
        
        
        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdTipoCompDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoCompDoc());
            }
            if (obj.getIdTipoStrutDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoStrutDoc());
            }
            pst.setString(indice++, obj.getNmTipoCompDoc());
            pst.setString(indice++, obj.getDsTipoCompDoc());
            pst.setString(indice++, obj.getTiUsoCompDoc());
            if (obj.getDtIstituz() != null) {
                pst.setObject(indice++, new java.sql.Date((obj.getDtIstituz()).getTime()));
            } else {
                pst.setObject(indice++, null);
            }
            if (obj.getDtSoppres() != null) {
                pst.setObject(indice++, new java.sql.Date((obj.getDtSoppres()).getTime()));
            } else {
                pst.setObject(indice++, null);
            }
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        } 
    }
}

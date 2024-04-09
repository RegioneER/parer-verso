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
 * VDecXsdAttribDatiSpec
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecXsdAttribDatiSpec;

public class VDecXsdAttribDatiSpecDAO extends VDecXsdAttribDatiSpec {

    private final Logger log = LoggerFactory.getLogger(VDecXsdAttribDatiSpecDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idXsdAttribDatiSpec", "idXsdDatiSpec", "idAttribDatiSpec", "niOrdAttrib" };
    }

    public VDecXsdAttribDatiSpecDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecXsdAttribDatiSpec obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_XSD_ATTRIB_DATI_SPEC") == null) {
            obj.setIdXsdAttribDatiSpec(null);
        } else {
            obj.setIdXsdAttribDatiSpec(r.getLong("ID_XSD_ATTRIB_DATI_SPEC"));
        }
        if (r.getObject("ID_XSD_DATI_SPEC") == null) {
            obj.setIdXsdDatiSpec(null);
        } else {
            obj.setIdXsdDatiSpec(r.getLong("ID_XSD_DATI_SPEC"));
        }
        if (r.getObject("ID_ATTRIB_DATI_SPEC") == null) {
            obj.setIdAttribDatiSpec(null);
        } else {
            obj.setIdAttribDatiSpec(r.getLong("ID_ATTRIB_DATI_SPEC"));
        }
        if (r.getObject("NI_ORD_ATTRIB") == null) {
            obj.setNiOrdAttrib(null);
        } else {
            obj.setNiOrdAttrib(r.getLong("NI_ORD_ATTRIB"));
        }
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecXsdAttribDatiSpec obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_XSD_ATTRIB_DATI_SPEC ( ID_XSD_ATTRIB_DATI_SPEC,ID_XSD_DATI_SPEC,ID_ATTRIB_DATI_SPEC,NI_ORD_ATTRIB ) values (? ,? ,? ,?   )";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIdXsdAttribDatiSpec() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdXsdAttribDatiSpec());
        }
        if (obj.getIdXsdDatiSpec() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdXsdDatiSpec());
        }
        if (obj.getIdAttribDatiSpec() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdAttribDatiSpec());
        }
        if (obj.getNiOrdAttrib() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getNiOrdAttrib());
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

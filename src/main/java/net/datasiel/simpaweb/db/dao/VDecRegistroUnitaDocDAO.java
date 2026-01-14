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
 * VDecRegistroUnitaDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecRegistroUnitaDoc;

public class VDecRegistroUnitaDocDAO extends VDecRegistroUnitaDoc {

    private final Logger log = LoggerFactory.getLogger(VDecRegistroUnitaDocDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idRegistroUnitaDoc", "idStrut", "cdRegistroUnitaDoc", "dsRegistroUnitaDoc",
                "dtIstituz", "dtSoppres", "idUserIam" };
    }

    public VDecRegistroUnitaDocDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecRegistroUnitaDoc obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_REGISTRO_UNITA_DOC") == null) {
            obj.setIdRegistroUnitaDoc(null);
        } else {
            obj.setIdRegistroUnitaDoc(r.getLong("ID_REGISTRO_UNITA_DOC"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        obj.setCdRegistroUnitaDoc(r.getString("CD_REGISTRO_UNITA_DOC"));
        obj.setDsRegistroUnitaDoc(r.getString("DS_REGISTRO_UNITA_DOC"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
        obj.setIdUserIam(r.getLong("ID_USER_IAM"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecRegistroUnitaDoc obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "INSERT INTO V_DEC_REGISTRO_UNITA_DOC (" + "ID_REGISTRO_UNITA_DOC, " + "ID_STRUT, "
                + "CD_REGISTRO_UNITA_DOC, " + "DS_REGISTRO_UNITA_DOC, " + "DT_ISTITUZ,DT_SOPPRES, " + "ID_USER_IAM"
                + ") values (?, ?, ?, ?, ?, ?, ?)";
        
                
        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdRegistroUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
            }
            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }
            pst.setString(indice++, obj.getCdRegistroUnitaDoc());
            pst.setString(indice++, obj.getDsRegistroUnitaDoc());
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
            pst.setLong(indice++, obj.getIdUserIam());
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        }
    }
}

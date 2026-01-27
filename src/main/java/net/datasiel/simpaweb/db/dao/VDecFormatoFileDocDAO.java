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
 * VDecFormatoFileDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecFormatoFileDoc;

public class VDecFormatoFileDocDAO extends VDecFormatoFileDoc {

    private final Logger log = LoggerFactory.getLogger(VDecFormatoFileDocDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idFormatoFileDoc", "idStrut", "nmFormatoFileDoc", "dsFormatoFileDoc", "cdVersione",
                "dtIstituz", "dtSoppres" };
    }

    public VDecFormatoFileDocDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecFormatoFileDoc obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_FORMATO_FILE_DOC") == null) {
            obj.setIdFormatoFileDoc(null);
        } else {
            obj.setIdFormatoFileDoc(r.getLong("ID_FORMATO_FILE_DOC"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        obj.setNmFormatoFileDoc(r.getString("NM_FORMATO_FILE_DOC"));
        obj.setDsFormatoFileDoc(r.getString("DS_FORMATO_FILE_DOC"));
        obj.setCdVersione(r.getString("CD_VERSIONE"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecFormatoFileDoc obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_FORMATO_FILE_DOC ( ID_FORMATO_FILE_DOC,ID_STRUT,NM_FORMATO_FILE_DOC,DS_FORMATO_FILE_DOC,CD_VERSIONE,DT_ISTITUZ,DT_SOPPRES ) values (? ,? ,? ,? ,? ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdFormatoFileDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileDoc());
            }
            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }
            pst.setString(indice++, obj.getNmFormatoFileDoc());
            pst.setString(indice++, obj.getDsFormatoFileDoc());
            pst.setString(indice++, obj.getCdVersione());
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

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
 * VDecFormatoFileBusta
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecFormatoFileBusta;

public class VDecFormatoFileBustaDAO extends VDecFormatoFileBusta {

    private final Logger log = LoggerFactory.getLogger(VDecFormatoFileBustaDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idFormatoFileBusta", "idFormatoFileStandard", "tiFormatoFirmaMarca" };
    }

    public VDecFormatoFileBustaDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecFormatoFileBusta obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_FORMATO_FILE_BUSTA") == null) {
            obj.setIdFormatoFileBusta(null);
        } else {
            obj.setIdFormatoFileBusta(r.getLong("ID_FORMATO_FILE_BUSTA"));
        }
        if (r.getObject("ID_FORMATO_FILE_STANDARD") == null) {
            obj.setIdFormatoFileStandard(null);
        } else {
            obj.setIdFormatoFileStandard(r.getLong("ID_FORMATO_FILE_STANDARD"));
        }
        obj.setTiFormatoFirmaMarca(r.getString("TI_FORMATO_FIRMA_MARCA"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecFormatoFileBusta obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_FORMATO_FILE_BUSTA ( ID_FORMATO_FILE_BUSTA,ID_FORMATO_FILE_STANDARD,TI_FORMATO_FIRMA_MARCA ) values (? ,? ,?   )";
        
        
        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdFormatoFileBusta() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileBusta());
            }
            if (obj.getIdFormatoFileStandard() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileStandard());
            }
            pst.setString(indice++, obj.getTiFormatoFirmaMarca());
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        } 
    }

}

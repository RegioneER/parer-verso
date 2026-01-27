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
  * VDecEstensioneFile
  *
  * WARNING! Automatically generated file!
  * Do not edit!
  */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecEstensioneFile;

public class VDecEstensioneFileDAO extends VDecEstensioneFile {

    private final Logger log = LoggerFactory.getLogger(VDecEstensioneFileDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;
    static {
        fieldNames = new String[] { "idEstensioneFile", "idFormatoFileStandard", "cdEstensioneFile" };
    }

    public VDecEstensioneFileDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecEstensioneFile obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_ESTENSIONE_FILE") == null) {
            obj.setIdEstensioneFile(null);
        } else {
            obj.setIdEstensioneFile(r.getLong("ID_ESTENSIONE_FILE"));
        }
        if (r.getObject("ID_FORMATO_FILE_STANDARD") == null) {
            obj.setIdFormatoFileStandard(null);
        } else {
            obj.setIdFormatoFileStandard(r.getLong("ID_FORMATO_FILE_STANDARD"));
        }
        obj.setCdEstensioneFile(r.getString("CD_ESTENSIONE_FILE"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecEstensioneFile obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_ESTENSIONE_FILE ( ID_ESTENSIONE_FILE,ID_FORMATO_FILE_STANDARD,CD_ESTENSIONE_FILE ) values (? ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdEstensioneFile() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdEstensioneFile());
            }
            if (obj.getIdFormatoFileStandard() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileStandard());
            }
            pst.setString(indice++, obj.getCdEstensioneFile());
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        }
    }

}

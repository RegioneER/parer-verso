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
 * VDecUsoFormatoFileStand
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecUsoFormatoFileStand;

public class VDecUsoFormatoFileStandDAO extends VDecUsoFormatoFileStand {

    private final Logger log = LoggerFactory.getLogger(VDecUsoFormatoFileStandDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idUsoFormatoFileAmmesso", "idFormatoFileDoc", "niOrdUso",
                "idFormatoFileStandard" };
    }

    public VDecUsoFormatoFileStandDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecUsoFormatoFileStand obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_USO_FORMATO_FILE_AMMESSO") == null) {
            obj.setIdUsoFormatoFileAmmesso(null);
        } else {
            obj.setIdUsoFormatoFileAmmesso(r.getLong("ID_USO_FORMATO_FILE_AMMESSO"));
        }
        if (r.getObject("ID_FORMATO_FILE_DOC") == null) {
            obj.setIdFormatoFileDoc(null);
        } else {
            obj.setIdFormatoFileDoc(r.getLong("ID_FORMATO_FILE_DOC"));
        }
        if (r.getObject("NI_ORD_USO") == null) {
            obj.setNiOrdUso(null);
        } else {
            obj.setNiOrdUso(r.getLong("NI_ORD_USO"));
        }
        if (r.getObject("ID_FORMATO_FILE_STANDARD") == null) {
            obj.setIdFormatoFileStandard(null);
        } else {
            obj.setIdFormatoFileStandard(r.getLong("ID_FORMATO_FILE_STANDARD"));
        }
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecUsoFormatoFileStand obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_USO_FORMATO_FILE_STAND ( ID_USO_FORMATO_FILE_AMMESSO,ID_FORMATO_FILE_DOC,NI_ORD_USO,ID_FORMATO_FILE_STANDARD ) values (? ,? ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdUsoFormatoFileAmmesso() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdUsoFormatoFileAmmesso());
            }
            if (obj.getIdFormatoFileDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileDoc());
            }
            if (obj.getNiOrdUso() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getNiOrdUso());
            }
            if (obj.getIdFormatoFileStandard() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileStandard());
            }
            log.debug(prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + prepQuery);
            throw e;
        }
    }
}

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
 * VDecFormatoFileAmmesso
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecFormatoFileAmmesso;

public class VDecFormatoFileAmmessoDAO extends VDecFormatoFileAmmesso {

    private final Logger log = LoggerFactory.getLogger(VDecFormatoFileAmmessoDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idFormatoFileAmmesso", "idTipoCompDoc", "idFormatoFileDoc" };
    }

    public VDecFormatoFileAmmessoDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecFormatoFileAmmesso obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_FORMATO_FILE_AMMESSO") == null) {
            obj.setIdFormatoFileAmmesso(null);
        } else {
            obj.setIdFormatoFileAmmesso(r.getLong("ID_FORMATO_FILE_AMMESSO"));
        }
        if (r.getObject("ID_TIPO_COMP_DOC") == null) {
            obj.setIdTipoCompDoc(null);
        } else {
            obj.setIdTipoCompDoc(r.getLong("ID_TIPO_COMP_DOC"));
        }
        if (r.getObject("ID_FORMATO_FILE_DOC") == null) {
            obj.setIdFormatoFileDoc(null);
        } else {
            obj.setIdFormatoFileDoc(r.getLong("ID_FORMATO_FILE_DOC"));
        }
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecFormatoFileAmmesso obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_FORMATO_FILE_AMMESSO ( ID_FORMATO_FILE_AMMESSO,ID_TIPO_COMP_DOC,ID_FORMATO_FILE_DOC ) values (? ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdFormatoFileAmmesso() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileAmmesso());
            }
            if (obj.getIdTipoCompDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoCompDoc());
            }
            if (obj.getIdFormatoFileDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileDoc());
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

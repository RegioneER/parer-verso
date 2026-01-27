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
 * VDecTipoUnitaDocAmmesso
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecTipoUnitaDocAmmesso;

public class VDecTipoUnitaDocAmmessoDAO extends VDecTipoUnitaDocAmmesso {

    private final Logger log = LoggerFactory.getLogger(VDecTipoUnitaDocAmmessoDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idTipoUnitaDocAmmesso", "idTipoUnitaDoc", "idRegistroUnitaDoc" };
    }

    public VDecTipoUnitaDocAmmessoDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecTipoUnitaDocAmmesso obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_TIPO_UNITA_DOC_AMMESSO") == null) {
            obj.setIdTipoUnitaDocAmmesso(null);
        } else {
            obj.setIdTipoUnitaDocAmmesso(r.getLong("ID_TIPO_UNITA_DOC_AMMESSO"));
        }
        if (r.getObject("ID_TIPO_UNITA_DOC") == null) {
            obj.setIdTipoUnitaDoc(null);
        } else {
            obj.setIdTipoUnitaDoc(r.getLong("ID_TIPO_UNITA_DOC"));
        }
        if (r.getObject("ID_REGISTRO_UNITA_DOC") == null) {
            obj.setIdRegistroUnitaDoc(null);
        } else {
            obj.setIdRegistroUnitaDoc(r.getLong("ID_REGISTRO_UNITA_DOC"));
        }
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecTipoUnitaDocAmmesso obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_TIPO_UNITA_DOC_AMMESSO ( ID_TIPO_UNITA_DOC_AMMESSO,ID_TIPO_UNITA_DOC,ID_REGISTRO_UNITA_DOC ) values (? ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdTipoUnitaDocAmmesso() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoUnitaDocAmmesso());
            }
            if (obj.getIdTipoUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoUnitaDoc());
            }
            if (obj.getIdRegistroUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
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

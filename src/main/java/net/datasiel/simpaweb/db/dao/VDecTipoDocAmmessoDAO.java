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
 * VDecTipoDocAmmesso
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecTipoDocAmmesso;

public class VDecTipoDocAmmessoDAO extends VDecTipoDocAmmesso {

    private final Logger log = LoggerFactory.getLogger(VDecTipoDocAmmessoDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idTipoDocAmmesso", "idTipoStrutUnitaDoc", "idTipoDoc", "tiDoc", "flObbl" };
    }

    public VDecTipoDocAmmessoDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecTipoDocAmmesso obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_TIPO_DOC_AMMESSO") == null) {
            obj.setIdTipoDocAmmesso(null);
        } else {
            obj.setIdTipoDocAmmesso(r.getLong("ID_TIPO_DOC_AMMESSO"));
        }
        if (r.getObject("ID_TIPO_STRUT_UNITA_DOC") == null) {
            obj.setIdTipoStrutUnitaDoc(null);
        } else {
            obj.setIdTipoStrutUnitaDoc(r.getLong("ID_TIPO_STRUT_UNITA_DOC"));
        }
        if (r.getObject("ID_TIPO_DOC") == null) {
            obj.setIdTipoDoc(null);
        } else {
            obj.setIdTipoDoc(r.getLong("ID_TIPO_DOC"));
        }
        obj.setTiDoc(r.getString("TI_DOC"));
        obj.setFlObbl(r.getString("FL_OBBL"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecTipoDocAmmesso obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_TIPO_DOC_AMMESSO ( ID_TIPO_DOC_AMMESSO,ID_TIPO_STRUT_UNITA_DOC,ID_TIPO_DOC,TI_DOC,FL_OBBL ) values (? ,? ,? ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdTipoDocAmmesso() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoDocAmmesso());
            }
            if (obj.getIdTipoStrutUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoStrutUnitaDoc());
            }
            if (obj.getIdTipoDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoDoc());
            }
            pst.setString(indice++, obj.getTiDoc());
            pst.setString(indice++, obj.getFlObbl());
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        }
    }
}

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
 * VDecTipoUnitaDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecTipoUnitaDoc;

public class VDecTipoUnitaDocDAO extends VDecTipoUnitaDoc {

    private final Logger log = LoggerFactory.getLogger(VDecTipoUnitaDocDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idTipoUnitaDoc", "idStrut", "nmTipoUnitaDoc", "dsTipoUnitaDoc", "cdSerie",
                "flForzaCollegamento", "tiCalcOrd", "dtIstituz", "dtSoppres", "idUserIam" };
    }

    public VDecTipoUnitaDocDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecTipoUnitaDoc obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_TIPO_UNITA_DOC") == null) {
            obj.setIdTipoUnitaDoc(null);
        } else {
            obj.setIdTipoUnitaDoc(r.getLong("ID_TIPO_UNITA_DOC"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        obj.setNmTipoUnitaDoc(r.getString("NM_TIPO_UNITA_DOC"));
        obj.setDsTipoUnitaDoc(r.getString("DS_TIPO_UNITA_DOC"));
        obj.setCdSerie(r.getString("CD_SERIE"));
        obj.setFlForzaCollegamento(r.getString("FL_FORZA_COLLEGAMENTO"));
        obj.setTiCalcOrd(r.getString("TI_CALC_ORD"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
        obj.setIdUserIam(r.getLong("ID_USER_IAM"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecTipoUnitaDoc obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "INSERT INTO V_DEC_TIPO_UNITA_DOC (" + "ID_TIPO_UNITA_DOC, " + "ID_STRUT, "
                + "NM_TIPO_UNITA_DOC, " + "DS_TIPO_UNITA_DOC, " + "CD_SERIE, " + "FL_FORZA_COLLEGAMENTO, "
                + "TI_CALC_ORD, " + "DT_ISTITUZ,DT_SOPPRES, " + "ID_USER_IAM"
                + ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIdTipoUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoUnitaDoc());
        }
        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }
        pst.setString(indice++, obj.getNmTipoUnitaDoc());
        pst.setString(indice++, obj.getDsTipoUnitaDoc());
        pst.setString(indice++, obj.getCdSerie());
        pst.setString(indice++, obj.getFlForzaCollegamento());
        pst.setString(indice++, obj.getTiCalcOrd());
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

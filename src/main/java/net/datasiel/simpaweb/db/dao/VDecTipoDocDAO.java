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
 * VDecTipoDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecTipoDoc;

public class VDecTipoDocDAO extends VDecTipoDoc {

    private final Logger log = LoggerFactory.getLogger(VDecTipoDocDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idTipoDoc", "idStrut", "nmTipoDoc", "dsTipoDoc", "flTipoDocPrincipale",
                "flTipoDocFisc", "cdPeriodoFiscIniVers", "cdRegistroFiscIniVers", "niProgrFiscIniVers", "dtIstituz",
                "dtSoppres", "idUserIam" };
    }

    public VDecTipoDocDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecTipoDoc obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_TIPO_DOC") == null) {
            obj.setIdTipoDoc(null);
        } else {
            obj.setIdTipoDoc(r.getLong("ID_TIPO_DOC"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        obj.setNmTipoDoc(r.getString("NM_TIPO_DOC"));
        obj.setDsTipoDoc(r.getString("DS_TIPO_DOC"));
        obj.setFlTipoDocPrincipale(r.getString("FL_TIPO_DOC_PRINCIPALE"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
        obj.setIdUserIam(r.getLong("ID_USER_IAM"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecTipoDoc obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "INSERT INTO V_DEC_TIPO_DOC (" + "ID_TIPO_DOC, " + "ID_STRUT, " + "NM_TIPO_DOC, "
                + "DS_TIPO_DOC, " + "FL_TIPO_DOC_PRINCIPALE, " + "FL_TIPO_DOC_FISC, " + "CD_PERIODO_FISC_INI_VERS, "
                + "CD_REGISTRO_FISC_INI_VERS, " + "NI_PROGR_FISC_INI_VERS, " + "DT_ISTITUZ,DT_SOPPRES, " + "ID_USER_IAM"
                + ") values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
                
        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdTipoDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoDoc());
            }
            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }
            pst.setString(indice++, obj.getNmTipoDoc());
            pst.setString(indice++, obj.getDsTipoDoc());
            pst.setString(indice++, obj.getFlTipoDocPrincipale());
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
            log.error("Failed query: {}", prepQuery);
            throw e;
        } 
    }
}

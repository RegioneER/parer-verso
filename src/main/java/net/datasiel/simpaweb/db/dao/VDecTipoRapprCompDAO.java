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
 * VDecTipoRapprComp
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VDecTipoRapprComp;

public class VDecTipoRapprCompDAO extends VDecTipoRapprComp {

    private final Logger log = LoggerFactory.getLogger(VDecTipoRapprCompDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idTipoRapprComp", "idStrut", "nmTipoRapprComp", "dsTipoRapprComp", "dtIstituz",
                "dtSoppres" };
    }

    public VDecTipoRapprCompDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VDecTipoRapprComp obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_TIPO_RAPPR_COMP") == null) {
            obj.setIdTipoRapprComp(null);
        } else {
            obj.setIdTipoRapprComp(r.getLong("ID_TIPO_RAPPR_COMP"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        obj.setNmTipoRapprComp(r.getString("NM_TIPO_RAPPR_COMP"));
        obj.setDsTipoRapprComp(r.getString("DS_TIPO_RAPPR_COMP"));
        obj.setDtIstituz(r.getDate("DT_ISTITUZ"));
        obj.setDtSoppres(r.getDate("DT_SOPPRES"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VDecTipoRapprComp obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_DEC_TIPO_RAPPR_COMP ( ID_TIPO_RAPPR_COMP,ID_STRUT,NM_TIPO_RAPPR_COMP,DS_TIPO_RAPPR_COMP,DT_ISTITUZ,DT_SOPPRES ) values (? ,? ,? ,? ,? ,?   )";
        
        
        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdTipoRapprComp() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoRapprComp());
            }
            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }
            pst.setString(indice++, obj.getNmTipoRapprComp());
            pst.setString(indice++, obj.getDsTipoRapprComp());
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

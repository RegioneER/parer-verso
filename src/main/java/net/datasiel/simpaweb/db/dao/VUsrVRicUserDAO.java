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
 * VUsrVRicUser
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.ResultSet;
import java.sql.SQLException;

import net.datasiel.simpaweb.db.pojo.VUsrVRicUser;

public class VUsrVRicUserDAO extends VUsrVRicUser {

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idUser", "nmCognomeUser", "nmNomeUser", "flAttivo", "nmUserid", "idApplic",
                "idRuolo", "nmRuolo", "idAmbiente", "idEnte", "idStrut", "nmStrut" };
    }

    public VUsrVRicUserDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VUsrVRicUser obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_USER") == null) {
            obj.setIdUser(null);
        } else {
            obj.setIdUser(r.getLong("ID_USER"));
        }
        obj.setNmCognomeUser(r.getString("NM_COGNOME_USER"));
        obj.setNmNomeUser(r.getString("NM_NOME_USER"));
        obj.setFlAttivo(r.getString("FL_ATTIVO"));
        obj.setNmUserid(r.getString("NM_USERID"));

        obj.setNmStrut(r.getString("NM_STRUT"));

        obj.setCd_psw(r.getString("CD_PSW"));
        obj.setCd_salt(r.getString("CD_SALT"));

        if (r.getObject("DT_SCAD_PSW") == null) {
            obj.setDt_scad_psw(null);
        } else {
            obj.setDt_scad_psw(r.getDate("DT_SCAD_PSW"));
        }
    }
}

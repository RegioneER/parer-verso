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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.datasiel.simpaweb.db.dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.SLLogLoginUser;

/**
 *
 * @author fioravanti_f
 */
public class SLLogLoginUserDAO extends SLLogLoginUser {

    private static final long serialVersionUID = 1L;

    private final Logger log = LoggerFactory.getLogger(SLLogLoginUserDAO.class);

    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idLoginUser", "idApplic", "nmUserid", "cdIndIpClient", "cdIndServer", "tipoEvento",
                "dtEvento", "dtVirtualEvento" };
    }

    public SLLogLoginUserDAO() {
        super();
    }

    public int insertPrepared(SLLogLoginUser obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "Insert into sacer_log.LOG_LOGIN_USER (" + "ID_LOGIN_USER,ID_APPLIC,NM_USERID,"
                + "CD_IND_IP_CLIENT,CD_IND_SERVER,DT_EVENTO,TIPO_EVENTO, CD_ID_ESTERNO, TIPO_UTENTE_AUTH)"
                + "values (?,?,?,?,?,sysdate,?,?,?)";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        // campo obbligatorio, inutile gestire il caso in cui questo valore è null
        pst.setLong(indice++, obj.getIdLoginUser());
        // campo obbligatorio, inutile gestire il caso in cui questo valore è null
        pst.setLong(indice++, obj.getIdApplic());
        pst.setString(indice++, obj.getNmUserid());
        pst.setString(indice++, obj.getCdIndIpClient());
        pst.setString(indice++, obj.getCdIndServer());
        pst.setString(indice++, obj.getTipoEvento());
        pst.setString(indice++, obj.getCdIdEsterno());
        pst.setString(indice++, obj.getTipoUtenteAuth());

        try {
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }

}

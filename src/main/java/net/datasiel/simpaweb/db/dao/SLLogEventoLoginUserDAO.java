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

import java.sql.Connection;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.SLLogEventoLoginUser;
import net.datasiel.simpaweb.db.pojo.SLLogLoginUser;

/**
 *
 * @author fioravanti_f
 */
public class SLLogEventoLoginUserDAO extends SLLogLoginUser {

    private static final long serialVersionUID = 1L;

    private final Logger log = LoggerFactory.getLogger(SLLogEventoLoginUserDAO.class);

    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idEventoLoginUser", "nmUserid", "cdIndIpClient", "cdIndServer", "dtEvento",
                "tipoEvento", "dsEvento", "nmNomeUser", "nmCognomeUser", "cdFiscUser", "dsEmailUser", "cdIdEsterno" };
    }

    public SLLogEventoLoginUserDAO() {
        super();
    }

    public int insertPrepared(SLLogEventoLoginUser obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "Insert into sacer_log.LOG_EVENTO_LOGIN_USER (ID_EVENTO_LOGIN_USER, NM_USERID,"
                + "CD_IND_IP_CLIENT, CD_IND_SERVER, DT_EVENTO, TIPO_EVENTO, "
                + "DS_EVENTO, nm_nome_user, nm_cognome_user, cd_fisc_user, " + "ds_email_user, CD_ID_ESTERNO)"
                + "values (?,?, ?,?,sysdate,?, ?,?,?,?, ?,?)";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        // campo obbligatorio, inutile gestire il caso in cui questo valore è null
        pst.setLong(indice++, obj.getIdEventoLoginUser());
        // campo obbligatorio, inutile gestire il caso in cui questo valore è null
        pst.setString(indice++, obj.getNmUserid());
        pst.setString(indice++, obj.getCdIndIpClient());
        pst.setString(indice++, obj.getCdIndServer());
        pst.setString(indice++, obj.getTipoEvento());
        pst.setString(indice++, obj.getDsEvento());
        pst.setString(indice++, obj.getNmNomeUser());
        pst.setString(indice++, obj.getNmCognomeUser());
        pst.setString(indice++, obj.getCdFiscUser());
        pst.setString(indice++, obj.getDsEmailUser());
        pst.setString(indice++, obj.getCdIDEsterno());

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

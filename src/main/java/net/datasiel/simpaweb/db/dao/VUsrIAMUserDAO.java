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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * VUsrVRicUser
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import it.eng.spagoLite.security.auth.PwdUtil;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.pojo.VUsrVRicUser;

public class VUsrIAMUserDAO extends VUsrVRicUser {

    private final Logger log = LoggerFactory.getLogger(VUsrIAMUserDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {

        fieldNames = new String[] { "idUser", "nmCognomeUser", "nmNomeUser", "flAttivo", "nmUserid", "nmAmbiente",
                "nmEnte", "idStrut", "nmStrut" };
    }

    public VUsrIAMUserDAO() {
        super();
    }

    public String isUserAuthenticated(String nmUserId, String password, Connection con) throws SQLException {
        String result = null;
        boolean isEquals = false;
        String codedPassw = null;

        String sQuery = "SELECT DISTINCT CD_PWD, CD_SALT " + "FROM V_USR_IAM " + "WHERE NM_USERID=? "
                + "AND FL_ATTIVO='1'";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(sQuery)) {
            st.setString(1, nmUserId);
            log.debug(sQuery + " - [" + nmUserId + "]");
            r = st.executeQuery();
            ParUnitadoc obj = null;
            if (r.next()) {
                String cdPwd = r.getString("CD_PWD");
                String cdSalt = r.getString("CD_SALT");
                if (cdSalt != null) {
                    byte[] decodeUFT8Base64String = PwdUtil.decodeUFT8Base64String(cdSalt);
                    codedPassw = PwdUtil.encodePBKDF2Password(decodeUFT8Base64String, password);

                } else {
                    codedPassw = PwdUtil.encodePassword(password);
                }

                result = cdPwd.equals(codedPassw) ? password : null;
            }
        } finally {
            if (r != null) {
                r.close();
            }
        }

        return result;
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VUsrVRicUser obj, ResultSet r) throws SQLException {
        /*
         * ID_USER_IAM, NM_COGNOME_USER, NM_NOME_USER, FL_ATTIVO, NM_USERID, CD_PWD, CD_SALT, NM_AMBIENTE, NM_ENTE,
         * ID_STRUT, NM_STRUT
         */
        if (r.getObject("ID_USER_IAM") == null) {
            obj.setIdUser(null);
        } else {
            obj.setIdUser(r.getLong("ID_USER_IAM"));
        }
        obj.setNmCognomeUser(r.getString("NM_COGNOME_USER"));
        obj.setNmNomeUser(r.getString("NM_NOME_USER"));
        obj.setFlAttivo(r.getString("FL_ATTIVO"));
        obj.setNmUserid(r.getString("NM_USERID"));
        obj.setNmAmbiente(r.getString("NM_AMBIENTE"));
        obj.setNmEnte(r.getString("NM_ENTE"));

        obj.setNmStrut(r.getString("NM_STRUT"));
    }
}

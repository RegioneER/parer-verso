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

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParConfigurazioneDAO implements Serializable {

    private static final long serialVersionUID = 0L;
    private final Logger log = LoggerFactory.getLogger(ParConfigurazioneDAO.class);

    static final String COL_VALORE = "VALORE";
    static final String COL_CHIAVE = " CHIAVE";

    /**
     * Retrieve from the database for table "PAR_COLLEGAMENTO"
     */
    public String retrieveByChiave(String chiave, Connection con) throws SQLException {
        String query = "select * from PAR_CONFIGURAZIONE" + " where " + COL_CHIAVE + "=?";
        try (java.sql.PreparedStatement st = con.prepareStatement(query);) {
            st.setString(1, chiave);
            String value = null;
            log.debug(query);
            try (ResultSet r = st.executeQuery();) {
                if (r.next()) {
                    if (r.getObject(COL_VALORE) != null) {
                        value = r.getString(COL_VALORE);
                    }
                    if (r.next()) {
                        throw new IllegalStateException(
                                "Trovato piu' di un  records in PAR_CONFIGURAZIONE con " + COL_CHIAVE + " " + chiave);
                    }
                } else {
                    // no records
                    throw new IllegalStateException(
                            "Non ci sono  record in PAR_CONFIGURAZIONE con CHIAVE " + COL_CHIAVE + " " + chiave);
                }

                return value;
            }
        }
    }
}

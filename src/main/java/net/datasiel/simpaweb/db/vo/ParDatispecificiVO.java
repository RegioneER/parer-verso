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

package net.datasiel.simpaweb.db.vo;

/**
 * Par_datispecifici
 *
 * WARNING! Automatically generated file! Do not edit! Code Generator by
 * J.A.Carter
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.beans.EnumEntitaDatiSpecifici;
import net.datasiel.simpaweb.db.DbConstants;
import net.datasiel.simpaweb.db.dao.DbUtil;
import net.datasiel.simpaweb.db.dao.ParDatispecificiDAO;
import net.datasiel.simpaweb.db.pojo.ParDatispecifici;

public class ParDatispecificiVO extends ParDatispecificiDAO {

    private static final long serialVersionUID = 1L;
    private Logger log = LoggerFactory.getLogger(ParDatispecificiVO.class);

    public ParDatispecificiVO() {
        super();
    }

    public long loadOrCreateDatiSpecifici(long idEntita, EnumEntitaDatiSpecifici entita, Connection connection)
            throws SQLException {

        StringBuilder sb = new StringBuilder();
        sb.append(" select iddatispecifici ");
        sb.append(" from PAR_DATISPECIFICI ");
        sb.append(" where ");
        sb.append(entita.getColumnNameForXsd());
        sb.append(" = ? ");
        sb.append(" and ENTITASACER = ? ");

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            int index = 1;
            pst = connection.prepareStatement(sb.toString());
            pst.setLong(index++, idEntita);
            pst.setString(index++, entita.name());

            rs = pst.executeQuery();

            if (rs.next()) {
                return rs.getLong(1);
            }
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }

        log.debug("Record PAR_DATISPECIFICI non trovato: lo inseriamo");
        ParDatispecifici parDs = new ParDatispecifici();
        Long idDatiSpecifici = DbUtil.getSequenceValue(DbConstants.SEQ_ID_GENERALI, connection);
        parDs.setIddatispecifici(idDatiSpecifici);
        switch (entita) {
        case UNI_DOC:
            parDs.setIdunitadoc(idEntita);
            break;
        case DOC:
            parDs.setIddocumento(idEntita);
            break;
        case COMP:
        case SUB_COMP:
            parDs.setIdcomponente(idEntita);
            break;
        default:
            throw new IllegalStateException("Entita non riconosciuta:" + entita);
        }
        parDs.setEntitasacer(entita.name());
        parDs.setPgm(this.getClass().getSimpleName());
        parDs.setId(0L);
        parDs.setFlgstato(0L);
        insertPrepared(parDs, connection);

        return idDatiSpecifici;
    }

    public ParDatispecifici getDatiSpecUnitaDoc(Long idunitadoc, String tipoEntitaSacer, Connection connection)
            throws SQLException {

        // String query = "select * from PAR_DATISPECIFICI"+" where IDDATISPECIFICI="+iddatispecifici;
        String query = "select * from PAR_DATISPECIFICI " + " where IDUNITADOC=? " + " and entitasacer=?";
        // java.sql.Statement st = con.createStatement();
        java.sql.PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, idunitadoc);
        st.setString(2, tipoEntitaSacer);
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery();
            ParDatispecifici obj = null;
            if (r.next()) {
                obj = new ParDatispecifici();
                getFromResultSet(obj, r);
            }
            return obj;
        } finally {
            DbUtils.closeQuietly(r);
            DbUtils.closeQuietly(st);

        }
    }

    public ParDatispecifici getDatiSpecDoc(Long iddoc, String tipoEntitaSacer, Connection connection)
            throws SQLException {

        // String query = "select * from PAR_DATISPECIFICI"+" where IDDATISPECIFICI="+iddatispecifici;
        String query = "select * from PAR_DATISPECIFICI " + " where IDDOCUMENTO=? " + " and entitasacer=?";
        // java.sql.Statement st = con.createStatement();
        java.sql.PreparedStatement st = connection.prepareStatement(query);
        st.setLong(1, iddoc);
        st.setString(2, tipoEntitaSacer);
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery();
            ParDatispecifici obj = null;
            if (r.next()) {
                obj = new ParDatispecifici();
                getFromResultSet(obj, r);
            }
            return obj;
        } finally {
            DbUtils.closeQuietly(r);
            DbUtils.closeQuietly(st);
        }
    }

}

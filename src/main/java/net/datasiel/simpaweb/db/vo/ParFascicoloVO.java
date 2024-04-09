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
 * Par_fascicolo
 *
 * WARNING! Automatically generated file! Do not edit! Code Generator by
 * J.A.Carter
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.dao.ParFascicoloDAO;
import net.datasiel.simpaweb.db.pojo.ParFascicolo;

public class ParFascicoloVO extends ParFascicoloDAO {

    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(ParFascicoloVO.class);

    public ParFascicoloVO() {
        super();
    }

    public ParFascicolo getFascicoloPrincipale(Long idUnitaDoc, Connection con) throws SQLException {
        StringBuffer sb = new StringBuffer();

        sb.append("select * ");
        sb.append("from PAR_FASCICOLO ");
        sb.append("where IDUNITADOC = ? ");
        sb.append("and CODTIPOFASCICOLO = ? ");

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            int index = 1;
            pst = con.prepareStatement(sb.toString());
            pst.setLong(index++, idUnitaDoc);
            pst.setString(index++, "P");
            log.debug(sb.toString());
            ResultSet r = pst.executeQuery();
            ParFascicolo obj = null;
            if (r.next()) {
                obj = new ParFascicolo();
                getFromResultSet(obj, r);
            }
            return obj;
        } catch (SQLException e) {
            log.error("Failed query: " + sb.toString());
            throw e;

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }

    }

    public List<ParFascicolo> getSecondari(Long idunitadoc, Connection con) throws SQLException {

        java.util.List<ParFascicolo> retRows = new java.util.ArrayList<ParFascicolo>();
        ParFascicolo curRow;
        StringBuffer sb = new StringBuffer();

        sb.append("select * from PAR_FASCICOLO ");
        sb.append("where IDUNITADOC = ? ");
        sb.append("and CODTIPOFASCICOLO = ? ");

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            int index = 1;
            pst = con.prepareStatement(sb.toString());
            pst.setLong(index++, idunitadoc);
            pst.setString(index++, "S");
            log.debug(sb.toString());
            ResultSet r = pst.executeQuery();
            while (r.next()) {
                curRow = new ParFascicolo();
                getFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } catch (SQLException e) {
            log.error("Query fallita: " + sb.toString());
            throw e;
        } finally {
            DbUtils.closeQuietly(pst);
            DbUtils.closeQuietly(rs);
        }

    }

}

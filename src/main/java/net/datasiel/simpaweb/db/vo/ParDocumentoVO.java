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
 * Par_documento
 *
 * WARNING! Automatically generated file! Do not edit! Code Generator by
 * J.A.Carter
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.dao.ParDocumentoDAO;
import net.datasiel.simpaweb.db.pojo.ParDocumento;

public class ParDocumentoVO extends ParDocumentoDAO {

    /**
     *
     */
    private static final long serialVersionUID = 2825033284949938331L;
    private final Logger log = LoggerFactory.getLogger(ParDocumentoVO.class);

    public ParDocumentoVO() {
        super();
    }

    public ParDocumentoVO getDocPrincipale(Long idUnitaDoc, Connection con) throws SQLException {
        StringBuffer query = new StringBuffer();
        query.append(" select * ");
        query.append(" from PAR_DOCUMENTO ");
        query.append(" where idunitadoc = ? ");
        query.append(" and tipologia = 'PRINC' ");

        PreparedStatement pst = con.prepareStatement(query.toString());
        pst.setLong(1, idUnitaDoc);
        ResultSet r = null;
        try {
            log.info(query + "[" + idUnitaDoc + "]");
            // ResultSet r = st.executeQuery(query);
            r = pst.executeQuery();
            ParDocumentoVO obj = null;
            if (r.next()) {
                obj = new ParDocumentoVO();
                getFromResultSet(obj, r);
            }
            return obj;
        } finally {
            if (r != null) {
                r.close();
            }
            if (pst != null) {
                pst.close();
            }
        }
    }

    /**
     * Estrae i documenti in base alla tipologia
     */
    public java.util.List<ParDocumento> getListadocumenti(Long idunitadoc, String tipodoc, Connection con)
            throws SQLException {
        java.util.List<ParDocumento> retRows = new java.util.ArrayList<ParDocumento>();
        ParDocumento curRow;
        StringBuffer query = new StringBuffer();
        query.append(" select * ");
        query.append(" from PAR_DOCUMENTO ");
        query.append(" where idunitadoc = ? ");
        query.append(" and tipologia = ? ");
        PreparedStatement pst = con.prepareStatement(query.toString());
        pst.setLong(1, idunitadoc);
        pst.setString(2, tipodoc);
        ResultSet r = null;
        try {
            log.info(query + "[" + idunitadoc + "," + tipodoc + "]");
            r = pst.executeQuery();
            while (r.next()) {
                curRow = new ParDocumento();
                getFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } finally {
            if (r != null) {
                r.close();
            }
            if (pst != null) {
                pst.close();
            }
        }
    }

}

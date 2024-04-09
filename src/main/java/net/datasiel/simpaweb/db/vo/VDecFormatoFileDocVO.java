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
 * V_dec_formato_file_doc
 *
 * WARNING! Automatically generated file! Do not edit! Code Generator by
 * J.A.Carter
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.dao.VDecFormatoFileDocDAO;

public class VDecFormatoFileDocVO extends VDecFormatoFileDocDAO {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VDecFormatoFileDocVO.class);

    public VDecFormatoFileDocVO() {
        super();
    }

    private String dsFormato;
    private String nmFormato;

    public static List<VDecFormatoFileDocVO> getFormatiAmmessi(Long idStrut, Connection con) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("select VF.ID_FORMATO_FILE_DOC, VF.NM_FORMATO_FILE_DOC, ");
        sb.append("	   VF.DS_FORMATO_FILE_DOC ");
        sb.append("from V_DEC_FORMATO_FILE_AMMESSO V ");
        sb.append("join V_DEC_TIPO_COMP_DOC TC on V.ID_TIPO_COMP_DOC = TC.ID_TIPO_COMP_DOC ");
        sb.append("join V_DEC_TIPO_STRUT_DOC TS on TC.ID_TIPO_STRUT_DOC = TS.ID_TIPO_STRUT_DOC ");
        sb.append("join V_DEC_FORMATO_FILE_DOC VF on V.ID_FORMATO_FILE_DOC = VF.ID_FORMATO_FILE_DOC ");
        sb.append("where ts.ID_STRUT = ? ");
        sb.append("and TC.TI_USO_COMP_DOC = ? ");

        List<VDecFormatoFileDocVO> resultList = new ArrayList<VDecFormatoFileDocVO>();
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sb.toString());
            pst.setLong(1, idStrut);
            pst.setString(2, "CONTENUTO");

            rs = pst.executeQuery();
            while (rs.next()) {
                VDecFormatoFileDocVO riga = new VDecFormatoFileDocVO();
                riga.setIdFormatoFileDoc(rs.getLong("ID_FORMATO_FILE_DOC"));
                riga.setNmFormato(rs.getString("NM_FORMATO_FILE_DOC"));
                riga.setDsFormato(rs.getString("DS_FORMATO_FILE_DOC"));
                resultList.add(riga);
            }
        } catch (SQLException e) {
            log.error("Failed query: {}", sb);
            throw e;

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }
        return resultList;
    }

    /**
     * @return the dsFormato
     */
    public String getDsFormato() {
        return dsFormato;
    }

    /**
     * @param dsFormato
     *            the dsFormato to set
     */
    public void setDsFormato(String dsFormato) {
        this.dsFormato = dsFormato;
    }

    /**
     * @return the nmFormato
     */
    public String getNmFormato() {
        return nmFormato;
    }

    /**
     * @param nmFormato
     *            the nmFormato to set
     */
    public void setNmFormato(String nmFormato) {
        this.nmFormato = nmFormato;
    }

    public String decodeFormato(Long idFormatoFileDoc, Connection con) throws SQLException {
        String nomeFormatoFile = null;
        StringBuffer sb = new StringBuffer();
        sb.append("select 	NM_FORMATO_FILE_DOC ");
        sb.append("from 	V_DEC_FORMATO_FILE_DOC ");
        sb.append("where 	ID_FORMATO_FILE_DOC = ? ");
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sb.toString());
            pst.setLong(1, idFormatoFileDoc);
            rs = pst.executeQuery();
            if (rs.next()) {
                nomeFormatoFile = rs.getString("NM_FORMATO_FILE_DOC");
            }
        } catch (SQLException e) {
            log.error("Failed query: " + sb.toString());
            throw e;
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }
        return nomeFormatoFile;
    }

}

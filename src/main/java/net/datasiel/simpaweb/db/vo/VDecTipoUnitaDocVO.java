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
 * V_dec_tipo_unita_doc
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

import net.datasiel.simpaweb.db.dao.VDecTipoUnitaDocDAO;

public class VDecTipoUnitaDocVO extends VDecTipoUnitaDocDAO {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(VDecTipoUnitaDocVO.class);

    public VDecTipoUnitaDocVO() {
        super();
    }

    // NON DOVREBBE ESSERE PIU' USATA
    //
    // public static DefaultSelectionProvider getTipiUnitaDoc(Long idStruttura, Connection con)
    // throws SQLException {
    // StringBuffer prepQuery = new StringBuffer();
    // prepQuery.append(" select id_tipo_unita_doc, nm_tipo_unita_doc ");
    // prepQuery.append(" from v_dec_tipo_unita_doc ");
    // if (idStruttura != null) {
    // prepQuery.append( " where id_strut = ? ");
    // }
    // PreparedStatement pst= con.prepareStatement(prepQuery.toString());
    // if (idStruttura != null) {
    // pst.setLong(1, idStruttura);
    // }
    // List<VDecTipoUnitaDocVO> results = new ArrayList<VDecTipoUnitaDocVO>();
    // DefaultSelectionProvider selTipoUD = new DefaultSelectionProvider("tipiUD");
    // ResultSet rs = pst.executeQuery();
    // while (rs.next()) {
    // VDecTipoUnitaDocVO tipoUD = new VDecTipoUnitaDocVO();
    // tipoUD.setIdTipoUnitaDoc((rs.getLong("ID_TIPO_UNITA_DOC")) );
    // tipoUD.setNmTipoUnitaDoc(rs.getString("NM_TIPO_UNITA_DOC"));
    // selTipoUD.appendRow(tipoUD.getIdTipoUnitaDoc(),tipoUD.getNmTipoUnitaDoc(), true);
    // }
    //
    // return selTipoUD;
    // }

    public static String getNomeTipoUnitaDoc(Long idTipoUnita, Long idUserIam, Connection con) throws SQLException {
        StringBuilder sb = new StringBuilder();
        String nomeTipoUD = "";

        sb.append("SELECT NM_TIPO_UNITA_DOC ");
        sb.append("FROM V_DEC_TIPO_UNITA_DOC ");
        sb.append("WHERE ID_TIPO_UNITA_DOC = ? AND ID_USER_IAM = ?");

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sb.toString());
            pst.setLong(1, idTipoUnita);
            pst.setLong(2, idUserIam);

            rs = pst.executeQuery();

            while (rs.next()) {
                nomeTipoUD = rs.getString("NM_TIPO_UNITA_DOC");
            }
        } catch (SQLException e) {
            log.error("Failed query: {}" + sb);
            throw e;

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }

        return nomeTipoUD;
    }
}

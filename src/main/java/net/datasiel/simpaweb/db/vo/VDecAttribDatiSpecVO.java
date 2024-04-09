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
 * V_dec_attrib_dati_spec
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
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.dbutils.DbUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.beans.EnumEntitaDatiSpecifici;
import net.datasiel.simpaweb.db.dao.VDecAttribDatiSpecDAO;

public class VDecAttribDatiSpecVO extends VDecAttribDatiSpecDAO {

    private static final long serialVersionUID = 1L;

    private Logger log = LoggerFactory.getLogger(VDecAttribDatiSpecVO.class);

    private Long idvaloredatispecifici;
    private String valore;

    public VDecAttribDatiSpecVO() {
        super();
    }

    public Long getIdvaloredatispecifici() {
        return idvaloredatispecifici;
    }

    public void setIdvaloredatispecifici(Long idvaloredatispecifici) {
        this.idvaloredatispecifici = idvaloredatispecifici;
    }

    public String getValore() {
        return valore;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }

    public List<VDecAttribDatiSpecVO> getValoriSpecifici(Long idDatiSpecifici, Long idStrut, String tipoEntitaSacer,
            Long idTipo, String cdVersioneXsd, Connection con) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(" select A.* ,v.IDVALOREDATISPECIFICI, V.VALORE ");
        sb.append("FROM V_DEC_ATTRIB_DATI_SPEC A ");
        sb.append("JOIN PAR_VALOREDATISPECIFICI V ");
        sb.append("ON (V.ID_ATTRIB_DATI_SPEC= A.ID_ATTRIB_DATI_SPEC) ");
        sb.append("WHERE v.iddatispecifici  = ? ");
        sb.append("AND v.CD_VERSIONE_XSD           = ? ");
        sb.append("AND a.id_strut           = ? ");
        sb.append("AND a.TI_ENTITA_SACER    = ? ");
        if ("UNI_DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            sb.append("AND a.id_tipo_unita_doc  =? ");
        } else if ("DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            sb.append("AND a.id_tipo_doc  =? ");
        }

        ResultSet rs = null;
        PreparedStatement pst = null;
        List<VDecAttribDatiSpecVO> resultList = new ArrayList<VDecAttribDatiSpecVO>();
        try {
            pst = con.prepareStatement(sb.toString());

            int index = 1;
            pst.setLong(index++, idDatiSpecifici);
            pst.setString(index++, cdVersioneXsd);
            pst.setLong(index++, idStrut);
            pst.setString(index++, tipoEntitaSacer);
            pst.setLong(index++, idTipo);

            rs = pst.executeQuery();
            while (rs.next()) {
                VDecAttribDatiSpecVO riga = new VDecAttribDatiSpecVO();
                getFromResultSet(riga, rs);
                riga.setIdvaloredatispecifici(rs.getLong("IDVALOREDATISPECIFICI"));
                riga.setValore(rs.getString("VALORE"));
                resultList.add(riga);
            }
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.close(pst);
        }
        return resultList;
    }

    public List<VDecAttribDatiSpecVO> getValoriSpecifici(Long idDatiSpecifici, Long idStrut, String tipoEntitaSacer,
            String colonna, Long id, String cdVersioneXsd, Connection con) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append(" select A.* ,v.IDVALOREDATISPECIFICI, V.VALORE ");
        sb.append(" from V_DEC_ATTRIB_DATI_SPEC A ");
        sb.append(
                " left join PAR_VALOREDATISPECIFICI V on (V.ID_ATTRIB_DATI_SPEC = A.ID_ATTRIB_DATI_SPEC and v.IDDATISPECIFICI = ? and v.ID_STRUT = ? and V.CD_VERSIONE_XSD=?) ");
        sb.append(" where A.id_strut = ? and A.TI_ENTITA_SACER = ? and A." + colonna + " = ? ");
        sb.append(" order by A.ID_ATTRIB_DATI_SPEC ");

        ResultSet rs = null;
        PreparedStatement pst = null;
        List<VDecAttribDatiSpecVO> resultList = new ArrayList<VDecAttribDatiSpecVO>();
        try {
            pst = con.prepareStatement(sb.toString());

            int index = 1;
            pst.setLong(index++, idDatiSpecifici);
            pst.setLong(index++, idStrut);
            pst.setString(index++, cdVersioneXsd);
            pst.setLong(index++, idStrut);
            pst.setString(index++, tipoEntitaSacer);
            pst.setLong(index++, id);

            rs = pst.executeQuery();
            while (rs.next()) {
                VDecAttribDatiSpecVO riga = new VDecAttribDatiSpecVO();
                getFromResultSet(riga, rs);
                riga.setIdvaloredatispecifici(rs.getLong("IDVALOREDATISPECIFICI"));
                riga.setValore(rs.getString("VALORE"));
                resultList.add(riga);
            }
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.close(pst);
        }
        return resultList;
    }

    public List<VDecAttribDatiSpecVO> getValoriSpecificiPerVersione(Long idDatiSpecifici, Long idStrut,
            String tipoEntitaSacer, Long idTipoUnitaDoc, String versioneXsd, Connection con) throws SQLException {
        /*
         * UNI_DOC("idunitadoc"), DOC("iddocumento"), COMP("idcomponente"), SUB_COMP("idcomponente")
         */
        String colonna = null;

        if (tipoEntitaSacer.equalsIgnoreCase(EnumEntitaDatiSpecifici.UNI_DOC.name())) {
            colonna = "ID_TIPO_UNITA_DOC";
        }
        if (tipoEntitaSacer.equalsIgnoreCase(EnumEntitaDatiSpecifici.DOC.name())) {
            colonna = "ID_TIPO_DOC";
        }
        StringBuilder logStr, sb = new StringBuilder();

        sb.append("SELECT DISTINCT lista.*, ");
        sb.append(" xa.ni_ord_attrib ");
        sb.append("FROM ");
        sb.append(" (SELECT DISTINCT a.*, ");
        sb.append(" v.IDVALOREDATISPECIFICI, ");
        sb.append(" V.VALORE, ");
        sb.append(" S.CD_VERSIONE_XSD, ");
        // sb.append(" S.DT_VERSIONE_XSD, ");
        sb.append(" s.id_xsd_dati_spec ");
        sb.append(" FROM V_DEC_ATTRIB_DATI_SPEC A ");
        sb.append(" LEFT JOIN PAR_VALOREDATISPECIFICI V ");
        sb.append(" ON (V.ID_ATTRIB_DATI_SPEC= A.ID_ATTRIB_DATI_SPEC ");
        sb.append(" AND v.IDDATISPECIFICI = ? ");
        sb.append(" AND v.cd_versione_xsd = ? ");
        sb.append(" AND v.ID_STRUT = a.id_strut), ");
        sb.append(" V_DEC_XSD_DATI_SPEC S ");
        sb.append(" WHERE S.ID_STRUT =a.id_strut ");
        sb.append(" AND S.TI_ENTITA_SACER =a.ti_entita_sacer ");
        sb.append(" AND S.ID_TIPO_UNITA_DOC=a.ID_TIPO_UNITA_DOC ");
        sb.append(" AND s.cd_versione_xsd =? ");
        sb.append(" AND S.id_strut = ? ");
        sb.append(" AND S.TI_ENTITA_SACER = ? ");
        sb.append(" AND S.ID_TIPO_UNITA_DOC= ? ");
        sb.append(" AND s.ti_uso_xsd ='VERS' ");
        sb.append(" ) lista, ");
        sb.append(" V_DEC_XSD_ATTRIB_DATI_SPEC XA ");
        sb.append("WHERE lista.id_xsd_dati_spec=xa.id_xsd_dati_spec ");
        sb.append("AND xa.id_attrib_dati_spec=lista.id_attrib_dati_spec ");
        sb.append("ORDER BY xa.ni_ord_attrib");
        ResultSet rs = null;
        PreparedStatement pst = null;
        logStr = new StringBuilder(sb);
        logStr.append(" - [");
        logStr.append(idDatiSpecifici).append(",");
        logStr.append(versioneXsd).append(",");
        logStr.append(versioneXsd).append(",");
        logStr.append(idStrut).append(",");
        logStr.append(tipoEntitaSacer).append(",");
        logStr.append(idTipoUnitaDoc).append("]");
        log.info(logStr.toString());
        List<VDecAttribDatiSpecVO> resultList = new ArrayList<VDecAttribDatiSpecVO>();
        try {
            pst = con.prepareStatement(sb.toString());

            int index = 1;
            pst.setLong(index++, idDatiSpecifici);
            pst.setString(index++, versioneXsd);
            pst.setString(index++, versioneXsd);
            pst.setLong(index++, idStrut);
            pst.setString(index++, tipoEntitaSacer);
            pst.setLong(index++, idTipoUnitaDoc);
            /*
             * pst.setLong(index++, idDatiSpecifici); pst.setLong(index++, idStrut);
             */

            rs = pst.executeQuery();
            while (rs.next()) {
                VDecAttribDatiSpecVO riga = new VDecAttribDatiSpecVO();
                getFromResultSet(riga, rs);
                riga.setIdvaloredatispecifici(rs.getLong("IDVALOREDATISPECIFICI"));
                riga.setValore(rs.getString("VALORE"));
                resultList.add(riga);
            }
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.close(pst);
        }
        return resultList;
    }

    public List<VDecAttribDatiSpecVO> getValoriSpecificiPerVersione(Long idDatiSpecifici, Long idStrut,
            EnumEntitaDatiSpecifici tipoEntitaSacer, Long id, String versioneXsd, Connection con) throws SQLException {
        /*
         * UNI_DOC("idunitadoc"), DOC("iddocumento"), COMP("idcomponente"), SUB_COMP("idcomponente")
         */
        String colonna = tipoEntitaSacer.getColumnNameForAttrib();

        StringBuilder logStr, sb = new StringBuilder();
        sb.append("SELECT DISTINCT lista.*, ");
        sb.append(" xa.ni_ord_attrib ");
        sb.append("FROM ");
        sb.append(" (SELECT DISTINCT a.*, ");
        sb.append(" v.IDVALOREDATISPECIFICI, ");
        sb.append(" V.VALORE, ");
        sb.append(" S.CD_VERSIONE_XSD, ");
        // sb.append(" S.DT_VERSIONE_XSD, ");
        sb.append(" s.id_xsd_dati_spec ");
        sb.append(" FROM V_DEC_ATTRIB_DATI_SPEC A ");
        sb.append(" LEFT JOIN PAR_VALOREDATISPECIFICI V ");
        sb.append(" ON (V.ID_ATTRIB_DATI_SPEC= A.ID_ATTRIB_DATI_SPEC ");
        sb.append(" AND v.IDDATISPECIFICI = ? ");
        sb.append(" AND v.cd_versione_xsd = ? ");
        sb.append(" AND v.ID_STRUT = a.id_strut), ");
        sb.append(" V_DEC_XSD_DATI_SPEC S ");
        sb.append(" WHERE S.ID_STRUT =a.id_strut ");
        sb.append(" AND S.TI_ENTITA_SACER =a.ti_entita_sacer ");
        sb.append(" AND S." + colonna + "=a." + colonna);
        sb.append(" AND s.cd_versione_xsd =? ");
        sb.append(" AND S.id_strut = ? ");
        sb.append(" AND S.TI_ENTITA_SACER = ? ");
        sb.append(" AND S." + colonna + "= ? ");
        sb.append(" AND s.ti_uso_xsd ='VERS' ");
        sb.append(" ) lista, ");
        sb.append(" V_DEC_XSD_ATTRIB_DATI_SPEC XA ");
        sb.append("WHERE lista.id_xsd_dati_spec=xa.id_xsd_dati_spec ");
        sb.append("AND xa.id_attrib_dati_spec=lista.id_attrib_dati_spec ");
        sb.append("ORDER BY xa.ni_ord_attrib");
        ResultSet rs = null;
        PreparedStatement pst = null;
        logStr = new StringBuilder(sb);
        logStr.append(" - [");
        logStr.append(idDatiSpecifici).append(",");
        logStr.append(versioneXsd).append(",");
        logStr.append(versioneXsd).append(",");
        logStr.append(idStrut).append(",");
        logStr.append(tipoEntitaSacer.name()).append(",");
        if (id == null) {
            logStr.append("3]");
        } else {
            logStr.append(id).append("]");
        }
        log.info(logStr.toString());
        List<VDecAttribDatiSpecVO> resultList = new ArrayList<VDecAttribDatiSpecVO>();
        try {
            pst = con.prepareStatement(sb.toString());

            int index = 1;
            pst.setLong(index++, idDatiSpecifici);
            pst.setString(index++, versioneXsd);
            pst.setString(index++, versioneXsd);
            pst.setLong(index++, idStrut);
            pst.setString(index++, tipoEntitaSacer.name());
            if (id == null) {
                pst.setNull(index++, 3);
            } else {
                pst.setLong(index++, id);
            }
            /*
             * pst.setLong(index++, idDatiSpecifici); pst.setLong(index++, idStrut);
             */

            rs = pst.executeQuery();
            while (rs.next()) {
                VDecAttribDatiSpecVO riga = new VDecAttribDatiSpecVO();
                getFromResultSet(riga, rs);
                riga.setIdvaloredatispecifici(rs.getLong("IDVALOREDATISPECIFICI"));
                riga.setValore(rs.getString("VALORE"));
                resultList.add(riga);
            }
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.close(pst);
        }
        return resultList;
    }

    public Map<String, String> getMapOfValoriSpecifici(Long idDatiSpecifici, Long idstruttura, String tipoEntitaSacer,
            Long idTipoUnitaDoc, String cdVersioneXsd, Connection connection) throws SQLException {
        TreeMap<String, String> result = null;
        StringBuilder sb = new StringBuilder();
        sb.append(" select A.* ,v.IDVALOREDATISPECIFICI, V.VALORE ");
        sb.append("FROM V_DEC_ATTRIB_DATI_SPEC A ");
        sb.append("JOIN PAR_VALOREDATISPECIFICI V ");
        sb.append("ON (V.ID_ATTRIB_DATI_SPEC= A.ID_ATTRIB_DATI_SPEC) ");
        sb.append("WHERE v.iddatispecifici  = ? ");
        sb.append("AND v.CD_VERSIONE_XSD           = ? ");
        sb.append("AND a.id_strut           = ? ");
        sb.append("AND a.TI_ENTITA_SACER    = ? ");
        if ("UNI_DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            sb.append("AND a.id_tipo_unita_doc  =? ");
        } else if ("DOC".equalsIgnoreCase(tipoEntitaSacer)) {
            sb.append("AND a.id_tipo_doc  =? ");
        }

        ResultSet rs = null;
        PreparedStatement pst = null;
        List<VDecAttribDatiSpecVO> resultList = new ArrayList<VDecAttribDatiSpecVO>();
        try {
            pst = connection.prepareStatement(sb.toString());

            int index = 1;
            pst.setLong(index++, idDatiSpecifici);
            pst.setString(index++, cdVersioneXsd);
            pst.setLong(index++, idstruttura);
            pst.setString(index++, tipoEntitaSacer);
            pst.setLong(index++, idTipoUnitaDoc);
            result = new TreeMap<String, String>();
            rs = pst.executeQuery();
            while (rs.next()) {
                String key = rs.getString("NM_ATTRIB_DATI_SPEC");
                String value = rs.getString("VALORE");
                result.put(key, value);
            }
        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.close(pst);
        }

        return result;
    }

}

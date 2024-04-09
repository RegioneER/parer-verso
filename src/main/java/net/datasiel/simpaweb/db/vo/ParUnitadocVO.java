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
 * Par_unitadoc
 *
 * WARNING! Automatically generated file! Do not edit! Code Generator by
 * J.A.Carter
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.commons.dbutils.DbUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.dao.ParUnitadocDAO;

public class ParUnitadocVO extends ParUnitadocDAO {

    private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(ParUnitadocVO.class);

    public ParUnitadocVO() {
        super();
    }

    private String descTipoUnitaDoc;
    private String cdRegistroUnitaDoc;

    public int aggiornaUnitaDoc(int stato, String xmlRichiesta, String xmlRisposta, Long idUnitaDoc, String esito,
            Connection con) throws SQLException {

        StringBuffer sb = new StringBuffer();
        sb.append(" update PAR_UNITADOC ");
        sb.append(" set STATO = ?, ");
        if (esito != null) {
            sb.append(" ESITOVERSAMENTO = ?, ");
        }
        if (xmlRichiesta != null) {
            sb.append(" XMLRICHIESTA = ?, ");
        }

        if (xmlRisposta != null) {
            sb.append(" XMLRISPOSTA = ?, ");
            sb.append(" DATAVERSAMENTO = current_timestamp, ");
        }
        sb.append("	dtagg = current_timestamp, ");
        sb.append("	id = id+1 ");
        sb.append(" where IDUNITADOC = ? ");

        // TODO se necessario aggiungere condizione su id per la concorrenza
        PreparedStatement pst = null;
        try {
            int index = 1;
            pst = con.prepareStatement(sb.toString());
            pst.setLong(index++, stato);
            if (esito != null) {
                pst.setString(index++, esito);
            }
            if (xmlRichiesta != null) {
                pst.setString(index++, xmlRichiesta);
            }
            if (xmlRisposta != null) {
                pst.setString(index++, xmlRisposta);
            }
            pst.setLong(index++, idUnitaDoc);

            int updates = pst.executeUpdate();
            return updates;

        } catch (SQLException e) {
            log.error("Failed query:" + sb.toString());
            throw e;
        } finally {
            DbUtils.closeQuietly(pst);
        }

    }

    /**
     * Retrieve from the database for table "PAR_UNITADOC"
     */
    public java.util.List<ParUnitadocVO> retrieveBozzeUtente(Long idUtente, Long idStrut, Connection con)
            throws SQLException {
        java.util.List<ParUnitadocVO> retRows = new java.util.ArrayList<ParUnitadocVO>();
        ParUnitadocVO curRow;
        /*
         * r.cd_registro_unita_doc "); query.
         * append("from (PAR_UNITADOC U join v_dec_registro_unita_doc r on u.id_registro_unita_doc=r.id_registro_unita_doc) "
         * );
         */
        String query = "SELECT u.IDUNITADOC, u.NUMERO, u.ANNO, u.OGGETTO, u.DATA, u.DTINS, u.DTAGG, u.STATO, t.DS_TIPO_UNITA_DOC, u.DATAVERSAMENTO, r.CD_REGISTRO_UNITA_DOC "
                + "FROM PAR_UNITADOC u " + "JOIN V_DEC_REGISTRO_UNITA_DOC r "
                + "ON (u.ID_REGISTRO_UNITA_DOC = r.id_registro_unita_doc AND u.IDUTENTE = r.ID_USER_IAM) "
                + "JOIN V_DEC_TIPO_UNITA_DOC t "
                + "ON (u.ID_TIPO_UNITA_DOC = t.ID_TIPO_UNITA_DOC AND u.IDUTENTE = t.ID_USER_IAM) "
                + "WHERE u.IDUTENTE = ? AND u.ID_STRUT = ? AND u.STATO < ? " + "ORDER BY u.DTAGG DESC";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        ResultSet r = null;
        try {
            int index = 1;
            log.info(query + " - [" + idUtente + ", " + idStrut + ", 4 ]");
            st.setLong(index++, idUtente);
            st.setLong(index++, idStrut);
            st.setLong(index++, 4);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParUnitadocVO();
                getRiepilogoUDFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } finally {
            if (r != null) {
                r.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    // restituisce le unità documentarie versate dalla struttura di appartenenza dell'utente corrente
    public java.util.List<ParUnitadocVO> retrieveVersamentiStruttura(Long idStrut, Long idUser, Connection con)
            throws SQLException {
        java.util.List<ParUnitadocVO> retRows = new java.util.ArrayList<ParUnitadocVO>();
        ParUnitadocVO curRow;

        StringBuffer query = new StringBuffer();

        query.append(
                "SELECT u.IDUNITADOC, u.NUMERO, u.ANNO, u.OGGETTO, u.DATA, u.DTINS, u.DTAGG, u.STATO, t.DS_TIPO_UNITA_DOC, u.DATAVERSAMENTO, r.CD_REGISTRO_UNITA_DOC ");
        query.append("FROM PAR_UNITADOC u ");
        query.append("JOIN v_dec_registro_unita_doc r ");
        query.append("ON (u.id_registro_unita_doc = r.id_registro_unita_doc AND u.IDUTENTE = r.ID_USER_IAM) ");
        query.append("JOIN V_DEC_TIPO_UNITA_DOC t ");
        query.append("ON (u.ID_TIPO_UNITA_DOC = t.ID_TIPO_UNITA_DOC AND u.IDUTENTE = t.ID_USER_IAM) ");
        query.append("WHERE u.IDUTENTE = ? AND u.ID_STRUT = ? AND u.STATO = ? ");
        query.append("ORDER BY u.DTAGG DESC ");

        java.sql.PreparedStatement st = con.prepareStatement(query.toString());
        ResultSet r = null;
        try {
            int index = 1;
            log.info(query + " - [" + idUser + "," + idStrut + ", 4]");
            st.setLong(index++, idUser);
            st.setLong(index++, idStrut);
            st.setLong(index++, 4);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParUnitadocVO();
                getRiepilogoUDFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } finally {
            if (r != null) {
                r.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    // restituisce le 5 unità documentarie versate più recentemente dalla struttura di appartenenza dell'utente corrente
    public java.util.List<ParUnitadocVO> retrieveUltimi5VersamentiStruttura(Long idStrut, Long idUserIam,
            Connection con) throws SQLException {
        java.util.List<ParUnitadocVO> retRows = new java.util.ArrayList<ParUnitadocVO>();
        ParUnitadocVO curRow;

        StringBuffer query = new StringBuffer();

        query.append("SELECT * " + "FROM ("
                + "SELECT DISTINCT u.IDUNITADOC, u.NUMERO, u.ANNO, u.OGGETTO, u.DATA, u.DTINS, u.DTAGG, u.STATO, t.DS_TIPO_UNITA_DOC, u.DATAVERSAMENTO, r.cd_registro_unita_doc "
                + "FROM PAR_UNITADOC u " + "JOIN PAR_DOCUMENTO doc " + "ON u.IDUNITADOC = doc.IDUNITADOC "
                + "JOIN V_DEC_REGISTRO_UNITA_DOC r " + "ON u.ID_REGISTRO_UNITA_DOC = r.ID_REGISTRO_UNITA_DOC "
                + "JOIN V_DEC_TIPO_UNITA_DOC t " + "ON u.ID_TIPO_UNITA_DOC = t.ID_TIPO_UNITA_DOC "
                + "JOIN V_DEC_TIPO_DOC tipo_doc " + "ON tipo_doc.ID_TIPO_DOC = doc.ID_TIPO_DOC "
                + "WHERE r.ID_USER_IAM = ? AND t.ID_USER_IAM = ? AND u.ID_STRUT = ? AND u.STATO = ? "
                + "ORDER BY u.DATAVERSAMENTO DESC) tmp " + "WHERE rownum < ?");

        java.sql.PreparedStatement st = con.prepareStatement(query.toString());
        ResultSet r = null;
        try {
            int index = 1;
            log.info(query + " - [" + idUserIam + "," + idUserIam + "," + idStrut + "4,6]");
            st.setLong(index++, idUserIam);
            st.setLong(index++, idUserIam);
            st.setLong(index++, idStrut);
            st.setLong(index++, 4);
            st.setLong(index++, 6);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParUnitadocVO();
                getRiepilogoUDFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } finally {
            if (r != null) {
                r.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * @return the descTipoUnitaDoc
     */
    public String getDescTipoUnitaDoc() {
        return descTipoUnitaDoc;
    }

    /**
     * @param descTipoUnitaDoc
     *            the descTipoUnitaDoc to set
     */
    public void setDescTipoUnitaDoc(String descTipoUnitaDoc) {
        this.descTipoUnitaDoc = descTipoUnitaDoc;
    }

    public void getRiepilogoUDFromResultSet(ParUnitadocVO obj, ResultSet r) throws SQLException {
        if (r.getObject("IDUNITADOC") == null) {
            obj.setIdunitadoc(null);
        } else {
            obj.setIdunitadoc(r.getLong("IDUNITADOC"));
        }
        obj.setNumero(r.getString("NUMERO"));
        if (r.getObject("ANNO") == null) {
            obj.setAnno(null);
        } else {
            obj.setAnno(r.getLong("ANNO"));
        }
        obj.setDtins(r.getDate("DTINS"));
        obj.setDtagg(r.getDate("DTAGG"));
        obj.setOggetto(r.getString("OGGETTO"));
        obj.setData(r.getDate("DATA"));
        if (r.getObject("STATO") == null) {
            obj.setStato(null);
        } else {
            obj.setStato(r.getLong("STATO"));
        }
        obj.setDescTipoUnitaDoc(r.getString("ds_tipo_unita_doc"));
        obj.setCdRegistroUnitaDoc(r.getString("cd_registro_unita_doc"));
        obj.setDataversamento(r.getTimestamp("DATAVERSAMENTO"));

    }

    public boolean userPossiedeUd(Long idUtente, Long idUD, Connection con) throws SQLException {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT IDUTENTE ");
        sb.append("FROM PAR_UNITADOC ");
        sb.append("WHERE IDUTENTE = ? AND IDUNITADOC = ?");
        ResultSet r = null;
        java.sql.PreparedStatement st = null;
        try {
            st = con.prepareStatement(sb.toString());
            int index = 1;
            st.setLong(index++, idUtente);
            st.setLong(index++, idUD);

            log.debug(sb.toString());
            // ResultSet r = st.executeQuery(query);
            r = st.executeQuery();
            return r.next();
        } finally {
            if (r != null) {
                r.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    public int getStato(Long idUD, Connection con) throws SQLException {
        int result = -1;
        ResultSet r = null;

        StringBuilder query = new StringBuilder();
        query.append("SELECT ud.STATO ");
        query.append("FROM PAR_UNITADOC ud ");
        query.append("WHERE ud.IDUNITADOC = ?");

        java.sql.PreparedStatement st = con.prepareStatement(query.toString());
        int index = 1;
        st.setLong(index++, idUD);

        try {
            log.debug(query.toString());
            r = st.executeQuery();
            if (r.next())
                result = r.getInt("STATO");
        } catch (SQLException e) {
            log.error(e.getMessage());
        } finally {
            if (r != null) {
                r.close();
            }
            if (st != null) {
                st.close();
            }
        }

        return result;
    }

    public List<ParUnitadocVO> cercaVersamenti(Connection con, String strWhere, Long idStrut, Long idUser,
            Collection<?> listaParametri) throws Exception {

        java.util.List<ParUnitadocVO> retRows = new java.util.ArrayList<ParUnitadocVO>();
        ParUnitadocVO curRow;
        StringBuffer query = new StringBuffer();

        query.append(
                "SELECT u.IDUNITADOC, u.NUMERO, u.ANNO, u.OGGETTO, u.DATA, u.DTINS, u.DTAGG, u.STATO, tud.DS_TIPO_UNITA_DOC, u.DATAVERSAMENTO, rud.CD_REGISTRO_UNITA_DOC ");
        query.append("FROM PAR_UNITADOC u ");
        query.append("JOIN V_DEC_REGISTRO_UNITA_DOC rud ");
        query.append("ON u.ID_REGISTRO_UNITA_DOC = rud.ID_REGISTRO_UNITA_DOC ");
        query.append("JOIN V_DEC_TIPO_UNITA_DOC tud ");
        query.append("ON U.ID_TIPO_UNITA_DOC = tud.ID_TIPO_UNITA_DOC ");
        query.append("WHERE u.ID_STRUT = ? AND u.STATO = ? AND rud.ID_USER_IAM = ? AND tud.ID_USER_IAM = ? ");

        if (StringUtils.trimToEmpty(strWhere).length() > 0) {
            query.append(" AND " + strWhere);
        }

        query.append(" ORDER BY u.DATAVERSAMENTO DESC");

        java.sql.PreparedStatement st = con.prepareStatement(query.toString());

        int index = 1;
        StringBuilder log_s = new StringBuilder(query);
        st.setLong(index++, idStrut);
        log_s.append(" - [" + idStrut);

        st.setLong(index++, 4);
        log_s.append("," + 4);

        st.setLong(index++, idUser);
        log_s.append("," + idUser);

        st.setLong(index++, idUser);
        log_s.append("," + idUser);

        for (Object object : listaParametri) {
            if (object instanceof java.util.Date) {
                object = new java.sql.Date(((java.util.Date) object).getTime());
            }
            st.setObject(index, object);
            log_s.append("," + object.toString());

            index++;
        }
        ResultSet r = null;
        try {
            log.info(log_s.toString());
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParUnitadocVO();
                getRiepilogoUDFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } catch (Exception e) {
            log.error(e.getMessage());
            return null;
        } finally {
            if (r != null) {
                r.close();
            }
            if (st != null) {
                st.close();
            }
        }

    }

    public String getCdRegistroUnitaDoc() {
        return cdRegistroUnitaDoc;
    }

    public void setCdRegistroUnitaDoc(String cdRegistroUnitaDoc) {
        this.cdRegistroUnitaDoc = cdRegistroUnitaDoc;
    }
}

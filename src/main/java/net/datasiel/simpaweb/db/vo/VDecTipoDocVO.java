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
 * V_dec_tipo_doc
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

import com.manydesigns.elements.options.DefaultSelectionProvider;

import net.datasiel.simpaweb.db.dao.VDecTipoDocDAO;
import net.datasiel.simpaweb.db.pojo.VDecTipoDoc;

public class VDecTipoDocVO extends VDecTipoDocDAO {

    private static final long serialVersionUID = 1L;
    private final Logger log = LoggerFactory.getLogger(VDecTipoDocVO.class);

    public VDecTipoDocVO() {
        super();
    }

    /**
     * Restituisce il numero di tipologie documentarie ammesse per la tipologia di unità documentaria e tipo di
     * documento ricevuti.
     *
     * @param idTipoUnitaDoc
     *            Codice tipologia unità documentaria
     * @param strTiDoc
     *            Tipo documento (PRINCIPALE, ALLEGATO, ANNESSO o ANNOTAZIONE)
     * @param idUserIam
     *            ID dell'utente in IAM
     * @param con
     *            Connessione a db
     *
     * @return Numero di tipologie di documento trovate.
     *
     * @throws SQLException
     */
    public static int getNumeroTipiDoc(Long idTipoUnitaDoc, String strTiDoc, Long idUserIam, Connection con)
            throws SQLException {
        StringBuilder sb = new StringBuilder();
        int returnValue = 0;

        sb.append("SELECT COUNT(*) ");
        sb.append("FROM V_DEC_TIPO_STRUT_UNITA_DOC ud ");
        sb.append("JOIN V_DEC_TIPO_DOC_AMMESSO tda ");
        sb.append("ON tda.ID_TIPO_STRUT_UNITA_DOC = ud.ID_TIPO_STRUT_UNITA_DOC ");
        sb.append("JOIN V_DEC_TIPO_DOC td ");
        sb.append("ON (td.id_tipo_doc = tda.id_tipo_doc AND td.id_user_iam = ?) ");
        sb.append("WHERE ud.ID_TIPO_UNITA_DOC = ? AND tda.TI_DOC = ?");
        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(sb.toString());
            pst.setLong(1, idUserIam);
            pst.setLong(2, idTipoUnitaDoc);
            pst.setString(3, strTiDoc);
            rs = pst.executeQuery();
            if (rs.next()) {
                returnValue = rs.getInt(1);
            }

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);

        }
        return returnValue;
    }

    /**
     * Restituisce l'elenco delle tipologie documentarie ammesse per la tipologia di unità documentaria e tipo di
     * documento ricevuti.
     *
     * @param idTipoUnitaDoc
     *            Codice tipologia unità documentaria
     * @param strTiDoc
     *            Tipo documento (principale, allegato, annesso o annotazione)
     * @param idUserIam
     *            ID dell'utente in IAM
     * @param con
     *            Connessione a db
     * @param fl_princ
     *
     * @return L'elenco delle tipologie documentarie ammesse.
     *
     * @throws SQLException
     */
    public static List<VDecTipoDocVO> getListaTipiDoc(Long idTipoUnitaDoc, String strTiDoc, Long idUserIam,
            Connection con, boolean fl_princ) throws SQLException {

        StringBuffer sb = new StringBuffer();
        /*
         * SELECT * FROM V_DEC_TIPO_DOC td where td.id_strut=41
         */
        sb.append("SELECT td.ID_TIPO_DOC, td.NM_TIPO_DOC ");
        sb.append("FROM V_DEC_TIPO_DOC td ");
        sb.append("WHERE td.id_strut = ? ");
        sb.append("AND td.id_user_iam = ? ");

        if (fl_princ) {
            sb.append(" and td.FL_TIPO_DOC_PRINCIPALE = '1'");
        }

        PreparedStatement pst = null;
        ResultSet rs = null;
        List<VDecTipoDocVO> returnList = new ArrayList<VDecTipoDocVO>();
        try {
            pst = con.prepareStatement(sb.toString());
            pst.setLong(1, idTipoUnitaDoc);
            pst.setLong(2, idUserIam);

            rs = pst.executeQuery();
            while (rs.next()) {
                VDecTipoDocVO tipoUD = new VDecTipoDocVO();

                tipoUD.setIdTipoDoc((rs.getLong("ID_TIPO_DOC")));
                tipoUD.setNmTipoDoc(rs.getString("NM_TIPO_DOC"));

                returnList.add(tipoUD);
            }

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }
        return returnList;

    }

    public static DefaultSelectionProvider getTipiDoc(Long idTipoUnitaDoc, String strTiDoc, Long idUserIam,
            Connection con, boolean fl_princ) throws SQLException {
        List<VDecTipoDocVO> listaValori = getListaTipiDoc(idTipoUnitaDoc, strTiDoc, idUserIam, con, fl_princ);

        DefaultSelectionProvider selTipoDocumento = new DefaultSelectionProvider("tipiDocumento");
        for (VDecTipoDocVO vDecTipoDocVO : listaValori) {
            selTipoDocumento.appendRow(vDecTipoDocVO.getIdTipoDoc(), vDecTipoDocVO.getNmTipoDoc(), true);
        }

        return selTipoDocumento;
    }

    public static DefaultSelectionProvider getTipiDoc(Long idStruttura, Long idUserIam, Connection con)
            throws SQLException {
        DefaultSelectionProvider selTipoDocumento = new DefaultSelectionProvider("tipiDocumento");
        StringBuffer prepQuery = new StringBuffer();
        prepQuery.append("SELECT ID_TIPO_DOC, NM_TIPO_DOC ");
        prepQuery.append("FROM V_DEC_TIPO_DOC ");
        prepQuery.append("WHERE ID_STRUT = ? AND ID_USER_IAM = ?");

        PreparedStatement pst = null;
        ResultSet rs = null;
        try {
            pst = con.prepareStatement(prepQuery.toString());
            pst.setLong(1, idStruttura);
            pst.setLong(2, idUserIam);

            rs = pst.executeQuery();
            while (rs.next()) {
                VDecTipoDocVO tipoUD = new VDecTipoDocVO();

                tipoUD.setIdTipoDoc((rs.getLong("ID_TIPO_DOC")));
                tipoUD.setNmTipoDoc(rs.getString("NM_TIPO_DOC"));

                selTipoDocumento.appendRow(tipoUD.getIdTipoDoc(), tipoUD.getNmTipoDoc(), true);
            }

        } finally {
            DbUtils.closeQuietly(rs);
            DbUtils.closeQuietly(pst);
        }

        return selTipoDocumento;
    }

    public String getNMTipoDoc(Long idtipodoc, Long idUserIam, Connection connection) throws SQLException {
        VDecTipoDoc tipoDoc = retrieveByIdTipoDoc(idtipodoc, idUserIam, connection);
        if (tipoDoc != null) {
            return tipoDoc.getNmTipoDoc();
        }
        return null;
    }

    public VDecTipoDoc retrieveByIdTipoDoc(Long idtipodoc, Long idUserIam, Connection con) throws SQLException {

        String query = "SELECT * " + "FROM V_DEC_TIPO_DOC " + "WHERE ID_TIPO_DOC = ? AND ID_USER_IAM = ?";

        ResultSet r = null;
        java.sql.PreparedStatement st = con.prepareStatement(query);
        st.setLong(1, idtipodoc);
        st.setLong(2, idUserIam);
        try {
            log.debug(query);
            r = st.executeQuery();
            VDecTipoDoc obj = null;
            if (r.next()) {
                obj = new VDecTipoDoc();
                getFromResultSet(obj, r);
            }
            return obj;
        } finally {
            DbUtils.closeQuietly(r);
            DbUtils.closeQuietly(st);
        }
    }
}

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

/**
 * ParDocumento
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.pojo.ParDatispecifici;
import net.datasiel.simpaweb.db.pojo.ParDocumento;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParComponenteVO;
import net.datasiel.simpaweb.db.vo.ParDatispecificiVO;

public class ParDocumentoDAO extends ParDocumento {
    private final Logger log = LoggerFactory.getLogger(ParDocumentoDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "iddocumento", "idunitadoc", "idTipoDoc", "flgstato", "dtins", "dtagg", "pgm", "id",
                "profiloautoredoc", "profilodescrizionedoc", "dfdenominazione", "dfcognome", "dfnome", "dfcodfiscale",
                "dfpiva", "dfdtemissione", "dfprogressivo", "dfregistro", "dfperiodo", "dfdttermineemissione",
                "tipologia", "cdVersioneXsd", "idTipoStrutDoc" };
    }

    public ParDocumentoDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(ParDocumento obj, ResultSet r) throws SQLException {
        if (r.getObject("IDDOCUMENTO") == null) {
            obj.setIddocumento(null);
        } else {
            obj.setIddocumento(r.getLong("IDDOCUMENTO"));
        }
        if (r.getObject("IDUNITADOC") == null) {
            obj.setIdunitadoc(null);
        } else {
            obj.setIdunitadoc(r.getLong("IDUNITADOC"));
        }
        if (r.getObject("ID_TIPO_DOC") == null) {
            obj.setIdTipoDoc(null);
        } else {
            obj.setIdTipoDoc(r.getLong("ID_TIPO_DOC"));
        }
        if (r.getObject("FLGSTATO") == null) {
            obj.setFlgstato(null);
        } else {
            obj.setFlgstato(r.getLong("FLGSTATO"));
        }
        obj.setDtins(r.getDate("DTINS"));
        obj.setDtagg(r.getDate("DTAGG"));
        obj.setPgm(r.getString("PGM"));
        if (r.getObject("ID") == null) {
            obj.setId(null);
        } else {
            obj.setId(r.getLong("ID"));
        }
        obj.setProfiloautoredoc(r.getString("PROFILOAUTOREDOC"));
        obj.setProfilodescrizionedoc(r.getString("PROFILODESCRIZIONEDOC"));
        obj.setDfdenominazione(r.getString("DFDENOMINAZIONE"));
        obj.setDfcognome(r.getString("DFCOGNOME"));
        obj.setDfnome(r.getString("DFNOME"));
        obj.setDfcodfiscale(r.getString("DFCODFISCALE"));
        obj.setDfpiva(r.getString("DFPIVA"));
        obj.setDfdtemissione(r.getDate("DFDTEMISSIONE"));
        if (r.getObject("DFPROGRESSIVO") == null) {
            obj.setDfprogressivo(null);
        } else {
            obj.setDfprogressivo(r.getLong("DFPROGRESSIVO"));
        }
        obj.setDfregistro(r.getString("DFREGISTRO"));
        obj.setDfperiodo(r.getString("DFPERIODO"));
        obj.setDfdttermineemissione(r.getDate("DFDTTERMINEEMISSIONE"));
        obj.setTipologia(r.getString("TIPOLOGIA"));
        obj.setCdVersioneXSD(r.getString("CD_VERSIONE_XSD"));

    }

    public String[] keyNames = { "idunitadoc", "iddocumento" };

    /**
     * Retrieve from the database for table "PAR_DOCUMENTO"
     */
    public ParDocumento retrieveByKey(Long idunitadoc, Long iddocumento, Connection con) throws SQLException {

        String query = "select * from PAR_DOCUMENTO" + " where IDUNITADOC=?" + " and IDDOCUMENTO=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        st.setLong(1, idunitadoc);
        st.setLong(2, iddocumento);
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery();
            ParDocumento obj = null;
            if (r.next()) {
                obj = new ParDocumento();
                getFromResultSet(obj, r);
            }
            return obj;
        } finally {
            if (r != null) {
                r.close();
            }
            if (st != null) {
                st.close();
            }
        }
    }

    public ParDocumento retrieveByKey(Long iddocumento, Connection con) throws SQLException {

        String query = "select * from PAR_DOCUMENTO" + " where IDDOCUMENTO=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        ResultSet r = null;
        st.setLong(1, iddocumento);
        try {
            log.debug(query);
            r = st.executeQuery();
            ParDocumento obj = null;
            if (r.next()) {
                obj = new ParDocumento();
                getFromResultSet(obj, r);
            }
            return obj;
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
     * Retrieve from the database for table "PAR_DOCUMENTO"
     */
    @Deprecated
    public java.util.List<ParDocumento> retrieveWhere(String where, String orderByClause, Connection con)
            throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDocumento> retRows = new java.util.ArrayList<ParDocumento>();
        ParDocumento curRow;

        String query = "select * from PAR_DOCUMENTO" + " where " + where;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
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
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Retrieve from the database for table "PAR_DOCUMENTO"
     */
    @Deprecated
    public java.util.List<ParDocumento> retrieveWhere(String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDocumento> retRows = new java.util.ArrayList<ParDocumento>();
        ParDocumento curRow;

        String query = "select * from PAR_DOCUMENTO" + " where " + where;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
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
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Updates the current object values into the database.
     */
    public int update(ParDocumento obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_DOCUMENTO set IDDOCUMENTO= ?  , IDUNITADOC= ?  , ID_TIPO_DOC= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , "
                + "PROFILOAUTOREDOC= ?  , PROFILODESCRIZIONEDOC= ?  , DFDENOMINAZIONE= ?  , DFCOGNOME= ?  , DFNOME= ?  , DFCODFISCALE= ?  , DFPIVA= ?  , "
                + "DFDTEMISSIONE= ?  , DFPROGRESSIVO= ?  , DFREGISTRO= ?  , DFPERIODO= ?  , DFDTTERMINEEMISSIONE= ?  , TIPOLOGIA= ? , CD_VERSIONE_XSD=? , ID_TIPO_STRUT_DOC=?  "
                + " where IDUNITADOC=? and IDDOCUMENTO=?";

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIddocumento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddocumento());
        }

        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }

        if (obj.getIdTipoDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoDoc());
        }

        if (obj.getFlgstato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgstato());
        }

        pst.setString(indice++, obj.getPgm());
        if (obj.getId() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getId());
        }

        pst.setString(indice++, obj.getProfiloautoredoc());
        pst.setString(indice++, obj.getProfilodescrizionedoc());
        pst.setString(indice++, obj.getDfdenominazione());
        pst.setString(indice++, obj.getDfcognome());
        pst.setString(indice++, obj.getDfnome());
        pst.setString(indice++, obj.getDfcodfiscale());
        pst.setString(indice++, obj.getDfpiva());
        if (obj.getDfdtemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdtemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        if (obj.getDfprogressivo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getDfprogressivo());
        }

        pst.setString(indice++, obj.getDfregistro());
        pst.setString(indice++, obj.getDfperiodo());
        if (obj.getDfdttermineemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdttermineemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        pst.setString(indice++, obj.getTipologia());
        pst.setString(indice++, obj.getCdVersioneXSD());
        pst.setNull(indice++, 3);

        pst.setLong(indice++, obj.getIdunitadoc());
        pst.setLong(indice++, obj.getIddocumento());

        try {
            log.debug(preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }

    /**
     * Updates the current object values into the database.
     */
    @Deprecated
    public int updateWhere(ParDocumento obj, String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String preparedQuery = "update PAR_DOCUMENTO set IDDOCUMENTO= ?  , IDUNITADOC= ?  , ID_TIPO_DOC= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , PROFILOAUTOREDOC= ?  , PROFILODESCRIZIONEDOC= ?  , DFDENOMINAZIONE= ?  , DFCOGNOME= ?  , DFNOME= ?  , DFCODFISCALE= ?  , DFPIVA= ?  , DFDTEMISSIONE= ?  , DFPROGRESSIVO= ?  , DFREGISTRO= ?  , DFPERIODO= ?  , DFDTTERMINEEMISSIONE= ?  , TIPOLOGIA= ? , CD_VERSIONE_XSD=? , ID_TIPO_STRUT_DOC=?  where "
                + where;

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIddocumento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddocumento());
        }

        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }

        if (obj.getIdTipoDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoDoc());
        }

        if (obj.getFlgstato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgstato());
        }

        pst.setString(indice++, obj.getPgm());
        if (obj.getId() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getId());
        }

        pst.setString(indice++, obj.getProfiloautoredoc());
        pst.setString(indice++, obj.getProfilodescrizionedoc());
        pst.setString(indice++, obj.getDfdenominazione());
        pst.setString(indice++, obj.getDfcognome());
        pst.setString(indice++, obj.getDfnome());
        pst.setString(indice++, obj.getDfcodfiscale());
        pst.setString(indice++, obj.getDfpiva());
        if (obj.getDfdtemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdtemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        if (obj.getDfprogressivo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getDfprogressivo());
        }

        pst.setString(indice++, obj.getDfregistro());
        pst.setString(indice++, obj.getDfperiodo());
        if (obj.getDfdttermineemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdttermineemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }
        // , CD_VERSIONE_XSD=?
        pst.setString(indice++, obj.getTipologia());
        pst.setString(indice++, obj.getCdVersioneXSD());
        pst.setNull(indice++, 3);

        try {
            log.debug(preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + preparedQuery);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }

    /**
     * Deletes from the database for table "PAR_DOCUMENTO"
     */
    public int delete(ParDocumento obj, Connection con) throws SQLException {
        String query = "delete from PAR_DOCUMENTO where IDUNITADOC=? and IDDOCUMENTO=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug(query);
            st.setLong(1, obj.getIdunitadoc());
            st.setLong(2, obj.getIddocumento());
            int updates = st.executeUpdate();
            return updates;
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Deletes from the database for table "PAR_DOCUMENTO"
     */
    public int deleteByIdUd(Long idUd, Connection con) throws SQLException {
        String query = "delete from PAR_DOCUMENTO where IDUNITADOC = ?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug(query);
            st.setLong(1, idUd);
            int updates = st.executeUpdate();
            return updates;
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Imported PAR_DOCUMENTO PK:PAR_UNITADOC FK:PAR_DOCUMENTO
     */
    public ParUnitadoc getParUnitadocByIdunitadoc(ParDocumento obj, java.sql.Connection con)
            throws java.sql.SQLException {
        ParUnitadocDAO x = new ParUnitadocDAO();
        ParUnitadoc o = x.retrieveByKey(obj.getIdunitadoc(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_DOCUMENTO"
     */
    public java.util.List<ParDocumento> getParDocumentosByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        java.util.List<ParDocumento> retRows = new java.util.ArrayList<>();
        ParDocumento curRow;

        String query = "select * from PAR_DOCUMENTO" + " where IDUNITADOC=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        ResultSet r = null;
        try {
            log.debug(query);
            st.setLong(1, idunitadoc);
            r = st.executeQuery();
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
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Retrieve from the database for table "PAR_DOCUMENTO"
     */
    @Deprecated
    public java.util.List<ParDocumento> getParDocumentosByIdunitadoc(Long idunitadoc, String orderByClause,
            Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDocumento> retRows = new java.util.ArrayList<ParDocumento>();
        ParDocumento curRow;

        String query = "select * from PAR_DOCUMENTO" + " where IDUNITADOC=" + idunitadoc;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
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
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Get all related PAR_COMPONENTE which have same iddocumento, idunitadoc
     */
    public java.util.List<ParComponente> getRelatedParComponenteByIddocumento(Long iddocumento, Long idunitadoc,
            Connection con) throws SQLException {
        ParComponenteVO x = new ParComponenteVO();
        return x.getParComponentesByIddocumentoIdunitadoc(iddocumento, idunitadoc, con);
    }

    /**
     * Get all related PAR_DATISPECIFICI which have same iddocumento, idunitadoc
     */
    public java.util.List<ParDatispecifici> getRelatedParDatispecificiByIddocumento(Long iddocumento, Long idunitadoc,
            Connection con) throws SQLException {
        ParDatispecificiVO x = new ParDatispecificiVO();
        return x.getParDatispecificisByIddocumentoIdunitadoc(iddocumento, idunitadoc, con);
    }

    /**
     * Retrieve from the database for table "PAR_DOCUMENTO"
     */
    public ParDocumento retrieveByIndex(Long iddocumento, Long idunitadoc, Connection con) throws SQLException {

        String query = "select * from PAR_DOCUMENTO" + " where IDDOCUMENTO=?" + " and IDUNITADOC=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        st.setLong(1, iddocumento);
        st.setLong(2, idunitadoc);
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery();
            ParDocumento obj = null;
            if (r.next()) {
                obj = new ParDocumento();
                getFromResultSet(obj, r);
            }
            return obj;
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
     * Updates the current object values into the database.
     */
    public int updateByIndex(ParDocumento obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_DOCUMENTO set IDDOCUMENTO= ?  , IDUNITADOC= ?  , ID_TIPO_DOC= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , "
                + "PROFILOAUTOREDOC= ?  , PROFILODESCRIZIONEDOC= ?  , DFDENOMINAZIONE= ?  , DFCOGNOME= ?  , DFNOME= ?  , DFCODFISCALE= ?  , DFPIVA= ?  , "
                + "DFDTEMISSIONE= ?  , DFPROGRESSIVO= ?  , DFREGISTRO= ?  , DFPERIODO= ?  , DFDTTERMINEEMISSIONE= ?  , TIPOLOGIA= ? , CD_VERSIONE_XSD=? , ID_TIPO_STRUT_DOC=?  "
                + " where IDDOCUMENTO=?  and IDUNITADOC=?";

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIddocumento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddocumento());
        }

        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }

        if (obj.getIdTipoDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoDoc());
        }

        if (obj.getFlgstato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgstato());
        }

        pst.setString(indice++, obj.getPgm());
        if (obj.getId() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getId());
        }

        pst.setString(indice++, obj.getProfiloautoredoc());
        pst.setString(indice++, obj.getProfilodescrizionedoc());
        pst.setString(indice++, obj.getDfdenominazione());
        pst.setString(indice++, obj.getDfcognome());
        pst.setString(indice++, obj.getDfnome());
        pst.setString(indice++, obj.getDfcodfiscale());
        pst.setString(indice++, obj.getDfpiva());
        if (obj.getDfdtemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdtemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        if (obj.getDfprogressivo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getDfprogressivo());
        }

        pst.setString(indice++, obj.getDfregistro());
        pst.setString(indice++, obj.getDfperiodo());
        if (obj.getDfdttermineemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdttermineemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        pst.setString(indice++, obj.getTipologia());
        pst.setString(indice++, obj.getCdVersioneXSD());
        pst.setNull(indice++, 3);

        pst.setLong(indice++, obj.getIddocumento());
        pst.setLong(indice++, obj.getIdunitadoc());

        try {
            log.debug("{}", preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }

    /**
     * Deletes from the database for table "PAR_DOCUMENTO"
     */
    public int deleteByIndex(ParDocumento obj, Connection con) throws SQLException {
        String query = "delete from PAR_DOCUMENTO where IDDOCUMENTO=?   and IDUNITADOC=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug("{}", query);
            st.setLong(1, obj.getIddocumento());
            st.setLong(2, obj.getIdunitadoc());
            int updates = st.executeUpdate();
            return updates;
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(ParDocumento obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into PAR_DOCUMENTO ( IDDOCUMENTO,IDUNITADOC,ID_TIPO_DOC,FLGSTATO,DTINS,DTAGG,PGM,ID,PROFILOAUTOREDOC,PROFILODESCRIZIONEDOC,DFDENOMINAZIONE,DFCOGNOME,DFNOME,DFCODFISCALE,DFPIVA,DFDTEMISSIONE,DFPROGRESSIVO,DFREGISTRO,DFPERIODO,DFDTTERMINEEMISSIONE,TIPOLOGIA, CD_VERSIONE_XSD, ID_TIPO_STRUT_DOC) values (? ,? ,? ,? , current_timestamp , current_timestamp ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?,?,?   )";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIddocumento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddocumento());
        }
        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }
        if (obj.getIdTipoDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoDoc());
        }
        if (obj.getFlgstato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgstato());
        }
        pst.setString(indice++, obj.getPgm());
        if (obj.getId() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getId());
        }
        pst.setString(indice++, obj.getProfiloautoredoc());
        pst.setString(indice++, obj.getProfilodescrizionedoc());
        pst.setString(indice++, obj.getDfdenominazione());
        pst.setString(indice++, obj.getDfcognome());
        pst.setString(indice++, obj.getDfnome());
        pst.setString(indice++, obj.getDfcodfiscale());
        pst.setString(indice++, obj.getDfpiva());
        if (obj.getDfdtemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdtemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }
        if (obj.getDfprogressivo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getDfprogressivo());
        }
        pst.setString(indice++, obj.getDfregistro());
        pst.setString(indice++, obj.getDfperiodo());
        if (obj.getDfdttermineemissione() != null) {
            pst.setObject(indice++, new java.sql.Date((obj.getDfdttermineemissione()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }
        pst.setString(indice++, obj.getTipologia());
        pst.setString(indice++, obj.getCdVersioneXSD());
        pst.setNull(indice++, 3);

        try {
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }

}

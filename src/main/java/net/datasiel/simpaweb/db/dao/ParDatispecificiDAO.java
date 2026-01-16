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
 * ParDatispecifici
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.pojo.ParDatispecifici;
import net.datasiel.simpaweb.db.pojo.ParDocumento;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.pojo.ParValoredatispecifici;
import net.datasiel.simpaweb.db.vo.ParValoredatispecificiVO;

public class ParDatispecificiDAO extends ParDatispecifici {

    private final Logger log = LoggerFactory.getLogger(ParDatispecificiDAO.class);

    private static final long serialVersionUID = 0L;
    protected static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "iddatispecifici", "idunitadoc", "iddocumento", "entitasacer", "idcomponente",
                "flgstato", "dtins", "dtagg", "pgm", "id" };
    }

    public ParDatispecificiDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(ParDatispecifici obj, ResultSet r) throws SQLException {
        if (r.getObject("IDDATISPECIFICI") == null) {
            obj.setIddatispecifici(null);
        } else {
            obj.setIddatispecifici(r.getLong("IDDATISPECIFICI"));
        }
        if (r.getObject("IDUNITADOC") == null) {
            obj.setIdunitadoc(null);
        } else {
            obj.setIdunitadoc(r.getLong("IDUNITADOC"));
        }
        if (r.getObject("IDDOCUMENTO") == null) {
            obj.setIddocumento(null);
        } else {
            obj.setIddocumento(r.getLong("IDDOCUMENTO"));
        }
        obj.setEntitasacer(r.getString("ENTITASACER"));
        if (r.getObject("IDCOMPONENTE") == null) {
            obj.setIdcomponente(null);
        } else {
            obj.setIdcomponente(r.getLong("IDCOMPONENTE"));
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
    }

    public String[] keyNames = { "iddatispecifici" };

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    public ParDatispecifici retrieveByKey(Long iddatispecifici, Connection con) throws SQLException {

        String query = "select * from PAR_DATISPECIFICI" + " where IDDATISPECIFICI=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddatispecifici);
            log.info("{} [ {} ]", query, iddatispecifici);
            r = st.executeQuery();
            ParDatispecifici obj = null;
            if (r.next()) {
                obj = new ParDatispecifici();
                getFromResultSet(obj, r);
            }
            return obj;
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParDatispecifici> retrieveWhere(String where, String orderByClause, Connection con)
            throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI" + " where " + where;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.info(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParDatispecifici();
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
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParDatispecifici> retrieveWhere(String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI" + " where " + where;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.info(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParDatispecifici();
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
    public int update(ParDatispecifici obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_DATISPECIFICI set IDDATISPECIFICI= ?  , IDUNITADOC= ?  , IDDOCUMENTO= ?  , ENTITASACER= ?  , IDCOMPONENTE= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  "
                + " where IDDATISPECIFICI=?";
        StringBuilder log_s = new StringBuilder(preparedQuery);
        
        int indice = 1;

        
        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            if (obj.getIddatispecifici() == null) {
                pst.setNull(indice++, 3);
                log_s.append("[3");
            } else {
                pst.setLong(indice++, obj.getIddatispecifici());
                log_s.append("[" + obj.getIddatispecifici());
            }
    
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
                log_s.append("," + obj.getIdunitadoc());
            }
    
            if (obj.getIddocumento() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIddocumento());
                log_s.append("," + obj.getIddocumento());
            }
    
            pst.setString(indice++, obj.getEntitasacer());
            log_s.append("," + obj.getEntitasacer());
    
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
                log_s.append("," + obj.getIdcomponente());
            }
    
            if (obj.getFlgstato() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getFlgstato());
                log_s.append("," + obj.getFlgstato());
            }
    
            pst.setString(indice++, obj.getPgm());
            log_s.append("," + obj.getPgm());
    
            if (obj.getId() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getId());
                log_s.append("," + obj.getId());
            }
    
            pst.setLong(indice++, obj.getIddatispecifici());
            log_s.append("," + obj.getIddatispecifici() + "]");
            log.info("{}", log_s);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", log_s, e);
            throw e;
        }
    }

    /**
     * Updates the current object values into the database.
     */
    @Deprecated
    public int updateWhere(ParDatispecifici obj, String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String preparedQuery = "update PAR_DATISPECIFICI set IDDATISPECIFICI= ?  , IDUNITADOC= ?  , IDDOCUMENTO= ?  , ENTITASACER= ?  , IDCOMPONENTE= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?   where "
                + where;

        
                
        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            int indice = 1;
            if (obj.getIddatispecifici() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIddatispecifici());
            }
    
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }
    
            if (obj.getIddocumento() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIddocumento());
            }
    
            pst.setString(indice++, obj.getEntitasacer());
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
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
            log.debug(preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        } 
    }

    /**
     * Deletes from the database for table "PAR_DATISPECIFICI"
     */
    public int delete(ParDatispecifici obj, Connection con) throws SQLException {
        String query = "delete from PAR_DATISPECIFICI where IDDATISPECIFICI=?";
        
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, obj.getIddatispecifici());
            log.info("{} [{}]", query, obj.getIddatispecifici());
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Deletes from the database for table "PAR_DATISPECIFICI"
     */
    @Deprecated
    public int deleteWhere(String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String query = "delete from PAR_DATISPECIFICI where " + where;
        java.sql.Statement st = con.createStatement();
        try {
            log.debug(query);
            int updates = st.executeUpdate(query);
            return updates;
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Imported PAR_DATISPECIFICI PK:PAR_COMPONENTE FK:PAR_DATISPECIFICI
     */
    public ParComponente getParComponenteByIddocumentoIdunitadocIdcomponente(ParDatispecifici obj,
            java.sql.Connection con) throws java.sql.SQLException {
        ParComponenteDAO x = new ParComponenteDAO();
        ParComponente o = x.retrieveByKey(obj.getIddocumento(), obj.getIdunitadoc(), obj.getIdcomponente(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    public java.util.List<ParDatispecifici> getParDatispecificisByIddocumentoIdunitadocIdcomponente(Long iddocumento,
            Long idunitadoc, Long idcomponente, Connection con) throws SQLException {
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI  where IDDOCUMENTO=? and IDUNITADOC=? and IDCOMPONENTE=? ";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddocumento);
            st.setLong(2, idunitadoc);
            st.setLong(3, idcomponente);
            log.info("{} - [{} , {}, {}]", query, iddocumento, idunitadoc, idcomponente);

            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParDatispecifici();
                getFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParDatispecifici> getParDatispecificisByIddocumentoIdunitadocIdcomponente(Long iddocumento,
            Long idunitadoc, Long idcomponente, String orderByClause, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI  where IDDOCUMENTO=? and IDUNITADOC=? and IDCOMPONENTE=?";
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParDatispecifici();
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
     * Imported PAR_DATISPECIFICI PK:PAR_DOCUMENTO FK:PAR_DATISPECIFICI
     */
    public ParDocumento getParDocumentoByIddocumentoIdunitadoc(ParDatispecifici obj, java.sql.Connection con)
            throws java.sql.SQLException {
        ParDocumentoDAO x = new ParDocumentoDAO();
        ParDocumento o = x.retrieveByKey(obj.getIddocumento(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    public java.util.List<ParDatispecifici> getParDatispecificisByIddocumentoIdunitadoc(Long iddocumento,
            Long idunitadoc, Connection con) throws SQLException {
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI" + " where IDDOCUMENTO=? and IDUNITADOC=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddocumento);
            st.setLong(2, idunitadoc);

            log.info("{} - [{} ,{}]", query, iddocumento, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParDatispecifici();
                getFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParDatispecifici> getParDatispecificisByIddocumentoIdunitadoc(Long iddocumento,
            Long idunitadoc, String orderByClause, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI" + " where IDDOCUMENTO=" + iddocumento + " and IDUNITADOC="
                + idunitadoc;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParDatispecifici();
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
     * Imported PAR_DATISPECIFICI PK:PAR_UNITADOC FK:PAR_DATISPECIFICI
     */
    public ParUnitadoc getParUnitadocByIdunitadoc(ParDatispecifici obj, java.sql.Connection con)
            throws java.sql.SQLException {
        ParUnitadocDAO x = new ParUnitadocDAO();
        ParUnitadoc o = x.retrieveByKey(obj.getIdunitadoc(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    public java.util.List<ParDatispecifici> getParDatispecificisByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI where IDUNITADOC=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idunitadoc);

            log.info("{} - [{}]", query, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParDatispecifici();
                getFromResultSet(curRow, r);
                retRows.add(curRow);
            }
            return retRows;
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParDatispecifici> getParDatispecificisByIdunitadoc(Long idunitadoc, String orderByClause,
            Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParDatispecifici> retRows = new java.util.ArrayList<ParDatispecifici>();
        ParDatispecifici curRow;

        String query = "select * from PAR_DATISPECIFICI" + " where IDUNITADOC=" + idunitadoc;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParDatispecifici();
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
     * Get all related PAR_VALOREDATISPECIFICI which have same iddatispecifici
     */
    public java.util.List<ParValoredatispecifici> getRelatedParValoredatispecificiByIddatispecifici(
            Long iddatispecifici, Connection con) throws SQLException {
        ParValoredatispecificiVO x = new ParValoredatispecificiVO();
        return x.getParValoredatispecificisByIddatispecifici(iddatispecifici, con);
    }

    /**
     * Retrieve from the database for table "PAR_DATISPECIFICI"
     */
    public ParDatispecifici retrieveByIndex(Long iddatispecifici, Long idunitadoc, Long iddocumento, Long idcomponente,
            Connection con) throws SQLException {

        String query = "select * from PAR_DATISPECIFICI" + " where IDDATISPECIFICI=?" + " and IDUNITADOC=?"
                + " and IDDOCUMENTO=?" + " and IDCOMPONENTE=?";
                ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddatispecifici);
            st.setLong(2, idunitadoc);
            st.setLong(3, iddocumento);
            st.setLong(4, idcomponente);
            r = st.executeQuery();
            log.info(query);
            ParDatispecifici obj = null;
            if (r.next()) {
                obj = new ParDatispecifici();
                getFromResultSet(obj, r);
            }
            return obj;
        } finally {
            if (r != null) {
                r.close();
            }
        }
    }

    /**
     * Updates the current object values into the database.
     */
    public int updateByIndex(ParDatispecifici obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_DATISPECIFICI set IDDATISPECIFICI= ?  , IDUNITADOC= ?  , IDDOCUMENTO= ?  , ENTITASACER= ?  , IDCOMPONENTE= ?  , FLGSTATO= ?  , "
                + "DTAGG= current_timestamp  , PGM= ?  , ID= ?  "
                + " where IDDATISPECIFICI=?  and IDUNITADOC=?  and IDDOCUMENTO=?  and IDCOMPONENTE=?";
        StringBuilder log_s = new StringBuilder(preparedQuery);
        
        int indice = 1;

        
        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            if (obj.getIddatispecifici() == null) {
                pst.setNull(indice++, 3);
                log_s.append(" - [3");
            } else {
                pst.setLong(indice++, obj.getIddatispecifici());
                log_s.append(" - [" + obj.getIddatispecifici());
            }
    
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
                log_s.append("," + obj.getIdunitadoc());
            }
    
            if (obj.getIddocumento() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIddocumento());
                log_s.append("," + obj.getIddocumento());
            }
    
            pst.setString(indice++, obj.getEntitasacer());
            log_s.append("," + obj.getEntitasacer());
    
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
                log_s.append("," + obj.getIdcomponente());
            }
    
            if (obj.getFlgstato() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getFlgstato());
                log_s.append("," + obj.getFlgstato());
            }
    
            pst.setString(indice++, obj.getPgm());
            log_s.append("," + obj.getPgm());
    
            if (obj.getId() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getId());
                log_s.append("," + obj.getId());
            }
            pst.setLong(indice++, obj.getIddatispecifici());
            log_s.append("," + obj.getIddatispecifici());
    
            pst.setLong(indice++, obj.getIdunitadoc());
            log_s.append("," + obj.getIdunitadoc());
    
            pst.setLong(indice++, obj.getIddocumento());
            log_s.append("," + obj.getIddocumento());
    
            pst.setLong(indice++, obj.getIdcomponente());
            log_s.append("," + obj.getIdcomponente() + "]");
            int updates = pst.executeUpdate();
            log.info("{}", log_s);
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        }
    }

    /**
     * Deletes from the database for table "PAR_DATISPECIFICI"
     */
    public int deleteByIndex(ParDatispecifici obj, Connection con) throws SQLException {
        String query = "delete from PAR_DATISPECIFICI where IDDATISPECIFICI=? and IDUNITADOC=? "
                + " and IDDOCUMENTO=? and IDCOMPONENTE=?";
        
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, obj.getIddatispecifici());
            st.setLong(2, obj.getIdunitadoc());
            st.setLong(3, obj.getIddocumento());
            st.setLong(4, obj.getIdcomponente());

            log.info("{} - [{}, {}, {}, {}]", query, obj.getIddatispecifici(), obj.getIdunitadoc(),
                    obj.getIddocumento(), obj.getIdcomponente());
            int updates = st.executeUpdate();
            return updates;
        } 
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(ParDatispecifici obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into PAR_DATISPECIFICI ( IDDATISPECIFICI,IDUNITADOC,IDDOCUMENTO,ENTITASACER,IDCOMPONENTE,FLGSTATO,DTINS,DTAGG,PGM,ID ) values (? ,? ,? ,? ,? ,? , current_timestamp , current_timestamp ,? ,?   )";
        
        StringBuilder logStr = new StringBuilder(prepQuery);

        
        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIddatispecifici() == null) {
                pst.setNull(indice++, 3);
                logStr.append(" - [3");
            } else {
                pst.setLong(indice++, obj.getIddatispecifici());
                logStr.append(" - [").append(obj.getIddatispecifici());
            }
    
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
                logStr.append(",").append(obj.getIdunitadoc());
            }
    
            if (obj.getIddocumento() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getIddocumento());
                logStr.append(",").append(obj.getIddocumento());
            }
    
            pst.setString(indice++, obj.getEntitasacer());
            logStr.append(",").append(obj.getEntitasacer());
    
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
                logStr.append(",").append(obj.getIdcomponente());
            }
    
            if (obj.getFlgstato() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getFlgstato());
                logStr.append(",").append(obj.getFlgstato());
            }
    
            pst.setString(indice++, obj.getPgm());
            logStr.append(",").append(obj.getPgm());
    
            if (obj.getId() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getId());
                logStr.append(",").append(obj.getId() + "]");
            }
            log.info("{}", logStr);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        } 
    }

    public Object getParObjectByIdDatiSpecificiAndEntitaSacer(Long idDatiSpecifici, String entita, Connection con)
            throws SQLException {
        Object result = null;
        StringBuilder SQL = new StringBuilder();
        SQL.append("SELECT IDDATISPECIFICI,");
        SQL.append("  IDUNITADOC,");
        SQL.append("  IDDOCUMENTO,");
        SQL.append("  ENTITASACER,");
        SQL.append("  IDCOMPONENTE,");
        SQL.append("  FLGSTATO,");
        SQL.append("  DTINS,");
        SQL.append("  DTAGG,");
        SQL.append("  PGM,");
        SQL.append("  ID");
        SQL.append(" FROM PAR_DATISPECIFICI");
        SQL.append(" WHERE IDDATISPECIFICI=?");
        SQL.append(" AND ENTITASACER      =?");
        
        ResultSet r = null;
        try (PreparedStatement pst = con.prepareStatement(SQL.toString())) {
            pst.setLong(1, idDatiSpecifici);
            pst.setString(2, entita);

            r = pst.executeQuery();

            if (r.next()) {
                ParDatispecifici obj = new ParDatispecifici();
                getFromResultSet(obj, r);
                if (obj.getIddocumento() != null) {

                    result = getParDocumentoByIddocumentoIdunitadoc(obj, con);

                }
                if (obj.getIdunitadoc() != null) {

                    result = getParUnitadocByIdunitadoc(obj, con);

                }

            }

        } finally {
            if (r != null) {
                r.close();
            }
        }

        return result;
    }
}

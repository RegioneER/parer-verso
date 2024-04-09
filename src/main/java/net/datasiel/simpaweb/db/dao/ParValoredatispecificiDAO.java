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
 * ParValoredatispecifici
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.ParDatispecifici;
import net.datasiel.simpaweb.db.pojo.ParValoredatispecifici;

public class ParValoredatispecificiDAO extends ParValoredatispecifici {

    private final Logger log = LoggerFactory.getLogger(ParValoredatispecificiDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idvaloredatispecifici", "iddatispecifici", "valore", "flgstato", "dtins", "dtagg",
                "pgm", "id", "idStrut", "idAttribDatiSpec" };
    }

    public ParValoredatispecificiDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(ParValoredatispecifici obj, ResultSet r) throws SQLException {
        if (r.getObject("IDVALOREDATISPECIFICI") == null) {
            obj.setIdvaloredatispecifici(null);
        } else {
            obj.setIdvaloredatispecifici(r.getLong("IDVALOREDATISPECIFICI"));
        }
        if (r.getObject("IDDATISPECIFICI") == null) {
            obj.setIddatispecifici(null);
        } else {
            obj.setIddatispecifici(r.getLong("IDDATISPECIFICI"));
        }
        obj.setValore(r.getString("VALORE"));
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
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        if (r.getObject("ID_ATTRIB_DATI_SPEC") == null) {
            obj.setIdAttribDatiSpec(null);
        } else {
            obj.setIdAttribDatiSpec(r.getLong("ID_ATTRIB_DATI_SPEC"));
        }
        // CD_VERSIONE_XSD
        obj.setCdVersioneXSD(r.getString("CD_VERSIONE_XSD"));

    }

    public String[] keyNames = { "idvaloredatispecifici" };

    /**
     * Retrieve from the database for table "PAR_VALOREDATISPECIFICI"
     */
    public ParValoredatispecifici retrieveByKey(Long idvaloredatispecifici, Connection con) throws SQLException {

        // String query = "select * from PAR_VALOREDATISPECIFICI"+" where IDVALOREDATISPECIFICI="+idvaloredatispecifici;
        String query = "select * from PAR_VALOREDATISPECIFICI" + " where IDVALOREDATISPECIFICI=?";
        // java.sql.Statement st = con.createStatement();
        java.sql.PreparedStatement st = con.prepareStatement(query);
        st.setLong(1, idvaloredatispecifici);
        ResultSet r = null;
        try {
            log.info(query + "[" + idvaloredatispecifici + "]");
            // ResultSet r = st.executeQuery(query);
            r = st.executeQuery();
            ParValoredatispecifici obj = null;
            if (r.next()) {
                obj = new ParValoredatispecifici();
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
     * Retrieve from the database for table "PAR_VALOREDATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParValoredatispecifici> retrieveWhere(String where, String orderByClause, Connection con)
            throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParValoredatispecifici> retRows = new java.util.ArrayList<ParValoredatispecifici>();
        ParValoredatispecifici curRow;

        String query = "select * from PAR_VALOREDATISPECIFICI" + " where " + where;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParValoredatispecifici();
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
     * Retrieve from the database for table "PAR_VALOREDATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParValoredatispecifici> retrieveWhere(String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParValoredatispecifici> retRows = new java.util.ArrayList<ParValoredatispecifici>();
        ParValoredatispecifici curRow;

        String query = "select * from PAR_VALOREDATISPECIFICI" + " where " + where;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParValoredatispecifici();
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
    public int update(ParValoredatispecifici obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_VALOREDATISPECIFICI set IDVALOREDATISPECIFICI= ?  , IDDATISPECIFICI= ?  , VALORE= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , ID_STRUT= ?  , "
                + "ID_ATTRIB_DATI_SPEC= ?, ,CD_VERSIONE_XSD=? " + " where IDVALOREDATISPECIFICI=?";

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIdvaloredatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdvaloredatispecifici());
        }

        if (obj.getIddatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddatispecifici());
        }

        pst.setString(indice++, obj.getValore());
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

        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }

        if (obj.getIdAttribDatiSpec() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdAttribDatiSpec());
        }
        pst.setString(indice, obj.getCdVersioneXSD());

        pst.setLong(indice++, obj.getIdvaloredatispecifici());

        try {
            log.info(preparedQuery);
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
     * Updates the current object values into the database.
     */
    @Deprecated
    public int updateWhere(ParValoredatispecifici obj, String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String preparedQuery = "update PAR_VALOREDATISPECIFICI set IDVALOREDATISPECIFICI= ?  , IDDATISPECIFICI= ?  , VALORE= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , ID_STRUT= ?  , ID_ATTRIB_DATI_SPEC= ?   where "
                + where;

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIdvaloredatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdvaloredatispecifici());
        }

        if (obj.getIddatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddatispecifici());
        }

        pst.setString(indice++, obj.getValore());
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

        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }

        if (obj.getIdAttribDatiSpec() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdAttribDatiSpec());
        }

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
     * Deletes from the database for table "PAR_VALOREDATISPECIFICI"
     */
    public int delete(ParValoredatispecifici obj, Connection con) throws SQLException {
        String query = "delete from PAR_VALOREDATISPECIFICI where IDVALOREDATISPECIFICI=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.info(query);
            st.setLong(1, obj.getIdvaloredatispecifici());
            int updates = st.executeUpdate();
            return updates;
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Deletes from the database for table "PAR_VALOREDATISPECIFICI"
     */
    public int deleteByIdDatiSpec(Long idDatiSpec, Connection con) throws SQLException {
        String query = "delete from PAR_VALOREDATISPECIFICI where IDDATISPECIFICI=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug(query);
            st.setLong(1, idDatiSpec);
            int updates = st.executeUpdate();
            return updates;
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Imported PAR_VALOREDATISPECIFICI PK:PAR_DATISPECIFICI FK:PAR_VALOREDATISPECIFICI
     */
    public ParDatispecifici getParDatispecificiByIddatispecifici(ParValoredatispecifici obj, java.sql.Connection con)
            throws java.sql.SQLException {
        ParDatispecificiDAO x = new ParDatispecificiDAO();
        ParDatispecifici o = x.retrieveByKey(obj.getIddatispecifici(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_VALOREDATISPECIFICI"
     */
    public java.util.List<ParValoredatispecifici> getParValoredatispecificisByIddatispecifici(Long iddatispecifici,
            Connection con) throws SQLException {
        java.util.List<ParValoredatispecifici> retRows = new java.util.ArrayList<ParValoredatispecifici>();
        ParValoredatispecifici curRow;

        String query = "select * from PAR_VALOREDATISPECIFICI where IDDATISPECIFICI=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        ResultSet r = null;
        try {
            log.debug(query);
            st.setLong(1, iddatispecifici);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParValoredatispecifici();
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
     * Retrieve from the database for table "PAR_VALOREDATISPECIFICI"
     */
    @Deprecated
    public java.util.List<ParValoredatispecifici> getParValoredatispecificisByIddatispecifici(Long iddatispecifici,
            String orderByClause, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParValoredatispecifici> retRows = new java.util.ArrayList<ParValoredatispecifici>();
        ParValoredatispecifici curRow;

        String query = "select * from PAR_VALOREDATISPECIFICI" + " where IDDATISPECIFICI=" + iddatispecifici;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParValoredatispecifici();
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
     * Retrieve from the database for table "PAR_VALOREDATISPECIFICI"
     */
    public ParValoredatispecifici retrieveByIndex(Long idvaloredatispecifici, Long iddatispecifici, Connection con)
            throws SQLException {

        // String query = "select * from PAR_VALOREDATISPECIFICI"+" where
        // IDVALOREDATISPECIFICI="+idvaloredatispecifici+" and IDDATISPECIFICI="+iddatispecifici;
        String query = "select * from PAR_VALOREDATISPECIFICI" + " where IDVALOREDATISPECIFICI=?"
                + " and IDDATISPECIFICI=?";
        // java.sql.Statement st = con.createStatement();
        java.sql.PreparedStatement st = con.prepareStatement(query);
        st.setLong(1, idvaloredatispecifici);
        st.setLong(2, iddatispecifici);
        ResultSet r = null;
        try {
            log.debug(query);
            // ResultSet r = st.executeQuery(query);
            r = st.executeQuery();
            ParValoredatispecifici obj = null;
            if (r.next()) {
                obj = new ParValoredatispecifici();
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
    public int updateByIndex(ParValoredatispecifici obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_VALOREDATISPECIFICI set IDVALOREDATISPECIFICI= ?  , IDDATISPECIFICI= ?  , VALORE= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , ID_STRUT= ?  , ID_ATTRIB_DATI_SPEC= ?  "
                + " where IDVALOREDATISPECIFICI=? and IDDATISPECIFICI= ? ";

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIdvaloredatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdvaloredatispecifici());
        }

        if (obj.getIddatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddatispecifici());
        }

        pst.setString(indice++, obj.getValore());
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

        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }

        if (obj.getIdAttribDatiSpec() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdAttribDatiSpec());
        }

        pst.setLong(indice++, obj.getIdvaloredatispecifici());
        pst.setLong(indice++, obj.getIddatispecifici());

        try {
            log.info(preparedQuery);
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
     * Deletes from the database for table "PAR_VALOREDATISPECIFICI"
     */
    public int deleteByIndex(ParValoredatispecifici obj, Connection con) throws SQLException {
        String query = "delete from PAR_VALOREDATISPECIFICI where IDVALOREDATISPECIFICI=? and IDDATISPECIFICI=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug(query);
            st.setLong(1, obj.getIdvaloredatispecifici());
            st.setLong(2, obj.getIddatispecifici());
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
    public int insertPrepared(ParValoredatispecifici obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into PAR_VALOREDATISPECIFICI ( IDVALOREDATISPECIFICI,IDDATISPECIFICI,VALORE,FLGSTATO,DTINS,DTAGG,PGM,ID,ID_STRUT,ID_ATTRIB_DATI_SPEC,CD_VERSIONE_XSD ) values (? ,? ,? ,? , current_timestamp , current_timestamp ,? ,? ,? ,?,?   )";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIdvaloredatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdvaloredatispecifici());
        }
        if (obj.getIddatispecifici() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIddatispecifici());
        }
        pst.setString(indice++, obj.getValore());
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
        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }
        if (obj.getIdAttribDatiSpec() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdAttribDatiSpec());
        }
        if (obj.getCdVersioneXSD() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getCdVersioneXSD());
        }
        try {
            log.debug(prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + prepQuery);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }
}

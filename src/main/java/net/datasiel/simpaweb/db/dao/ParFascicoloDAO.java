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
 * ParFascicolo
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.ParFascicolo;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;

public class ParFascicoloDAO extends ParFascicolo {

    private final Logger log = LoggerFactory.getLogger(ParFascicoloDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idfascicolo", "idunitadoc", "codtipofascicolo", "identificativo", "oggetto",
                "idsottofascicolo", "oggettosottofascicolo", "classifica", "flgstato", "dtins", "dtagg", "pgm", "id" };
    }

    public ParFascicoloDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(ParFascicolo obj, ResultSet r) throws SQLException {
        if (r.getObject("IDFASCICOLO") == null) {
            obj.setIdfascicolo(null);
        } else {
            obj.setIdfascicolo(r.getLong("IDFASCICOLO"));
        }
        if (r.getObject("IDUNITADOC") == null) {
            obj.setIdunitadoc(null);
        } else {
            obj.setIdunitadoc(r.getLong("IDUNITADOC"));
        }
        obj.setCodtipofascicolo(r.getString("CODTIPOFASCICOLO"));
        obj.setIdentificativo(r.getString("IDENTIFICATIVO"));
        obj.setOggetto(r.getString("OGGETTO"));
        obj.setIdsottofascicolo(r.getString("IDSOTTOFASCICOLO"));
        obj.setOggettosottofascicolo(r.getString("OGGETTOSOTTOFASCICOLO"));
        obj.setClassifica(r.getString("CLASSIFICA"));
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

    public String[] keyNames = { "idunitadoc", "idfascicolo" };

    /**
     * Retrieve from the database for table "PAR_FASCICOLO"
     */
    public ParFascicolo retrieveByKey(Long idunitadoc, Long idfascicolo, Connection con) throws SQLException {

        String query = "select * from PAR_FASCICOLO" + " where IDUNITADOC=?" + " and IDFASCICOLO=?";

        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idunitadoc);
            st.setLong(2, idfascicolo);
            log.debug("{}", query);
            r = st.executeQuery();
            ParFascicolo obj = null;
            if (r.next()) {
                obj = new ParFascicolo();
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
     * Retrieve from the database for table "PAR_FASCICOLO"
     */
    @Deprecated
    public java.util.List<ParFascicolo> retrieveWhere(String where, String orderByClause, Connection con)
            throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParFascicolo> retRows = new java.util.ArrayList<ParFascicolo>();
        ParFascicolo curRow;

        String query = "select * from PAR_FASCICOLO" + " where " + where;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug("{}", query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParFascicolo();
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
     * Retrieve from the database for table "PAR_FASCICOLO"
     */
    public java.util.List<ParFascicolo> retrieveByIdUdCodTipoFascicoloP(Long idunitadoc, Connection con)
            throws SQLException {
        java.util.List<ParFascicolo> retRows = new java.util.ArrayList<ParFascicolo>();
        ParFascicolo curRow;

        String query = "select * from PAR_FASCICOLO" + " where IDUNITADOC = ? and CODTIPOFASCICOLO = 'P'";

        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug("{}", query);
            st.setLong(1, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParFascicolo();
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
     * Retrieve from the database for table "PAR_FASCICOLO"
     */
    public java.util.List<ParFascicolo> retrieveByIdUdCodTipoFascicoloS(Long idunitadoc, Connection con)
            throws SQLException {
        java.util.List<ParFascicolo> retRows = new java.util.ArrayList<ParFascicolo>();
        ParFascicolo curRow;

        String query = "select * from PAR_FASCICOLO" + " where IDUNITADOC = ? and CODTIPOFASCICOLO = 'S'";

        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug("{}", query);
            st.setLong(1, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParFascicolo();
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
     * Updates the current object values into the database.
     */
    public int update(ParFascicolo obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_FASCICOLO set IDFASCICOLO= ?  , IDUNITADOC= ?  , CODTIPOFASCICOLO= ?  , IDENTIFICATIVO= ?  , "
                + "OGGETTO= ?  , IDSOTTOFASCICOLO= ?  , OGGETTOSOTTOFASCICOLO= ?  , CLASSIFICA= ?  , "
                + "FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  "
                + " where IDUNITADOC=? and IDFASCICOLO=?";

        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            int indice = 1;
            if (obj.getIdfascicolo() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdfascicolo());
            }

            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }

            pst.setString(indice++, obj.getCodtipofascicolo());
            pst.setString(indice++, obj.getIdentificativo());
            pst.setString(indice++, obj.getOggetto());
            pst.setString(indice++, obj.getIdsottofascicolo());
            pst.setString(indice++, obj.getOggettosottofascicolo());
            pst.setString(indice++, obj.getClassifica());
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

            pst.setLong(indice++, obj.getIdunitadoc());
            pst.setLong(indice++, obj.getIdfascicolo());
            log.debug("{}", preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + preparedQuery);
            throw e;
        }
    }

    /**
     * Updates the current object values into the database.
     */
    @Deprecated
    public int updateWhere(ParFascicolo obj, String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String preparedQuery = "update PAR_FASCICOLO set IDFASCICOLO= ?  , IDUNITADOC= ?  , CODTIPOFASCICOLO= ?  , IDENTIFICATIVO= ?  , OGGETTO= ?  , IDSOTTOFASCICOLO= ?  , OGGETTOSOTTOFASCICOLO= ?  , CLASSIFICA= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?   where "
                + where;

        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            int indice = 1;
            if (obj.getIdfascicolo() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdfascicolo());
            }

            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }

            pst.setString(indice++, obj.getCodtipofascicolo());
            pst.setString(indice++, obj.getIdentificativo());
            pst.setString(indice++, obj.getOggetto());
            pst.setString(indice++, obj.getIdsottofascicolo());
            pst.setString(indice++, obj.getOggettosottofascicolo());
            pst.setString(indice++, obj.getClassifica());
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
            log.debug("{}", preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + preparedQuery);
            throw e;
        }
    }

    /**
     * Deletes from the database for table "PAR_FASCICOLO"
     */
    public int delete(ParFascicolo obj, Connection con) throws SQLException {
        String query = "delete from PAR_FASCICOLO where IDUNITADOC=? and IDFASCICOLO=?";

        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug("{}", query);
            st.setLong(1, obj.getIdunitadoc());
            st.setLong(2, obj.getIdfascicolo());
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Deletes from the database for table "PAR_FASCICOLO"
     */
    public int deleteByIdUd(Long idUd, Connection con) throws SQLException {
        String query = "delete from PAR_FASCICOLO where IDUNITADOC = ?";

        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug("{}", query);
            st.setLong(1, idUd);
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Imported PAR_FASCICOLO PK:PAR_UNITADOC FK:PAR_FASCICOLO
     */
    public ParUnitadoc getParUnitadocByIdunitadoc(ParFascicolo obj, java.sql.Connection con)
            throws java.sql.SQLException {
        ParUnitadocDAO x = new ParUnitadocDAO();
        ParUnitadoc o = x.retrieveByKey(obj.getIdunitadoc(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_FASCICOLO"
     */
    public java.util.List<ParFascicolo> getParFascicolosByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        java.util.List<ParFascicolo> retRows = new java.util.ArrayList<ParFascicolo>();
        ParFascicolo curRow;

        String query = "select * from PAR_FASCICOLO" + " where IDUNITADOC=?";

        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug("{}", query);
            st.setLong(1, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParFascicolo();
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
     * Retrieve from the database for table "PAR_FASCICOLO"
     */
    @Deprecated
    public java.util.List<ParFascicolo> getParFascicolosByIdunitadoc(Long idunitadoc, String orderByClause,
            Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParFascicolo> retRows = new java.util.ArrayList<ParFascicolo>();
        ParFascicolo curRow;

        String query = "select * from PAR_FASCICOLO" + " where IDUNITADOC=" + idunitadoc;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParFascicolo();
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
     * Retrieve from the database for table "PAR_FASCICOLO"
     */
    public ParFascicolo retrieveByIndex(Long idfascicolo, Long idunitadoc, Connection con) throws SQLException {

        // String query = "select * from PAR_FASCICOLO"+" where IDFASCICOLO="+idfascicolo+" and IDUNITADOC="+idunitadoc;
        String query = "select * from PAR_FASCICOLO" + " where IDFASCICOLO=?" + " and IDUNITADOC=?";
        // java.sql.Statement st = con.createStatement();

        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idfascicolo);
            st.setLong(2, idunitadoc);
            log.debug(query);
            // ResultSet r = st.executeQuery(query);
            r = st.executeQuery();
            ParFascicolo obj = null;
            if (r.next()) {
                obj = new ParFascicolo();
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
    public int updateByIndex(ParFascicolo obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_FASCICOLO set IDFASCICOLO= ?  , IDUNITADOC= ?  , CODTIPOFASCICOLO= ?  , IDENTIFICATIVO= ?  , OGGETTO= ?  , IDSOTTOFASCICOLO= ?  , OGGETTOSOTTOFASCICOLO= ?  , "
                + "CLASSIFICA= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  "
                + " where IDFASCICOLO=?  and IDUNITADOC=?";

        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            int indice = 1;
            if (obj.getIdfascicolo() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdfascicolo());
            }

            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }

            pst.setString(indice++, obj.getCodtipofascicolo());
            pst.setString(indice++, obj.getIdentificativo());
            pst.setString(indice++, obj.getOggetto());
            pst.setString(indice++, obj.getIdsottofascicolo());
            pst.setString(indice++, obj.getOggettosottofascicolo());
            pst.setString(indice++, obj.getClassifica());
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

            pst.setLong(indice++, obj.getIdfascicolo());
            pst.setLong(indice++, obj.getIdunitadoc());
            log.debug("{}", preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        }
    }

    /**
     * Deletes from the database for table "PAR_FASCICOLO"
     */
    public int deleteByIndex(ParFascicolo obj, Connection con) throws SQLException {
        String query = "delete from PAR_FASCICOLO where IDFASCICOLO=? and IDUNITADOC=? ";

        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug("{}", query);
            st.setLong(1, obj.getIdfascicolo());
            st.setLong(2, obj.getIdunitadoc());
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(ParFascicolo obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into PAR_FASCICOLO ( IDFASCICOLO,IDUNITADOC,CODTIPOFASCICOLO,IDENTIFICATIVO,OGGETTO,IDSOTTOFASCICOLO,OGGETTOSOTTOFASCICOLO,CLASSIFICA,FLGSTATO,DTINS,DTAGG,PGM,ID ) values (? ,? ,? ,? ,? ,? ,? ,? ,? , current_timestamp , current_timestamp ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdfascicolo() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdfascicolo());
            }
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }
            pst.setString(indice++, obj.getCodtipofascicolo());
            pst.setString(indice++, obj.getIdentificativo());
            pst.setString(indice++, obj.getOggetto());
            pst.setString(indice++, obj.getIdsottofascicolo());
            pst.setString(indice++, obj.getOggettosottofascicolo());
            pst.setString(indice++, obj.getClassifica());
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
            log.debug("{}", prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        }
    }
}

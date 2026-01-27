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
 * ParCollegamento
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.ParCollegamento;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;

public class ParCollegamentoDAO extends ParCollegamento {

    private final Logger log = LoggerFactory.getLogger(ParCollegamentoDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idcollegamento", "idunitadoc", "idStrut", "numero", "anno", "idRegistroUnitaDoc",
                "flgstato", "dtins", "dtagg", "pgm", "id", "descrizione" };
    }

    public ParCollegamentoDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(ParCollegamento obj, ResultSet r) throws SQLException {
        if (r.getObject("IDCOLLEGAMENTO") == null) {
            obj.setIdcollegamento(null);
        } else {
            obj.setIdcollegamento(r.getLong("IDCOLLEGAMENTO"));
        }
        if (r.getObject("IDUNITADOC") == null) {
            obj.setIdunitadoc(null);
        } else {
            obj.setIdunitadoc(r.getLong("IDUNITADOC"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        obj.setNumero(r.getString("NUMERO"));
        if (r.getObject("ANNO") == null) {
            obj.setAnno(null);
        } else {
            obj.setAnno(r.getLong("ANNO"));
        }
        if (r.getObject("ID_REGISTRO_UNITA_DOC") == null) {
            obj.setIdRegistroUnitaDoc(null);
        } else {
            obj.setIdRegistroUnitaDoc(r.getLong("ID_REGISTRO_UNITA_DOC"));
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
        obj.setDescrizione(r.getString("DESCRIZIONE"));
    }

    public String[] keyNames = { "idcollegamento" };

    /**
     * Retrieve from the database for table "PAR_COLLEGAMENTO"
     */
    public ParCollegamento retrieveByKey(Long idcollegamento, Connection con) throws SQLException {

        String query = "select * from PAR_COLLEGAMENTO" + " where IDCOLLEGAMENTO=?";

        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idcollegamento);
            log.debug(query);
            r = st.executeQuery();
            ParCollegamento obj = null;
            if (r.next()) {
                obj = new ParCollegamento();
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
     * Retrieve from the database for table "PAR_COLLEGAMENTO"
     */
    public java.util.List<ParCollegamento> retrieveByIdUdOrderByIdCollegamento(Long idUnitadoc, Connection con)
            throws SQLException {
        java.util.List<ParCollegamento> retRows = new java.util.ArrayList<ParCollegamento>();
        ParCollegamento curRow;

        String query = "select * from PAR_COLLEGAMENTO  where idunitadoc = ? order by idcollegamento";
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setLong(1, idUnitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParCollegamento();
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
     * Retrieve from the database for table "PAR_COLLEGAMENTO"
     */
    @Deprecated
    public java.util.List<ParCollegamento> retrieveWhere(String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParCollegamento> retRows = new java.util.ArrayList<ParCollegamento>();
        ParCollegamento curRow;

        String query = "select * from PAR_COLLEGAMENTO" + " where " + where;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParCollegamento();
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
    public int update(ParCollegamento obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_COLLEGAMENTO set IDCOLLEGAMENTO= ?  , IDUNITADOC= ?  , ID_STRUT= ?  , NUMERO= ?  , ANNO= ?  , ID_REGISTRO_UNITA_DOC= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , DESCRIZIONE= ?  "
                + " where IDCOLLEGAMENTO=?";

        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            int indice = 1;
            if (obj.getIdcollegamento() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdcollegamento());
            }

            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }

            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }

            pst.setString(indice++, obj.getNumero());
            if (obj.getAnno() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getAnno());
            }

            if (obj.getIdRegistroUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
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

            pst.setString(indice++, obj.getDescrizione());
            pst.setLong(indice++, obj.getIdcollegamento());
            log.debug(preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        }
    }

    /**
     * Updates the current object values into the database.
     */
    @Deprecated
    public int updateWhere(ParCollegamento obj, String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String preparedQuery = "update PAR_COLLEGAMENTO set IDCOLLEGAMENTO= ?  , IDUNITADOC= ?  , ID_STRUT= ?  , NUMERO= ?  , ANNO= ?  , ID_REGISTRO_UNITA_DOC= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , DESCRIZIONE= ?   where "
                + where;

        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            int indice = 1;
            if (obj.getIdcollegamento() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdcollegamento());
            }

            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }

            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }

            pst.setString(indice++, obj.getNumero());
            if (obj.getAnno() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getAnno());
            }

            if (obj.getIdRegistroUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
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

            pst.setString(indice++, obj.getDescrizione());
            log.debug(preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        }
    }

    /**
     * Deletes from the database for table "PAR_COLLEGAMENTO"
     */
    public int delete(ParCollegamento obj, Connection con) throws SQLException {
        String query = "delete from PAR_COLLEGAMENTO where IDCOLLEGAMENTO=?";

        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setLong(1, obj.getIdcollegamento());
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Deletes from the database for table "PAR_COLLEGAMENTO"
     */
    public int deleteByIdUd(Long idUd, Connection con) throws SQLException {
        String query = "delete from PAR_COLLEGAMENTO where IDUNITADOC = ?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setLong(1, idUd);
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Imported PAR_COLLEGAMENTO PK:PAR_UNITADOC FK:PAR_COLLEGAMENTO
     */
    public ParUnitadoc getParUnitadocByIdunitadoc(ParCollegamento obj, java.sql.Connection con)
            throws java.sql.SQLException {
        ParUnitadocDAO x = new ParUnitadocDAO();
        ParUnitadoc o = x.retrieveByKey(obj.getIdunitadoc(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_COLLEGAMENTO"
     */
    public java.util.List<ParCollegamento> getParCollegamentosByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        java.util.List<ParCollegamento> retRows = new java.util.ArrayList<ParCollegamento>();
        ParCollegamento curRow;

        String query = "select * from PAR_COLLEGAMENTO" + " where IDUNITADOC= ?";
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setLong(1, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParCollegamento();
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
     * Retrieve from the database for table "PAR_COLLEGAMENTO"
     */
    @Deprecated
    public java.util.List<ParCollegamento> getParCollegamentosByIdunitadoc(Long idunitadoc, String orderByClause,
            Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParCollegamento> retRows = new java.util.ArrayList<ParCollegamento>();
        ParCollegamento curRow;

        String query = "select * from PAR_COLLEGAMENTO  where IDUNITADOC= ? ";
        query += " order by " + orderByClause;
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setLong(1, idunitadoc);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParCollegamento();
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
     * Retrieve from the database for table "PAR_COLLEGAMENTO"
     */
    public ParCollegamento retrieveByIndex(Long idcollegamento, Long idunitadoc, Connection con) throws SQLException {

        String query = "select * from PAR_COLLEGAMENTO" + " where IDCOLLEGAMENTO=?" + " and IDUNITADOC=?";
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query);) {
            st.setLong(1, idcollegamento);
            st.setLong(2, idunitadoc);
            log.debug(query);
            r = st.executeQuery();
            ParCollegamento obj = null;
            if (r.next()) {
                obj = new ParCollegamento();
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
    public int updateByIndex(ParCollegamento obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_COLLEGAMENTO set IDCOLLEGAMENTO= ?  , IDUNITADOC= ?  , ID_STRUT= ?  , "
                + "NUMERO= ?  , ANNO= ?  , ID_REGISTRO_UNITA_DOC= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , "
                + "PGM= ?  , ID= ?  , DESCRIZIONE= ?   where IDCOLLEGAMENTO=?  and IDUNITADOC=?";

        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            int indice = 1;
            if (obj.getIdcollegamento() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdcollegamento());
            }

            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }

            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }

            pst.setString(indice++, obj.getNumero());
            if (obj.getAnno() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getAnno());
            }

            if (obj.getIdRegistroUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
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

            pst.setString(indice++, obj.getDescrizione());
            pst.setLong(indice++, obj.getIdcollegamento());
            pst.setLong(indice++, obj.getIdunitadoc());
            log.debug(preparedQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        }
    }

    /**
     * Deletes from the database for table "PAR_COLLEGAMENTO"
     */
    public int deleteByIndex(ParCollegamento obj, Connection con) throws SQLException {
        String query = "delete from PAR_COLLEGAMENTO where IDCOLLEGAMENTO=? and IDUNITADOC=?";
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setLong(1, obj.getIdcollegamento());
            st.setLong(2, obj.getIdunitadoc());
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(ParCollegamento obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into PAR_COLLEGAMENTO ( IDCOLLEGAMENTO,IDUNITADOC,ID_STRUT,NUMERO,ANNO,ID_REGISTRO_UNITA_DOC,FLGSTATO,DTINS,DTAGG,PGM,ID,DESCRIZIONE ) values (? ,? ,? ,? ,? ,? ,? , current_timestamp , current_timestamp ,? ,? ,?   )";

        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIdcollegamento() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdcollegamento());
            }
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
            }
            if (obj.getIdStrut() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdStrut());
            }
            pst.setString(indice++, obj.getNumero());
            if (obj.getAnno() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getAnno());
            }
            if (obj.getIdRegistroUnitaDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
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
            pst.setString(indice++, obj.getDescrizione());
            log.debug(prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        }
    }

}

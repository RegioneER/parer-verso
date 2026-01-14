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

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.sql.PreparedStatement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.blobs.Blob;

/**
 * ParComponente
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.pojo.ParDatispecifici;
import net.datasiel.simpaweb.db.pojo.ParDocumento;
import net.datasiel.simpaweb.db.vo.ParDatispecificiVO;

public class ParComponenteDAO extends ParComponente {

    private final Logger log = LoggerFactory.getLogger(ParComponenteDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "iddocumento", "idunitadoc", "idcomponente", "urnfile", "nome", "rifnumero",
                "rifanno", "riftiporegistro", "codallegato", "flgstato", "dtins", "dtagg", "pgm", "id",
                "idFormatoFileDoc", "idTipoCompDoc", "flgFirmaPerRifTemp", "dataRifTemp", "dsHashFileVers" };
    }

    public ParComponenteDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(ParComponente obj, ResultSet r) throws SQLException {
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
        if (r.getObject("IDCOMPONENTE") == null) {
            obj.setIdcomponente(null);
        } else {
            obj.setIdcomponente(r.getLong("IDCOMPONENTE"));
        }
        obj.setUrnfile(r.getString("URNFILE"));
        obj.setNome(r.getString("NOME"));
        obj.setRifnumero(r.getString("RIFNUMERO"));
        if (r.getObject("RIFANNO") == null) {
            obj.setRifanno(null);
        } else {
            obj.setRifanno(r.getLong("RIFANNO"));
        }
        if (r.getObject("RIFTIPOREGISTRO") == null) {
            obj.setRiftiporegistro(null);
        } else {
            obj.setRiftiporegistro(r.getLong("RIFTIPOREGISTRO"));
        }
        obj.setCodallegato(r.getString("CODALLEGATO"));
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
        if (r.getObject("ID_FORMATO_FILE_DOC") == null) {
            obj.setIdFormatoFileDoc(null);
        } else {
            obj.setIdFormatoFileDoc(r.getLong("ID_FORMATO_FILE_DOC"));
        }
        if (r.getObject("ID_TIPO_COMP_DOC") == null) {
            obj.setIdTipoCompDoc(null);
        } else {
            obj.setIdTipoCompDoc(r.getLong("ID_TIPO_COMP_DOC"));
        }
        obj.setFlgFirmaPerRifTemp(r.getBoolean("FLGUTFIRMARIFERIMENTOTEMP"));
        obj.setDataRifTemp(r.getDate("DATARIFERIMENTOTEMP"));
        // DESCRIFERIMENTOTEMP
        obj.setDescRifTemp(r.getString("DESCRIFERIMENTOTEMP"));

        // Hash file
        if (r.getObject("DS_HASH_FILE_VERS") == null) {
            obj.setDsHashFileVers(null);
        } else {
            obj.setDsHashFileVers(r.getString("DS_HASH_FILE_VERS"));
        }

    }

    public String[] keyNames = { "idunitadoc", "iddocumento", "idcomponente" };

    /**
     * Retrieve from the database for table "PAR_COMPONENTE"
     */
    public ParComponente retrieveByKey(Long idunitadoc, Long iddocumento, Long idcomponente, Connection con)
            throws SQLException {

        String query = "select * from PAR_COMPONENTE" + " where IDUNITADOC=?" + " and IDDOCUMENTO=?"
                + " and IDCOMPONENTE=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idunitadoc);
            st.setLong(2, iddocumento);
            st.setLong(3, idcomponente);
            log.info("{} - [{} ,{} , {}]", query, idunitadoc, iddocumento, idcomponente);
            r = st.executeQuery();
            ParComponente obj = null;
            if (r.next()) {
                obj = new ParComponente();
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
     * Retrieve from the database for table "PAR_COMPONENTE"
     */
    public java.util.List<ParComponente> retrieveByIddocumentoOrderByIdComp(Long idDocumento, Connection con)
            throws SQLException {
        java.util.List<ParComponente> retRows = new java.util.ArrayList<ParComponente>();
        ParComponente curRow;

        String query = "select * from PAR_COMPONENTE" + " where iddocumento = ?  order by idcomponente";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idDocumento);
            log.debug("{} - [{}]", query, idDocumento);

            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParComponente();
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
     * Retrieve from the database for table "PAR_COMPONENTE"
     */
    public java.util.List<ParComponente> retrieveByCodallegatoNoBlob(String code, Connection con) throws SQLException {
        java.util.List<ParComponente> retRows = new java.util.ArrayList<ParComponente>();
        ParComponente curRow;

        String query = "SELECT * " + "FROM PAR_COMPONENTE " + " where CODALLEGATO=?";

        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setString(1, code);
            log.debug("{} - [{}]", query, code);

            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParComponente();
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
    public int update(ParComponente obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_COMPONENTE set IDDOCUMENTO= ?  ," + " IDUNITADOC= ?  ,"
                + " IDCOMPONENTE= ?  ," + " URNFILE= ?  ," + " NOME= ?  ," + " RIFNUMERO= ?  ," + " RIFANNO= ?  ,"
                + " RIFTIPOREGISTRO= ?  ," + " CODALLEGATO= ?  ," + " FLGSTATO= ?  ," + " DTAGG= current_timestamp  ,"
                + " PGM= ?  ," + " ID= ?  ," + " ID_FORMATO_FILE_DOC= ?," + " ID_TIPO_COMP_DOC=?,"
                + " FLGUTFIRMARIFERIMENTOTEMP=?," + " DATARIFERIMENTOTEMP=?,  " + " DESCRIFERIMENTOTEMP=?, "
                + " DS_HASH_FILE_VERS=? " + " where IDUNITADOC=?" + " and IDDOCUMENTO=?" + " and IDCOMPONENTE=?";

        
        int indice = 1;
        StringBuilder log_s = new StringBuilder(preparedQuery);

        
        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            if (obj.getIddocumento() == null) {
                pst.setNull(indice++, 3);
                log_s.append(" - [3");
            } else {
                pst.setLong(indice++, obj.getIddocumento());
                log_s.append(" - [").append(obj.getIddocumento());
            }
    
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
                log_s.append(",").append(obj.getIdunitadoc());
            }
    
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
                log_s.append(",").append(obj.getIdcomponente());
            }
    
            pst.setString(indice++, obj.getUrnfile());
            log_s.append(",").append(obj.getUrnfile());
    
            pst.setString(indice++, obj.getNome());
            log_s.append(",").append(obj.getNome());
    
            pst.setString(indice++, obj.getRifnumero());
            log_s.append(",").append(obj.getRifnumero());
    
            if (obj.getRifanno() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getRifanno());
                log_s.append(",").append(obj.getRifanno());
            }
    
            if (obj.getRiftiporegistro() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getRiftiporegistro());
                log_s.append(",").append(obj.getRiftiporegistro());
            }
    
            pst.setString(indice++, obj.getCodallegato());
            if (obj.getFlgstato() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getFlgstato());
                log_s.append(",").append(obj.getFlgstato());
            }
    
            pst.setString(indice++, obj.getPgm());
            log_s.append(",").append(obj.getPgm());
    
            if (obj.getId() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getId());
                log_s.append(",").append(obj.getId());
            }
    
            if (obj.getIdFormatoFileDoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileDoc());
                log_s.append(",").append(obj.getIdFormatoFileDoc());
            }
    
            if (obj.getIdTipoCompDoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdTipoCompDoc());
                log_s.append(",").append(obj.getIdTipoCompDoc());
            }
    
            if (obj.getFlgFirmaPerRifTemp() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setBoolean(indice++, obj.getFlgFirmaPerRifTemp());
                log_s.append(",").append(obj.getFlgFirmaPerRifTemp());
            }
            if (obj.getDataRifTemp() == null) {
                pst.setNull(indice++, Types.DATE);
                log_s.append(",3");
            } else {
                pst.setDate(indice++, new java.sql.Date(((java.util.Date) obj.getDataRifTemp()).getTime()));
                log_s.append(",").append(new java.sql.Date(((java.util.Date) obj.getDataRifTemp()).getTime()).toString());
            }
            pst.setString(indice++, obj.getDescRifTemp());
            log_s.append(",").append(obj.getDescRifTemp());
    
            pst.setString(indice++, obj.getDsHashFileVers());
            log_s.append(",").append(obj.getDsHashFileVers());
    
            pst.setLong(indice++, obj.getIdunitadoc());
            log_s.append(",").append(obj.getIdunitadoc());
    
            pst.setLong(indice++, obj.getIddocumento());
            log_s.append(",").append(obj.getIddocumento());
    
            pst.setLong(indice++, obj.getIdcomponente());
            log_s.append(",").append(obj.getIdcomponente()).append("]");
            int updates = pst.executeUpdate();
            log.info("{}", log_s);
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
    public int updateWhere(ParComponente obj, String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String preparedQuery = "update PAR_COMPONENTE set IDDOCUMENTO= ?  , IDUNITADOC= ?  , IDCOMPONENTE= ?  , URNFILE= ?  , NOME= ?  , RIFNUMERO= ?  , RIFANNO= ?  , RIFTIPOREGISTRO= ?  , CODALLEGATO= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , ID_FORMATO_FILE_DOC= ?, ID_TIPO_COMP_DOC=?   where "
                + where;

                
        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
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
    
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
            }
    
            pst.setString(indice++, obj.getUrnfile());
            pst.setString(indice++, obj.getNome());
            pst.setString(indice++, obj.getRifnumero());
            if (obj.getRifanno() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getRifanno());
            }
    
            if (obj.getRiftiporegistro() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getRiftiporegistro());
            }
    
            pst.setString(indice++, obj.getCodallegato());
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
    
            if (obj.getIdFormatoFileDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileDoc());
            }
    
            if (obj.getIdTipoCompDoc() == null) {
                pst.setNull(indice++, 3);
            } else {
                pst.setLong(indice++, obj.getIdTipoCompDoc());
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
     * Deletes from the database for table "PAR_COMPONENTE"
     */
    public int delete(ParComponente obj, Connection con) throws SQLException {
        String query = "delete from PAR_COMPONENTE where IDUNITADOC=? and IDDOCUMENTO=? and IDCOMPONENTE=?";
        
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, obj.getIdunitadoc());
            st.setLong(2, obj.getIddocumento());
            st.setLong(3, obj.getIdcomponente());
            log.info("{} - [{}, {}, {}]", query, obj.getIdunitadoc(), obj.getIddocumento(), obj.getIdcomponente());
            int updates = st.executeUpdate();
            return updates;
        }
    }

    /**
     * Deletes from the database for table "PAR_COMPONENTE"
     */
    @Deprecated
    public int deleteWhere(String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String query = "delete from PAR_COMPONENTE where " + where;
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
     * Imported PAR_COMPONENTE PK:PAR_DOCUMENTO FK:PAR_COMPONENTE
     */
    public ParDocumento getParDocumentoByIddocumentoIdunitadoc(ParComponente obj, java.sql.Connection con)
            throws java.sql.SQLException {
        ParDocumentoDAO x = new ParDocumentoDAO();
        ParDocumento o = x.retrieveByKey(obj.getIddocumento(), obj.getIdunitadoc(), con);
        return o;
    }

    /**
     * Retrieve from the database for table "PAR_COMPONENTE"
     */
    public java.util.List<ParComponente> getParComponentesByIddocumentoIdunitadoc(Long iddocumento, Long idunitadoc,
            Connection con) throws SQLException {
        java.util.List<ParComponente> retRows = new java.util.ArrayList<ParComponente>();
        ParComponente curRow;

        String query = "select * from PAR_COMPONENTE" + " where IDDOCUMENTO=?  and IDUNITADOC=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddocumento);
            st.setLong(2, idunitadoc);

            log.info("{} - [{}, {}]", query, iddocumento, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParComponente();
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
     * Retrieve from the database for table "PAR_COMPONENTE"
     */
    public java.util.List<ParComponente> getParComponentesByIddocumentoIdunitadocNoBlob(Long iddocumento,
            Long idunitadoc, Connection con) throws SQLException {
        java.util.List<ParComponente> retRows = new java.util.ArrayList<ParComponente>();
        ParComponente curRow;

        String query = "select IDDOCUMENTO, " + "  IDUNITADOC, " + "  IDCOMPONENTE, " + "  URNFILE, " + "  NOME, "
                + "  RIFNUMERO, " + "  RIFANNO, " + "  RIFTIPOREGISTRO, " + "  CODALLEGATO, " + "  FLGSTATO, "
                + "  DTINS, " + "  DTAGG, " + "  PGM, " + "  ID, " + "  ID_FORMATO_FILE_DOC, " + "  ID_TIPO_COMP_DOC, "
                + "  FLGUTFIRMARIFERIMENTOTEMP, " + "  DATARIFERIMENTOTEMP, " + "  DESCRIFERIMENTOTEMP, "
                + "  DS_HASH_FILE_VERS from PAR_COMPONENTE" + " where IDDOCUMENTO=?  and IDUNITADOC=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddocumento);
            st.setLong(2, idunitadoc);

            log.info("{} - [{}, {}]", query, iddocumento, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParComponente();
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

    public java.util.List<ParComponente> getParComponentesByIdcomponenteIdunitadocNoBlob(Long idcomponente,
            Long idunitadoc, Connection con) throws SQLException {
        java.util.List<ParComponente> retRows = new java.util.ArrayList<ParComponente>();
        ParComponente curRow;

        String query = "select IDDOCUMENTO, " + "  IDUNITADOC, " + "  IDCOMPONENTE, " + "  URNFILE, " + "  NOME, "
                + "  RIFNUMERO, " + "  RIFANNO, " + "  RIFTIPOREGISTRO, " + "  CODALLEGATO, " + "  FLGSTATO, "
                + "  DTINS, " + "  DTAGG, " + "  PGM, " + "  ID, " + "  ID_FORMATO_FILE_DOC, " + "  ID_TIPO_COMP_DOC, "
                + "  FLGUTFIRMARIFERIMENTOTEMP, " + "  DATARIFERIMENTOTEMP, " + "  DESCRIFERIMENTOTEMP, "
                + "  DS_HASH_FILE_VERS from PAR_COMPONENTE" + " where IDCOMPONENTE=?  and IDUNITADOC=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idcomponente);
            st.setLong(2, idunitadoc);
            log.info("{} - [{}, {}]", query, idcomponente, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParComponente();
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

    public java.util.List<ParComponente> getParComponentesByIdunitadocNoBlob(Long idunitadoc, Connection con)
            throws SQLException {
        java.util.List<ParComponente> retRows = new java.util.ArrayList<ParComponente>();
        ParComponente curRow;

        String query = "select IDDOCUMENTO, " + "  IDUNITADOC, " + "  IDCOMPONENTE, " + "  URNFILE, " + "  NOME, "
                + "  RIFNUMERO, " + "  RIFANNO, " + "  RIFTIPOREGISTRO, " + "  CODALLEGATO, " + "  FLGSTATO, "
                + "  DTINS, " + "  DTAGG, " + "  PGM, " + "  ID, " + "  ID_FORMATO_FILE_DOC, " + "  ID_TIPO_COMP_DOC, "
                + "  FLGUTFIRMARIFERIMENTOTEMP, " + "  DATARIFERIMENTOTEMP, " + "  DESCRIFERIMENTOTEMP, "
                + "  DS_HASH_FILE_VERS from PAR_COMPONENTE" + " where IDUNITADOC=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, idunitadoc);
            log.info("{} - [{}]", query, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParComponente();
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
     * Retrieve from the database for table "PAR_COMPONENTE"
     */
    public java.util.List<ParComponente> getParComponentesByIddocumentoIdunitadocOrdered(Long iddocumento,
            Long idunitadoc, Connection con) throws SQLException {
        java.util.List<ParComponente> retRows = new java.util.ArrayList<>();
        ParComponente curRow;

        String query = "select * from PAR_COMPONENTE" + " where IDDOCUMENTO=? and IDUNITADOC=? ";
        query += " order by idcomponente";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddocumento);
            st.setLong(2, idunitadoc);
            log.debug("{} - [{}, {}]", query, iddocumento, idunitadoc);
            r = st.executeQuery();
            while (r.next()) {
                curRow = new ParComponente();
                getFromResultSet(curRow, r);
                retRows.add(curRow);
            }

        } catch (Exception e) {
            log.error(e.toString());
        } finally {
            if (r != null) {
                r.close();
            }
        }
        return retRows;
    }

    /**
     * Get all related PAR_DATISPECIFICI which have same iddocumento, idunitadoc, idcomponente
     */
    public java.util.List<ParDatispecifici> getRelatedParDatispecificiByIddocumento(Long iddocumento, Long idunitadoc,
            Long idcomponente, Connection con) throws SQLException {
        ParDatispecificiVO x = new ParDatispecificiVO();
        return x.getParDatispecificisByIddocumentoIdunitadocIdcomponente(iddocumento, idunitadoc, idcomponente, con);
    }

    /**
     * Retrieve from the database for table "PAR_COMPONENTE"
     */
    public ParComponente retrieveByIndex(Long iddocumento, Long idunitadoc, Long idcomponente, Connection con)
            throws SQLException {

        String query = "select * from PAR_COMPONENTE" + " where IDDOCUMENTO=?" + " and IDUNITADOC=?"
                + " and IDCOMPONENTE=?";
        
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, iddocumento);
            st.setLong(2, idunitadoc);
            st.setLong(3, idcomponente);
            log.debug("{} - [{}, {}, {}]", query, iddocumento, idunitadoc, idcomponente);
            r = st.executeQuery();
            ParComponente obj = null;
            if (r.next()) {
                obj = new ParComponente();
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
    public int updateByIndex(ParComponente obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_COMPONENTE set " + "IDDOCUMENTO=?, " + "IDUNITADOC=?, " + "IDCOMPONENTE=?, "
                + "URNFILE=?, " + "NOME=?, " + "RIFNUMERO=?, " + "RIFANNO=?, " + "RIFTIPOREGISTRO=?, "
                + "CODALLEGATO=?, " + "FLGSTATO=?, " + "DTAGG=current_timestamp, " + "PGM=?, " + "ID=?, "
                + "ID_FORMATO_FILE_DOC=?, " + "ID_TIPO_COMP_DOC=?, " + "FLGUTFIRMARIFERIMENTOTEMP=?, "
                + "DATARIFERIMENTOTEMP=?, " + "DESCRIFERIMENTOTEMP=?, " + "DS_HASH_FILE_VERS=? "
                + " where IDDOCUMENTO=? and IDUNITADOC=? and IDCOMPONENTE=?";
        StringBuilder logStr = new StringBuilder(preparedQuery);
        int indice = 1;

        
        try (PreparedStatement pst = con.prepareStatement(preparedQuery)) {
            if (obj.getIddocumento() == null) {
                pst.setNull(indice++, 3);
                logStr.append(" - [3");
            } else {
                pst.setLong(indice++, obj.getIddocumento());
                logStr.append(" - [" + obj.getIddocumento());
            }
    
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
                logStr.append(",").append(obj.getIdunitadoc());
            }
    
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
                logStr.append(",").append(obj.getIdcomponente());
            }
    
            pst.setString(indice++, obj.getUrnfile());
            logStr.append(",").append(obj.getUrnfile());
    
            pst.setString(indice++, obj.getNome());
            logStr.append(",").append(obj.getNome());
    
            pst.setString(indice++, obj.getRifnumero());
            logStr.append(",").append(obj.getRifnumero());
    
            if (obj.getRifanno() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getRifanno());
                logStr.append(",").append(obj.getRifanno());
            }
    
            if (obj.getRiftiporegistro() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getRiftiporegistro());
                logStr.append(",").append(obj.getRiftiporegistro());
            }
    
            pst.setString(indice++, obj.getCodallegato());
            logStr.append(",").append(obj.getCodallegato());
    
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
                logStr.append(",").append(obj.getId());
            }
    
            if (obj.getIdFormatoFileDoc() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileDoc());
                logStr.append(",").append(obj.getIdFormatoFileDoc());
            }
    
            if (obj.getIdTipoCompDoc() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdTipoCompDoc());
                logStr.append(",").append(obj.getIdTipoCompDoc());
            }
    
            if (obj.getFlgFirmaPerRifTemp() == null) {
                pst.setNull(indice++, 3);
                logStr.append(",3");
            } else {
                pst.setBoolean(indice++, obj.getFlgFirmaPerRifTemp());
                logStr.append(",").append(obj.getFlgFirmaPerRifTemp());
            }
            if (obj.getDataRifTemp() == null) {
                pst.setNull(indice++, Types.DATE);
                logStr.append(",3");
            } else {
                pst.setDate(indice++, new java.sql.Date((obj.getDataRifTemp()).getTime()));
                logStr.append(",").append(new java.sql.Date((obj.getDataRifTemp()).getTime()));
            }
            pst.setString(indice++, obj.getDescRifTemp());
            logStr.append(",").append(obj.getDescRifTemp());
    
            pst.setString(indice++, obj.getDsHashFileVers());
            logStr.append(",").append(obj.getDsHashFileVers());
    
            pst.setLong(indice++, obj.getIddocumento());
            logStr.append(",").append(obj.getIddocumento());
    
            pst.setLong(indice++, obj.getIdunitadoc());
            logStr.append(",").append(obj.getIdunitadoc());
    
            pst.setLong(indice++, obj.getIdcomponente());
            logStr.append(",").append(obj.getIdcomponente()).append("]");
            int updates = pst.executeUpdate();
            log.debug("{}", logStr);
            return updates;
        } catch (SQLException e) {
            log.error("Failed query: {}", preparedQuery, e);
            throw e;
        }
    }

    /**
     * Deletes from the database for table "PAR_COMPONENTE"
     */
    public int deleteByIndex(ParComponente obj, Connection con) throws SQLException {
        String query = "delete from PAR_COMPONENTE where IDDOCUMENTO=? and IDUNITADOC=?  and IDCOMPONENTE=?";
        
        try (PreparedStatement st = con.prepareStatement(query)) {
            st.setLong(1, obj.getIddocumento());
            st.setLong(2, obj.getIdunitadoc());
            st.setLong(3, obj.getIdcomponente());

            log.debug("{} - [{}, {}, {}]", query, obj.getIddocumento(), obj.getIdunitadoc(), obj.getIdcomponente());
            int updates = st.executeUpdate();
            return updates;
        } 
    }

    /**
     * Inserts the current object values into the database.
     *
     * @param blob
     *
     * @throws Exception
     */
    public int insertPrepared(ParComponente obj, Blob blob, Connection con) throws Exception {
        int indice = 1;
        String prepQuery = "insert into PAR_COMPONENTE ( " + "IDDOCUMENTO," + "IDUNITADOC," + "IDCOMPONENTE,"
                + "URNFILE," + "RIFNUMERO," + "RIFANNO," + "RIFTIPOREGISTRO," + "CODALLEGATO," + "FLGSTATO," + "DTINS,"
                + "DTAGG," + "PGM," + "ID," + "ID_FORMATO_FILE_DOC," + " ID_TIPO_COMP_DOC ,"
                + "FLGUTFIRMARIFERIMENTOTEMP," + " DATARIFERIMENTOTEMP, " + "NOME," + "BL_FILE_COMP,"
                + " DESCRIFERIMENTOTEMP," + " DS_HASH_FILE_VERS" + ") values (" + "? ," + "? ," + "? ," + "? ," + "? ,"
                + "? ," + "? ," + "? ," + "? ," + " current_timestamp ," + " current_timestamp ," + "? ," + "? ,"
                + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? )";
        
        StringBuilder log_s = new StringBuilder(prepQuery);
        FileInputStream fileDati = null;

        
        try (PreparedStatement pst = con.prepareStatement(prepQuery)) {
            if (obj.getIddocumento() == null) {
                pst.setNull(indice++, 3);
                log_s.append(" - [3");
            } else {
                pst.setLong(indice++, obj.getIddocumento());
                log_s.append(" - [" + obj.getIddocumento());
            }
    
            if (obj.getIdunitadoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdunitadoc());
                log_s.append("," + obj.getIdunitadoc());
            }
    
            if (obj.getIdcomponente() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdcomponente());
                log_s.append("," + obj.getIdcomponente());
            }
    
            pst.setString(indice++, obj.getUrnfile());
            log_s.append("," + obj.getUrnfile());
    
            pst.setString(indice++, obj.getRifnumero());
            log_s.append("," + obj.getRifnumero());
    
            if (obj.getRifanno() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getRifanno());
                log_s.append("," + obj.getRifanno());
            }
    
            if (obj.getRiftiporegistro() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getRiftiporegistro());
                log_s.append("," + obj.getRiftiporegistro());
            }
    
            pst.setString(indice++, obj.getCodallegato());
            log_s.append("," + obj.getCodallegato());
    
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
    
            if (obj.getIdFormatoFileDoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdFormatoFileDoc());
                log_s.append("," + obj.getIdFormatoFileDoc());
            }
    
            if (obj.getIdTipoCompDoc() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setLong(indice++, obj.getIdTipoCompDoc());
                log_s.append("," + obj.getIdTipoCompDoc());
            }
    
            if (obj.getFlgFirmaPerRifTemp() == null) {
                pst.setNull(indice++, 3);
                log_s.append(",3");
            } else {
                pst.setBoolean(indice++, obj.getFlgFirmaPerRifTemp());
                log_s.append("," + obj.getFlgFirmaPerRifTemp());
            }
    
            if (obj.getDataRifTemp() == null) {
                pst.setNull(indice++, Types.DATE);
                log_s.append(",").append(Types.DATE);
            } else {
                pst.setDate(indice++, new java.sql.Date((obj.getDataRifTemp()).getTime()));
                log_s.append("," + new java.sql.Date((obj.getDataRifTemp()).getTime()));
            }
    
            if (blob != null && blob.getDataFile() != null) {
                File dataFile = blob.getDataFile();
                pst.setString(indice++, blob.getFilename());
                log_s.append(",").append(blob.getFilename());
    
                fileDati = new FileInputStream(dataFile);
                pst.setBinaryStream(indice++, fileDati, (int) dataFile.length());
                log_s.append(",BYNARYSTREAM");
            } else {
                pst.setNull(indice++, 3);
                log_s.append(",3");
    
                pst.setNull(indice++, 3);
                log_s.append(",3");
            }
            pst.setString(indice++, obj.getDescRifTemp());
            log_s.append(",").append(obj.getDescRifTemp());
    
            pst.setString(indice++, obj.getDsHashFileVers());
            log_s.append(",").append(obj.getDsHashFileVers()).append("]");
            int updates = pst.executeUpdate();
            log.info("{}", log_s);

            if (fileDati != null) {
                fileDati.close();
            }
            return updates;
        } catch (Exception e) {
            log.error("Failed query: {}", prepQuery, e);
            throw e;
        } 
    }

    public InputStream getBlobWhereByCodAllegato(String codAllegato, Connection con) throws SQLException {
        String query = "select BL_FILE_COMP from PAR_COMPONENTE  where CODALLEGATO = ?";
        
        InputStream ret = null;
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setString(1, codAllegato);
            r = st.executeQuery();
            while (r.next()) {
                java.sql.Blob blob = r.getBlob(1);
                ret = blob.getBinaryStream();
            }
        } finally {
            if (r != null) {
                r.close();
            }
        }
        return ret;
    }

    public InputStream getBlobWhereByIdCompIdUD(Long idComp, Long idUd, Connection con) throws SQLException {
        String query = "select BL_FILE_COMP from PAR_COMPONENTE  where IDCOMPONENTE = ? and IDUNITADOC = ?";
        
        InputStream ret = null;
        ResultSet r = null;
        try (PreparedStatement st = con.prepareStatement(query)) {
            log.debug(query);
            st.setLong(1, idComp);
            st.setLong(2, idUd);
            r = st.executeQuery();
            while (r.next()) {
                java.sql.Blob blob = r.getBlob(1);
                ret = blob.getBinaryStream();
            }
        } finally {
            if (r != null) {
                r.close();
            }
        }
        return ret;
    }
}

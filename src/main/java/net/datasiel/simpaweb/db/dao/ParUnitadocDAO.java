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
 * ParUnitadoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.ParCollegamento;
import net.datasiel.simpaweb.db.pojo.ParDatispecifici;
import net.datasiel.simpaweb.db.pojo.ParDocumento;
import net.datasiel.simpaweb.db.pojo.ParFascicolo;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParCollegamentoVO;
import net.datasiel.simpaweb.db.vo.ParDatispecificiVO;
import net.datasiel.simpaweb.db.vo.ParDocumentoVO;
import net.datasiel.simpaweb.db.vo.ParFascicoloVO;

public class ParUnitadocDAO extends ParUnitadoc {

    private final Logger log = LoggerFactory.getLogger(ParUnitadocDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idunitadoc", "numero", "versione", "anno", "tipoconservazione", "flgstato",
                "dtins", "dtagg", "pgm", "id", "flgforzaconservazione", "flgforzaaccettazione", "flgforzacollegamento",
                "oggetto", "data", "flgcartaceo", "xmlrichiesta", "xmlrisposta", "dataversamento", "esitoversamento",
                "flgerrorerevisionato", "note", "idutente", "idStrut", "idTipoUnitaDoc", "idRegistroUnitaDoc", "stato",
                "cdVersioneXsd" };
    }

    public ParUnitadocDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(ParUnitadoc obj, ResultSet r) throws SQLException {
        if (r.getObject("IDUNITADOC") == null) {
            obj.setIdunitadoc(null);
        } else {
            obj.setIdunitadoc(r.getLong("IDUNITADOC"));
        }
        obj.setNumero(r.getString("NUMERO"));
        obj.setVersione(r.getString("VERSIONE"));
        if (r.getObject("ANNO") == null) {
            obj.setAnno(null);
        } else {
            obj.setAnno(r.getLong("ANNO"));
        }
        obj.setTipoconservazione(r.getString("TIPOCONSERVAZIONE"));
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
        obj.setFlgforzaconservazione(r.getBoolean("FLGFORZACONSERVAZIONE"));
        obj.setFlgforzaaccettazione(r.getBoolean("FLGFORZAACCETTAZIONE"));
        obj.setFlgforzacollegamento(r.getBoolean("FLGFORZACOLLEGAMENTO"));
        obj.setOggetto(r.getString("OGGETTO"));
        obj.setData(r.getDate("DATA"));
        if (r.getObject("FLGCARTACEO") == null) {
            obj.setFlgcartaceo(null);
        } else {
            obj.setFlgcartaceo(r.getLong("FLGCARTACEO"));
        }
        Clob tmpXMLRICHIESTA = r.getClob("XMLRICHIESTA");
        if (tmpXMLRICHIESTA != null) {
            obj.setStrappoxmlrichiesta(tmpXMLRICHIESTA.getSubString(1, (int) tmpXMLRICHIESTA.length()));
        } else {
            obj.setStrappoxmlrichiesta(null);
        }

        Clob tmpXMLRISPOSTA = r.getClob("XMLRISPOSTA");
        if (tmpXMLRISPOSTA != null) {
            obj.setStrappoxmlrisposta(tmpXMLRISPOSTA.getSubString(1, (int) tmpXMLRISPOSTA.length()));
        } else {
            obj.setStrappoxmlrisposta(null);
        }

        obj.setDataversamento(r.getTimestamp("DATAVERSAMENTO"));
        if (r.getObject("ESITOVERSAMENTO") == null) {
            obj.setEsitoversamento(null);
        } else {
            obj.setEsitoversamento(r.getString("ESITOVERSAMENTO"));
        }
        if (r.getObject("FLGERROREREVISIONATO") == null) {
            obj.setFlgerrorerevisionato(null);
        } else {
            obj.setFlgerrorerevisionato(r.getLong("FLGERROREREVISIONATO"));
        }
        Clob tmpNOTE = r.getClob("NOTE");
        if (tmpNOTE != null) {
            obj.setStrapponote(tmpNOTE.getSubString(1, (int) tmpNOTE.length()));
        } else {
            obj.setStrapponote(null);
        }

        if (r.getObject("IDUTENTE") == null) {
            obj.setIdutente(null);
        } else {
            obj.setIdutente(r.getLong("IDUTENTE"));
        }
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        if (r.getObject("ID_TIPO_UNITA_DOC") == null) {
            obj.setIdTipoUnitaDoc(null);
        } else {
            obj.setIdTipoUnitaDoc(r.getLong("ID_TIPO_UNITA_DOC"));
        }
        if (r.getObject("ID_REGISTRO_UNITA_DOC") == null) {
            obj.setIdRegistroUnitaDoc(null);
        } else {
            obj.setIdRegistroUnitaDoc(r.getLong("ID_REGISTRO_UNITA_DOC"));
        }
        if (r.getObject("STATO") == null) {
            obj.setStato(null);
        } else {
            obj.setStato(r.getLong("STATO"));
        }
        // CD_VERSIONE_XSD
        obj.setCdVersioneXSD(r.getString("CD_VERSIONE_XSD"));
        // FLGUTFIRMARIFERIMENTOTEMP
    }

    public String[] keyNames = { "idunitadoc" };

    /**
     * Retrieve from the database for table "PAR_UNITADOC"
     */
    public ParUnitadoc retrieveByKey(Long idunitadoc, Connection con) throws SQLException {

        String query = "select * from PAR_UNITADOC" + " where IDUNITADOC=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        st.setLong(1, idunitadoc);
        ResultSet r = null;
        try {
            log.info("{} - [{}]", query, idunitadoc);
            r = st.executeQuery();
            ParUnitadoc obj = null;
            if (r.next()) {
                obj = new ParUnitadoc();
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
     * Retrieve from the database for table "PAR_UNITADOC"
     */
    @Deprecated
    public java.util.List<ParUnitadoc> retrieveWhere(String where, String orderByClause, Connection con)
            throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParUnitadoc> retRows = new java.util.ArrayList<ParUnitadoc>();
        ParUnitadoc curRow;

        String query = "select * from PAR_UNITADOC" + " where " + where;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug("{}", query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParUnitadoc();
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
     * Retrieve from the database for table "PAR_UNITADOC"
     */
    @Deprecated
    public java.util.List<ParUnitadoc> retrieveWhere(String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParUnitadoc> retRows = new java.util.ArrayList<ParUnitadoc>();
        ParUnitadoc curRow;

        String query = "select * from PAR_UNITADOC" + " where " + where;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug("{}", query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParUnitadoc();
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
    public int update(ParUnitadoc obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_UNITADOC set IDUNITADOC= ?  ," + " NUMERO= ?  ," + " VERSIONE= ?  ,"
                + " ANNO= ?  ," + " TIPOCONSERVAZIONE= ?  ," + " FLGSTATO= ?  ," + " DTAGG= current_timestamp  ,"
                + " PGM= ?  ," + " ID= ?  ," + " FLGFORZACONSERVAZIONE= ?  ," + " FLGFORZAACCETTAZIONE= ?  ,"
                + " FLGFORZACOLLEGAMENTO= ?  ," + " OGGETTO= ?  ," + " DATA= ?  ," + " FLGCARTACEO= ?  ,"
                + " XMLRICHIESTA= ?  ," + " XMLRISPOSTA= ?  ," + " DATAVERSAMENTO= ?  ," + " ESITOVERSAMENTO= ?  ,"
                + " FLGERROREREVISIONATO= ?  ," + " NOTE= ?  ," + " IDUTENTE= ?  ," + " ID_STRUT= ?  ,"
                + " ID_TIPO_UNITA_DOC= ?  ," + " ID_REGISTRO_UNITA_DOC= ?  ," + " STATO= ?," + " CD_VERSIONE_XSD=? "
                + "where IDUNITADOC=? ";

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }

        pst.setString(indice++, obj.getNumero());
        pst.setString(indice++, obj.getVersione());
        if (obj.getAnno() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getAnno());
        }

        pst.setString(indice++, obj.getTipoconservazione());
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

        if (obj.getFlgforzaconservazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaconservazione());
        }

        if (obj.getFlgforzaaccettazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaaccettazione());
        }

        if (obj.getFlgforzacollegamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzacollegamento());
        }

        pst.setString(indice++, obj.getOggetto());
        if (obj.getData() != null) {
            pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getData()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        if (obj.getFlgcartaceo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgcartaceo());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrichiesta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrichiesta());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrisposta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrisposta());
        }

        if (obj.getDataversamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setTimestamp(indice++, obj.getDataversamento());
        }

        if (obj.getEsitoversamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getEsitoversamento());
        }

        if (obj.getFlgerrorerevisionato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgerrorerevisionato());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrapponote() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrapponote());
        }

        if (obj.getIdutente() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdutente());
        }

        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }

        if (obj.getIdTipoUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoUnitaDoc());
        }

        if (obj.getIdRegistroUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
        }

        if (obj.getStato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getStato());
        }

        pst.setString(indice++, obj.getCdVersioneXSD());
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
     * Updates the current object values into the database.
     */
    @Deprecated
    public int updateWhere(ParUnitadoc obj, String where, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        String preparedQuery = "update PAR_UNITADOC set IDUNITADOC= ?  , NUMERO= ?  , VERSIONE= ?  , ANNO= ?  , TIPOCONSERVAZIONE= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , FLGFORZACONSERVAZIONE= ?  , FLGFORZAACCETTAZIONE= ?  , FLGFORZACOLLEGAMENTO= ?  , OGGETTO= ?  , DATA= ?  , FLGCARTACEO= ?  , XMLRICHIESTA= ?  , XMLRISPOSTA= ?  , DATAVERSAMENTO= ?  , ESITOVERSAMENTO= ?  , FLGERROREREVISIONATO= ?  , NOTE= ?  , IDUTENTE= ?  , ID_STRUT= ?  , ID_TIPO_UNITA_DOC= ?  , ID_REGISTRO_UNITA_DOC= ?  , STATO= ?, CD_VERSIONE_XSD=?   where "
                + where;

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }

        pst.setString(indice++, obj.getNumero());
        pst.setString(indice++, obj.getVersione());
        if (obj.getAnno() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getAnno());
        }

        pst.setString(indice++, obj.getTipoconservazione());
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

        if (obj.getFlgforzaconservazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaconservazione());
        }

        if (obj.getFlgforzaaccettazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaaccettazione());
        }

        if (obj.getFlgforzacollegamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzacollegamento());
        }

        pst.setString(indice++, obj.getOggetto());
        if (obj.getData() != null) {
            pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getData()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        if (obj.getFlgcartaceo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgcartaceo());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrichiesta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrichiesta());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrisposta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrisposta());
        }

        if (obj.getDataversamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setTimestamp(indice++, obj.getDataversamento());
        }

        if (obj.getEsitoversamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getEsitoversamento());
        }

        if (obj.getFlgerrorerevisionato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgerrorerevisionato());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrapponote() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrapponote());
        }

        if (obj.getIdutente() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdutente());
        }

        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }

        if (obj.getIdTipoUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoUnitaDoc());
        }

        if (obj.getIdRegistroUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
        }

        if (obj.getStato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getStato());
        }

        pst.setString(indice++, obj.getCdVersioneXSD());

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
     * Deletes from the database for table "PAR_UNITADOC"
     */
    public int delete(ParUnitadoc obj, Connection con) throws SQLException {
        String query = "delete from PAR_UNITADOC where IDUNITADOC=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug("{}", query);
            st.setLong(1, obj.getIdunitadoc());
            int updates = st.executeUpdate();
            return updates;
        } finally {
            if (st != null) {
                st.close();
            }
        }
    }

    /**
     * Deletes from the database for table "PAR_UNITADOC"
     */
    public int deleteByIdUd(Long idUd, Connection con) throws SQLException {
        String query = "delete from PAR_UNITADOC where IDUNITADOC = ?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug("{}", query);
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
     * Retrieve from the database for table "PAR_UNITADOC"
     */
    @Deprecated
    public java.util.List<ParUnitadoc> getParUnitadocsByIdutenteIdStrut(Long idutente, Long idStrut, Connection con)
            throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParUnitadoc> retRows = new java.util.ArrayList<ParUnitadoc>();
        ParUnitadoc curRow;

        String query = "select * from PAR_UNITADOC  where IDUTENTE = ? and ID_STRUT = ?";
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug("{}", query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParUnitadoc();
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
     * Retrieve from the database for table "PAR_UNITADOC"
     */
    @Deprecated
    public java.util.List<ParUnitadoc> getParUnitadocsByIdutenteIdStrut(Long idutente, Long idStrut,
            String orderByClause, Connection con) throws SQLException {
        if (true) {
            throw new IllegalArgumentException("Metodo deprecato, non usato e vulnerabile alla SQL INJECTION");
        }
        java.util.List<ParUnitadoc> retRows = new java.util.ArrayList<ParUnitadoc>();
        ParUnitadoc curRow;

        String query = "select * from PAR_UNITADOC" + " where IDUTENTE=" + idutente + " and ID_STRUT=" + idStrut;
        query += " order by " + orderByClause;
        java.sql.Statement st = con.createStatement();
        ResultSet r = null;
        try {
            log.debug("{}", query);
            r = st.executeQuery(query);
            while (r.next()) {
                curRow = new ParUnitadoc();
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
     * Get all related PAR_COLLEGAMENTO which have same idunitadoc
     */
    public java.util.List<ParCollegamento> getRelatedParCollegamentoByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        ParCollegamentoVO x = new ParCollegamentoVO();
        return x.getParCollegamentosByIdunitadoc(idunitadoc, con);
    }

    /**
     * Get all related PAR_DATISPECIFICI which have same idunitadoc
     */
    public java.util.List<ParDatispecifici> getRelatedParDatispecificiByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        ParDatispecificiVO x = new ParDatispecificiVO();
        return x.getParDatispecificisByIdunitadoc(idunitadoc, con);
    }

    /**
     * Get all related PAR_DOCUMENTO which have same idunitadoc
     */
    public java.util.List<ParDocumento> getRelatedParDocumentoByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        ParDocumentoVO x = new ParDocumentoVO();
        return x.getParDocumentosByIdunitadoc(idunitadoc, con);
    }

    /**
     * Get all related PAR_FASCICOLO which have same idunitadoc
     */
    public java.util.List<ParFascicolo> getRelatedParFascicoloByIdunitadoc(Long idunitadoc, Connection con)
            throws SQLException {
        ParFascicoloVO x = new ParFascicoloVO();
        return x.getParFascicolosByIdunitadoc(idunitadoc, con);
    }

    /**
     * Retrieve from the database for table "PAR_UNITADOC"
     */
    public ParUnitadoc retrieveByIndex(Long idunitadoc, Long idutente, Long idStrut, Connection con)
            throws SQLException {

        String query = "select * from PAR_UNITADOC" + " where IDUNITADOC=?" + " and IDUTENTE=?" + " and ID_STRUT=?";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        st.setLong(1, idunitadoc);
        st.setLong(2, idutente);
        st.setLong(3, idStrut);
        ResultSet r = null;
        try {
            log.debug(query);
            r = st.executeQuery();
            ParUnitadoc obj = null;
            if (r.next()) {
                obj = new ParUnitadoc();
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
    public int updateByIndex(ParUnitadoc obj, Connection con) throws SQLException {
        String preparedQuery = "update PAR_UNITADOC set IDUNITADOC= ?  , NUMERO= ?  , VERSIONE= ?  , ANNO= ?  , TIPOCONSERVAZIONE= ?  , FLGSTATO= ?  , DTAGG= current_timestamp  , PGM= ?  , ID= ?  , "
                + "FLGFORZACONSERVAZIONE= ?  , FLGFORZAACCETTAZIONE= ?  , FLGFORZACOLLEGAMENTO= ?  , OGGETTO= ?  , DATA= ?  , FLGCARTACEO= ?  , XMLRICHIESTA= ?  , XMLRISPOSTA= ?  , "
                + "DATAVERSAMENTO= ?  , ESITOVERSAMENTO= ?  , FLGERROREREVISIONATO= ?  , NOTE= ?  , IDUTENTE= ?  , ID_STRUT= ?  , ID_TIPO_UNITA_DOC= ?  , ID_REGISTRO_UNITA_DOC= ?  , "
                + "STATO= ?, CD_VERSIONE_XSD=?  " + " where IDUNITADOC=?  and IDUTENTE=?  and ID_STRUT=? ";

        java.sql.PreparedStatement pst = con.prepareStatement(preparedQuery);
        int indice = 1;
        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }

        pst.setString(indice++, obj.getNumero());
        pst.setString(indice++, obj.getVersione());
        if (obj.getAnno() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getAnno());
        }

        pst.setString(indice++, obj.getTipoconservazione());
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

        if (obj.getFlgforzaconservazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaconservazione());
        }

        if (obj.getFlgforzaaccettazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaaccettazione());
        }

        if (obj.getFlgforzacollegamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzacollegamento());
        }

        pst.setString(indice++, obj.getOggetto());
        if (obj.getData() != null) {
            pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getData()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }

        if (obj.getFlgcartaceo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgcartaceo());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrichiesta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrichiesta());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrisposta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrisposta());
        }

        if (obj.getDataversamento() == null) {
            pst.setNull(indice++, Types.TIMESTAMP);
        } else {
            pst.setTimestamp(indice++, obj.getDataversamento());
        }

        if (obj.getEsitoversamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getEsitoversamento());
        }

        if (obj.getFlgerrorerevisionato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgerrorerevisionato());
        }

        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrapponote() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrapponote());
        }

        if (obj.getIdutente() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdutente());
        }

        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }

        if (obj.getIdTipoUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoUnitaDoc());
        }

        if (obj.getIdRegistroUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
        }

        if (obj.getStato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getStato());
        }
        pst.setString(indice++, obj.getCdVersioneXSD());

        pst.setLong(indice++, obj.getIdunitadoc());
        pst.setLong(indice++, obj.getIdutente());
        pst.setLong(indice++, obj.getIdStrut());

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
     * Deletes from the database for table "PAR_UNITADOC"
     */
    public int deleteByIndex(ParUnitadoc obj, Connection con) throws SQLException {
        String query = "delete from PAR_UNITADOC where IDUNITADOC=? and IDUTENTE=? and ID_STRUT= ? ";
        java.sql.PreparedStatement st = con.prepareStatement(query);
        try {
            log.debug(query);
            st.setLong(1, obj.getIdunitadoc());
            st.setLong(2, obj.getIdutente());
            st.setLong(3, obj.getIdStrut());
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
    public int insertPrepared(ParUnitadoc obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into PAR_UNITADOC ( " + "IDUNITADOC," + "NUMERO," + "VERSIONE," + "ANNO,"
                + "TIPOCONSERVAZIONE," + "FLGSTATO," + "DTINS," + "DTAGG," + "PGM," + "ID," + "FLGFORZACONSERVAZIONE,"
                + "FLGFORZAACCETTAZIONE," + "FLGFORZACOLLEGAMENTO," + "OGGETTO," + "DATA," + "FLGCARTACEO,"
                + "XMLRICHIESTA," + "XMLRISPOSTA," + "DATAVERSAMENTO," + "ESITOVERSAMENTO," + "FLGERROREREVISIONATO,"
                + "NOTE," + "IDUTENTE," + "ID_STRUT," + "ID_TIPO_UNITA_DOC," + "ID_REGISTRO_UNITA_DOC,"
                + "STATO) values (? ," + "? ," + "? ," + "? ," + "? ," + "? ," + " current_timestamp ,"
                + " current_timestamp ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ,"
                + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? ," + "? )";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIdunitadoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdunitadoc());
        }
        pst.setString(indice++, obj.getNumero());
        pst.setString(indice++, obj.getVersione());
        if (obj.getAnno() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getAnno());
        }
        pst.setString(indice++, obj.getTipoconservazione());
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
        if (obj.getFlgforzaconservazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaconservazione());
        }
        if (obj.getFlgforzaaccettazione() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzaaccettazione());
        }
        if (obj.getFlgforzacollegamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setBoolean(indice++, obj.getFlgforzacollegamento());
        }
        pst.setString(indice++, obj.getOggetto());
        if (obj.getData() != null) {
            pst.setObject(indice++, new java.sql.Date(((java.util.Date) obj.getData()).getTime()));
        } else {
            pst.setObject(indice++, null);
        }
        if (obj.getFlgcartaceo() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgcartaceo());
        }
        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrichiesta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrichiesta());
        }
        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrappoxmlrisposta() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrappoxmlrisposta());
        }
        if (obj.getDataversamento() == null) {
            pst.setNull(indice++, Types.TIMESTAMP);
        } else {
            pst.setTimestamp(indice++, obj.getDataversamento());
        }
        if (obj.getEsitoversamento() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getEsitoversamento());
        }
        if (obj.getFlgerrorerevisionato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getFlgerrorerevisionato());
        }
        /* La colonna CLOB viene valorizzata con la stringa di appoggio */
        if (obj.getStrapponote() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setString(indice++, obj.getStrapponote());
        }
        if (obj.getIdutente() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdutente());
        }
        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }
        if (obj.getIdTipoUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdTipoUnitaDoc());
        }
        if (obj.getIdRegistroUnitaDoc() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdRegistroUnitaDoc());
        }
        if (obj.getStato() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getStato());
        }

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

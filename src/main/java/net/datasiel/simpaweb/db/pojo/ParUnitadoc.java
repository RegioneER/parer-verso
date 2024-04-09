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

package net.datasiel.simpaweb.db.pojo;

/**
 * ParUnitadoc
 * 
 * WARNING! Automatically generated file!
 * Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.Label;
import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Multiline;
import com.manydesigns.elements.annotations.Required;
import com.manydesigns.elements.annotations.Select;
import com.manydesigns.elements.options.DisplayMode;

import net.datasiel.elements.annotations.SingleValueSearch;
import net.datasiel.webapp.elements.annotations.ColumnName;

public class ParUnitadoc implements Serializable {
    private static final long serialVersionUID = 1L;

    public ParUnitadoc() {
        super();
    }

    /**
     * Type : BIGINT Name : IDUNITADOC
     */
    public Long idunitadoc;

    /**
     * Type : VARCHAR Name : NUMERO
     */
    public String numero;

    /**
     * Type : VARCHAR Name : VERSIONE
     */
    public String versione;

    /**
     * Type : BIGINT Name : ANNO
     */
    public Long anno;

    /**
     * Type : VARCHAR Name : TIPOCONSERVAZIONE
     */
    public String tipoconservazione;

    /**
     * Type : BIGINT Name : FLGSTATO
     */
    public Long flgstato;

    /**
     * Type : DATE(7) Name : DTINS
     */
    public java.util.Date dtins;

    /**
     * Type : DATE(7) Name : DTAGG
     */
    public java.util.Date dtagg;

    /**
     * Type : VARCHAR Name : PGM
     */
    public String pgm;

    /**
     * Type : BIGINT Name : ID
     */
    public Long id;

    /**
     * Type : BIGINT Name : FLGFORZACONSERVAZIONE
     */
    public Boolean flgforzaconservazione;

    /**
     * Type : BIGINT Name : FLGFORZAACCETTAZIONE
     */
    public Boolean flgforzaaccettazione;

    /**
     * Type : BIGINT Name : FLGFORZACOLLEGAMENTO
     */
    public Boolean flgforzacollegamento;

    /**
     * Type : VARCHAR Name : OGGETTO
     */
    public String oggetto;

    /**
     * Type : DATE(7) Name : DATA
     */
    public java.util.Date data;

    /**
     * Type : BIGINT Name : FLGCARTACEO
     */
    public Long flgcartaceo;

    /**
     * Type : NTEXT Name : XMLRICHIESTA
     */
    public javax.sql.rowset.serial.SerialClob xmlrichiesta;

    public String strappoxmlrichiesta;
    /**
     * Type : NTEXT Name : XMLRISPOSTA
     */
    public javax.sql.rowset.serial.SerialClob xmlrisposta;

    public String strappoxmlrisposta;
    /**
     * Type : TIMESTAMP Name : DATAVERSAMENTO
     */
    public java.sql.Timestamp dataversamento;

    /**
     * Type : BIGINT Name : ESITOVERSAMENTO
     */
    public String esitoversamento;

    /**
     * Type : BIGINT Name : FLGERROREREVISIONATO
     */
    public Long flgerrorerevisionato;

    /**
     * Type : NTEXT Name : NOTE
     */
    public javax.sql.rowset.serial.SerialClob note;

    public String strapponote;
    /**
     * Type : BIGINT Name : IDUTENTE
     */
    public Long idutente;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : BIGINT Name : ID_TIPO_UNITA_DOC
     */
    public Long idTipoUnitaDoc;

    /**
     * Type : BIGINT Name : ID_REGISTRO_UNITA_DOC
     */
    public Long idRegistroUnitaDoc;

    /**
     * Type : BIGINT Name : STATO
     */
    public Long stato;

    private String cdVersioneXSD;

    /**
     * Sets the value for idunitadoc
     */
    public void setIdunitadoc(Long idunitadoc) {
        this.idunitadoc = idunitadoc;
    }

    /**
     * Gets the value for idunitadoc
     */
    @Required
    public Long getIdunitadoc() {
        return idunitadoc;
    }

    /**
     * Sets the value for numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Gets the value for numero
     */
    @Required
    @MaxLength(20)
    @Label("Numero")
    public String getNumero() {
        return numero;
    }

    /**
     * Sets the value for versione
     */
    public void setVersione(String versione) {
        this.versione = versione;
    }

    /**
     * Gets the value for versione
     */
    @Required
    @MaxLength(100)
    public String getVersione() {
        return versione;
    }

    /**
     * Sets the value for anno
     */
    public void setAnno(Long anno) {
        this.anno = anno;
    }

    /**
     * Gets the value for anno
     */
    @Label("Anno")
    @SingleValueSearch
    @Required
    public Long getAnno() {
        return anno;
    }

    /**
     * Sets the value for tipoconservazione
     */
    public void setTipoconservazione(String tipoconservazione) {
        this.tipoconservazione = tipoconservazione;
    }

    /**
     * Gets the value for tipoconservazione
     */
    @Required
    @MaxLength(1)
    @Label("Tipo conservazione")
    @Select(labels = { "Versamento Anticipato", "Fiscale" }, values = { "A", "F" }, displayMode = DisplayMode.DROPDOWN)
    public String getTipoconservazione() {
        return tipoconservazione;
    }

    /**
     * Sets the value for flgstato
     */
    public void setFlgstato(Long flgstato) {
        this.flgstato = flgstato;
    }

    /**
     * Gets the value for flgstato
     */
    @Required
    public Long getFlgstato() {
        return flgstato;
    }

    /**
     * Sets the value for dtins
     */
    public void setDtins(java.util.Date dtins) {
        this.dtins = dtins;
    }

    /**
     * Gets the value for dtins
     */
    @Required
    public java.util.Date getDtins() {
        return dtins;
    }

    /**
     * Sets the value for dtagg
     */
    public void setDtagg(java.util.Date dtagg) {
        this.dtagg = dtagg;
    }

    /**
     * Gets the value for dtagg
     */
    @Required
    public java.util.Date getDtagg() {
        return dtagg;
    }

    /**
     * Sets the value for pgm
     */
    public void setPgm(String pgm) {
        this.pgm = pgm;
    }

    /**
     * Gets the value for pgm
     */
    @Required
    @MaxLength(50)
    public String getPgm() {
        return pgm;
    }

    /**
     * Sets the value for id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets the value for id
     */
    @Required
    public Long getId() {
        return id;
    }

    /**
     * Sets the value for flgforzaconservazione
     */
    public void setFlgforzaconservazione(Boolean flgforzaconservazione) {
        this.flgforzaconservazione = flgforzaconservazione;
    }

    /**
     * Gets the value for flgforzaconservazione
     */
    @Label("Forza conservazione")
    public Boolean getFlgforzaconservazione() {
        return flgforzaconservazione;
    }

    /**
     * Sets the value for flgforzaaccettazione
     */
    public void setFlgforzaaccettazione(Boolean flgforzaaccettazione) {
        this.flgforzaaccettazione = flgforzaaccettazione;
    }

    /**
     * Gets the value for flgforzaaccettazione
     */
    @Label("Forza accettazione")
    public Boolean getFlgforzaaccettazione() {
        return flgforzaaccettazione;
    }

    /**
     * Sets the value for flgforzacollegamento
     */
    public void setFlgforzacollegamento(Boolean flgforzacollegamento) {
        this.flgforzacollegamento = flgforzacollegamento;
    }

    /**
     * Gets the value for flgforzacollegamento
     */
    @Label("Forza collegamento")
    public Boolean getFlgforzacollegamento() {
        return flgforzacollegamento;
    }

    /**
     * Sets the value for oggetto
     */
    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    /**
     * Gets the value for oggetto
     */
    @Required
    @MaxLength(1024)
    @Label("Oggetto")
    @Multiline
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Sets the value for data
     */
    public void setData(java.util.Date data) {
        this.data = data;
    }

    /**
     * Gets the value for data
     */
    @Label("Data")
    @Required
    public java.util.Date getData() {
        return data;
    }

    /**
     * Sets the value for flgcartaceo
     */
    public void setFlgcartaceo(Long flgcartaceo) {
        this.flgcartaceo = flgcartaceo;
    }

    /**
     * Gets the value for flgcartaceo
     */
    @Label("Cartaceo")
    public Long getFlgcartaceo() {
        return flgcartaceo;
    }

    /**
     * Sets the value for xmlrichiesta
     */
    public void setXmlrichiesta(javax.sql.rowset.serial.SerialClob xmlrichiesta) {
        this.xmlrichiesta = xmlrichiesta;
    }

    /**
     * Gets the value for xmlrichiesta
     */
    public javax.sql.rowset.serial.SerialClob getXmlrichiesta() {
        return xmlrichiesta;
    }

    /**
     * Sets the value for strappoxmlrichiesta
     */
    public void setStrappoxmlrichiesta(String strappoxmlrichiesta) {
        this.strappoxmlrichiesta = strappoxmlrichiesta;
    }

    /**
     * Gets the value for strappoxmlrichiesta
     */
    @Multiline
    public String getStrappoxmlrichiesta() {
        return strappoxmlrichiesta;
    }

    /**
     * Sets the value for xmlrisposta
     */
    public void setXmlrisposta(javax.sql.rowset.serial.SerialClob xmlrisposta) {
        this.xmlrisposta = xmlrisposta;
    }

    /**
     * Gets the value for xmlrisposta
     */
    public javax.sql.rowset.serial.SerialClob getXmlrisposta() {
        return xmlrisposta;
    }

    /**
     * Sets the value for strappoxmlrisposta
     */
    public void setStrappoxmlrisposta(String strappoxmlrisposta) {
        this.strappoxmlrisposta = strappoxmlrisposta;
    }

    /**
     * Gets the value for strappoxmlrisposta
     */
    @Multiline
    public String getStrappoxmlrisposta() {
        return strappoxmlrisposta;
    }

    /**
     * Sets the value for dataversamento
     */
    public void setDataversamento(java.sql.Timestamp dataversamento) {
        this.dataversamento = dataversamento;
    }

    /**
     * Gets the value for dataversamento
     */
    public java.sql.Timestamp getDataversamento() {
        return dataversamento;
    }

    /**
     * Sets the value for esitoversamento
     */
    public void setEsitoversamento(String esitoversamento) {
        this.esitoversamento = esitoversamento;
    }

    /**
     * Gets the value for esitoversamento
     */
    public String getEsitoversamento() {
        return esitoversamento;
    }

    /**
     * Sets the value for flgerrorerevisionato
     */
    public void setFlgerrorerevisionato(Long flgerrorerevisionato) {
        this.flgerrorerevisionato = flgerrorerevisionato;
    }

    /**
     * Gets the value for flgerrorerevisionato
     */
    public Long getFlgerrorerevisionato() {
        return flgerrorerevisionato;
    }

    /**
     * Sets the value for note
     */
    public void setNote(javax.sql.rowset.serial.SerialClob note) {
        this.note = note;
    }

    /**
     * Gets the value for note
     */
    public javax.sql.rowset.serial.SerialClob getNote() {
        return note;
    }

    /**
     * Sets the value for strapponote
     */
    public void setStrapponote(String strapponote) {
        this.strapponote = strapponote;
    }

    /**
     * Gets the value for strapponote
     */
    @Multiline
    public String getStrapponote() {
        return strapponote;
    }

    /**
     * Sets the value for idutente
     */
    public void setIdutente(Long idutente) {
        this.idutente = idutente;
    }

    /**
     * Gets the value for idutente
     */
    public Long getIdutente() {
        return idutente;
    }

    /**
     * Sets the value for idStrut
     */
    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    /**
     * Gets the value for idStrut
     */
    public Long getIdStrut() {
        return idStrut;
    }

    /**
     * Sets the value for idTipoUnitaDoc
     */
    public void setIdTipoUnitaDoc(Long idTipoUnitaDoc) {
        this.idTipoUnitaDoc = idTipoUnitaDoc;
    }

    /**
     * Gets the value for idTipoUnitaDoc
     */
    @Required
    @Label("Tipo unità documentaria")
    @ColumnName("ID_TIPO_UNITA_DOC")
    public Long getIdTipoUnitaDoc() {
        return idTipoUnitaDoc;
    }

    /**
     * Sets the value for idRegistroUnitaDoc
     */
    public void setIdRegistroUnitaDoc(Long idRegistroUnitaDoc) {
        this.idRegistroUnitaDoc = idRegistroUnitaDoc;
    }

    /**
     * Gets the value for idRegistroUnitaDoc
     */
    @Required
    @Label("Registro")
    @ColumnName("ID_REGISTRO_UNITA_DOC")
    public Long getIdRegistroUnitaDoc() {
        return idRegistroUnitaDoc;
    }

    /**
     * Sets the value for stato
     */
    public void setStato(Long stato) {
        this.stato = stato;
    }

    /**
     * Gets the value for stato
     */
    @Label("Stato unità documentaria")
    @Select(values = { "0", "1", "2", "3", "4" }, labels = { "Bozza", "Verifica fallita", "Verificata", "Versabile",
            "Versata" }, displayMode = DisplayMode.DROPDOWN)
    public Long getStato() {
        return stato;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(super.toString());
        try {
            java.lang.reflect.Field[] fields = getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                Object fieldValue = fields[i].get(this);
                String line = "\n" + fieldName + ": " + fieldValue;
                str.append(line);
            }
            return str.toString();
        } catch (Exception e) {
            return str.toString();
        }
    }

    /**
     * @return the cdVersioneXSD
     */
    @Label("Versione Dati Specifici")
    public String getCdVersioneXSD() {
        return cdVersioneXSD;
    }

    /**
     * @param cdVersioneXSD
     *            the cdVersioneXSD to set
     */
    public void setCdVersioneXSD(String cdVersioneXSD) {
        this.cdVersioneXSD = cdVersioneXSD;
    }

}

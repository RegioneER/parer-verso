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
 * ParDocumento
 *
 * WARNING! Automatically generated file!
 * Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.Label;
import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;
import com.manydesigns.elements.annotations.Select;
import com.manydesigns.elements.options.DisplayMode;

public class ParDocumento implements Serializable {
    private static final long serialVersionUID = 1L;

    public ParDocumento() {
        super();
    }

    /**
     * Type : BIGINT Name : IDDOCUMENTO
     */
    private Long iddocumento;

    /**
     * Type : BIGINT Name : IDUNITADOC
     */
    private Long idunitadoc;

    /**
     * Type : BIGINT Name : ID_TIPO_DOC
     */
    private Long idTipoDoc;

    /**
     * Type : BIGINT Name : FLGSTATO
     */
    private Long flgstato;

    /**
     * Type : DATE(7) Name : DTINS
     */
    private java.util.Date dtins;

    /**
     * Type : DATE(7) Name : DTAGG
     */
    private java.util.Date dtagg;

    /**
     * Type : VARCHAR Name : PGM
     */
    private String pgm;

    /**
     * Type : BIGINT Name : ID
     */
    private Long id;

    /**
     * Type : VARCHAR Name : PROFILOAUTOREDOC
     */
    private String profiloautoredoc;

    /**
     * Type : VARCHAR Name : PROFILODESCRIZIONEDOC
     */
    private String profilodescrizionedoc;

    /**
     * Type : VARCHAR Name : DFDENOMINAZIONE
     */
    private String dfdenominazione;

    /**
     * Type : VARCHAR Name : DFCOGNOME
     */
    private String dfcognome;

    /**
     * Type : VARCHAR Name : DFNOME
     */
    private String dfnome;

    /**
     * Type : VARCHAR Name : DFCODFISCALE
     */
    private String dfcodfiscale;

    /**
     * Type : VARCHAR Name : DFPIVA
     */
    private String dfpiva;

    /**
     * Type : DATE(7) Name : DFDTEMISSIONE
     */
    private java.util.Date dfdtemissione;

    /**
     * Type : BIGINT Name : DFPROGRESSIVO
     */
    private Long dfprogressivo;

    /**
     * Type : VARCHAR Name : DFREGISTRO
     */
    private String dfregistro;

    /**
     * Type : VARCHAR Name : DFPERIODO
     */
    private String dfperiodo;

    /**
     * Type : DATE(7) Name : DFDTTERMINEEMISSIONE
     */
    private java.util.Date dfdttermineemissione;

    /**
     * Type : VARCHAR Name : TIPOLOGIA
     */
    private String tipologia;

    private String cdVersioneXSD;

    /**
     * Sets the value for iddocumento
     */
    public void setIddocumento(Long iddocumento) {
        this.iddocumento = iddocumento;
    }

    /**
     * Gets the value for iddocumento
     */
    @Required
    public Long getIddocumento() {
        return iddocumento;
    }

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
     * Sets the value for idTipoDoc
     */
    public void setIdTipoDoc(Long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    /**
     * Gets the value for idTipoDoc
     */
    @Label("Tipologia")
    public Long getIdTipoDoc() {
        return idTipoDoc;
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
     * Sets the value for profiloautoredoc
     */
    public void setProfiloautoredoc(String profiloautoredoc) {
        this.profiloautoredoc = profiloautoredoc;
    }

    /**
     * Gets the value for profiloautoredoc
     */
    @MaxLength(254)
    @Label("Autore")
    public String getProfiloautoredoc() {
        return profiloautoredoc;
    }

    /**
     * Sets the value for profilodescrizionedoc
     */
    public void setProfilodescrizionedoc(String profilodescrizionedoc) {
        this.profilodescrizionedoc = profilodescrizionedoc;
    }

    /**
     * Gets the value for profilodescrizionedoc
     */
    @MaxLength(1024)
    @Label("Descrizione")
    public String getProfilodescrizionedoc() {
        return profilodescrizionedoc;
    }

    /**
     * Sets the value for dfdenominazione
     */
    public void setDfdenominazione(String dfdenominazione) {
        this.dfdenominazione = dfdenominazione;
    }

    /**
     * Gets the value for dfdenominazione
     */
    @MaxLength(254)
    public String getDfdenominazione() {
        return dfdenominazione;
    }

    /**
     * Sets the value for dfcognome
     */
    public void setDfcognome(String dfcognome) {
        this.dfcognome = dfcognome;
    }

    /**
     * Gets the value for dfcognome
     */
    @MaxLength(100)
    public String getDfcognome() {
        return dfcognome;
    }

    /**
     * Sets the value for dfnome
     */
    public void setDfnome(String dfnome) {
        this.dfnome = dfnome;
    }

    /**
     * Gets the value for dfnome
     */
    @MaxLength(100)
    public String getDfnome() {
        return dfnome;
    }

    /**
     * Sets the value for dfcodfiscale
     */
    public void setDfcodfiscale(String dfcodfiscale) {
        this.dfcodfiscale = dfcodfiscale;
    }

    /**
     * Gets the value for dfcodfiscale
     */
    @MaxLength(16)
    public String getDfcodfiscale() {
        return dfcodfiscale;
    }

    /**
     * Sets the value for dfpiva
     */
    public void setDfpiva(String dfpiva) {
        this.dfpiva = dfpiva;
    }

    /**
     * Gets the value for dfpiva
     */
    @MaxLength(11)
    public String getDfpiva() {
        return dfpiva;
    }

    /**
     * Sets the value for dfdtemissione
     */
    public void setDfdtemissione(java.util.Date dfdtemissione) {
        this.dfdtemissione = dfdtemissione;
    }

    /**
     * Gets the value for dfdtemissione
     */
    public java.util.Date getDfdtemissione() {
        return dfdtemissione;
    }

    /**
     * Sets the value for dfprogressivo
     */
    public void setDfprogressivo(Long dfprogressivo) {
        this.dfprogressivo = dfprogressivo;
    }

    /**
     * Gets the value for dfprogressivo
     */
    public Long getDfprogressivo() {
        return dfprogressivo;
    }

    /**
     * Sets the value for dfregistro
     */
    public void setDfregistro(String dfregistro) {
        this.dfregistro = dfregistro;
    }

    /**
     * Gets the value for dfregistro
     */
    @MaxLength(100)
    public String getDfregistro() {
        return dfregistro;
    }

    /**
     * Sets the value for dfperiodo
     */
    public void setDfperiodo(String dfperiodo) {
        this.dfperiodo = dfperiodo;
    }

    /**
     * Gets the value for dfperiodo
     */
    @MaxLength(100)
    public String getDfperiodo() {
        return dfperiodo;
    }

    /**
     * Sets the value for dfdttermineemissione
     */
    public void setDfdttermineemissione(java.util.Date dfdttermineemissione) {
        this.dfdttermineemissione = dfdttermineemissione;
    }

    /**
     * Gets the value for dfdttermineemissione
     */
    public java.util.Date getDfdttermineemissione() {
        return dfdttermineemissione;
    }

    /**
     * Sets the value for tipologia
     */
    public void setTipologia(String tipologia) {
        this.tipologia = tipologia;
    }

    /**
     * Gets the value for tipologia
     */
    @MaxLength(20)
    @Label("Tipo documento allegato")
    @Select(labels = { "Documento Principale", "Allegato", "Annotazione", "Annesso" }, values = { "PRINC", "ALLE",
            "ANNO", "ANNE" }, displayMode = DisplayMode.DROPDOWN)
    public String getTipologia() {
        return tipologia;
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

    @Label("Versione Dati Specifici")
    public String getCdVersioneXSD() {
        return cdVersioneXSD;
    }

    public void setCdVersioneXSD(String cdVersioneXSD) {
        this.cdVersioneXSD = cdVersioneXSD;
    }

    // public Long getIdTipoStrutDoc() {
    // return idTipoStrutDoc;
    // }
    //
    // public void setIdTipoStrutDoc(Long idTipoStrutDoc) {
    // this.idTipoStrutDoc = idTipoStrutDoc;
    // }
}

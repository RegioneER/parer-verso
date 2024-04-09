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
 * ParComponente
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.io.Serializable;

import com.manydesigns.elements.annotations.FileBlob;
import com.manydesigns.elements.annotations.Label;
import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Multiline;
import com.manydesigns.elements.annotations.Required;

public class ParComponente implements Serializable {

    private static final long serialVersionUID = 1L;

    public ParComponente() {
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
     * Type : BIGINT Name : IDCOMPONENTE
     */
    private Long idcomponente;

    /**
     * Type : VARCHAR Name : URNFILE
     */
    private String urnfile;

    /**
     * Type : VARCHAR Name : NOME
     */
    private String nome;

    /**
     * Type : VARCHAR Name : RIFNUMERO
     */
    private String rifnumero;

    /**
     * Type : BIGINT Name : RIFANNO
     */
    private Long rifanno;

    /**
     * Type : BIGINT Name : RIFTIPOREGISTRO
     */
    private Long riftiporegistro;

    /**
     * Type : VARCHAR Name : CODALLEGATO
     */
    private String codallegato;

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
     * Type : BIGINT Name : ID_FORMATO_FILE_DOC
     */
    private Long idFormatoFileDoc;

    /**
     * Type : BIGINT Name : ID_TIPO_COMP_DOC
     */
    private Long idTipoCompDoc;

    /**
     * Type : BIGINT Name : FLGUTFIRMARIFERIMENTOTEMP
     */
    private Boolean flgFirmaPerRifTemp;

    /**
     * Type : DATE(7) Name : DATARIFERIMENTOTEMP
     */
    private java.util.Date dataRifTemp;

    /**
     * Type : VARCHAR Name : DESCRIFERIMENTOTEMP
     */
    private String descRifTemp;

    /**
     * Type : VARCHAR Name : DESCRIFERIMENTOTEMP
     */
    private String dsHashFileVers;

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
     * Sets the value for idcomponente
     */
    public void setIdcomponente(Long idcomponente) {
        this.idcomponente = idcomponente;
    }

    /**
     * Gets the value for idcomponente
     */
    @Required
    public Long getIdcomponente() {
        return idcomponente;
    }

    /**
     * Sets the value for urnfile
     */
    public void setUrnfile(String urnfile) {
        this.urnfile = urnfile;
    }

    /**
     * Gets the value for urnfile
     */
    @MaxLength(1024)
    public String getUrnfile() {
        return urnfile;
    }

    /**
     * Sets the value for nome
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Gets the value for nome
     */
    @MaxLength(254)
    public String getNome() {
        return nome;
    }

    /**
     * Sets the value for rifnumero
     */
    public void setRifnumero(String rifnumero) {
        this.rifnumero = rifnumero;
    }

    /**
     * Gets the value for rifnumero
     */
    @MaxLength(100)
    @Label("Numero")
    public String getRifnumero() {
        return rifnumero;
    }

    /**
     * Sets the value for rifanno
     */
    public void setRifanno(Long rifanno) {
        this.rifanno = rifanno;
    }

    /**
     * Gets the value for rifanno
     */
    @Label("Anno")
    public Long getRifanno() {
        return rifanno;
    }

    /**
     * Sets the value for riftiporegistro
     */
    public void setRiftiporegistro(Long riftiporegistro) {
        this.riftiporegistro = riftiporegistro;
    }

    /**
     * Gets the value for riftiporegistro
     */
    @Label("Registro")
    public Long getRiftiporegistro() {
        return riftiporegistro;
    }

    /**
     * Sets the value for codallegato
     */
    public void setCodallegato(String codallegato) {
        this.codallegato = codallegato;
    }

    /**
     * Gets the value for codallegato
     */
    @MaxLength(32)
    @FileBlob
    @Label("File")
    public String getCodallegato() {
        return codallegato;
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
     * Sets the value for idFormatoFileDoc
     */
    public void setIdFormatoFileDoc(Long idFormatoFileDoc) {
        this.idFormatoFileDoc = idFormatoFileDoc;
    }

    /**
     * Gets the value for idFormatoFileDoc
     */
    @Label("Formato file")
    public Long getIdFormatoFileDoc() {
        return idFormatoFileDoc;
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

    @Label("Tipo Componente")
    public Long getIdTipoCompDoc() {
        return idTipoCompDoc;
    }

    public void setIdTipoCompDoc(Long idTipoCompDoc) {
        this.idTipoCompDoc = idTipoCompDoc;
    }

    @Label("Data della firma come riferimento temporale")
    public Boolean getFlgFirmaPerRifTemp() {
        return flgFirmaPerRifTemp;
    }

    public void setFlgFirmaPerRifTemp(Boolean flgFirmaPerRifTemp) {
        this.flgFirmaPerRifTemp = flgFirmaPerRifTemp;
    }

    @Label("Riferimento temporale")
    public java.util.Date getDataRifTemp() {
        return dataRifTemp;
    }

    public void setDataRifTemp(java.util.Date dataRifTemp) {
        this.dataRifTemp = dataRifTemp;
    }

    @MaxLength(1024)
    @Label("Descrizione Riferimento Temporale")
    @Multiline
    public String getDescRifTemp() {
        return descRifTemp;
    }

    public void setDescRifTemp(String descRifTemp) {
        this.descRifTemp = descRifTemp;
    }

    @MaxLength(254)
    @Label("Hash")
    public String getDsHashFileVers() {
        return dsHashFileVers;
    }

    public void setDsHashFileVers(String dsHashFileVers) {
        this.dsHashFileVers = dsHashFileVers;
    }
}

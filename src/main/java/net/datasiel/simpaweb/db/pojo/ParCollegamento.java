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
 * ParCollegamento
 * 
 * WARNING! Automatically generated file!
 * Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.Label;
import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class ParCollegamento implements Serializable {
    private static final long serialVersionUID = 1L;

    public ParCollegamento() {
        super();
    }

    /**
     * Type : BIGINT Name : IDCOLLEGAMENTO
     */
    public Long idcollegamento;

    /**
     * Type : BIGINT Name : IDUNITADOC
     */
    public Long idunitadoc;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : NUMERO
     */
    public String numero;

    /**
     * Type : BIGINT Name : ANNO
     */
    public Long anno;

    /**
     * Type : BIGINT Name : ID_REGISTRO_UNITA_DOC
     */
    public Long idRegistroUnitaDoc;

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
     * Type : VARCHAR Name : DESCRIZIONE
     */
    public String descrizione;

    private String descRegistro;

    /**
     * Sets the value for idcollegamento
     */
    public void setIdcollegamento(Long idcollegamento) {
        this.idcollegamento = idcollegamento;
    }

    /**
     * Gets the value for idcollegamento
     */
    @Required
    public Long getIdcollegamento() {
        return idcollegamento;
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
    public Long getIdunitadoc() {
        return idunitadoc;
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
     * Sets the value for numero
     */
    public void setNumero(String numero) {
        this.numero = numero;
    }

    /**
     * Gets the value for numero
     */

    @MaxLength(20)
    @Label("Numero")
    public String getNumero() {
        return numero;
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
    public Long getAnno() {
        return anno;
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
    @Label("Registro Unità Documentaria")
    public Long getIdRegistroUnitaDoc() {
        return idRegistroUnitaDoc;
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
     * Sets the value for descrizione
     */
    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    /**
     * Gets the value for descrizione
     */
    @MaxLength(254)
    public String getDescrizione() {
        return descrizione;
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

    public String getDescRegistro() {
        return descRegistro;
    }

    public void setDescRegistro(String descRegistro) {
        this.descRegistro = descRegistro;
    }
}

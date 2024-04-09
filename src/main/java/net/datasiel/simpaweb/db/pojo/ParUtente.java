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
 * ParUtente
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class ParUtente implements Serializable {

    private static final long serialVersionUID = 1L;

    public ParUtente() {
        super();
    }

    /**
     * Type : BIGINT Name : IDUTENTE
     */
    public Long idutente;

    /**
     * Type : VARCHAR Name : CODFISCALE
     */
    public String codfiscale;

    /**
     * Type : VARCHAR Name : COGNOME
     */
    public String cognome;

    /**
     * Type : VARCHAR Name : NOME
     */
    public String nome;

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
     * Type : VARCHAR Name : PASSWORD
     */
    public String password;

    /**
     * Type : VARCHAR Name : PWDSALT
     */
    public String pwdsalt;

    /**
     * Sets the value for idutente
     */
    public void setIdutente(Long idutente) {
        this.idutente = idutente;
    }

    /**
     * Gets the value for idutente
     */
    @Required
    public Long getIdutente() {
        return idutente;
    }

    /**
     * Sets the value for codfiscale
     */
    public void setCodfiscale(String codfiscale) {
        this.codfiscale = codfiscale;
    }

    /**
     * Gets the value for codfiscale
     */
    @MaxLength(20)
    public String getCodfiscale() {
        return codfiscale;
    }

    /**
     * Sets the value for cognome
     */
    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    /**
     * Gets the value for cognome
     */
    @MaxLength(20)
    public String getCognome() {
        return cognome;
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
    @MaxLength(20)
    public String getNome() {
        return nome;
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
     * Sets the value for password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Gets the value for password
     */
    @MaxLength(1024)
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value for pwdsalt
     */
    public void setPwdsalt(String pwdsalt) {
        this.pwdsalt = pwdsalt;
    }

    /**
     * Gets the value for pwdsalt
     */
    @MaxLength(32)
    public String getPwdsalt() {
        return pwdsalt;
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
}

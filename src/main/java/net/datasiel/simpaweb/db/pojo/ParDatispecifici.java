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
 * ParDatispecifici
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class ParDatispecifici implements Serializable {

    private static final long serialVersionUID = 1L;

    public ParDatispecifici() {
        super();
    }

    /**
     * Type : BIGINT Name : IDDATISPECIFICI
     */
    public Long iddatispecifici;

    /**
     * Type : BIGINT Name : IDUNITADOC
     */
    public Long idunitadoc;

    /**
     * Type : BIGINT Name : IDDOCUMENTO
     */
    public Long iddocumento;

    /**
     * Type : VARCHAR Name : ENTITASACER
     */
    public String entitasacer;

    /**
     * Type : BIGINT Name : IDCOMPONENTE
     */
    public Long idcomponente;

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
     * Sets the value for iddatispecifici
     */
    public void setIddatispecifici(Long iddatispecifici) {
        this.iddatispecifici = iddatispecifici;
    }

    /**
     * Gets the value for iddatispecifici
     */
    @Required
    public Long getIddatispecifici() {
        return iddatispecifici;
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
     * Sets the value for iddocumento
     */
    public void setIddocumento(Long iddocumento) {
        this.iddocumento = iddocumento;
    }

    /**
     * Gets the value for iddocumento
     */
    public Long getIddocumento() {
        return iddocumento;
    }

    /**
     * Sets the value for entitasacer
     */
    public void setEntitasacer(String entitasacer) {
        this.entitasacer = entitasacer;
    }

    /**
     * Gets the value for entitasacer
     */
    @MaxLength(20)
    public String getEntitasacer() {
        return entitasacer;
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
    public Long getIdcomponente() {
        return idcomponente;
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

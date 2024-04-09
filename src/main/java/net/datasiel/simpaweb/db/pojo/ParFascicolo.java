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
 * ParFascicolo
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.Label;
import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Multiline;
import com.manydesigns.elements.annotations.Required;
import com.manydesigns.elements.annotations.Select;
import com.manydesigns.elements.options.DisplayMode;

public class ParFascicolo implements Serializable {

    private static final long serialVersionUID = 1L;

    public ParFascicolo() {
        super();
    }

    /**
     * Type : BIGINT Name : IDFASCICOLO
     */
    public Long idfascicolo;

    /**
     * Type : BIGINT Name : IDUNITADOC
     */
    public Long idunitadoc;

    /**
     * Type : VARCHAR Name : CODTIPOFASCICOLO
     */
    public String codtipofascicolo;

    /**
     * Type : VARCHAR Name : IDENTIFICATIVO
     */
    public String identificativo;

    /**
     * Type : VARCHAR Name : OGGETTO
     */
    public String oggetto;

    /**
     * Type : VARCHAR Name : IDSOTTOFASCICOLO
     */
    public String idsottofascicolo;

    /**
     * Type : VARCHAR Name : OGGETTOSOTTOFASCICOLO
     */
    public String oggettosottofascicolo;

    /**
     * Type : VARCHAR Name : CLASSIFICA
     */
    public String classifica;

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
     * Sets the value for idfascicolo
     */
    public void setIdfascicolo(Long idfascicolo) {
        this.idfascicolo = idfascicolo;
    }

    /**
     * Gets the value for idfascicolo
     */
    @Required
    public Long getIdfascicolo() {
        return idfascicolo;
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
     * Sets the value for codtipofascicolo
     */
    public void setCodtipofascicolo(String codtipofascicolo) {
        this.codtipofascicolo = codtipofascicolo;
    }

    /**
     * Gets the value for codtipofascicolo
     */
    @MaxLength(20)
    @Select(labels = { "Fascicolo Principale", "Fascicolo secondario" }, values = { "P",
            "S" }, displayMode = DisplayMode.DROPDOWN)
    @Label("Tipo fascicolo")
    public String getCodtipofascicolo() {
        return codtipofascicolo;
    }

    /**
     * Sets the value for identificativo
     */
    public void setIdentificativo(String identificativo) {
        this.identificativo = identificativo;
    }

    /**
     * Gets the value for identificativo
     */
    @MaxLength(100)
    @Label("Identificativo fascicolo")
    public String getIdentificativo() {
        return identificativo;
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
    @MaxLength(1024)
    @Multiline
    @Label("Oggetto fascicolo")
    public String getOggetto() {
        return oggetto;
    }

    /**
     * Sets the value for idsottofascicolo
     */
    public void setIdsottofascicolo(String idsottofascicolo) {
        this.idsottofascicolo = idsottofascicolo;
    }

    /**
     * Gets the value for idsottofascicolo
     */
    @MaxLength(100)
    @Label("Identificativo")

    public String getIdsottofascicolo() {
        return idsottofascicolo;
    }

    /**
     * Sets the value for oggettosottofascicolo
     */
    public void setOggettosottofascicolo(String oggettosottofascicolo) {
        this.oggettosottofascicolo = oggettosottofascicolo;
    }

    /**
     * Gets the value for oggettosottofascicolo
     */
    @MaxLength(1024)
    @Multiline
    @Label("Oggetto")
    public String getOggettosottofascicolo() {
        return oggettosottofascicolo;
    }

    /**
     * Sets the value for classifica
     */
    public void setClassifica(String classifica) {
        this.classifica = classifica;
    }

    /**
     * Gets the value for classifica
     */
    @MaxLength(254)
    @Label("Classifica")
    public String getClassifica() {
        return classifica;
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

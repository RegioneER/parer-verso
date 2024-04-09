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
 * VOrgEnte
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VOrgEnte implements Serializable {

    private static final long serialVersionUID = 1L;

    public VOrgEnte() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_ENTE
     */
    public Long idEnte;

    /**
     * Type : BIGINT Name : ID_AMBIENTE
     */
    public Long idAmbiente;

    /**
     * Type : VARCHAR Name : NM_ENTE
     */
    public String nmEnte;

    /**
     * Type : VARCHAR Name : DS_ENTE
     */
    public String dsEnte;

    /**
     * Sets the value for idEnte
     */
    public void setIdEnte(Long idEnte) {
        this.idEnte = idEnte;
    }

    /**
     * Gets the value for idEnte
     */
    @Required
    public Long getIdEnte() {
        return idEnte;
    }

    /**
     * Sets the value for idAmbiente
     */
    public void setIdAmbiente(Long idAmbiente) {
        this.idAmbiente = idAmbiente;
    }

    /**
     * Gets the value for idAmbiente
     */
    @Required
    public Long getIdAmbiente() {
        return idAmbiente;
    }

    /**
     * Sets the value for nmEnte
     */
    public void setNmEnte(String nmEnte) {
        this.nmEnte = nmEnte;
    }

    /**
     * Gets the value for nmEnte
     */
    @Required
    @MaxLength(100)
    public String getNmEnte() {
        return nmEnte;
    }

    /**
     * Sets the value for dsEnte
     */
    public void setDsEnte(String dsEnte) {
        this.dsEnte = dsEnte;
    }

    /**
     * Gets the value for dsEnte
     */
    @Required
    @MaxLength(254)
    public String getDsEnte() {
        return dsEnte;
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

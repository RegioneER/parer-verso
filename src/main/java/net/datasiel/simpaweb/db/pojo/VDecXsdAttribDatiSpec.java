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
 * VDecXsdAttribDatiSpec
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.Required;

public class VDecXsdAttribDatiSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecXsdAttribDatiSpec() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_XSD_ATTRIB_DATI_SPEC
     */
    public Long idXsdAttribDatiSpec;

    /**
     * Type : BIGINT Name : ID_XSD_DATI_SPEC
     */
    public Long idXsdDatiSpec;

    /**
     * Type : BIGINT Name : ID_ATTRIB_DATI_SPEC
     */
    public Long idAttribDatiSpec;

    /**
     * Type : BIGINT Name : NI_ORD_ATTRIB
     */
    public Long niOrdAttrib;

    /**
     * Sets the value for idXsdAttribDatiSpec
     */
    public void setIdXsdAttribDatiSpec(Long idXsdAttribDatiSpec) {
        this.idXsdAttribDatiSpec = idXsdAttribDatiSpec;
    }

    /**
     * Gets the value for idXsdAttribDatiSpec
     */
    @Required
    public Long getIdXsdAttribDatiSpec() {
        return idXsdAttribDatiSpec;
    }

    /**
     * Sets the value for idXsdDatiSpec
     */
    public void setIdXsdDatiSpec(Long idXsdDatiSpec) {
        this.idXsdDatiSpec = idXsdDatiSpec;
    }

    /**
     * Gets the value for idXsdDatiSpec
     */
    @Required
    public Long getIdXsdDatiSpec() {
        return idXsdDatiSpec;
    }

    /**
     * Sets the value for idAttribDatiSpec
     */
    public void setIdAttribDatiSpec(Long idAttribDatiSpec) {
        this.idAttribDatiSpec = idAttribDatiSpec;
    }

    /**
     * Gets the value for idAttribDatiSpec
     */
    @Required
    public Long getIdAttribDatiSpec() {
        return idAttribDatiSpec;
    }

    /**
     * Sets the value for niOrdAttrib
     */
    public void setNiOrdAttrib(Long niOrdAttrib) {
        this.niOrdAttrib = niOrdAttrib;
    }

    /**
     * Gets the value for niOrdAttrib
     */
    @Required
    public Long getNiOrdAttrib() {
        return niOrdAttrib;
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

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
 * VDecFormatoFileBusta
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecFormatoFileBusta implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecFormatoFileBusta() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_FORMATO_FILE_BUSTA
     */
    public Long idFormatoFileBusta;

    /**
     * Type : BIGINT Name : ID_FORMATO_FILE_STANDARD
     */
    public Long idFormatoFileStandard;

    /**
     * Type : VARCHAR Name : TI_FORMATO_FIRMA_MARCA
     */
    public String tiFormatoFirmaMarca;

    /**
     * Sets the value for idFormatoFileBusta
     */
    public void setIdFormatoFileBusta(Long idFormatoFileBusta) {
        this.idFormatoFileBusta = idFormatoFileBusta;
    }

    /**
     * Gets the value for idFormatoFileBusta
     */
    @Required
    public Long getIdFormatoFileBusta() {
        return idFormatoFileBusta;
    }

    /**
     * Sets the value for idFormatoFileStandard
     */
    public void setIdFormatoFileStandard(Long idFormatoFileStandard) {
        this.idFormatoFileStandard = idFormatoFileStandard;
    }

    /**
     * Gets the value for idFormatoFileStandard
     */
    @Required
    public Long getIdFormatoFileStandard() {
        return idFormatoFileStandard;
    }

    /**
     * Sets the value for tiFormatoFirmaMarca
     */
    public void setTiFormatoFirmaMarca(String tiFormatoFirmaMarca) {
        this.tiFormatoFirmaMarca = tiFormatoFirmaMarca;
    }

    /**
     * Gets the value for tiFormatoFirmaMarca
     */
    @Required
    @MaxLength(20)
    public String getTiFormatoFirmaMarca() {
        return tiFormatoFirmaMarca;
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

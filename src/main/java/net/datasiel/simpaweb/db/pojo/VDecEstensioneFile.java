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
 * VDecEstensioneFile
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecEstensioneFile implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecEstensioneFile() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_ESTENSIONE_FILE
     */
    public Long idEstensioneFile;

    /**
     * Type : BIGINT Name : ID_FORMATO_FILE_STANDARD
     */
    public Long idFormatoFileStandard;

    /**
     * Type : VARCHAR Name : CD_ESTENSIONE_FILE
     */
    public String cdEstensioneFile;

    /**
     * Sets the value for idEstensioneFile
     */
    public void setIdEstensioneFile(Long idEstensioneFile) {
        this.idEstensioneFile = idEstensioneFile;
    }

    /**
     * Gets the value for idEstensioneFile
     */
    @Required
    public Long getIdEstensioneFile() {
        return idEstensioneFile;
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
     * Sets the value for cdEstensioneFile
     */
    public void setCdEstensioneFile(String cdEstensioneFile) {
        this.cdEstensioneFile = cdEstensioneFile;
    }

    /**
     * Gets the value for cdEstensioneFile
     */
    @Required
    @MaxLength(100)
    public String getCdEstensioneFile() {
        return cdEstensioneFile;
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

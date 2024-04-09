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
 * VDecFormatoFileAmmesso
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.Required;

public class VDecFormatoFileAmmesso implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecFormatoFileAmmesso() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_FORMATO_FILE_AMMESSO
     */
    public Long idFormatoFileAmmesso;

    /**
     * Type : BIGINT Name : ID_TIPO_COMP_DOC
     */
    public Long idTipoCompDoc;

    /**
     * Type : BIGINT Name : ID_FORMATO_FILE_DOC
     */
    public Long idFormatoFileDoc;

    /**
     * Sets the value for idFormatoFileAmmesso
     */
    public void setIdFormatoFileAmmesso(Long idFormatoFileAmmesso) {
        this.idFormatoFileAmmesso = idFormatoFileAmmesso;
    }

    /**
     * Gets the value for idFormatoFileAmmesso
     */
    @Required
    public Long getIdFormatoFileAmmesso() {
        return idFormatoFileAmmesso;
    }

    /**
     * Sets the value for idTipoCompDoc
     */
    public void setIdTipoCompDoc(Long idTipoCompDoc) {
        this.idTipoCompDoc = idTipoCompDoc;
    }

    /**
     * Gets the value for idTipoCompDoc
     */
    @Required
    public Long getIdTipoCompDoc() {
        return idTipoCompDoc;
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
    @Required
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
}

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
 * VDecTipoUnitaDocAmmesso
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.Required;

public class VDecTipoUnitaDocAmmesso implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecTipoUnitaDocAmmesso() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_TIPO_UNITA_DOC_AMMESSO
     */
    public Long idTipoUnitaDocAmmesso;

    /**
     * Type : BIGINT Name : ID_TIPO_UNITA_DOC
     */
    public Long idTipoUnitaDoc;

    /**
     * Type : BIGINT Name : ID_REGISTRO_UNITA_DOC
     */
    public Long idRegistroUnitaDoc;

    /**
     * Sets the value for idTipoUnitaDocAmmesso
     */
    public void setIdTipoUnitaDocAmmesso(Long idTipoUnitaDocAmmesso) {
        this.idTipoUnitaDocAmmesso = idTipoUnitaDocAmmesso;
    }

    /**
     * Gets the value for idTipoUnitaDocAmmesso
     */
    @Required
    public Long getIdTipoUnitaDocAmmesso() {
        return idTipoUnitaDocAmmesso;
    }

    /**
     * Sets the value for idTipoUnitaDoc
     */
    public void setIdTipoUnitaDoc(Long idTipoUnitaDoc) {
        this.idTipoUnitaDoc = idTipoUnitaDoc;
    }

    /**
     * Gets the value for idTipoUnitaDoc
     */
    @Required
    public Long getIdTipoUnitaDoc() {
        return idTipoUnitaDoc;
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
    @Required
    public Long getIdRegistroUnitaDoc() {
        return idRegistroUnitaDoc;
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

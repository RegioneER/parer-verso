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
 * VDecTipoDocAmmesso
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecTipoDocAmmesso implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecTipoDocAmmesso() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_TIPO_DOC_AMMESSO
     */
    public Long idTipoDocAmmesso;

    /**
     * Type : BIGINT Name : ID_TIPO_STRUT_UNITA_DOC
     */
    public Long idTipoStrutUnitaDoc;

    /**
     * Type : BIGINT Name : ID_TIPO_DOC
     */
    public Long idTipoDoc;

    /**
     * Type : VARCHAR Name : TI_DOC
     */
    public String tiDoc;

    /**
     * Type : CHAR(1) Name : FL_OBBL
     */
    public String flObbl;

    /**
     * Sets the value for idTipoDocAmmesso
     */
    public void setIdTipoDocAmmesso(Long idTipoDocAmmesso) {
        this.idTipoDocAmmesso = idTipoDocAmmesso;
    }

    /**
     * Gets the value for idTipoDocAmmesso
     */
    @Required
    public Long getIdTipoDocAmmesso() {
        return idTipoDocAmmesso;
    }

    /**
     * Sets the value for idTipoStrutUnitaDoc
     */
    public void setIdTipoStrutUnitaDoc(Long idTipoStrutUnitaDoc) {
        this.idTipoStrutUnitaDoc = idTipoStrutUnitaDoc;
    }

    /**
     * Gets the value for idTipoStrutUnitaDoc
     */
    @Required
    public Long getIdTipoStrutUnitaDoc() {
        return idTipoStrutUnitaDoc;
    }

    /**
     * Sets the value for idTipoDoc
     */
    public void setIdTipoDoc(Long idTipoDoc) {
        this.idTipoDoc = idTipoDoc;
    }

    /**
     * Gets the value for idTipoDoc
     */
    @Required
    public Long getIdTipoDoc() {
        return idTipoDoc;
    }

    /**
     * Sets the value for tiDoc
     */
    public void setTiDoc(String tiDoc) {
        this.tiDoc = tiDoc;
    }

    /**
     * Gets the value for tiDoc
     */
    @Required
    @MaxLength(20)
    public String getTiDoc() {
        return tiDoc;
    }

    /**
     * Sets the value for flObbl
     */
    public void setFlObbl(String flObbl) {
        this.flObbl = flObbl;
    }

    /**
     * Gets the value for flObbl
     */
    @Required
    @MaxLength(1)
    public String getFlObbl() {
        return flObbl;
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

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
 * VDecTipoStrutUnitaDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecTipoStrutUnitaDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecTipoStrutUnitaDoc() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_TIPO_STRUT_UNITA_DOC
     */
    public Long idTipoStrutUnitaDoc;

    /**
     * Type : BIGINT Name : ID_TIPO_UNITA_DOC
     */
    public Long idTipoUnitaDoc;

    /**
     * Type : VARCHAR Name : NM_TIPO_STRUT_UNITA_DOC
     */
    public String nmTipoStrutUnitaDoc;

    /**
     * Type : VARCHAR Name : DS_TIPO_STRUT_UNITA_DOC
     */
    public String dsTipoStrutUnitaDoc;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

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
     * Sets the value for nmTipoStrutUnitaDoc
     */
    public void setNmTipoStrutUnitaDoc(String nmTipoStrutUnitaDoc) {
        this.nmTipoStrutUnitaDoc = nmTipoStrutUnitaDoc;
    }

    /**
     * Gets the value for nmTipoStrutUnitaDoc
     */
    @Required
    @MaxLength(100)
    public String getNmTipoStrutUnitaDoc() {
        return nmTipoStrutUnitaDoc;
    }

    /**
     * Sets the value for dsTipoStrutUnitaDoc
     */
    public void setDsTipoStrutUnitaDoc(String dsTipoStrutUnitaDoc) {
        this.dsTipoStrutUnitaDoc = dsTipoStrutUnitaDoc;
    }

    /**
     * Gets the value for dsTipoStrutUnitaDoc
     */
    @Required
    @MaxLength(254)
    public String getDsTipoStrutUnitaDoc() {
        return dsTipoStrutUnitaDoc;
    }

    /**
     * Sets the value for dtIstituz
     */
    public void setDtIstituz(java.util.Date dtIstituz) {
        this.dtIstituz = dtIstituz;
    }

    /**
     * Gets the value for dtIstituz
     */
    @Required
    public java.util.Date getDtIstituz() {
        return dtIstituz;
    }

    /**
     * Sets the value for dtSoppres
     */
    public void setDtSoppres(java.util.Date dtSoppres) {
        this.dtSoppres = dtSoppres;
    }

    /**
     * Gets the value for dtSoppres
     */
    @Required
    public java.util.Date getDtSoppres() {
        return dtSoppres;
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

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
 * VDecTipoCompDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecTipoCompDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecTipoCompDoc() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_TIPO_COMP_DOC
     */
    public Long idTipoCompDoc;

    /**
     * Type : BIGINT Name : ID_TIPO_STRUT_DOC
     */
    public Long idTipoStrutDoc;

    /**
     * Type : VARCHAR Name : NM_TIPO_COMP_DOC
     */
    public String nmTipoCompDoc;

    /**
     * Type : VARCHAR Name : DS_TIPO_COMP_DOC
     */
    public String dsTipoCompDoc;

    /**
     * Type : VARCHAR Name : TI_USO_COMP_DOC
     */
    public String tiUsoCompDoc;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

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
     * Sets the value for idTipoStrutDoc
     */
    public void setIdTipoStrutDoc(Long idTipoStrutDoc) {
        this.idTipoStrutDoc = idTipoStrutDoc;
    }

    /**
     * Gets the value for idTipoStrutDoc
     */
    @Required
    public Long getIdTipoStrutDoc() {
        return idTipoStrutDoc;
    }

    /**
     * Sets the value for nmTipoCompDoc
     */
    public void setNmTipoCompDoc(String nmTipoCompDoc) {
        this.nmTipoCompDoc = nmTipoCompDoc;
    }

    /**
     * Gets the value for nmTipoCompDoc
     */
    @Required
    @MaxLength(100)
    public String getNmTipoCompDoc() {
        return nmTipoCompDoc;
    }

    /**
     * Sets the value for dsTipoCompDoc
     */
    public void setDsTipoCompDoc(String dsTipoCompDoc) {
        this.dsTipoCompDoc = dsTipoCompDoc;
    }

    /**
     * Gets the value for dsTipoCompDoc
     */
    @Required
    @MaxLength(254)
    public String getDsTipoCompDoc() {
        return dsTipoCompDoc;
    }

    /**
     * Sets the value for tiUsoCompDoc
     */
    public void setTiUsoCompDoc(String tiUsoCompDoc) {
        this.tiUsoCompDoc = tiUsoCompDoc;
    }

    /**
     * Gets the value for tiUsoCompDoc
     */
    @Required
    @MaxLength(20)
    public String getTiUsoCompDoc() {
        return tiUsoCompDoc;
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

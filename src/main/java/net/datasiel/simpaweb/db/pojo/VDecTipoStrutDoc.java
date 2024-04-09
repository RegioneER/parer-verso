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
 * VDecTipoStrutDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecTipoStrutDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecTipoStrutDoc() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_TIPO_STRUT_DOC
     */
    public Long idTipoStrutDoc;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : NM_TIPO_STRUT_DOC
     */
    public String nmTipoStrutDoc;

    /**
     * Type : VARCHAR Name : DS_TIPO_STRUT_DOC
     */
    public String dsTipoStrutDoc;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

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
     * Sets the value for idStrut
     */
    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    /**
     * Gets the value for idStrut
     */
    @Required
    public Long getIdStrut() {
        return idStrut;
    }

    /**
     * Sets the value for nmTipoStrutDoc
     */
    public void setNmTipoStrutDoc(String nmTipoStrutDoc) {
        this.nmTipoStrutDoc = nmTipoStrutDoc;
    }

    /**
     * Gets the value for nmTipoStrutDoc
     */
    @Required
    @MaxLength(100)
    public String getNmTipoStrutDoc() {
        return nmTipoStrutDoc;
    }

    /**
     * Sets the value for dsTipoStrutDoc
     */
    public void setDsTipoStrutDoc(String dsTipoStrutDoc) {
        this.dsTipoStrutDoc = dsTipoStrutDoc;
    }

    /**
     * Gets the value for dsTipoStrutDoc
     */
    @Required
    @MaxLength(254)
    public String getDsTipoStrutDoc() {
        return dsTipoStrutDoc;
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

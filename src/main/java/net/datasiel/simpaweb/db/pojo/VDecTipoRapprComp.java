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
 * VDecTipoRapprComp
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecTipoRapprComp implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecTipoRapprComp() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_TIPO_RAPPR_COMP
     */
    public Long idTipoRapprComp;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : NM_TIPO_RAPPR_COMP
     */
    public String nmTipoRapprComp;

    /**
     * Type : VARCHAR Name : DS_TIPO_RAPPR_COMP
     */
    public String dsTipoRapprComp;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

    /**
     * Sets the value for idTipoRapprComp
     */
    public void setIdTipoRapprComp(Long idTipoRapprComp) {
        this.idTipoRapprComp = idTipoRapprComp;
    }

    /**
     * Gets the value for idTipoRapprComp
     */
    @Required
    public Long getIdTipoRapprComp() {
        return idTipoRapprComp;
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
     * Sets the value for nmTipoRapprComp
     */
    public void setNmTipoRapprComp(String nmTipoRapprComp) {
        this.nmTipoRapprComp = nmTipoRapprComp;
    }

    /**
     * Gets the value for nmTipoRapprComp
     */
    @Required
    @MaxLength(100)
    public String getNmTipoRapprComp() {
        return nmTipoRapprComp;
    }

    /**
     * Sets the value for dsTipoRapprComp
     */
    public void setDsTipoRapprComp(String dsTipoRapprComp) {
        this.dsTipoRapprComp = dsTipoRapprComp;
    }

    /**
     * Gets the value for dsTipoRapprComp
     */
    @Required
    @MaxLength(254)
    public String getDsTipoRapprComp() {
        return dsTipoRapprComp;
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

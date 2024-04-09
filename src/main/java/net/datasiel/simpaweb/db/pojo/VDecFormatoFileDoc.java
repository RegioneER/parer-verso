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
 * VDecFormatoFileDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecFormatoFileDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecFormatoFileDoc() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_FORMATO_FILE_DOC
     */
    public Long idFormatoFileDoc;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : NM_FORMATO_FILE_DOC
     */
    public String nmFormatoFileDoc;

    /**
     * Type : VARCHAR Name : DS_FORMATO_FILE_DOC
     */
    public String dsFormatoFileDoc;

    /**
     * Type : VARCHAR Name : CD_VERSIONE
     */
    public String cdVersione;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

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
     * Sets the value for nmFormatoFileDoc
     */
    public void setNmFormatoFileDoc(String nmFormatoFileDoc) {
        this.nmFormatoFileDoc = nmFormatoFileDoc;
    }

    /**
     * Gets the value for nmFormatoFileDoc
     */
    @Required
    @MaxLength(100)
    public String getNmFormatoFileDoc() {
        return nmFormatoFileDoc;
    }

    /**
     * Sets the value for dsFormatoFileDoc
     */
    public void setDsFormatoFileDoc(String dsFormatoFileDoc) {
        this.dsFormatoFileDoc = dsFormatoFileDoc;
    }

    /**
     * Gets the value for dsFormatoFileDoc
     */
    @Required
    @MaxLength(254)
    public String getDsFormatoFileDoc() {
        return dsFormatoFileDoc;
    }

    /**
     * Sets the value for cdVersione
     */
    public void setCdVersione(String cdVersione) {
        this.cdVersione = cdVersione;
    }

    /**
     * Gets the value for cdVersione
     */
    @Required
    @MaxLength(100)
    public String getCdVersione() {
        return cdVersione;
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

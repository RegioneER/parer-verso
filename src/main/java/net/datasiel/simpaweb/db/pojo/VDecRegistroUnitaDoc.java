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
 * VDecRegistroUnitaDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecRegistroUnitaDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecRegistroUnitaDoc() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_REGISTRO_UNITA_DOC
     */
    public Long idRegistroUnitaDoc;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : CD_REGISTRO_UNITA_DOC
     */
    public String cdRegistroUnitaDoc;

    /**
     * Type : VARCHAR Name : DS_REGISTRO_UNITA_DOC
     */
    public String dsRegistroUnitaDoc;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

    /**
     * Type : BIGINT Name : ID_USER_IAM
     */
    public Long idUserIam;

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
     * Sets the value for cdRegistroUnitaDoc
     */
    public void setCdRegistroUnitaDoc(String cdRegistroUnitaDoc) {
        this.cdRegistroUnitaDoc = cdRegistroUnitaDoc;
    }

    /**
     * Gets the value for cdRegistroUnitaDoc
     */
    @Required
    @MaxLength(100)
    public String getCdRegistroUnitaDoc() {
        return cdRegistroUnitaDoc;
    }

    /**
     * Sets the value for dsRegistroUnitaDoc
     */
    public void setDsRegistroUnitaDoc(String dsRegistroUnitaDoc) {
        this.dsRegistroUnitaDoc = dsRegistroUnitaDoc;
    }

    /**
     * Gets the value for dsRegistroUnitaDoc
     */
    @Required
    @MaxLength(254)
    public String getDsRegistroUnitaDoc() {
        return dsRegistroUnitaDoc;
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

    /**
     * Sets the value for idUserIam
     */
    public void setIdUserIam(Long idUserIam) {
        this.idUserIam = idUserIam;
    }

    /**
     * Gets the value for idUserIam
     */
    @Required
    public Long getIdUserIam() {
        return idUserIam;
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

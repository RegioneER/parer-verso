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
 * VDecAttribDatiSpec
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecAttribDatiSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecAttribDatiSpec() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_ATTRIB_DATI_SPEC
     */
    public Long idAttribDatiSpec;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : TI_USO_ATTRIB
     */
    public String tiUsoAttrib;

    /**
     * Type : VARCHAR Name : TI_ENTITA_SACER
     */
    public String tiEntitaSacer;

    /**
     * Type : BIGINT Name : ID_TIPO_UNITA_DOC
     */
    public Long idTipoUnitaDoc;

    /**
     * Type : BIGINT Name : ID_TIPO_DOC
     */
    public Long idTipoDoc;

    /**
     * Type : BIGINT Name : ID_TIPO_COMP_DOC
     */
    public Long idTipoCompDoc;

    /**
     * Type : VARCHAR Name : NM_SISTEMA_MIGRAZ
     */
    public String nmSistemaMigraz;

    /**
     * Type : VARCHAR Name : NM_ATTRIB_DATI_SPEC
     */
    public String nmAttribDatiSpec;

    /**
     * Type : VARCHAR Name : DS_ATTRIB_DATI_SPEC
     */
    public String dsAttribDatiSpec;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

    /**
     * Sets the value for idAttribDatiSpec
     */
    public void setIdAttribDatiSpec(Long idAttribDatiSpec) {
        this.idAttribDatiSpec = idAttribDatiSpec;
    }

    /**
     * Gets the value for idAttribDatiSpec
     */
    @Required
    public Long getIdAttribDatiSpec() {
        return idAttribDatiSpec;
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
     * Sets the value for tiUsoAttrib
     */
    public void setTiUsoAttrib(String tiUsoAttrib) {
        this.tiUsoAttrib = tiUsoAttrib;
    }

    /**
     * Gets the value for tiUsoAttrib
     */
    @Required
    @MaxLength(20)
    public String getTiUsoAttrib() {
        return tiUsoAttrib;
    }

    /**
     * Sets the value for tiEntitaSacer
     */
    public void setTiEntitaSacer(String tiEntitaSacer) {
        this.tiEntitaSacer = tiEntitaSacer;
    }

    /**
     * Gets the value for tiEntitaSacer
     */
    @Required
    @MaxLength(20)
    public String getTiEntitaSacer() {
        return tiEntitaSacer;
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
    public Long getIdTipoUnitaDoc() {
        return idTipoUnitaDoc;
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
    public Long getIdTipoDoc() {
        return idTipoDoc;
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
    public Long getIdTipoCompDoc() {
        return idTipoCompDoc;
    }

    /**
     * Sets the value for nmSistemaMigraz
     */
    public void setNmSistemaMigraz(String nmSistemaMigraz) {
        this.nmSistemaMigraz = nmSistemaMigraz;
    }

    /**
     * Gets the value for nmSistemaMigraz
     */
    @MaxLength(100)
    public String getNmSistemaMigraz() {
        return nmSistemaMigraz;
    }

    /**
     * Sets the value for nmAttribDatiSpec
     */
    public void setNmAttribDatiSpec(String nmAttribDatiSpec) {
        this.nmAttribDatiSpec = nmAttribDatiSpec;
    }

    /**
     * Gets the value for nmAttribDatiSpec
     */
    @Required
    @MaxLength(100)
    public String getNmAttribDatiSpec() {
        return nmAttribDatiSpec;
    }

    /**
     * Sets the value for dsAttribDatiSpec
     */
    public void setDsAttribDatiSpec(String dsAttribDatiSpec) {
        this.dsAttribDatiSpec = dsAttribDatiSpec;
    }

    /**
     * Gets the value for dsAttribDatiSpec
     */
    @Required
    @MaxLength(254)
    public String getDsAttribDatiSpec() {
        return dsAttribDatiSpec;
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

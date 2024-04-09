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
 * VDecXsdDatiSpec
 *
 * WARNING! Automatically generated file! Do not edit!
 */
import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecXsdDatiSpec implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecXsdDatiSpec() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_XSD_DATI_SPEC
     */
    public Long idXsdDatiSpec;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : TI_USO_XSD
     */
    public String tiUsoXsd;

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
     * Type : VARCHAR Name : CD_VERSIONE_XSD
     */
    public String cdVersioneXsd;

    // /**
    // * Type : DATE(7) Name : DT_VERSIONE_XSD
    // */
    // public java.util.Date dtVersioneXsd;

    /**
     * Type : DATE(7) Name : DT_ISTITUZ
     */
    public java.util.Date dtIstituz;

    /**
     * Type : DATE(7) Name : DT_SOPPRES
     */
    public java.util.Date dtSoppres;

    /**
     * Sets the value for idXsdDatiSpec
     */
    public void setIdXsdDatiSpec(Long idXsdDatiSpec) {
        this.idXsdDatiSpec = idXsdDatiSpec;
    }

    /**
     * Gets the value for idXsdDatiSpec
     */
    @Required
    public Long getIdXsdDatiSpec() {
        return idXsdDatiSpec;
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
     * Sets the value for tiUsoXsd
     */
    public void setTiUsoXsd(String tiUsoXsd) {
        this.tiUsoXsd = tiUsoXsd;
    }

    /**
     * Gets the value for tiUsoXsd
     */
    @Required
    @MaxLength(20)
    public String getTiUsoXsd() {
        return tiUsoXsd;
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
     * Sets the value for cdVersioneXsd
     */
    public void setCdVersioneXsd(String cdVersioneXsd) {
        this.cdVersioneXsd = cdVersioneXsd;
    }

    /**
     * Gets the value for cdVersioneXsd
     */
    @Required
    @MaxLength(100)
    public String getCdVersioneXsd() {
        return cdVersioneXsd;
    }

    // /**
    // * Sets the value for dtVersioneXsd
    // */
    // public void setDtVersioneXsd(java.util.Date dtVersioneXsd) {
    // this.dtVersioneXsd = dtVersioneXsd;
    // }
    //
    // /**
    // * Gets the value for dtVersioneXsd
    // */
    // @Required
    // public java.util.Date getDtVersioneXsd() {
    // return dtVersioneXsd;
    // }

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

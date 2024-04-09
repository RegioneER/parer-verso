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
 * VDecTipoUnitaDoc
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecTipoUnitaDoc implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecTipoUnitaDoc() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_TIPO_UNITA_DOC
     */
    public Long idTipoUnitaDoc;

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : VARCHAR Name : NM_TIPO_UNITA_DOC
     */
    public String nmTipoUnitaDoc;

    /**
     * Type : VARCHAR Name : DS_TIPO_UNITA_DOC
     */
    public String dsTipoUnitaDoc;

    /**
     * Type : VARCHAR Name : CD_SERIE
     */
    public String cdSerie;

    /**
     * Type : CHAR(1) Name : FL_FORZA_COLLEGAMENTO
     */
    public String flForzaCollegamento;

    /**
     * Type : VARCHAR Name : TI_CALC_ORD
     */
    public String tiCalcOrd;

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
     * Sets the value for nmTipoUnitaDoc
     */
    public void setNmTipoUnitaDoc(String nmTipoUnitaDoc) {
        this.nmTipoUnitaDoc = nmTipoUnitaDoc;
    }

    /**
     * Gets the value for nmTipoUnitaDoc
     */
    @MaxLength(100)
    public String getNmTipoUnitaDoc() {
        return nmTipoUnitaDoc;
    }

    /**
     * Sets the value for dsTipoUnitaDoc
     */
    public void setDsTipoUnitaDoc(String dsTipoUnitaDoc) {
        this.dsTipoUnitaDoc = dsTipoUnitaDoc;
    }

    /**
     * Gets the value for dsTipoUnitaDoc
     */
    @MaxLength(254)
    public String getDsTipoUnitaDoc() {
        return dsTipoUnitaDoc;
    }

    /**
     * Sets the value for cdSerie
     */
    public void setCdSerie(String cdSerie) {
        this.cdSerie = cdSerie;
    }

    /**
     * Gets the value for cdSerie
     */
    @MaxLength(100)
    public String getCdSerie() {
        return cdSerie;
    }

    /**
     * Sets the value for flForzaCollegamento
     */
    public void setFlForzaCollegamento(String flForzaCollegamento) {
        this.flForzaCollegamento = flForzaCollegamento;
    }

    /**
     * Gets the value for flForzaCollegamento
     */
    @Required
    @MaxLength(1)
    public String getFlForzaCollegamento() {
        return flForzaCollegamento;
    }

    /**
     * Sets the value for tiCalcOrd
     */
    public void setTiCalcOrd(String tiCalcOrd) {
        this.tiCalcOrd = tiCalcOrd;
    }

    /**
     * Gets the value for tiCalcOrd
     */
    @Required
    @MaxLength(20)
    public String getTiCalcOrd() {
        return tiCalcOrd;
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

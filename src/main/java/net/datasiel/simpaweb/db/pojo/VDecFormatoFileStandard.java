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
 * VDecFormatoFileStandard
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

public class VDecFormatoFileStandard implements Serializable {

    private static final long serialVersionUID = 1L;

    public VDecFormatoFileStandard() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_FORMATO_FILE_STANDARD
     */
    public Long idFormatoFileStandard;

    /**
     * Type : VARCHAR Name : NM_FORMATO_FILE_STANDARD
     */
    public String nmFormatoFileStandard;

    /**
     * Type : VARCHAR Name : DS_FORMATO_FILE_STANDARD
     */
    public String dsFormatoFileStandard;

    /**
     * Type : VARCHAR Name : CD_VERSIONE
     */
    public String cdVersione;

    /**
     * Type : VARCHAR Name : DS_COPYRIGHT
     */
    public String dsCopyright;

    /**
     * Type : VARCHAR Name : NM_MIMETYPE_FILE
     */
    public String nmMimetypeFile;

    /**
     * Type : VARCHAR Name : TI_ESITO_CONTR_FORMATO
     */
    public String tiEsitoContrFormato;

    /**
     * Type : CHAR(1) Name : FL_FORMATO_CONCAT
     */
    public String flFormatoConcat;

    /**
     * Sets the value for idFormatoFileStandard
     */
    public void setIdFormatoFileStandard(Long idFormatoFileStandard) {
        this.idFormatoFileStandard = idFormatoFileStandard;
    }

    /**
     * Gets the value for idFormatoFileStandard
     */
    @Required
    public Long getIdFormatoFileStandard() {
        return idFormatoFileStandard;
    }

    /**
     * Sets the value for nmFormatoFileStandard
     */
    public void setNmFormatoFileStandard(String nmFormatoFileStandard) {
        this.nmFormatoFileStandard = nmFormatoFileStandard;
    }

    /**
     * Gets the value for nmFormatoFileStandard
     */
    @Required
    @MaxLength(100)
    public String getNmFormatoFileStandard() {
        return nmFormatoFileStandard;
    }

    /**
     * Sets the value for dsFormatoFileStandard
     */
    public void setDsFormatoFileStandard(String dsFormatoFileStandard) {
        this.dsFormatoFileStandard = dsFormatoFileStandard;
    }

    /**
     * Gets the value for dsFormatoFileStandard
     */
    @Required
    @MaxLength(254)
    public String getDsFormatoFileStandard() {
        return dsFormatoFileStandard;
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
     * Sets the value for dsCopyright
     */
    public void setDsCopyright(String dsCopyright) {
        this.dsCopyright = dsCopyright;
    }

    /**
     * Gets the value for dsCopyright
     */
    @Required
    @MaxLength(254)
    public String getDsCopyright() {
        return dsCopyright;
    }

    /**
     * Sets the value for nmMimetypeFile
     */
    public void setNmMimetypeFile(String nmMimetypeFile) {
        this.nmMimetypeFile = nmMimetypeFile;
    }

    /**
     * Gets the value for nmMimetypeFile
     */
    @Required
    @MaxLength(100)
    public String getNmMimetypeFile() {
        return nmMimetypeFile;
    }

    /**
     * Sets the value for tiEsitoContrFormato
     */
    public void setTiEsitoContrFormato(String tiEsitoContrFormato) {
        this.tiEsitoContrFormato = tiEsitoContrFormato;
    }

    /**
     * Gets the value for tiEsitoContrFormato
     */
    @Required
    @MaxLength(20)
    public String getTiEsitoContrFormato() {
        return tiEsitoContrFormato;
    }

    /**
     * Sets the value for flFormatoConcat
     */
    public void setFlFormatoConcat(String flFormatoConcat) {
        this.flFormatoConcat = flFormatoConcat;
    }

    /**
     * Gets the value for flFormatoConcat
     */
    @Required
    @MaxLength(1)
    public String getFlFormatoConcat() {
        return flFormatoConcat;
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

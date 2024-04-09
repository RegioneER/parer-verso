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

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Required;

/**
 * 
 * @author Moretti_Lu
 */
public class VUsrAbilApplic implements Serializable {

    private static final long serialVersionUID = 1L;

    public VUsrAbilApplic() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_USER_IAM
     */
    public Long idUserIam;

    /**
     * Type : VARCHAR Name : NM_USERID
     */
    public String nmUserid;

    /**
     * Type : BIGINT Name : ID_APPLIC
     */
    public Long idApplic;

    /**
     * Type : VARCHAR Name : NM_APPLIC
     */
    public String nmApplic;

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

    /**
     * Sets the value for nmUserid
     */
    public void setNmUserid(String nmUserid) {
        this.nmUserid = nmUserid;
    }

    /**
     * Gets the value for nmUserid
     */
    @MaxLength(100)
    public String getNmUserid() {
        return nmUserid;
    }

    /**
     * Sets the value for idApplic
     */
    public void setIdApplic(Long idApplic) {
        this.idApplic = idApplic;
    }

    /**
     * Gets the value for idApplic
     */
    @Required
    public Long getIdApplic() {
        return idApplic;
    }

    /**
     * Sets the value for nmApplic
     */
    public void setNmApplic(String nmApplic) {
        this.nmApplic = nmApplic;
    }

    /**
     * Gets the value for nmApplic
     */
    @MaxLength(100)
    // @Label("Numero")
    public String getNmApplic() {
        return nmApplic;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
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

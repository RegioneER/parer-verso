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

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.datasiel.simpaweb.db.pojo;

import java.io.Serializable;

/**
 *
 * @author fioravanti_f
 */
public class SIAplApplic implements Serializable {

    private static final long serialVersionUID = 1L;
    private long idApplic;
    private String cdPswReplicaUser;
    private String dsApplic;
    private String dsUrlReplicaUser;
    private String nmApplic;
    private String nmUserReplicaUser;

    public long getIdApplic() {
        return idApplic;
    }

    public String getCdPswReplicaUser() {
        return cdPswReplicaUser;
    }

    public String getDsApplic() {
        return dsApplic;
    }

    public String getDsUrlReplicaUser() {
        return dsUrlReplicaUser;
    }

    public String getNmApplic() {
        return nmApplic;
    }

    public String getNmUserReplicaUser() {
        return nmUserReplicaUser;
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

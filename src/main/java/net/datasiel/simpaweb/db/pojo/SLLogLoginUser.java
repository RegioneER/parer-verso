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
import java.util.Date;

/**
 *
 * @author fioravanti_f
 */
public class SLLogLoginUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private long idLoginUser;
    private long idApplic;
    private String nmUserid;
    private String cdIndIpClient;
    private String cdIndServer;
    private String tipoEvento;
    private Date dtEvento;
    private String cdIdEsterno;
    private String tipoUtenteAuth;

    private String dtVirtualEvento;

    public long getIdLoginUser() {
        return idLoginUser;
    }

    public void setIdLoginUser(long idLoginUser) {
        this.idLoginUser = idLoginUser;
    }

    public long getIdApplic() {
        return idApplic;
    }

    public void setIdApplic(long idApplic) {
        this.idApplic = idApplic;
    }

    public String getNmUserid() {
        return nmUserid;
    }

    public void setNmUserid(String nmUserid) {
        this.nmUserid = nmUserid;
    }

    public String getCdIndIpClient() {
        return cdIndIpClient;
    }

    public void setCdIndIpClient(String cdIndIpClient) {
        this.cdIndIpClient = cdIndIpClient;
    }

    public String getCdIndServer() {
        return cdIndServer;
    }

    public void setCdIndServer(String cdIndServer) {
        this.cdIndServer = cdIndServer;
    }

    public String getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public Date getDtEvento() {
        return dtEvento;
    }

    public void setDtEvento(Date dtEvento) {
        this.dtEvento = dtEvento;
    }

    public String getCdIdEsterno() {
        return cdIdEsterno;
    }

    public void setCdIdEsterno(String cdIdEsterno) {
        this.cdIdEsterno = cdIdEsterno;
    }

    public String getTipoUtenteAuth() {
        return tipoUtenteAuth;
    }

    public void setTipoUtenteAuth(String tipoUtenteAuth) {
        this.tipoUtenteAuth = tipoUtenteAuth;
    }

    /**
     * Colonna virtuale, non deve <b>MAI</b> essere scritta sul db
     *
     * @return
     */
    public String getDtVirtualEvento() {
        return dtVirtualEvento;
    }

    /**
     * Colonna virtuale, non deve <b>MAI</b> essere scritta sul db
     *
     * @return
     */
    public void setDtVirtualEvento(String dtVirtualEvento) {
        this.dtVirtualEvento = dtVirtualEvento;
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

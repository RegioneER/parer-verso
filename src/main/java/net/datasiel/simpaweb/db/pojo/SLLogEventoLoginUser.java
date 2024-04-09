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
import java.util.Date;

/**
 *
 * @author iacolucci_M
 */
public class SLLogEventoLoginUser implements Serializable {

    private static final long serialVersionUID = 1L;
    private Long idEventoLoginUser;
    private String cdIndIpClient;
    private String cdIndServer;
    private String dsEvento;
    private Date dtEvento;
    private String nmUserid;
    private String tipoEvento;
    private String nmNomeUser;
    private String nmCognomeUser;
    private String cdFiscUser;
    private String dsEmailUser;
    private String cdIDEsterno;

    public SLLogEventoLoginUser() {
        //
    }

    public Long getIdEventoLoginUser() {
        return this.idEventoLoginUser;
    }

    public void setIdEventoLoginUser(Long idEventoLoginUser) {
        this.idEventoLoginUser = idEventoLoginUser;
    }

    public String getCdIndIpClient() {
        return this.cdIndIpClient;
    }

    public void setCdIndIpClient(String cdIndIpClient) {
        this.cdIndIpClient = cdIndIpClient;
    }

    public String getCdIndServer() {
        return this.cdIndServer;
    }

    public void setCdIndServer(String cdIndServer) {
        this.cdIndServer = cdIndServer;
    }

    public String getDsEvento() {
        return this.dsEvento;
    }

    public void setDsEvento(String dsEvento) {
        this.dsEvento = dsEvento;
    }

    public Date getDtEvento() {
        return this.dtEvento;
    }

    public void setDtEvento(Date dtEvento) {
        this.dtEvento = dtEvento;
    }

    public String getNmUserid() {
        return this.nmUserid;
    }

    public void setNmUserid(String nmUserid) {
        this.nmUserid = nmUserid;
    }

    public String getTipoEvento() {
        return this.tipoEvento;
    }

    public void setTipoEvento(String tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public String getNmNomeUser() {
        return nmNomeUser;
    }

    public void setNmNomeUser(String nmNomeUser) {
        this.nmNomeUser = nmNomeUser;
    }

    public String getNmCognomeUser() {
        return nmCognomeUser;
    }

    public void setNmCognomeUser(String nmCognomeUser) {
        this.nmCognomeUser = nmCognomeUser;
    }

    public String getCdFiscUser() {
        return cdFiscUser;
    }

    public void setCdFiscUser(String cdFiscUser) {
        this.cdFiscUser = cdFiscUser;
    }

    public String getDsEmailUser() {
        return dsEmailUser;
    }

    public void setDsEmailUser(String dsEmailUser) {
        this.dsEmailUser = dsEmailUser;
    }

    public String getCdIDEsterno() {
        return cdIDEsterno;
    }

    public void setCdIDEsterno(String cdIDEsterno) {
        this.cdIDEsterno = cdIDEsterno;
    }

}

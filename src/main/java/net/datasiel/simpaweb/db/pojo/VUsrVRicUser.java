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
 * VUsrVRicUser
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;
import java.util.Date;

import com.manydesigns.elements.annotations.MaxLength;

public class VUsrVRicUser implements Serializable {
    /*
     * ID_USER, NM_COGNOME_USER, NM_NOME_USER, FL_ATTIVO, NM_USERID, ID_APPLIC, ID_RUOLO, NM_RUOLO, ID_AMBIENTE,
     * ID_ENTE, ID_STRUT, NM_STRUT, CD_PSW, DT_SCAD_PSW
     *
     * ID_USER_IAM, NM_COGNOME_USER, NM_NOME_USER, FL_ATTIVO, NM_USERID, CD_PWD, CD_SALT, NM_AMBIENTE, NM_ENTE,
     * ID_STRUT, NM_STRUT
     *
     */

    private static final long serialVersionUID = 1L;

    public VUsrVRicUser() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_USER
     */
    public Long idUser;

    /**
     * Type : VARCHAR Name : NM_COGNOME_USER
     */
    public String nmCognomeUser;

    /**
     * Type : VARCHAR Name : NM_NOME_USER
     */
    public String nmNomeUser;

    /**
     * Type : CHAR(1) Name : FL_ATTIVO
     */
    public String flAttivo;

    /**
     * Type : VARCHAR Name : NM_USERID
     */
    public String nmUserid;

    /**
     * Type : BIGINT Name : ID_AMBIENTE
     */
    public String nmAmbiente;

    /**
     * Type : BIGINT Name : ID_ENTE
     */
    public String nmEnte;

    /**
     * Type : VARCHAR Name : NM_STRUT
     */
    public String nmStrut;

    /**
     * Type : VARCHAR Name : CD_PSW
     */
    private String cd_psw;

    /**
     * Type : DATETIME Name : DT_SCAD_PSW
     */
    private Date dt_scad_psw;

    /**
     * Type : VARCHAR Name : CD_PSW
     */
    private String cd_salt;

    /**
     * Sets the value for idUser
     */
    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    /**
     * Gets the value for idUser
     */
    public Long getIdUser() {
        return idUser;
    }

    /**
     * Sets the value for nmCognomeUser
     */
    public void setNmCognomeUser(String nmCognomeUser) {
        this.nmCognomeUser = nmCognomeUser;
    }

    /**
     * Gets the value for nmCognomeUser
     */
    @MaxLength(100)
    public String getNmCognomeUser() {
        return nmCognomeUser;
    }

    /**
     * Sets the value for nmNomeUser
     */
    public void setNmNomeUser(String nmNomeUser) {
        this.nmNomeUser = nmNomeUser;
    }

    /**
     * Gets the value for nmNomeUser
     */
    @MaxLength(100)
    public String getNmNomeUser() {
        return nmNomeUser;
    }

    /**
     * Sets the value for flAttivo
     */
    public void setFlAttivo(String flAttivo) {
        this.flAttivo = flAttivo;
    }

    /**
     * Gets the value for flAttivo
     */
    @MaxLength(1)
    public String getFlAttivo() {
        return flAttivo;
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
     * Sets the value for idAmbiente
     */
    public void setNmAmbiente(String nmAmbiente) {
        this.nmAmbiente = nmAmbiente;
    }

    /**
     * Gets the value for idAmbiente
     */
    public String getNmAmbiente() {
        return nmAmbiente;
    }

    /**
     * Sets the value for idEnte
     */
    public void setNmEnte(String nmEnte) {
        this.nmEnte = nmEnte;
    }

    /**
     * Gets the value for idEnte
     */
    public String getNmEnte() {
        return nmEnte;
    }

    /**
     * Sets the value for nmStrut
     */
    public void setNmStrut(String nmStrut) {
        this.nmStrut = nmStrut;
    }

    /**
     * Gets the value for nmStrut
     */
    @MaxLength(304)
    public String getNmStrut() {
        return nmStrut;
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

    public String getCd_psw() {
        return cd_psw;
    }

    public void setCd_psw(String cd_psw) {
        this.cd_psw = cd_psw;
    }

    public Date getDt_scad_psw() {
        return dt_scad_psw;
    }

    public void setDt_scad_psw(Date dt_scad_psw) {
        this.dt_scad_psw = dt_scad_psw;
    }

    public String getCd_salt() {
        return cd_salt;
    }

    public void setCd_salt(String cd_salt) {
        this.cd_salt = cd_salt;
    }
}

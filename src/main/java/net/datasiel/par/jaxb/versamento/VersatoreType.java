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

//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2021.05.11 at 03:55:57 PM CEST 
//

package net.datasiel.par.jaxb.versamento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for VersatoreType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="VersatoreType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Ambiente" type="{}StringNVMax100Type"/>
 *         &lt;element name="Ente" type="{}StringNVMax100Type"/>
 *         &lt;element name="Struttura" type="{}StringNVMax100Type"/>
 *         &lt;element name="UserID" type="{}StringNVMax100Type"/>
 *         &lt;element name="Utente" type="{}StringNonVuotoType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "VersatoreType", propOrder = { "ambiente", "ente", "struttura", "userID", "utente" })
public class VersatoreType {

    @XmlElement(name = "Ambiente", required = true)
    protected String ambiente;
    @XmlElement(name = "Ente", required = true)
    protected String ente;
    @XmlElement(name = "Struttura", required = true)
    protected String struttura;
    @XmlElement(name = "UserID", required = true)
    protected String userID;
    @XmlElement(name = "Utente")
    protected String utente;

    /**
     * Gets the value of the ambiente property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getAmbiente() {
        return ambiente;
    }

    /**
     * Sets the value of the ambiente property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setAmbiente(String value) {
        this.ambiente = value;
    }

    /**
     * Gets the value of the ente property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getEnte() {
        return ente;
    }

    /**
     * Sets the value of the ente property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setEnte(String value) {
        this.ente = value;
    }

    /**
     * Gets the value of the struttura property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getStruttura() {
        return struttura;
    }

    /**
     * Sets the value of the struttura property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setStruttura(String value) {
        this.struttura = value;
    }

    /**
     * Gets the value of the userID property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getUserID() {
        return userID;
    }

    /**
     * Sets the value of the userID property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setUserID(String value) {
        this.userID = value;
    }

    /**
     * Gets the value of the utente property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getUtente() {
        return utente;
    }

    /**
     * Sets the value of the utente property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setUtente(String value) {
        this.utente = value;
    }

}
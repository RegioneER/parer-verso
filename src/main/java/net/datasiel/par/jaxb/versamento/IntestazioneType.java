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
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.0 in JDK 1.6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2013.02.26 at 02:20:31 PM CET 
//

package net.datasiel.par.jaxb.versamento;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for IntestazioneType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="IntestazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Versione" type="{}StringNVMax100Type"/>
 *         &lt;element name="Versatore" type="{}VersatoreType"/>
 *         &lt;element name="Chiave" type="{}ChiaveType"/>
 *         &lt;element name="TipologiaUnitaDocumentaria" type="{}TokenNonVuotoType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "IntestazioneType", propOrder = { "versione", "versatore", "chiave", "tipologiaUnitaDocumentaria" })
public class IntestazioneType {

    @XmlElement(name = "Versione", required = true)
    protected String versione;
    @XmlElement(name = "Versatore", required = true)
    protected VersatoreType versatore;
    @XmlElement(name = "Chiave", required = true)
    protected ChiaveType chiave;
    @XmlElement(name = "TipologiaUnitaDocumentaria", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tipologiaUnitaDocumentaria;

    /**
     * Gets the value of the versione property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVersione() {
        return versione;
    }

    /**
     * Sets the value of the versione property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setVersione(String value) {
        this.versione = value;
    }

    /**
     * Gets the value of the versatore property.
     * 
     * @return possible object is {@link VersatoreType }
     * 
     */
    public VersatoreType getVersatore() {
        return versatore;
    }

    /**
     * Sets the value of the versatore property.
     * 
     * @param value
     *            allowed object is {@link VersatoreType }
     * 
     */
    public void setVersatore(VersatoreType value) {
        this.versatore = value;
    }

    /**
     * Gets the value of the chiave property.
     * 
     * @return possible object is {@link ChiaveType }
     * 
     */
    public ChiaveType getChiave() {
        return chiave;
    }

    /**
     * Sets the value of the chiave property.
     * 
     * @param value
     *            allowed object is {@link ChiaveType }
     * 
     */
    public void setChiave(ChiaveType value) {
        this.chiave = value;
    }

    /**
     * Gets the value of the tipologiaUnitaDocumentaria property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTipologiaUnitaDocumentaria() {
        return tipologiaUnitaDocumentaria;
    }

    /**
     * Sets the value of the tipologiaUnitaDocumentaria property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTipologiaUnitaDocumentaria(String value) {
        this.tipologiaUnitaDocumentaria = value;
    }

}
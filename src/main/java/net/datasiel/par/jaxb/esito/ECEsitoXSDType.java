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
// Generated on: 2013.02.26 at 03:12:57 PM CET 
//

package net.datasiel.par.jaxb.esito;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ECEsitoXSDType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECEsitoXSDType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="CodiceEsito" type="{}ECEsitoPosNegType"/>
 *         &lt;sequence>
 *           &lt;element name="ControlloStrutturaXML" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="UnivocitaIDComponenti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="UnivocitaIDDocumenti" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *           &lt;element name="CorrispondenzaAllegatiDichiarati" type="{}ECEsitoPosNegType" minOccurs="0"/>
 *           &lt;element name="CorrispondenzaAnnessiDichiarati" type="{}ECEsitoPosNegType" minOccurs="0"/>
 *           &lt;element name="CorrispondenzaAnnotazioniDichiarate" type="{}ECEsitoPosNegType" minOccurs="0"/>
 *         &lt;/sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECEsitoXSDType", propOrder = { "codiceEsito", "controlloStrutturaXML", "univocitaIDComponenti",
        "univocitaIDDocumenti", "corrispondenzaAllegatiDichiarati", "corrispondenzaAnnessiDichiarati",
        "corrispondenzaAnnotazioniDichiarate" })
public class ECEsitoXSDType {

    @XmlElement(name = "CodiceEsito", required = true)
    protected ECEsitoPosNegType codiceEsito;
    @XmlElement(name = "ControlloStrutturaXML")
    protected String controlloStrutturaXML;
    @XmlElement(name = "UnivocitaIDComponenti")
    protected String univocitaIDComponenti;
    @XmlElement(name = "UnivocitaIDDocumenti")
    protected String univocitaIDDocumenti;
    @XmlElement(name = "CorrispondenzaAllegatiDichiarati")
    protected ECEsitoPosNegType corrispondenzaAllegatiDichiarati;
    @XmlElement(name = "CorrispondenzaAnnessiDichiarati")
    protected ECEsitoPosNegType corrispondenzaAnnessiDichiarati;
    @XmlElement(name = "CorrispondenzaAnnotazioniDichiarate")
    protected ECEsitoPosNegType corrispondenzaAnnotazioniDichiarate;

    /**
     * Gets the value of the codiceEsito property.
     * 
     * @return possible object is {@link ECEsitoPosNegType }
     * 
     */
    public ECEsitoPosNegType getCodiceEsito() {
        return codiceEsito;
    }

    /**
     * Sets the value of the codiceEsito property.
     * 
     * @param value
     *            allowed object is {@link ECEsitoPosNegType }
     * 
     */
    public void setCodiceEsito(ECEsitoPosNegType value) {
        this.codiceEsito = value;
    }

    /**
     * Gets the value of the controlloStrutturaXML property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getControlloStrutturaXML() {
        return controlloStrutturaXML;
    }

    /**
     * Sets the value of the controlloStrutturaXML property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setControlloStrutturaXML(String value) {
        this.controlloStrutturaXML = value;
    }

    /**
     * Gets the value of the univocitaIDComponenti property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getUnivocitaIDComponenti() {
        return univocitaIDComponenti;
    }

    /**
     * Sets the value of the univocitaIDComponenti property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setUnivocitaIDComponenti(String value) {
        this.univocitaIDComponenti = value;
    }

    /**
     * Gets the value of the univocitaIDDocumenti property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getUnivocitaIDDocumenti() {
        return univocitaIDDocumenti;
    }

    /**
     * Sets the value of the univocitaIDDocumenti property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setUnivocitaIDDocumenti(String value) {
        this.univocitaIDDocumenti = value;
    }

    /**
     * Gets the value of the corrispondenzaAllegatiDichiarati property.
     * 
     * @return possible object is {@link ECEsitoPosNegType }
     * 
     */
    public ECEsitoPosNegType getCorrispondenzaAllegatiDichiarati() {
        return corrispondenzaAllegatiDichiarati;
    }

    /**
     * Sets the value of the corrispondenzaAllegatiDichiarati property.
     * 
     * @param value
     *            allowed object is {@link ECEsitoPosNegType }
     * 
     */
    public void setCorrispondenzaAllegatiDichiarati(ECEsitoPosNegType value) {
        this.corrispondenzaAllegatiDichiarati = value;
    }

    /**
     * Gets the value of the corrispondenzaAnnessiDichiarati property.
     * 
     * @return possible object is {@link ECEsitoPosNegType }
     * 
     */
    public ECEsitoPosNegType getCorrispondenzaAnnessiDichiarati() {
        return corrispondenzaAnnessiDichiarati;
    }

    /**
     * Sets the value of the corrispondenzaAnnessiDichiarati property.
     * 
     * @param value
     *            allowed object is {@link ECEsitoPosNegType }
     * 
     */
    public void setCorrispondenzaAnnessiDichiarati(ECEsitoPosNegType value) {
        this.corrispondenzaAnnessiDichiarati = value;
    }

    /**
     * Gets the value of the corrispondenzaAnnotazioniDichiarate property.
     * 
     * @return possible object is {@link ECEsitoPosNegType }
     * 
     */
    public ECEsitoPosNegType getCorrispondenzaAnnotazioniDichiarate() {
        return corrispondenzaAnnotazioniDichiarate;
    }

    /**
     * Sets the value of the corrispondenzaAnnotazioniDichiarate property.
     * 
     * @param value
     *            allowed object is {@link ECEsitoPosNegType }
     * 
     */
    public void setCorrispondenzaAnnotazioniDichiarate(ECEsitoPosNegType value) {
        this.corrispondenzaAnnotazioniDichiarate = value;
    }

}

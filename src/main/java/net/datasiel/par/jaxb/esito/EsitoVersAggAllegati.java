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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Versione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="VersioneXMLChiamata" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="DataVersamento" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="EsitoGenerale" type="{}ECEsitoGeneraleType"/>
 *         &lt;element name="EsitoChiamataWS" type="{}ECEsitoChiamataWSType"/>
 *         &lt;element name="EsitoXSD" type="{}ECEsitoXSDAggAllType"/>
 *         &lt;element name="Configurazione" type="{}ECConfigurazioneType" minOccurs="0"/>
 *         &lt;element name="UnitaDocumentaria" type="{}ECUnitaDocAggAllType" minOccurs="0"/>
 *         &lt;element name="XMLVersamento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "versione", "versioneXMLChiamata", "dataVersamento", "esitoGenerale",
        "esitoChiamataWS", "esitoXSD", "configurazione", "unitaDocumentaria", "xmlVersamento" })
@XmlRootElement(name = "EsitoVersAggAllegati")
public class EsitoVersAggAllegati {

    @XmlElement(name = "Versione")
    protected String versione;
    @XmlElement(name = "VersioneXMLChiamata")
    protected String versioneXMLChiamata;
    @XmlElement(name = "DataVersamento", required = true)
    protected XMLGregorianCalendar dataVersamento;
    @XmlElement(name = "EsitoGenerale", required = true)
    protected ECEsitoGeneraleType esitoGenerale;
    @XmlElement(name = "EsitoChiamataWS", required = true)
    protected ECEsitoChiamataWSType esitoChiamataWS;
    @XmlElement(name = "EsitoXSD", required = true)
    protected ECEsitoXSDAggAllType esitoXSD;
    @XmlElement(name = "Configurazione")
    protected ECConfigurazioneType configurazione;
    @XmlElement(name = "UnitaDocumentaria")
    protected ECUnitaDocAggAllType unitaDocumentaria;
    @XmlElement(name = "XMLVersamento")
    protected String xmlVersamento;

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
     * Gets the value of the versioneXMLChiamata property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVersioneXMLChiamata() {
        return versioneXMLChiamata;
    }

    /**
     * Sets the value of the versioneXMLChiamata property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setVersioneXMLChiamata(String value) {
        this.versioneXMLChiamata = value;
    }

    /**
     * Gets the value of the dataVersamento property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDataVersamento() {
        return dataVersamento;
    }

    /**
     * Sets the value of the dataVersamento property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDataVersamento(XMLGregorianCalendar value) {
        this.dataVersamento = value;
    }

    /**
     * Gets the value of the esitoGenerale property.
     * 
     * @return possible object is {@link ECEsitoGeneraleType }
     * 
     */
    public ECEsitoGeneraleType getEsitoGenerale() {
        return esitoGenerale;
    }

    /**
     * Sets the value of the esitoGenerale property.
     * 
     * @param value
     *            allowed object is {@link ECEsitoGeneraleType }
     * 
     */
    public void setEsitoGenerale(ECEsitoGeneraleType value) {
        this.esitoGenerale = value;
    }

    /**
     * Gets the value of the esitoChiamataWS property.
     * 
     * @return possible object is {@link ECEsitoChiamataWSType }
     * 
     */
    public ECEsitoChiamataWSType getEsitoChiamataWS() {
        return esitoChiamataWS;
    }

    /**
     * Sets the value of the esitoChiamataWS property.
     * 
     * @param value
     *            allowed object is {@link ECEsitoChiamataWSType }
     * 
     */
    public void setEsitoChiamataWS(ECEsitoChiamataWSType value) {
        this.esitoChiamataWS = value;
    }

    /**
     * Gets the value of the esitoXSD property.
     * 
     * @return possible object is {@link ECEsitoXSDAggAllType }
     * 
     */
    public ECEsitoXSDAggAllType getEsitoXSD() {
        return esitoXSD;
    }

    /**
     * Sets the value of the esitoXSD property.
     * 
     * @param value
     *            allowed object is {@link ECEsitoXSDAggAllType }
     * 
     */
    public void setEsitoXSD(ECEsitoXSDAggAllType value) {
        this.esitoXSD = value;
    }

    /**
     * Gets the value of the configurazione property.
     * 
     * @return possible object is {@link ECConfigurazioneType }
     * 
     */
    public ECConfigurazioneType getConfigurazione() {
        return configurazione;
    }

    /**
     * Sets the value of the configurazione property.
     * 
     * @param value
     *            allowed object is {@link ECConfigurazioneType }
     * 
     */
    public void setConfigurazione(ECConfigurazioneType value) {
        this.configurazione = value;
    }

    /**
     * Gets the value of the unitaDocumentaria property.
     * 
     * @return possible object is {@link ECUnitaDocAggAllType }
     * 
     */
    public ECUnitaDocAggAllType getUnitaDocumentaria() {
        return unitaDocumentaria;
    }

    /**
     * Sets the value of the unitaDocumentaria property.
     * 
     * @param value
     *            allowed object is {@link ECUnitaDocAggAllType }
     * 
     */
    public void setUnitaDocumentaria(ECUnitaDocAggAllType value) {
        this.unitaDocumentaria = value;
    }

    /**
     * Gets the value of the xmlVersamento property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getXMLVersamento() {
        return xmlVersamento;
    }

    /**
     * Sets the value of the xmlVersamento property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setXMLVersamento(String value) {
        this.xmlVersamento = value;
    }

}

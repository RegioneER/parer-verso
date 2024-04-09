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
// Generated on: 2013.03.05 at 01:48:42 PM CET 
//

package net.datasiel.par.jaxb.datispecifici;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for DatiSpecificiType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiSpecificiType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="VersioneDatiSpecifici" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="DenominazioneBando" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="RagioneSociale" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataPresentazione" type="{http://www.w3.org/2001/XMLSchema}date"/>
 *         &lt;element name="ImportoTotaleLordo" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="ContributoRichiesto" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiSpecificiTypeUD", propOrder = { "versioneDatiSpecifici", "denominazioneBando", "ragioneSociale",
        "dataPresentazione", "importoTotaleLordo", "contributoRichiesto" })
public class DatiSpecificiType {

    @XmlElement(name = "VersioneDatiSpecifici", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String versioneDatiSpecifici;
    @XmlElement(name = "DenominazioneBando", required = true)
    protected String denominazioneBando;
    @XmlElement(name = "RagioneSociale", required = true)
    protected String ragioneSociale;
    @XmlElement(name = "DataPresentazione", required = true)
    protected XMLGregorianCalendar dataPresentazione;
    @XmlElement(name = "ImportoTotaleLordo", required = true)
    protected String importoTotaleLordo;
    @XmlElement(name = "ContributoRichiesto", required = true)
    protected String contributoRichiesto;

    /**
     * Gets the value of the versioneDatiSpecifici property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getVersioneDatiSpecifici() {
        return versioneDatiSpecifici;
    }

    /**
     * Sets the value of the versioneDatiSpecifici property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setVersioneDatiSpecifici(String value) {
        this.versioneDatiSpecifici = value;
    }

    /**
     * Gets the value of the denominazioneBando property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getDenominazioneBando() {
        return denominazioneBando;
    }

    /**
     * Sets the value of the denominazioneBando property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setDenominazioneBando(String value) {
        this.denominazioneBando = value;
    }

    /**
     * Gets the value of the ragioneSociale property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getRagioneSociale() {
        return ragioneSociale;
    }

    /**
     * Sets the value of the ragioneSociale property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setRagioneSociale(String value) {
        this.ragioneSociale = value;
    }

    /**
     * Gets the value of the dataPresentazione property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getDataPresentazione() {
        return dataPresentazione;
    }

    /**
     * Sets the value of the dataPresentazione property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setDataPresentazione(XMLGregorianCalendar value) {
        this.dataPresentazione = value;
    }

    /**
     * Gets the value of the importoTotaleLordo property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getImportoTotaleLordo() {
        return importoTotaleLordo;
    }

    /**
     * Sets the value of the importoTotaleLordo property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setImportoTotaleLordo(String value) {
        this.importoTotaleLordo = value;
    }

    /**
     * Gets the value of the contributoRichiesto property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getContributoRichiesto() {
        return contributoRichiesto;
    }

    /**
     * Sets the value of the contributoRichiesto property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setContributoRichiesto(String value) {
        this.contributoRichiesto = value;
    }

}

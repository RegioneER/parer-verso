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
// Generated on: 2013.02.26 at 03:12:46 PM CET 
//

package net.datasiel.par.jaxb.stato;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for DatiUnitaDocType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="DatiUnitaDocType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Versatore" type="{}SCVersatoreType" minOccurs="0"/>
 *         &lt;element name="Chiave" type="{}ChiaveType"/>
 *         &lt;element name="StatoConservazioneUD" type="{}TokenNonVuotoType"/>
 *         &lt;element name="Volumi" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Volume" type="{}DatiVolumeType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DatiUnitaDocType", propOrder = { "versatore", "chiave", "statoConservazioneUD", "volumi" })
public class DatiUnitaDocType {

    @XmlElement(name = "Versatore")
    protected SCVersatoreType versatore;
    @XmlElement(name = "Chiave", required = true)
    protected ChiaveType chiave;
    @XmlElement(name = "StatoConservazioneUD", required = true)
    protected String statoConservazioneUD;
    @XmlElement(name = "Volumi")
    protected DatiUnitaDocType.Volumi volumi;

    /**
     * Gets the value of the versatore property.
     * 
     * @return possible object is {@link SCVersatoreType }
     * 
     */
    public SCVersatoreType getVersatore() {
        return versatore;
    }

    /**
     * Sets the value of the versatore property.
     * 
     * @param value
     *            allowed object is {@link SCVersatoreType }
     * 
     */
    public void setVersatore(SCVersatoreType value) {
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
     * Gets the value of the statoConservazioneUD property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getStatoConservazioneUD() {
        return statoConservazioneUD;
    }

    /**
     * Sets the value of the statoConservazioneUD property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setStatoConservazioneUD(String value) {
        this.statoConservazioneUD = value;
    }

    /**
     * Gets the value of the volumi property.
     * 
     * @return possible object is {@link DatiUnitaDocType.Volumi }
     * 
     */
    public DatiUnitaDocType.Volumi getVolumi() {
        return volumi;
    }

    /**
     * Sets the value of the volumi property.
     * 
     * @param value
     *            allowed object is {@link DatiUnitaDocType.Volumi }
     * 
     */
    public void setVolumi(DatiUnitaDocType.Volumi value) {
        this.volumi = value;
    }

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
     *         &lt;element name="Volume" type="{}DatiVolumeType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "volume" })
    public static class Volumi {

        @XmlElement(name = "Volume", required = true)
        protected List<DatiVolumeType> volume;

        /**
         * Gets the value of the volume property.
         * 
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you
         * make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE>
         * method for the volume property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * 
         * <pre>
         * getVolume().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list {@link DatiVolumeType }
         * 
         * 
         */
        public List<DatiVolumeType> getVolume() {
            if (volume == null) {
                volume = new ArrayList<DatiVolumeType>();
            }
            return this.volume;
        }

    }

}

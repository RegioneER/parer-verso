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

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for ECMarcaType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECMarcaType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrdineMarca" type="{http://www.w3.org/2001/XMLSchema}positiveInteger" minOccurs="0"/>
 *         &lt;element name="FormatoMarca" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Timestamp" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="EsitoMarca">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ControlloConformita" type="{}ECEsitoControlloType" minOccurs="0"/>
 *                   &lt;element name="VerificaMarca" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="CodiceEsito" type="{}ECEsitoPosNegWarType"/>
 *                             &lt;element name="ControlloCrittografico" type="{}ECEsitoControlloType" minOccurs="0"/>
 *                             &lt;element name="ControlloCatenaTrusted" type="{}ECEsitoControlloType" minOccurs="0"/>
 *                             &lt;element name="ControlloCertificato" type="{}ECEsitoControlloType" minOccurs="0"/>
 *                             &lt;element name="ControlloCRL" type="{}ECEsitoControlloType" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
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
@XmlType(name = "ECMarcaType", propOrder = { "ordineMarca", "formatoMarca", "timestamp", "esitoMarca" })
public class ECMarcaType {

    @XmlElement(name = "OrdineMarca")
    protected BigInteger ordineMarca;
    @XmlElement(name = "FormatoMarca")
    protected String formatoMarca;
    @XmlElement(name = "Timestamp")
    protected XMLGregorianCalendar timestamp;
    @XmlElement(name = "EsitoMarca", required = true)
    protected ECMarcaType.EsitoMarca esitoMarca;

    /**
     * Gets the value of the ordineMarca property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getOrdineMarca() {
        return ordineMarca;
    }

    /**
     * Sets the value of the ordineMarca property.
     * 
     * @param value
     *            allowed object is {@link BigInteger }
     * 
     */
    public void setOrdineMarca(BigInteger value) {
        this.ordineMarca = value;
    }

    /**
     * Gets the value of the formatoMarca property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getFormatoMarca() {
        return formatoMarca;
    }

    /**
     * Sets the value of the formatoMarca property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setFormatoMarca(String value) {
        this.formatoMarca = value;
    }

    /**
     * Gets the value of the timestamp property.
     * 
     * @return possible object is {@link XMLGregorianCalendar }
     * 
     */
    public XMLGregorianCalendar getTimestamp() {
        return timestamp;
    }

    /**
     * Sets the value of the timestamp property.
     * 
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     * 
     */
    public void setTimestamp(XMLGregorianCalendar value) {
        this.timestamp = value;
    }

    /**
     * Gets the value of the esitoMarca property.
     * 
     * @return possible object is {@link ECMarcaType.EsitoMarca }
     * 
     */
    public ECMarcaType.EsitoMarca getEsitoMarca() {
        return esitoMarca;
    }

    /**
     * Sets the value of the esitoMarca property.
     * 
     * @param value
     *            allowed object is {@link ECMarcaType.EsitoMarca }
     * 
     */
    public void setEsitoMarca(ECMarcaType.EsitoMarca value) {
        this.esitoMarca = value;
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
     *         &lt;element name="ControlloConformita" type="{}ECEsitoControlloType" minOccurs="0"/>
     *         &lt;element name="VerificaMarca" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="CodiceEsito" type="{}ECEsitoPosNegWarType"/>
     *                   &lt;element name="ControlloCrittografico" type="{}ECEsitoControlloType" minOccurs="0"/>
     *                   &lt;element name="ControlloCatenaTrusted" type="{}ECEsitoControlloType" minOccurs="0"/>
     *                   &lt;element name="ControlloCertificato" type="{}ECEsitoControlloType" minOccurs="0"/>
     *                   &lt;element name="ControlloCRL" type="{}ECEsitoControlloType" minOccurs="0"/>
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
    @XmlType(name = "", propOrder = { "controlloConformita", "verificaMarca" })
    public static class EsitoMarca {

        @XmlElement(name = "ControlloConformita")
        protected ECEsitoControlloType controlloConformita;
        @XmlElement(name = "VerificaMarca")
        protected ECMarcaType.EsitoMarca.VerificaMarca verificaMarca;

        /**
         * Gets the value of the controlloConformita property.
         * 
         * @return possible object is {@link ECEsitoControlloType }
         * 
         */
        public ECEsitoControlloType getControlloConformita() {
            return controlloConformita;
        }

        /**
         * Sets the value of the controlloConformita property.
         * 
         * @param value
         *            allowed object is {@link ECEsitoControlloType }
         * 
         */
        public void setControlloConformita(ECEsitoControlloType value) {
            this.controlloConformita = value;
        }

        /**
         * Gets the value of the verificaMarca property.
         * 
         * @return possible object is {@link ECMarcaType.EsitoMarca.VerificaMarca }
         * 
         */
        public ECMarcaType.EsitoMarca.VerificaMarca getVerificaMarca() {
            return verificaMarca;
        }

        /**
         * Sets the value of the verificaMarca property.
         * 
         * @param value
         *            allowed object is {@link ECMarcaType.EsitoMarca.VerificaMarca }
         * 
         */
        public void setVerificaMarca(ECMarcaType.EsitoMarca.VerificaMarca value) {
            this.verificaMarca = value;
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
         *         &lt;element name="CodiceEsito" type="{}ECEsitoPosNegWarType"/>
         *         &lt;element name="ControlloCrittografico" type="{}ECEsitoControlloType" minOccurs="0"/>
         *         &lt;element name="ControlloCatenaTrusted" type="{}ECEsitoControlloType" minOccurs="0"/>
         *         &lt;element name="ControlloCertificato" type="{}ECEsitoControlloType" minOccurs="0"/>
         *         &lt;element name="ControlloCRL" type="{}ECEsitoControlloType" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = { "codiceEsito", "controlloCrittografico", "controlloCatenaTrusted",
                "controlloCertificato", "controlloCRL" })
        public static class VerificaMarca {

            @XmlElement(name = "CodiceEsito", required = true)
            protected ECEsitoPosNegWarType codiceEsito;
            @XmlElement(name = "ControlloCrittografico")
            protected ECEsitoControlloType controlloCrittografico;
            @XmlElement(name = "ControlloCatenaTrusted")
            protected ECEsitoControlloType controlloCatenaTrusted;
            @XmlElement(name = "ControlloCertificato")
            protected ECEsitoControlloType controlloCertificato;
            @XmlElement(name = "ControlloCRL")
            protected ECEsitoControlloType controlloCRL;

            /**
             * Gets the value of the codiceEsito property.
             * 
             * @return possible object is {@link ECEsitoPosNegWarType }
             * 
             */
            public ECEsitoPosNegWarType getCodiceEsito() {
                return codiceEsito;
            }

            /**
             * Sets the value of the codiceEsito property.
             * 
             * @param value
             *            allowed object is {@link ECEsitoPosNegWarType }
             * 
             */
            public void setCodiceEsito(ECEsitoPosNegWarType value) {
                this.codiceEsito = value;
            }

            /**
             * Gets the value of the controlloCrittografico property.
             * 
             * @return possible object is {@link ECEsitoControlloType }
             * 
             */
            public ECEsitoControlloType getControlloCrittografico() {
                return controlloCrittografico;
            }

            /**
             * Sets the value of the controlloCrittografico property.
             * 
             * @param value
             *            allowed object is {@link ECEsitoControlloType }
             * 
             */
            public void setControlloCrittografico(ECEsitoControlloType value) {
                this.controlloCrittografico = value;
            }

            /**
             * Gets the value of the controlloCatenaTrusted property.
             * 
             * @return possible object is {@link ECEsitoControlloType }
             * 
             */
            public ECEsitoControlloType getControlloCatenaTrusted() {
                return controlloCatenaTrusted;
            }

            /**
             * Sets the value of the controlloCatenaTrusted property.
             * 
             * @param value
             *            allowed object is {@link ECEsitoControlloType }
             * 
             */
            public void setControlloCatenaTrusted(ECEsitoControlloType value) {
                this.controlloCatenaTrusted = value;
            }

            /**
             * Gets the value of the controlloCertificato property.
             * 
             * @return possible object is {@link ECEsitoControlloType }
             * 
             */
            public ECEsitoControlloType getControlloCertificato() {
                return controlloCertificato;
            }

            /**
             * Sets the value of the controlloCertificato property.
             * 
             * @param value
             *            allowed object is {@link ECEsitoControlloType }
             * 
             */
            public void setControlloCertificato(ECEsitoControlloType value) {
                this.controlloCertificato = value;
            }

            /**
             * Gets the value of the controlloCRL property.
             * 
             * @return possible object is {@link ECEsitoControlloType }
             * 
             */
            public ECEsitoControlloType getControlloCRL() {
                return controlloCRL;
            }

            /**
             * Sets the value of the controlloCRL property.
             * 
             * @param value
             *            allowed object is {@link ECEsitoControlloType }
             * 
             */
            public void setControlloCRL(ECEsitoControlloType value) {
                this.controlloCRL = value;
            }

        }

    }

}
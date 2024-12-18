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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ECDocumentoType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ECDocumentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChiaveDoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="IDDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="TipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FirmatoDigitalmente" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="EsitoDocumento">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="CodiceEsito" type="{}ECEsitoPosNegWarType"/>
 *                   &lt;sequence>
 *                     &lt;element name="VerificaTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                     &lt;element name="CorrispondenzaDatiSpecifici" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="CorrispondenzaDatiFiscali" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                     &lt;element name="NumerazioneFiscale" type="{}ECEsitoPosNegType" minOccurs="0"/>
 *                     &lt;element name="VerificaTipoStruttura" type="{}ECEsitoPosNegType" minOccurs="0"/>
 *                     &lt;element name="VerificaFirmeDocumento" type="{}ECEsitoPosNegWarType" minOccurs="0"/>
 *                     &lt;element name="UnivocitaOrdinePresentazione" type="{}ECEsitoPosNegType" minOccurs="0"/>
 *                   &lt;/sequence>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="Componenti" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Componente" type="{}ECComponenteType" maxOccurs="unbounded"/>
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
@XmlType(name = "ECDocumentoType", propOrder = { "chiaveDoc", "idDocumento", "tipoDocumento", "firmatoDigitalmente",
        "esitoDocumento", "componenti" })
public class ECDocumentoType {

    @XmlElement(name = "ChiaveDoc", required = true)
    protected String chiaveDoc;
    @XmlElement(name = "IDDocumento")
    protected String idDocumento;
    @XmlElement(name = "TipoDocumento")
    protected String tipoDocumento;
    @XmlElement(name = "FirmatoDigitalmente")
    protected Boolean firmatoDigitalmente;
    @XmlElement(name = "EsitoDocumento", required = true)
    protected ECDocumentoType.EsitoDocumento esitoDocumento;
    @XmlElement(name = "Componenti")
    protected ECDocumentoType.Componenti componenti;

    /**
     * Gets the value of the chiaveDoc property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getChiaveDoc() {
        return chiaveDoc;
    }

    /**
     * Sets the value of the chiaveDoc property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setChiaveDoc(String value) {
        this.chiaveDoc = value;
    }

    /**
     * Gets the value of the idDocumento property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIDDocumento() {
        return idDocumento;
    }

    /**
     * Sets the value of the idDocumento property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setIDDocumento(String value) {
        this.idDocumento = value;
    }

    /**
     * Gets the value of the tipoDocumento property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTipoDocumento() {
        return tipoDocumento;
    }

    /**
     * Sets the value of the tipoDocumento property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setTipoDocumento(String value) {
        this.tipoDocumento = value;
    }

    /**
     * Gets the value of the firmatoDigitalmente property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean isFirmatoDigitalmente() {
        return firmatoDigitalmente;
    }

    /**
     * Sets the value of the firmatoDigitalmente property.
     *
     * @param value
     *            allowed object is {@link Boolean }
     *
     */
    public void setFirmatoDigitalmente(Boolean value) {
        this.firmatoDigitalmente = value;
    }

    /**
     * Gets the value of the esitoDocumento property.
     *
     * @return possible object is {@link ECDocumentoType.EsitoDocumento }
     *
     */
    public ECDocumentoType.EsitoDocumento getEsitoDocumento() {
        return esitoDocumento;
    }

    /**
     * Sets the value of the esitoDocumento property.
     *
     * @param value
     *            allowed object is {@link ECDocumentoType.EsitoDocumento }
     *
     */
    public void setEsitoDocumento(ECDocumentoType.EsitoDocumento value) {
        this.esitoDocumento = value;
    }

    /**
     * Gets the value of the componenti property.
     *
     * @return possible object is {@link ECDocumentoType.Componenti }
     *
     */
    public ECDocumentoType.Componenti getComponenti() {
        return componenti;
    }

    /**
     * Sets the value of the componenti property.
     *
     * @param value
     *            allowed object is {@link ECDocumentoType.Componenti }
     *
     */
    public void setComponenti(ECDocumentoType.Componenti value) {
        this.componenti = value;
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
     *         &lt;element name="Componente" type="{}ECComponenteType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "componente" })
    public static class Componenti {

        @XmlElement(name = "Componente", required = true)
        protected List<ECComponenteType> componente;

        /**
         * Gets the value of the componente property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you
         * make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE>
         * method for the componente property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getComponente().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link ECComponenteType }
         *
         *
         */
        public List<ECComponenteType> getComponente() {
            if (componente == null) {
                componente = new ArrayList<ECComponenteType>();
            }
            return this.componente;
        }

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
     *         &lt;sequence>
     *           &lt;element name="VerificaTipoDocumento" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *           &lt;element name="CorrispondenzaDatiSpecifici" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *           &lt;element name="CorrispondenzaDatiFiscali" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *           &lt;element name="NumerazioneFiscale" type="{}ECEsitoPosNegType" minOccurs="0"/>
     *           &lt;element name="VerificaTipoStruttura" type="{}ECEsitoPosNegType" minOccurs="0"/>
     *           &lt;element name="VerificaFirmeDocumento" type="{}ECEsitoPosNegWarType" minOccurs="0"/>
     *           &lt;element name="UnivocitaOrdinePresentazione" type="{}ECEsitoPosNegType" minOccurs="0"/>
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
    @XmlType(name = "", propOrder = { "codiceEsito", "verificaTipoDocumento", "corrispondenzaDatiSpecifici",
            "corrispondenzaDatiFiscali", "numerazioneFiscale", "verificaTipoStruttura", "verificaFirmeDocumento",
            "univocitaOrdinePresentazione" })
    public static class EsitoDocumento {

        @XmlElement(name = "CodiceEsito", required = true)
        protected ECEsitoPosNegWarType codiceEsito;
        @XmlElement(name = "VerificaTipoDocumento", required = true)
        protected String verificaTipoDocumento;
        @XmlElement(name = "CorrispondenzaDatiSpecifici")
        protected String corrispondenzaDatiSpecifici;
        @XmlElement(name = "CorrispondenzaDatiFiscali")
        protected String corrispondenzaDatiFiscali;
        @XmlElement(name = "NumerazioneFiscale")
        protected ECEsitoPosNegType numerazioneFiscale;
        @XmlElement(name = "VerificaTipoStruttura")
        protected ECEsitoPosNegType verificaTipoStruttura;
        @XmlElement(name = "VerificaFirmeDocumento")
        protected ECEsitoPosNegWarType verificaFirmeDocumento;
        @XmlElement(name = "UnivocitaOrdinePresentazione")
        protected ECEsitoPosNegType univocitaOrdinePresentazione;

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
         * Gets the value of the verificaTipoDocumento property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getVerificaTipoDocumento() {
            return verificaTipoDocumento;
        }

        /**
         * Sets the value of the verificaTipoDocumento property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setVerificaTipoDocumento(String value) {
            this.verificaTipoDocumento = value;
        }

        /**
         * Gets the value of the corrispondenzaDatiSpecifici property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCorrispondenzaDatiSpecifici() {
            return corrispondenzaDatiSpecifici;
        }

        /**
         * Sets the value of the corrispondenzaDatiSpecifici property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCorrispondenzaDatiSpecifici(String value) {
            this.corrispondenzaDatiSpecifici = value;
        }

        /**
         * Gets the value of the corrispondenzaDatiFiscali property.
         *
         * @return possible object is {@link String }
         *
         */
        public String getCorrispondenzaDatiFiscali() {
            return corrispondenzaDatiFiscali;
        }

        /**
         * Sets the value of the corrispondenzaDatiFiscali property.
         *
         * @param value
         *            allowed object is {@link String }
         *
         */
        public void setCorrispondenzaDatiFiscali(String value) {
            this.corrispondenzaDatiFiscali = value;
        }

        /**
         * Gets the value of the numerazioneFiscale property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         *
         */
        public ECEsitoPosNegType getNumerazioneFiscale() {
            return numerazioneFiscale;
        }

        /**
         * Sets the value of the numerazioneFiscale property.
         *
         * @param value
         *            allowed object is {@link ECEsitoPosNegType }
         *
         */
        public void setNumerazioneFiscale(ECEsitoPosNegType value) {
            this.numerazioneFiscale = value;
        }

        /**
         * Gets the value of the verificaTipoStruttura property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         *
         */
        public ECEsitoPosNegType getVerificaTipoStruttura() {
            return verificaTipoStruttura;
        }

        /**
         * Sets the value of the verificaTipoStruttura property.
         *
         * @param value
         *            allowed object is {@link ECEsitoPosNegType }
         *
         */
        public void setVerificaTipoStruttura(ECEsitoPosNegType value) {
            this.verificaTipoStruttura = value;
        }

        /**
         * Gets the value of the verificaFirmeDocumento property.
         *
         * @return possible object is {@link ECEsitoPosNegWarType }
         *
         */
        public ECEsitoPosNegWarType getVerificaFirmeDocumento() {
            return verificaFirmeDocumento;
        }

        /**
         * Sets the value of the verificaFirmeDocumento property.
         *
         * @param value
         *            allowed object is {@link ECEsitoPosNegWarType }
         *
         */
        public void setVerificaFirmeDocumento(ECEsitoPosNegWarType value) {
            this.verificaFirmeDocumento = value;
        }

        /**
         * Gets the value of the univocitaOrdinePresentazione property.
         *
         * @return possible object is {@link ECEsitoPosNegType }
         *
         */
        public ECEsitoPosNegType getUnivocitaOrdinePresentazione() {
            return univocitaOrdinePresentazione;
        }

        /**
         * Sets the value of the univocitaOrdinePresentazione property.
         *
         * @param value
         *            allowed object is {@link ECEsitoPosNegType }
         *
         */
        public void setUnivocitaOrdinePresentazione(ECEsitoPosNegType value) {
            this.univocitaOrdinePresentazione = value;
        }

    }

}

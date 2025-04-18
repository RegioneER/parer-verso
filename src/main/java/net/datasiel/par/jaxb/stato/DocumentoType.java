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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for DocumentoType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="DocumentoType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ChiaveDoc" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="DataVersamentoDoc" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="TipoConservazione" type="{}TipoConservazioneType" minOccurs="0"/>
 *         &lt;element name="SistemaDiMigrazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IDDocumento" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="TipoDocumento" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *         &lt;element name="StatoConservazione" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *               &lt;enumeration value="IN_ATTESA_SCHED"/>
 *               &lt;enumeration value="IN_VOLUME_APERTO"/>
 *               &lt;enumeration value="IN_VOLUME_CHIUSO"/>
 *               &lt;enumeration value="IN_VOLUME_IN_ERRORE"/>
 *               &lt;enumeration value="NON_SELEZ_SCHED"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="Volumi" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="Volume" type="{}DatiRifVolumeType" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="ProfiloDocumento" type="{}ProfiloDocumentoType" minOccurs="0"/>
 *         &lt;element name="DatiSpecifici" type="{}DatiSpecificiType" minOccurs="0"/>
 *         &lt;element name="DatiSpecificiMigrazione" type="{}DatiSpecificiType" minOccurs="0"/>
 *         &lt;element name="DatiFiscali" type="{}DatiFiscaliType" minOccurs="0"/>
 *         &lt;element name="StrutturaOriginale" type="{}StrutturaType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "DocumentoType", propOrder = { "chiaveDoc", "dataVersamentoDoc", "tipoConservazione",
        "sistemaDiMigrazione", "idDocumento", "tipoDocumento", "statoConservazione", "volumi", "profiloDocumento",
        "datiSpecifici", "datiSpecificiMigrazione", "datiFiscali", "strutturaOriginale" })
public class DocumentoType {

    @XmlElement(name = "ChiaveDoc", required = true)
    protected String chiaveDoc;
    @XmlElement(name = "DataVersamentoDoc", required = true)
    protected XMLGregorianCalendar dataVersamentoDoc;
    @XmlElement(name = "TipoConservazione")
    protected TipoConservazioneType tipoConservazione;
    @XmlElement(name = "SistemaDiMigrazione")
    protected String sistemaDiMigrazione;
    @XmlElement(name = "IDDocumento", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String idDocumento;
    @XmlElement(name = "TipoDocumento", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tipoDocumento;
    @XmlElement(name = "StatoConservazione")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String statoConservazione;
    @XmlElement(name = "Volumi")
    protected DocumentoType.Volumi volumi;
    @XmlElement(name = "ProfiloDocumento")
    protected ProfiloDocumentoType profiloDocumento;
    @XmlElement(name = "DatiSpecifici")
    protected DatiSpecificiType datiSpecifici;
    @XmlElement(name = "DatiSpecificiMigrazione")
    protected DatiSpecificiType datiSpecificiMigrazione;
    @XmlElement(name = "DatiFiscali")
    protected DatiFiscaliType datiFiscali;
    @XmlElement(name = "StrutturaOriginale", required = true)
    protected StrutturaType strutturaOriginale;

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
     * Gets the value of the dataVersamentoDoc property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getDataVersamentoDoc() {
        return dataVersamentoDoc;
    }

    /**
     * Sets the value of the dataVersamentoDoc property.
     *
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setDataVersamentoDoc(XMLGregorianCalendar value) {
        this.dataVersamentoDoc = value;
    }

    /**
     * Gets the value of the tipoConservazione property.
     *
     * @return possible object is {@link TipoConservazioneType }
     *
     */
    public TipoConservazioneType getTipoConservazione() {
        return tipoConservazione;
    }

    /**
     * Sets the value of the tipoConservazione property.
     *
     * @param value
     *            allowed object is {@link TipoConservazioneType }
     *
     */
    public void setTipoConservazione(TipoConservazioneType value) {
        this.tipoConservazione = value;
    }

    /**
     * Gets the value of the sistemaDiMigrazione property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getSistemaDiMigrazione() {
        return sistemaDiMigrazione;
    }

    /**
     * Sets the value of the sistemaDiMigrazione property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setSistemaDiMigrazione(String value) {
        this.sistemaDiMigrazione = value;
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
     * Gets the value of the statoConservazione property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getStatoConservazione() {
        return statoConservazione;
    }

    /**
     * Sets the value of the statoConservazione property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setStatoConservazione(String value) {
        this.statoConservazione = value;
    }

    /**
     * Gets the value of the volumi property.
     *
     * @return possible object is {@link DocumentoType.Volumi }
     *
     */
    public DocumentoType.Volumi getVolumi() {
        return volumi;
    }

    /**
     * Sets the value of the volumi property.
     *
     * @param value
     *            allowed object is {@link DocumentoType.Volumi }
     *
     */
    public void setVolumi(DocumentoType.Volumi value) {
        this.volumi = value;
    }

    /**
     * Gets the value of the profiloDocumento property.
     *
     * @return possible object is {@link ProfiloDocumentoType }
     *
     */
    public ProfiloDocumentoType getProfiloDocumento() {
        return profiloDocumento;
    }

    /**
     * Sets the value of the profiloDocumento property.
     *
     * @param value
     *            allowed object is {@link ProfiloDocumentoType }
     *
     */
    public void setProfiloDocumento(ProfiloDocumentoType value) {
        this.profiloDocumento = value;
    }

    /**
     * Gets the value of the datiSpecifici property.
     *
     * @return possible object is {@link DatiSpecificiType }
     *
     */
    public DatiSpecificiType getDatiSpecifici() {
        return datiSpecifici;
    }

    /**
     * Sets the value of the datiSpecifici property.
     *
     * @param value
     *            allowed object is {@link DatiSpecificiType }
     *
     */
    public void setDatiSpecifici(DatiSpecificiType value) {
        this.datiSpecifici = value;
    }

    /**
     * Gets the value of the datiSpecificiMigrazione property.
     *
     * @return possible object is {@link DatiSpecificiType }
     *
     */
    public DatiSpecificiType getDatiSpecificiMigrazione() {
        return datiSpecificiMigrazione;
    }

    /**
     * Sets the value of the datiSpecificiMigrazione property.
     *
     * @param value
     *            allowed object is {@link DatiSpecificiType }
     *
     */
    public void setDatiSpecificiMigrazione(DatiSpecificiType value) {
        this.datiSpecificiMigrazione = value;
    }

    /**
     * Gets the value of the datiFiscali property.
     *
     * @return possible object is {@link DatiFiscaliType }
     *
     */
    public DatiFiscaliType getDatiFiscali() {
        return datiFiscali;
    }

    /**
     * Sets the value of the datiFiscali property.
     *
     * @param value
     *            allowed object is {@link DatiFiscaliType }
     *
     */
    public void setDatiFiscali(DatiFiscaliType value) {
        this.datiFiscali = value;
    }

    /**
     * Gets the value of the strutturaOriginale property.
     *
     * @return possible object is {@link StrutturaType }
     *
     */
    public StrutturaType getStrutturaOriginale() {
        return strutturaOriginale;
    }

    /**
     * Sets the value of the strutturaOriginale property.
     *
     * @param value
     *            allowed object is {@link StrutturaType }
     *
     */
    public void setStrutturaOriginale(StrutturaType value) {
        this.strutturaOriginale = value;
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
     *         &lt;element name="Volume" type="{}DatiRifVolumeType" maxOccurs="unbounded"/>
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
        protected List<DatiRifVolumeType> volume;

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
         * Objects of the following type(s) are allowed in the list {@link DatiRifVolumeType }
         *
         *
         */
        public List<DatiRifVolumeType> getVolume() {
            if (volume == null) {
                volume = new ArrayList<DatiRifVolumeType>();
            }
            return this.volume;
        }

    }

}

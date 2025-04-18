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

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>
 * Java class for ComponenteType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ComponenteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ID" type="{}TokenNVMax254Type"/>
 *         &lt;element name="OrdinePresentazione" type="{}IntMax5DgtType"/>
 *         &lt;element name="TipoComponente" type="{}TokenNonVuotoType" minOccurs="0"/>
 *         &lt;element name="TipoSupportoComponente" type="{}TipoSupportoType" minOccurs="0"/>
 *         &lt;element name="Riferimento" type="{}ChiaveType" minOccurs="0"/>
 *         &lt;element name="TipoRappresentazioneComponente" type="{}TokenNonVuotoType" minOccurs="0"/>
 *         &lt;element name="NomeComponente" type="{}TokenNVMax254Type" minOccurs="0"/>
 *         &lt;element name="FormatoFileVersato" type="{}TokenNonVuotoType" minOccurs="0"/>
 *         &lt;element name="HashVersato" type="{}TokenNVMax254Type" minOccurs="0"/>
 *         &lt;element name="UrnVersato" type="{}TokenNVMax1024Type" minOccurs="0"/>
 *         &lt;element name="IDComponenteVersato" type="{}TokenNVMax254Type" minOccurs="0"/>
 *         &lt;element name="DatiSpecifici" type="{}DatiSpecificiType" minOccurs="0"/>
 *         &lt;element name="DatiSpecificiMigrazione" type="{}DatiSpecificiType" minOccurs="0"/>
 *         &lt;element name="UtilizzoDataFirmaPerRifTemp" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="RiferimentoTemporale" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="DescrizioneRiferimentoTemporale" type="{}StringMax254Type" minOccurs="0"/>
 *         &lt;element name="SottoComponenti" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="SottoComponente" type="{}SottoComponenteType" maxOccurs="unbounded"/>
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
@XmlType(name = "ComponenteType", propOrder = { "id", "ordinePresentazione", "tipoComponente", "tipoSupportoComponente",
        "riferimento", "tipoRappresentazioneComponente", "nomeComponente", "formatoFileVersato", "hashVersato",
        "urnVersato", "idComponenteVersato", "datiSpecifici", "datiSpecificiMigrazione", "utilizzoDataFirmaPerRifTemp",
        "riferimentoTemporale", "descrizioneRiferimentoTemporale", "sottoComponenti" })
public class ComponenteType {

    @XmlElement(name = "ID", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String id;
    @XmlElement(name = "OrdinePresentazione")
    protected int ordinePresentazione;
    @XmlElement(name = "TipoComponente")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tipoComponente;
    @XmlElement(name = "TipoSupportoComponente")
    protected TipoSupportoType tipoSupportoComponente;
    @XmlElement(name = "Riferimento")
    protected ChiaveType riferimento;
    @XmlElement(name = "TipoRappresentazioneComponente")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tipoRappresentazioneComponente;
    @XmlElement(name = "NomeComponente")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String nomeComponente;
    @XmlElement(name = "FormatoFileVersato")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String formatoFileVersato;
    @XmlElement(name = "HashVersato")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String hashVersato;
    @XmlElement(name = "UrnVersato")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String urnVersato;
    @XmlElement(name = "IDComponenteVersato")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String idComponenteVersato;
    @XmlElementRef(name = "DatiSpecifici", type = JAXBElement.class)
    protected JAXBElement<DatiSpecificiType> datiSpecifici;
    @XmlElementRef(name = "DatiSpecificiMigrazione", type = JAXBElement.class)
    protected JAXBElement<DatiSpecificiType> datiSpecificiMigrazione;
    @XmlElement(name = "UtilizzoDataFirmaPerRifTemp")
    protected Boolean utilizzoDataFirmaPerRifTemp;
    @XmlElement(name = "RiferimentoTemporale")
    protected XMLGregorianCalendar riferimentoTemporale;
    @XmlElement(name = "DescrizioneRiferimentoTemporale")
    protected String descrizioneRiferimentoTemporale;
    @XmlElement(name = "SottoComponenti")
    protected ComponenteType.SottoComponenti sottoComponenti;

    /**
     * Gets the value of the id property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getID() {
        return id;
    }

    /**
     * Sets the value of the id property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * Gets the value of the ordinePresentazione property.
     *
     */
    public int getOrdinePresentazione() {
        return ordinePresentazione;
    }

    /**
     * Sets the value of the ordinePresentazione property.
     *
     */
    public void setOrdinePresentazione(int value) {
        this.ordinePresentazione = value;
    }

    /**
     * Gets the value of the tipoComponente property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTipoComponente() {
        return tipoComponente;
    }

    /**
     * Sets the value of the tipoComponente property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setTipoComponente(String value) {
        this.tipoComponente = value;
    }

    /**
     * Gets the value of the tipoSupportoComponente property.
     *
     * @return possible object is {@link TipoSupportoType }
     *
     */
    public TipoSupportoType getTipoSupportoComponente() {
        return tipoSupportoComponente;
    }

    /**
     * Sets the value of the tipoSupportoComponente property.
     *
     * @param value
     *            allowed object is {@link TipoSupportoType }
     *
     */
    public void setTipoSupportoComponente(TipoSupportoType value) {
        this.tipoSupportoComponente = value;
    }

    /**
     * Gets the value of the riferimento property.
     *
     * @return possible object is {@link ChiaveType }
     *
     */
    public ChiaveType getRiferimento() {
        return riferimento;
    }

    /**
     * Sets the value of the riferimento property.
     *
     * @param value
     *            allowed object is {@link ChiaveType }
     *
     */
    public void setRiferimento(ChiaveType value) {
        this.riferimento = value;
    }

    /**
     * Gets the value of the tipoRappresentazioneComponente property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getTipoRappresentazioneComponente() {
        return tipoRappresentazioneComponente;
    }

    /**
     * Sets the value of the tipoRappresentazioneComponente property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setTipoRappresentazioneComponente(String value) {
        this.tipoRappresentazioneComponente = value;
    }

    /**
     * Gets the value of the nomeComponente property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNomeComponente() {
        return nomeComponente;
    }

    /**
     * Sets the value of the nomeComponente property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setNomeComponente(String value) {
        this.nomeComponente = value;
    }

    /**
     * Gets the value of the formatoFileVersato property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFormatoFileVersato() {
        return formatoFileVersato;
    }

    /**
     * Sets the value of the formatoFileVersato property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setFormatoFileVersato(String value) {
        this.formatoFileVersato = value;
    }

    /**
     * Gets the value of the hashVersato property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getHashVersato() {
        return hashVersato;
    }

    /**
     * Sets the value of the hashVersato property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setHashVersato(String value) {
        this.hashVersato = value;
    }

    /**
     * Gets the value of the urnVersato property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUrnVersato() {
        return urnVersato;
    }

    /**
     * Sets the value of the urnVersato property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setUrnVersato(String value) {
        this.urnVersato = value;
    }

    /**
     * Gets the value of the idComponenteVersato property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIDComponenteVersato() {
        return idComponenteVersato;
    }

    /**
     * Sets the value of the idComponenteVersato property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setIDComponenteVersato(String value) {
        this.idComponenteVersato = value;
    }

    /**
     * Gets the value of the datiSpecifici property.
     *
     * @return possible object is {@link JAXBElement }{@code <}{@link DatiSpecificiType }{@code >}
     *
     */
    public JAXBElement<DatiSpecificiType> getDatiSpecifici() {
        return datiSpecifici;
    }

    /**
     * Sets the value of the datiSpecifici property.
     *
     * @param value
     *            allowed object is {@link JAXBElement }{@code <}{@link DatiSpecificiType }{@code >}
     *
     */
    public void setDatiSpecifici(JAXBElement<DatiSpecificiType> value) {
        this.datiSpecifici = ((JAXBElement<DatiSpecificiType>) value);
    }

    /**
     * Gets the value of the datiSpecificiMigrazione property.
     *
     * @return possible object is {@link JAXBElement }{@code <}{@link DatiSpecificiType }{@code >}
     *
     */
    public JAXBElement<DatiSpecificiType> getDatiSpecificiMigrazione() {
        return datiSpecificiMigrazione;
    }

    /**
     * Sets the value of the datiSpecificiMigrazione property.
     *
     * @param value
     *            allowed object is {@link JAXBElement }{@code <}{@link DatiSpecificiType }{@code >}
     *
     */
    public void setDatiSpecificiMigrazione(JAXBElement<DatiSpecificiType> value) {
        this.datiSpecificiMigrazione = ((JAXBElement<DatiSpecificiType>) value);
    }

    /**
     * Gets the value of the utilizzoDataFirmaPerRifTemp property.
     *
     * @return possible object is {@link Boolean }
     *
     */
    public Boolean isUtilizzoDataFirmaPerRifTemp() {
        return utilizzoDataFirmaPerRifTemp;
    }

    /**
     * Sets the value of the utilizzoDataFirmaPerRifTemp property.
     *
     * @param value
     *            allowed object is {@link Boolean }
     *
     */
    public void setUtilizzoDataFirmaPerRifTemp(Boolean value) {
        this.utilizzoDataFirmaPerRifTemp = value;
    }

    /**
     * Gets the value of the riferimentoTemporale property.
     *
     * @return possible object is {@link XMLGregorianCalendar }
     *
     */
    public XMLGregorianCalendar getRiferimentoTemporale() {
        return riferimentoTemporale;
    }

    /**
     * Sets the value of the riferimentoTemporale property.
     *
     * @param value
     *            allowed object is {@link XMLGregorianCalendar }
     *
     */
    public void setRiferimentoTemporale(XMLGregorianCalendar value) {
        this.riferimentoTemporale = value;
    }

    /**
     * Gets the value of the descrizioneRiferimentoTemporale property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDescrizioneRiferimentoTemporale() {
        return descrizioneRiferimentoTemporale;
    }

    /**
     * Sets the value of the descrizioneRiferimentoTemporale property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setDescrizioneRiferimentoTemporale(String value) {
        this.descrizioneRiferimentoTemporale = value;
    }

    /**
     * Gets the value of the sottoComponenti property.
     *
     * @return possible object is {@link ComponenteType.SottoComponenti }
     *
     */
    public ComponenteType.SottoComponenti getSottoComponenti() {
        return sottoComponenti;
    }

    /**
     * Sets the value of the sottoComponenti property.
     *
     * @param value
     *            allowed object is {@link ComponenteType.SottoComponenti }
     *
     */
    public void setSottoComponenti(ComponenteType.SottoComponenti value) {
        this.sottoComponenti = value;
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
     *         &lt;element name="SottoComponente" type="{}SottoComponenteType" maxOccurs="unbounded"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     *
     *
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = { "sottoComponente" })
    public static class SottoComponenti {

        @XmlElement(name = "SottoComponente", required = true)
        protected List<SottoComponenteType> sottoComponente;

        /**
         * Gets the value of the sottoComponente property.
         *
         * <p>
         * This accessor method returns a reference to the live list, not a snapshot. Therefore any modification you
         * make to the returned list will be present inside the JAXB object. This is why there is not a <CODE>set</CODE>
         * method for the sottoComponente property.
         *
         * <p>
         * For example, to add a new item, do as follows:
         *
         * <pre>
         * getSottoComponente().add(newItem);
         * </pre>
         *
         *
         * <p>
         * Objects of the following type(s) are allowed in the list {@link SottoComponenteType }
         *
         *
         */
        public List<SottoComponenteType> getSottoComponente() {
            if (sottoComponente == null) {
                sottoComponente = new ArrayList<SottoComponenteType>();
            }
            return this.sottoComponente;
        }

    }

}

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

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for SottoComponenteType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="SottoComponenteType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="OrdinePresentazione" type="{http://www.w3.org/2001/XMLSchema}nonNegativeInteger"/>
 *         &lt;element name="TipoComponente" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="TipoSupportoComponente" type="{}TipoSupportoType" minOccurs="0"/>
 *         &lt;element name="Riferimento" type="{}ChiaveType" minOccurs="0"/>
 *         &lt;element name="NomeComponente" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="FormatoRappresentazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="FormatoRappresentazioneEsteso" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="UrnVersato" type="{}TokenNonVuotoType" minOccurs="0"/>
 *         &lt;element name="UrnCalcolato" type="{}TokenNonVuotoType" minOccurs="0"/>
 *         &lt;element name="IDComponenteVersato" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="DatiSpecifici" type="{}DatiSpecificiType" minOccurs="0"/>
 *         &lt;element name="DatiSpecificiMigrazione" type="{}DatiSpecificiType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SottoComponenteType", propOrder = { "ordinePresentazione", "tipoComponente", "tipoSupportoComponente",
        "riferimento", "nomeComponente", "formatoRappresentazione", "formatoRappresentazioneEsteso", "urnVersato",
        "urnCalcolato", "idComponenteVersato", "datiSpecifici", "datiSpecificiMigrazione" })
public class SottoComponenteType {

    @XmlElement(name = "OrdinePresentazione", required = true)
    protected BigInteger ordinePresentazione;
    @XmlElement(name = "TipoComponente")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tipoComponente;
    @XmlElement(name = "TipoSupportoComponente")
    protected TipoSupportoType tipoSupportoComponente;
    @XmlElement(name = "Riferimento")
    protected ChiaveType riferimento;
    @XmlElement(name = "NomeComponente")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String nomeComponente;
    @XmlElement(name = "FormatoRappresentazione")
    protected String formatoRappresentazione;
    @XmlElement(name = "FormatoRappresentazioneEsteso")
    protected String formatoRappresentazioneEsteso;
    @XmlElement(name = "UrnVersato")
    protected String urnVersato;
    @XmlElement(name = "UrnCalcolato")
    protected String urnCalcolato;
    @XmlElement(name = "IDComponenteVersato")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String idComponenteVersato;
    @XmlElement(name = "DatiSpecifici")
    protected DatiSpecificiType datiSpecifici;
    @XmlElement(name = "DatiSpecificiMigrazione")
    protected DatiSpecificiType datiSpecificiMigrazione;

    /**
     * Gets the value of the ordinePresentazione property.
     *
     * @return possible object is {@link BigInteger }
     *
     */
    public BigInteger getOrdinePresentazione() {
        return ordinePresentazione;
    }

    /**
     * Sets the value of the ordinePresentazione property.
     *
     * @param value
     *            allowed object is {@link BigInteger }
     *
     */
    public void setOrdinePresentazione(BigInteger value) {
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
     * Gets the value of the formatoRappresentazione property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFormatoRappresentazione() {
        return formatoRappresentazione;
    }

    /**
     * Sets the value of the formatoRappresentazione property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setFormatoRappresentazione(String value) {
        this.formatoRappresentazione = value;
    }

    /**
     * Gets the value of the formatoRappresentazioneEsteso property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getFormatoRappresentazioneEsteso() {
        return formatoRappresentazioneEsteso;
    }

    /**
     * Sets the value of the formatoRappresentazioneEsteso property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setFormatoRappresentazioneEsteso(String value) {
        this.formatoRappresentazioneEsteso = value;
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
     * Gets the value of the urnCalcolato property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getUrnCalcolato() {
        return urnCalcolato;
    }

    /**
     * Sets the value of the urnCalcolato property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setUrnCalcolato(String value) {
        this.urnCalcolato = value;
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

}

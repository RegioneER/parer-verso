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
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for ECConfigurazioneType complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ECConfigurazioneType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="TipoConservazione" minOccurs="0">
 *           &lt;simpleType>
 *             &lt;restriction base="{http://www.w3.org/2001/XMLSchema}NMTOKEN">
 *               &lt;enumeration value="VERSAMENTO_ANTICIPATO"/>
 *               &lt;enumeration value="FISCALE"/>
 *               &lt;enumeration value="MIGRAZIONE"/>
 *             &lt;/restriction>
 *           &lt;/simpleType>
 *         &lt;/element>
 *         &lt;element name="SistemaDiMigrazione" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ForzaAccettazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ForzaConservazione" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="ForzaCollegamento" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AbilitaControlloCrittografico" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AbilitaControlloTrust" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AbilitaControlloCertificato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AbilitaControlloCRL" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AbilitaControlloFormato" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaFirmaSconosciuta" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaFirmaNonConforme" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaFirmaNoDelibera45" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaMarcaSconosciuta" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCrittograficoNegativo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloTrustNegativo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCertificatoScaduto" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCertificatoNoValido" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCertificatoNoFirma" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCRLNegativo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCRLScaduta" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCRLNoValida" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloCRLNoScaric" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="AccettaControlloFormatoNegativo" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ECConfigurazioneType", propOrder = { "tipoConservazione", "sistemaDiMigrazione", "forzaAccettazione",
        "forzaConservazione", "forzaCollegamento", "abilitaControlloCrittografico", "abilitaControlloTrust",
        "abilitaControlloCertificato", "abilitaControlloCRL", "abilitaControlloFormato", "accettaFirmaSconosciuta",
        "accettaFirmaNonConforme", "accettaFirmaNoDelibera45", "accettaMarcaSconosciuta",
        "accettaControlloCrittograficoNegativo", "accettaControlloTrustNegativo", "accettaControlloCertificatoScaduto",
        "accettaControlloCertificatoNoValido", "accettaControlloCertificatoNoFirma", "accettaControlloCRLNegativo",
        "accettaControlloCRLScaduta", "accettaControlloCRLNoValida", "accettaControlloCRLNoScaric",
        "accettaControlloFormatoNegativo" })
public class ECConfigurazioneType {

    @XmlElement(name = "TipoConservazione")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String tipoConservazione;
    @XmlElement(name = "SistemaDiMigrazione")
    protected String sistemaDiMigrazione;
    @XmlElement(name = "ForzaAccettazione")
    protected Boolean forzaAccettazione;
    @XmlElement(name = "ForzaConservazione")
    protected Boolean forzaConservazione;
    @XmlElement(name = "ForzaCollegamento")
    protected Boolean forzaCollegamento;
    @XmlElement(name = "AbilitaControlloCrittografico")
    protected Boolean abilitaControlloCrittografico;
    @XmlElement(name = "AbilitaControlloTrust")
    protected Boolean abilitaControlloTrust;
    @XmlElement(name = "AbilitaControlloCertificato")
    protected Boolean abilitaControlloCertificato;
    @XmlElement(name = "AbilitaControlloCRL")
    protected Boolean abilitaControlloCRL;
    @XmlElement(name = "AbilitaControlloFormato")
    protected Boolean abilitaControlloFormato;
    @XmlElement(name = "AccettaFirmaSconosciuta")
    protected Boolean accettaFirmaSconosciuta;
    @XmlElement(name = "AccettaFirmaNonConforme")
    protected Boolean accettaFirmaNonConforme;
    @XmlElement(name = "AccettaFirmaNoDelibera45")
    protected Boolean accettaFirmaNoDelibera45;
    @XmlElement(name = "AccettaMarcaSconosciuta")
    protected Boolean accettaMarcaSconosciuta;
    @XmlElement(name = "AccettaControlloCrittograficoNegativo")
    protected Boolean accettaControlloCrittograficoNegativo;
    @XmlElement(name = "AccettaControlloTrustNegativo")
    protected Boolean accettaControlloTrustNegativo;
    @XmlElement(name = "AccettaControlloCertificatoScaduto")
    protected Boolean accettaControlloCertificatoScaduto;
    @XmlElement(name = "AccettaControlloCertificatoNoValido")
    protected Boolean accettaControlloCertificatoNoValido;
    @XmlElement(name = "AccettaControlloCertificatoNoFirma")
    protected Boolean accettaControlloCertificatoNoFirma;
    @XmlElement(name = "AccettaControlloCRLNegativo")
    protected Boolean accettaControlloCRLNegativo;
    @XmlElement(name = "AccettaControlloCRLScaduta")
    protected Boolean accettaControlloCRLScaduta;
    @XmlElement(name = "AccettaControlloCRLNoValida")
    protected Boolean accettaControlloCRLNoValida;
    @XmlElement(name = "AccettaControlloCRLNoScaric")
    protected Boolean accettaControlloCRLNoScaric;
    @XmlElement(name = "AccettaControlloFormatoNegativo")
    protected Boolean accettaControlloFormatoNegativo;

    /**
     * Gets the value of the tipoConservazione property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public String getTipoConservazione() {
        return tipoConservazione;
    }

    /**
     * Sets the value of the tipoConservazione property.
     * 
     * @param value
     *            allowed object is {@link String }
     * 
     */
    public void setTipoConservazione(String value) {
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
     * Gets the value of the forzaAccettazione property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isForzaAccettazione() {
        return forzaAccettazione;
    }

    /**
     * Sets the value of the forzaAccettazione property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setForzaAccettazione(Boolean value) {
        this.forzaAccettazione = value;
    }

    /**
     * Gets the value of the forzaConservazione property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isForzaConservazione() {
        return forzaConservazione;
    }

    /**
     * Sets the value of the forzaConservazione property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setForzaConservazione(Boolean value) {
        this.forzaConservazione = value;
    }

    /**
     * Gets the value of the forzaCollegamento property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isForzaCollegamento() {
        return forzaCollegamento;
    }

    /**
     * Sets the value of the forzaCollegamento property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setForzaCollegamento(Boolean value) {
        this.forzaCollegamento = value;
    }

    /**
     * Gets the value of the abilitaControlloCrittografico property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAbilitaControlloCrittografico() {
        return abilitaControlloCrittografico;
    }

    /**
     * Sets the value of the abilitaControlloCrittografico property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAbilitaControlloCrittografico(Boolean value) {
        this.abilitaControlloCrittografico = value;
    }

    /**
     * Gets the value of the abilitaControlloTrust property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAbilitaControlloTrust() {
        return abilitaControlloTrust;
    }

    /**
     * Sets the value of the abilitaControlloTrust property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAbilitaControlloTrust(Boolean value) {
        this.abilitaControlloTrust = value;
    }

    /**
     * Gets the value of the abilitaControlloCertificato property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAbilitaControlloCertificato() {
        return abilitaControlloCertificato;
    }

    /**
     * Sets the value of the abilitaControlloCertificato property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAbilitaControlloCertificato(Boolean value) {
        this.abilitaControlloCertificato = value;
    }

    /**
     * Gets the value of the abilitaControlloCRL property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAbilitaControlloCRL() {
        return abilitaControlloCRL;
    }

    /**
     * Sets the value of the abilitaControlloCRL property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAbilitaControlloCRL(Boolean value) {
        this.abilitaControlloCRL = value;
    }

    /**
     * Gets the value of the abilitaControlloFormato property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAbilitaControlloFormato() {
        return abilitaControlloFormato;
    }

    /**
     * Sets the value of the abilitaControlloFormato property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAbilitaControlloFormato(Boolean value) {
        this.abilitaControlloFormato = value;
    }

    /**
     * Gets the value of the accettaFirmaSconosciuta property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaFirmaSconosciuta() {
        return accettaFirmaSconosciuta;
    }

    /**
     * Sets the value of the accettaFirmaSconosciuta property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaFirmaSconosciuta(Boolean value) {
        this.accettaFirmaSconosciuta = value;
    }

    /**
     * Gets the value of the accettaFirmaNonConforme property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaFirmaNonConforme() {
        return accettaFirmaNonConforme;
    }

    /**
     * Sets the value of the accettaFirmaNonConforme property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaFirmaNonConforme(Boolean value) {
        this.accettaFirmaNonConforme = value;
    }

    /**
     * Gets the value of the accettaFirmaNoDelibera45 property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaFirmaNoDelibera45() {
        return accettaFirmaNoDelibera45;
    }

    /**
     * Sets the value of the accettaFirmaNoDelibera45 property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaFirmaNoDelibera45(Boolean value) {
        this.accettaFirmaNoDelibera45 = value;
    }

    /**
     * Gets the value of the accettaMarcaSconosciuta property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaMarcaSconosciuta() {
        return accettaMarcaSconosciuta;
    }

    /**
     * Sets the value of the accettaMarcaSconosciuta property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaMarcaSconosciuta(Boolean value) {
        this.accettaMarcaSconosciuta = value;
    }

    /**
     * Gets the value of the accettaControlloCrittograficoNegativo property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCrittograficoNegativo() {
        return accettaControlloCrittograficoNegativo;
    }

    /**
     * Sets the value of the accettaControlloCrittograficoNegativo property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCrittograficoNegativo(Boolean value) {
        this.accettaControlloCrittograficoNegativo = value;
    }

    /**
     * Gets the value of the accettaControlloTrustNegativo property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloTrustNegativo() {
        return accettaControlloTrustNegativo;
    }

    /**
     * Sets the value of the accettaControlloTrustNegativo property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloTrustNegativo(Boolean value) {
        this.accettaControlloTrustNegativo = value;
    }

    /**
     * Gets the value of the accettaControlloCertificatoScaduto property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCertificatoScaduto() {
        return accettaControlloCertificatoScaduto;
    }

    /**
     * Sets the value of the accettaControlloCertificatoScaduto property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCertificatoScaduto(Boolean value) {
        this.accettaControlloCertificatoScaduto = value;
    }

    /**
     * Gets the value of the accettaControlloCertificatoNoValido property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCertificatoNoValido() {
        return accettaControlloCertificatoNoValido;
    }

    /**
     * Sets the value of the accettaControlloCertificatoNoValido property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCertificatoNoValido(Boolean value) {
        this.accettaControlloCertificatoNoValido = value;
    }

    /**
     * Gets the value of the accettaControlloCertificatoNoFirma property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCertificatoNoFirma() {
        return accettaControlloCertificatoNoFirma;
    }

    /**
     * Sets the value of the accettaControlloCertificatoNoFirma property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCertificatoNoFirma(Boolean value) {
        this.accettaControlloCertificatoNoFirma = value;
    }

    /**
     * Gets the value of the accettaControlloCRLNegativo property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCRLNegativo() {
        return accettaControlloCRLNegativo;
    }

    /**
     * Sets the value of the accettaControlloCRLNegativo property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCRLNegativo(Boolean value) {
        this.accettaControlloCRLNegativo = value;
    }

    /**
     * Gets the value of the accettaControlloCRLScaduta property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCRLScaduta() {
        return accettaControlloCRLScaduta;
    }

    /**
     * Sets the value of the accettaControlloCRLScaduta property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCRLScaduta(Boolean value) {
        this.accettaControlloCRLScaduta = value;
    }

    /**
     * Gets the value of the accettaControlloCRLNoValida property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCRLNoValida() {
        return accettaControlloCRLNoValida;
    }

    /**
     * Sets the value of the accettaControlloCRLNoValida property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCRLNoValida(Boolean value) {
        this.accettaControlloCRLNoValida = value;
    }

    /**
     * Gets the value of the accettaControlloCRLNoScaric property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloCRLNoScaric() {
        return accettaControlloCRLNoScaric;
    }

    /**
     * Sets the value of the accettaControlloCRLNoScaric property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloCRLNoScaric(Boolean value) {
        this.accettaControlloCRLNoScaric = value;
    }

    /**
     * Gets the value of the accettaControlloFormatoNegativo property.
     * 
     * @return possible object is {@link Boolean }
     * 
     */
    public Boolean isAccettaControlloFormatoNegativo() {
        return accettaControlloFormatoNegativo;
    }

    /**
     * Sets the value of the accettaControlloFormatoNegativo property.
     * 
     * @param value
     *            allowed object is {@link Boolean }
     * 
     */
    public void setAccettaControlloFormatoNegativo(Boolean value) {
        this.accettaControlloFormatoNegativo = value;
    }

}

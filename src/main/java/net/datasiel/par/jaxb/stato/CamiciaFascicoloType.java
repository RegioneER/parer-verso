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

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.CollapsedStringAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

/**
 * <p>
 * Java class for CamiciaFascicoloType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="CamiciaFascicoloType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="Classifica" type="{http://www.w3.org/2001/XMLSchema}token" minOccurs="0"/>
 *         &lt;element name="Fascicolo" type="{}FascicoloType" minOccurs="0"/>
 *         &lt;element name="SottoFascicolo" type="{}FascicoloType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CamiciaFascicoloType", propOrder = { "classifica", "fascicolo", "sottoFascicolo" })
public class CamiciaFascicoloType {

    @XmlElement(name = "Classifica")
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String classifica;
    @XmlElement(name = "Fascicolo")
    protected FascicoloType fascicolo;
    @XmlElement(name = "SottoFascicolo")
    protected FascicoloType sottoFascicolo;

    /**
     * Gets the value of the classifica property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getClassifica() {
        return classifica;
    }

    /**
     * Sets the value of the classifica property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setClassifica(String value) {
        this.classifica = value;
    }

    /**
     * Gets the value of the fascicolo property.
     *
     * @return possible object is {@link FascicoloType }
     *
     */
    public FascicoloType getFascicolo() {
        return fascicolo;
    }

    /**
     * Sets the value of the fascicolo property.
     *
     * @param value
     *            allowed object is {@link FascicoloType }
     *
     */
    public void setFascicolo(FascicoloType value) {
        this.fascicolo = value;
    }

    /**
     * Gets the value of the sottoFascicolo property.
     *
     * @return possible object is {@link FascicoloType }
     *
     */
    public FascicoloType getSottoFascicolo() {
        return sottoFascicolo;
    }

    /**
     * Sets the value of the sottoFascicolo property.
     *
     * @param value
     *            allowed object is {@link FascicoloType }
     *
     */
    public void setSottoFascicolo(FascicoloType value) {
        this.sottoFascicolo = value;
    }

}

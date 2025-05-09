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
 * Java class for PCVolumeType complex type.
 *
 * <p>
 * The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="PCVolumeType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IdVolume" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="NomeVolume" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="Directory" type="{http://www.w3.org/2001/XMLSchema}token"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PCVolumeType", propOrder = { "idVolume", "nomeVolume", "directory" })
public class PCVolumeType {

    @XmlElement(name = "IdVolume", required = true)
    protected String idVolume;
    @XmlElement(name = "NomeVolume", required = true)
    protected String nomeVolume;
    @XmlElement(name = "Directory", required = true)
    @XmlJavaTypeAdapter(CollapsedStringAdapter.class)
    protected String directory;

    /**
     * Gets the value of the idVolume property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getIdVolume() {
        return idVolume;
    }

    /**
     * Sets the value of the idVolume property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setIdVolume(String value) {
        this.idVolume = value;
    }

    /**
     * Gets the value of the nomeVolume property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getNomeVolume() {
        return nomeVolume;
    }

    /**
     * Sets the value of the nomeVolume property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setNomeVolume(String value) {
        this.nomeVolume = value;
    }

    /**
     * Gets the value of the directory property.
     *
     * @return possible object is {@link String }
     *
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Sets the value of the directory property.
     *
     * @param value
     *            allowed object is {@link String }
     *
     */
    public void setDirectory(String value) {
        this.directory = value;
    }

}

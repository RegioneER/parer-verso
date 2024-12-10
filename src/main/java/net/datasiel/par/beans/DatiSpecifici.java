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

package net.datasiel.par.beans;

import org.w3c.dom.Document;

/**
 * Oggetto contenente i dati specifici corrispondenti a un xsd custom. versioneDatiSpecifici e gli elementi descritti
 * nell'xsd devono avere corrispondenza sulla configurazione della base dati di SACER.
 *
 * @author reisoli
 *
 */
public class DatiSpecifici {
    /**
     * Massimo 1024 caratteri
     */
    private String versioneDatiSpecifici;

    private Document docDatiSpecifici;

    public DatiSpecifici() {
        super();
    }

    public DatiSpecifici(String versioneDatiSpecifici, Document datiSpecifici) {
        super();
        this.versioneDatiSpecifici = versioneDatiSpecifici;
        this.docDatiSpecifici = datiSpecifici;
    }

    public String getVersioneDatiSpecifici() {
        return versioneDatiSpecifici;
    }

    public void setVersioneDatiSpecifici(String versioneDatiSpecifici) {
        this.versioneDatiSpecifici = versioneDatiSpecifici;
    }

    public Document getDocDatiSpecifici() {
        return docDatiSpecifici;
    }

    public void setDocDatiSpecifici(Document datiSpecifici) {
        this.docDatiSpecifici = datiSpecifici;
    }
}

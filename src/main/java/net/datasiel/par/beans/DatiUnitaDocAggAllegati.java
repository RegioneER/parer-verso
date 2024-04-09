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

/**
 * 
 */
package net.datasiel.par.beans;

/**
 * @author reisoli
 *
 */
public class DatiUnitaDocAggAllegati {
    private Intestazione intestazione;
    private Configurazione configurazione;

    private Documento datiDocumento;

    private TipoDocumento tipoDocumento;

    public enum TipoDocumento {

        ALLEGATO, ANNESSO, ANNOTAZIONE;

        public String value() {
            return name();
        }

        public static TipoDocumento fromValue(String v) {
            return valueOf(v);
        }

    }

    public Intestazione getIntestazione() {
        return intestazione;
    }

    public void setIntestazione(Intestazione intestazione) {
        this.intestazione = intestazione;
    }

    public Configurazione getConfigurazione() {
        return configurazione;
    }

    public void setConfigurazione(Configurazione configurazione) {
        this.configurazione = configurazione;
    }

    public Documento getDatiDocumento() {
        return datiDocumento;
    }

    public void setDatiDocumento(Documento datiDocumento) {
        this.datiDocumento = datiDocumento;
    }

    public TipoDocumento getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
}

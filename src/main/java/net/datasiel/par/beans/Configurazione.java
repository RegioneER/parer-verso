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

public class Configurazione {
    /**
     * Può assumere i valori: "VERSAMENTO_ANTICIPATO", "FISCALE", "MIGRAZIONE"
     */
    private TipoConservazione tipoConservazione;
    private String sistemaDiMigrazione;
    private boolean forzaAccettazione;
    private boolean forzaConservazione;
    private boolean forzaCollegamento;
    private boolean simulaSalvataggioDatiInDB;

    public TipoConservazione getTipoConservazione() {
        return tipoConservazione;
    }

    public void setTipoConservazione(TipoConservazione tipoConservazione) {
        this.tipoConservazione = tipoConservazione;
    }

    public String getSistemaDiMigrazione() {
        return sistemaDiMigrazione;
    }

    public void setSistemaDiMigrazione(String sistemaDiMigrazione) {
        this.sistemaDiMigrazione = sistemaDiMigrazione;
    }

    public boolean isForzaAccettazione() {
        return forzaAccettazione;
    }

    public void setForzaAccettazione(boolean forzaAccettazione) {
        this.forzaAccettazione = forzaAccettazione;
    }

    public boolean isForzaConservazione() {
        return forzaConservazione;
    }

    public void setForzaConservazione(boolean forzaConservazione) {
        this.forzaConservazione = forzaConservazione;
    }

    public boolean isForzaCollegamento() {
        return forzaCollegamento;
    }

    public void setForzaCollegamento(boolean forzaCollegamento) {
        this.forzaCollegamento = forzaCollegamento;
    }

    public boolean isSimulaSalvataggioDatiInDB() {
        return simulaSalvataggioDatiInDB;
    }

    public void setSimulaSalvataggioDatiInDB(boolean simulaSalvataggioDatiInDB) {
        this.simulaSalvataggioDatiInDB = simulaSalvataggioDatiInDB;
    }

    public enum TipoConservazione {

        VERSAMENTO_ANTICIPATO, FISCALE, MIGRAZIONE;

        public String value() {
            return name();
        }

        public static TipoConservazione fromValue(String v) {
            return valueOf(v);
        }

    }

}

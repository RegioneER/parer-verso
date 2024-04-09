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

public class CamiciaFascicolo {
    private String classifica;
    private Fascicolo fascicolo;
    private Fascicolo sottoFascicolo;

    public String getClassifica() {
        return classifica;
    }

    public void setClassifica(String classifica) {
        this.classifica = classifica;
    }

    public Fascicolo getFascicolo() {
        return fascicolo;
    }

    public void setFascicolo(Fascicolo fascicolo) {
        this.fascicolo = fascicolo;
    }

    public Fascicolo getSottoFascicolo() {
        return sottoFascicolo;
    }

    public void setSottoFascicolo(Fascicolo sottoFascicolo) {
        this.sottoFascicolo = sottoFascicolo;
    }

    public class Fascicolo {

        public Fascicolo(String identificativo, String oggetto) {
            super();
            this.identificativo = identificativo;
            this.oggetto = oggetto;
        }

        private String identificativo;
        private String oggetto;

        public String getIdentificativo() {
            return identificativo;
        }

        public void setIdentificativo(String identificativo) {
            this.identificativo = identificativo;
        }

        public String getOggetto() {
            return oggetto;
        }

        public void setOggetto(String oggetto) {
            this.oggetto = oggetto;
        }
    }

}

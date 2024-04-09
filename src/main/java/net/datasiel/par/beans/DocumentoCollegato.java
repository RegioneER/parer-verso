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

public class DocumentoCollegato {
    private Chiave chiaveCollegamento;
    /**
     * Massimo 254 caratteri
     */
    private String descrizioneCollegamento;

    public DocumentoCollegato(Chiave chiaveCollegamento, String descrizioneCollegamento) {
        super();
        this.chiaveCollegamento = chiaveCollegamento;
        this.descrizioneCollegamento = descrizioneCollegamento;
    }

    public Chiave getChiaveCollegamento() {
        return chiaveCollegamento;
    }

    public void setChiaveCollegamento(Chiave chiaveCollegamento) {
        this.chiaveCollegamento = chiaveCollegamento;
    }

    public String getDescrizioneCollegamento() {
        return descrizioneCollegamento;
    }

    public void setDescrizioneCollegamento(String descrizioneCollegamento) {
        this.descrizioneCollegamento = descrizioneCollegamento;
    }

}

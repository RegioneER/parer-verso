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

import java.util.List;

import org.joda.time.DateTime;

public class FileAllegato extends FileAllegatoBase {
    private String tipoRappresentazioneComponente;

    /**
     * Massimo 254 caratteri
     */
    private String hashVersato;

    private boolean utilizzoDataFirmaPerRifTemp;

    private DateTime riferimentoTemporale;

    /**
     * Massimo 254 caratteri
     */
    private String descrizioneRiferimentoTemporale;

    private List<FileAllegatoBase> sottoComponenti;

    public String getTipoRappresentazioneComponente() {
        return tipoRappresentazioneComponente;
    }

    public void setTipoRappresentazioneComponente(String tipoRappresentazioneComponente) {
        this.tipoRappresentazioneComponente = tipoRappresentazioneComponente;
    }

    public String getHashVersato() {
        return hashVersato;
    }

    public void setHashVersato(String hashVersato) {
        this.hashVersato = hashVersato;
    }

    public boolean isUtilizzoDataFirmaPerRifTemp() {
        return utilizzoDataFirmaPerRifTemp;
    }

    public void setUtilizzoDataFirmaPerRifTemp(boolean utilizzoDataFirmaPerRifTemp) {
        this.utilizzoDataFirmaPerRifTemp = utilizzoDataFirmaPerRifTemp;
    }

    public DateTime getRiferimentoTemporale() {
        return riferimentoTemporale;
    }

    public void setRiferimentoTemporale(DateTime riferimentoTemporale) {
        this.riferimentoTemporale = riferimentoTemporale;
    }

    public String getDescrizioneRiferimentoTemporale() {
        return descrizioneRiferimentoTemporale;
    }

    public void setDescrizioneRiferimentoTemporale(String descrizioneRiferimentoTemporale) {
        this.descrizioneRiferimentoTemporale = descrizioneRiferimentoTemporale;
    }

    public List<FileAllegatoBase> getSottoComponenti() {
        return sottoComponenti;
    }

    public void setSottoComponenti(List<FileAllegatoBase> sottoComponenti) {
        this.sottoComponenti = sottoComponenti;
    }

}

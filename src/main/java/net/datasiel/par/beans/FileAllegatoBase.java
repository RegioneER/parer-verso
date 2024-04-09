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

import java.io.File;
import java.util.UUID;

public class FileAllegatoBase {
    /**
     * Lunghezza massima 254 caratteri
     */
    private String id;

    private int ordinePresentazione;

    /**
     * Lunghezza minima 1 carattere
     */
    private String tipoComponente;

    /**
     * Può assumere solo i valori: "FILE", "RIFERIMENTO", "METADATI"
     */
    private TipoSupporto tipoSupportoComponente;

    private Chiave riferimento;

    /**
     * Lunghezza massima 254 caratteri
     */
    private String nomeComponente;

    private String formatoFileVersato;

    /**
     * Lunghezza massima 1024 caratteri
     */
    private String urnVersato;

    private String idComponenteVersato;

    private DatiSpecifici datiSpecifici;

    private DatiSpecifici datiSpecificiMigrazione;

    private File fileVersato;
    private String codAllegato;

    public String getId() {
        return id;
    }

    public int getOrdinePresentazione() {
        return ordinePresentazione;
    }

    public void setOrdinePresentazione(int ordinePresentazione) {
        this.ordinePresentazione = ordinePresentazione;
    }

    public String getTipoComponente() {
        return tipoComponente;
    }

    public FileAllegatoBase() {
        this.id = UUID.randomUUID().toString();
    }

    public void setTipoComponente(String tipoComponente) {
        this.tipoComponente = tipoComponente;
    }

    public TipoSupporto getTipoSupportoComponente() {
        return tipoSupportoComponente;
    }

    public void setTipoSupportoComponente(TipoSupporto tipoSupportoComponente) {
        this.tipoSupportoComponente = tipoSupportoComponente;
    }

    public Chiave getRiferimento() {
        return riferimento;
    }

    public void setRiferimento(Chiave riferimento) {
        this.riferimento = riferimento;
    }

    public String getNomeComponente() {
        return nomeComponente;
    }

    public void setNomeComponente(String nomeComponente) {
        this.nomeComponente = nomeComponente;
    }

    public String getFormatoFileVersato() {
        return formatoFileVersato;
    }

    public void setFormatoFileVersato(String formatoFileVersato) {
        this.formatoFileVersato = formatoFileVersato;
    }

    public String getUrnVersato() {
        return urnVersato;
    }

    public void setUrnVersato(String urnVersato) {
        this.urnVersato = urnVersato;
    }

    public String getIdComponenteVersato() {
        return idComponenteVersato;
    }

    public void setIdComponenteVersato(String idComponenteVersato) {
        this.idComponenteVersato = idComponenteVersato;
    }

    public DatiSpecifici getDatiSpecifici() {
        return datiSpecifici;
    }

    public void setDatiSpecifici(DatiSpecifici datiSpecifici) {
        this.datiSpecifici = datiSpecifici;
    }

    public DatiSpecifici getDatiSpecificiMigrazione() {
        return datiSpecificiMigrazione;
    }

    public void setDatiSpecificiMigrazione(DatiSpecifici datiSpecificiMigrazione) {
        this.datiSpecificiMigrazione = datiSpecificiMigrazione;
    }

    public File getFileVersato() {
        return fileVersato;
    }

    public void setFileVersato(File fileVersato) {
        this.fileVersato = fileVersato;
        this.id = UUID.randomUUID().toString();
    }

    public enum TipoSupporto {

        FILE, RIFERIMENTO, METADATI;

        public String value() {
            return name();
        }

        public static TipoSupporto fromValue(String v) {
            return valueOf(v);
        }

    }

    public String getCodAllegato() {
        return codAllegato;
    }

    public void setCodAllegato(String codAllegato) {
        this.codAllegato = codAllegato;
    }
}

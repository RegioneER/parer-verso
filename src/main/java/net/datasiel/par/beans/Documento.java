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

public class Documento {
    private String idDocumento;
    private String tipoDocumento;
    private String profiloDescrizioneDoc;
    private String profiloAutoreDoc;
    private DatiSpecifici datiSpecifici;
    private DatiSpecifici datiSpecificiMigrazione;
    private DatiFiscali datiFiscali;

    private String tipoStruttura;

    private List<FileAllegato> componenti;

    public String getIdDocumento() {
        return idDocumento;
    }

    public void setIdDocumento(String idDocumento) {
        this.idDocumento = idDocumento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getProfiloDescrizioneDoc() {
        return profiloDescrizioneDoc;
    }

    public void setProfiloDescrizioneDoc(String profiloDescrizioneDoc) {
        this.profiloDescrizioneDoc = profiloDescrizioneDoc;
    }

    public String getProfiloAutoreDoc() {
        return profiloAutoreDoc;
    }

    public void setProfiloAutoreDoc(String profiloAutoreDoc) {
        this.profiloAutoreDoc = profiloAutoreDoc;
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

    public DatiFiscali getDatiFiscali() {
        return datiFiscali;
    }

    public void setDatiFiscali(DatiFiscali datiFiscali) {
        this.datiFiscali = datiFiscali;
    }

    public String getTipoStruttura() {
        return tipoStruttura;
    }

    public void setTipoStruttura(String tipoStruttura) {
        this.tipoStruttura = tipoStruttura;
    }

    public List<FileAllegato> getComponenti() {
        return componenti;
    }

    public void setComponenti(List<FileAllegato> componenti) {
        this.componenti = componenti;
    }

}

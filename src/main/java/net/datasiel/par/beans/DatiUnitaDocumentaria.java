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

public class DatiUnitaDocumentaria {

    private Intestazione intestazione;
    private Configurazione configurazione;
    private CamiciaFascicolo fascicoloPrincipale;
    private List<CamiciaFascicolo> fascicoliSecondari;
    /**
     * Massimo 1024 caratteri
     */
    private String oggetto;

    private DateTime data;

    private boolean cartaceo;

    private DatiSpecifici datiSpecifici;

    private DatiSpecifici datiSpecificiMigrazione;

    private List<DocumentoCollegato> documentiCollegati;

    private Documento documentoPrincipale;

    private List<Documento> allegati;

    private List<Documento> annessi;

    private List<Documento> annotazioni;

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

    public CamiciaFascicolo getFascicoloPrincipale() {
        return fascicoloPrincipale;
    }

    public void setFascicoloPrincipale(CamiciaFascicolo fascicoloPrincipale) {
        this.fascicoloPrincipale = fascicoloPrincipale;
    }

    public List<CamiciaFascicolo> getFascicoliSecondari() {
        return fascicoliSecondari;
    }

    public void setFascicoliSecondari(List<CamiciaFascicolo> fascicoliSecondari) {
        this.fascicoliSecondari = fascicoliSecondari;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public boolean isCartaceo() {
        return cartaceo;
    }

    public void setCartaceo(boolean cartaceo) {
        this.cartaceo = cartaceo;
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

    public List<DocumentoCollegato> getDocumentiCollegati() {
        return documentiCollegati;
    }

    public void setDocumentiCollegati(List<DocumentoCollegato> documentiCollegati) {
        this.documentiCollegati = documentiCollegati;
    }

    public int getNumeroAllegati() {
        if (getAllegati() != null) {
            return getAllegati().size();
        } else {
            return 0;
        }
    }

    public int getNumeroAnnessi() {
        if (getAnnessi() != null) {
            return getAnnessi().size();
        } else {
            return 0;
        }
    }

    public int getNumeroAnnotazioni() {
        if (getAnnotazioni() != null) {
            return getAnnotazioni().size();
        } else {
            return 0;
        }
    }

    public Documento getDocumentoPrincipale() {
        return documentoPrincipale;
    }

    public void setDocumentoPrincipale(Documento documentoPrincipale) {
        this.documentoPrincipale = documentoPrincipale;
    }

    public List<Documento> getAllegati() {
        return allegati;
    }

    public void setAllegati(List<Documento> allegati) {
        this.allegati = allegati;
    }

    public List<Documento> getAnnessi() {
        return annessi;
    }

    public void setAnnessi(List<Documento> annessi) {
        this.annessi = annessi;
    }

    public List<Documento> getAnnotazioni() {
        return annotazioni;
    }

    public void setAnnotazioni(List<Documento> annotazioni) {
        this.annotazioni = annotazioni;
    }

    public DateTime getData() {
        return data;
    }

    public void setData(DateTime data) {
        this.data = data;
    }

}

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

import org.joda.time.DateTime;

public class DatiFiscali {

    private String denominazione;
    private String nome;
    private String cognome;
    private String cF;
    private String pIVA;
    private DateTime dataEmissione;
    private Long numeroProgressivo;
    private String registro;
    private String periodoFiscale;
    private DateTime dataTermineEmissione;

    public String getDenominazione() {
        return denominazione;
    }

    public void setDenominazione(String denominazione) {
        this.denominazione = denominazione;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCF() {
        return cF;
    }

    public void setCF(String cF) {
        this.cF = cF;
    }

    public String getPIVA() {
        return pIVA;
    }

    public void setPIVA(String pIVA) {
        this.pIVA = pIVA;
    }

    public DateTime getDataEmissione() {
        return dataEmissione;
    }

    public void setDataEmissione(DateTime dataEmissione) {
        this.dataEmissione = dataEmissione;
    }

    public Long getNumeroProgressivo() {
        return numeroProgressivo;
    }

    public void setNumeroProgressivo(Long numeroProgressivo) {
        this.numeroProgressivo = numeroProgressivo;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }

    public String getPeriodoFiscale() {
        return periodoFiscale;
    }

    public void setPeriodoFiscale(String periodoFiscale) {
        this.periodoFiscale = periodoFiscale;
    }

    public DateTime getDataTermineEmissione() {
        return dataTermineEmissione;
    }

    public void setDataTermineEmissione(DateTime dataTermineEmissione) {
        this.dataTermineEmissione = dataTermineEmissione;
    }

}

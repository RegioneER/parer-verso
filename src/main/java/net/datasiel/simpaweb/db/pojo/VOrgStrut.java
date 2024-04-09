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

package net.datasiel.simpaweb.db.pojo;

/**
 * VOrgStrut
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.io.Serializable;

import com.manydesigns.elements.annotations.MaxLength;
import com.manydesigns.elements.annotations.Password;
import com.manydesigns.elements.annotations.Required;

public class VOrgStrut implements Serializable {

    private static final long serialVersionUID = 1L;

    public VOrgStrut() {
        super();
    }

    /**
     * Type : BIGINT Name : ID_STRUT
     */
    public Long idStrut;

    /**
     * Type : BIGINT Name : ID_ENTE
     */
    public Long idEnte;

    /**
     * Type : VARCHAR Name : NM_STRUT
     */
    public String nmStrut;

    /**
     * Type : VARCHAR Name : DS_STRUT
     */
    public String dsStrut;

    /**
     * Type : VARCHAR Name : TI_SCAD_CHIUS_VOLUME
     */
    public String tiScadChiusVolume;

    /**
     * Type : VARCHAR Name : TI_TEMPO_SCAD_CHIUS
     */
    public String tiTempoScadChius;

    /**
     * Type : BIGINT Name : NI_TEMPO_SCAD_CHIUS
     */
    public Long niTempoScadChius;

    /**
     * Type : VARCHAR Name : TI_TEMPO_SCAD_CHIUS_FIRME
     */
    public String tiTempoScadChiusFirme;

    /**
     * Type : BIGINT Name : NI_TEMPO_SCAD_CHIUS_FIRME
     */
    public Long niTempoScadChiusFirme;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_CONTR_CRITTOG_VERS
     */
    public String flAbilitaContrCrittogVers;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_CONTR_TRUST_VERS
     */
    public String flAbilitaContrTrustVers;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_CONTR_CERTIF_VERS
     */
    public String flAbilitaContrCertifVers;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_CONTR_CRL_VERS
     */
    public String flAbilitaContrCrlVers;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_FIRMA_NOCONOS
     */
    public String flAccettaFirmaNoconos;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_FIRMA_NOCONF
     */
    public String flAccettaFirmaNoconf;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_FIRMA_GIUGNO_2011
     */
    public String flAccettaFirmaGiugno2011;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CRITTOG_NEG
     */
    public String flAccettaContrCrittogNeg;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_TRUST_NEG
     */
    public String flAccettaContrTrustNeg;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CERTIF_SCAD
     */
    public String flAccettaContrCertifScad;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CERTIF_NOVAL
     */
    public String flAccettaContrCertifNoval;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CERTIF_NOCERT
     */
    public String flAccettaContrCertifNocert;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CRL_NEG
     */
    public String flAccettaContrCrlNeg;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CRL_SCAD
     */
    public String flAccettaContrCrlScad;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CRL_NOVAL
     */
    public String flAccettaContrCrlNoval;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_CRL_NOSCAR
     */
    public String flAccettaContrCrlNoscar;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_SERV_MODIFICA
     */
    public String flAbilitaServModifica;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_SERV_INTEGR
     */
    public String flAbilitaServIntegr;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_VERS_COMP_META
     */
    public String flAbilitaVersCompMeta;

    /**
     * Type : CHAR(1) Name : FL_ABILITA_CONTR_FMT
     */
    public String flAbilitaContrFmt;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_MARCA_NOCONOS
     */
    public String flAccettaMarcaNoconos;

    /**
     * Type : CHAR(1) Name : FL_ACCETTA_CONTR_FMT_NEG
     */
    public String flAccettaContrFmtNeg;

    public String passwordVersatore;

    /**
     * Sets the value for idStrut
     */
    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    /**
     * Gets the value for idStrut
     */
    @Required
    public Long getIdStrut() {
        return idStrut;
    }

    /**
     * Sets the value for idEnte
     */
    public void setIdEnte(Long idEnte) {
        this.idEnte = idEnte;
    }

    /**
     * Gets the value for idEnte
     */
    @Required
    public Long getIdEnte() {
        return idEnte;
    }

    /**
     * Sets the value for nmStrut
     */
    public void setNmStrut(String nmStrut) {
        this.nmStrut = nmStrut;
    }

    /**
     * Gets the value for nmStrut
     */
    @Required
    @MaxLength(100)
    public String getNmStrut() {
        return nmStrut;
    }

    /**
     * Sets the value for dsStrut
     */
    public void setDsStrut(String dsStrut) {
        this.dsStrut = dsStrut;
    }

    /**
     * Gets the value for dsStrut
     */
    @Required
    @MaxLength(254)
    public String getDsStrut() {
        return dsStrut;
    }

    /**
     * Sets the value for tiScadChiusVolume
     */
    public void setTiScadChiusVolume(String tiScadChiusVolume) {
        this.tiScadChiusVolume = tiScadChiusVolume;
    }

    /**
     * Gets the value for tiScadChiusVolume
     */
    @MaxLength(20)
    public String getTiScadChiusVolume() {
        return tiScadChiusVolume;
    }

    /**
     * Sets the value for tiTempoScadChius
     */
    public void setTiTempoScadChius(String tiTempoScadChius) {
        this.tiTempoScadChius = tiTempoScadChius;
    }

    /**
     * Gets the value for tiTempoScadChius
     */
    @MaxLength(20)
    public String getTiTempoScadChius() {
        return tiTempoScadChius;
    }

    /**
     * Sets the value for niTempoScadChius
     */
    public void setNiTempoScadChius(Long niTempoScadChius) {
        this.niTempoScadChius = niTempoScadChius;
    }

    /**
     * Gets the value for niTempoScadChius
     */
    public Long getNiTempoScadChius() {
        return niTempoScadChius;
    }

    /**
     * Sets the value for tiTempoScadChiusFirme
     */
    public void setTiTempoScadChiusFirme(String tiTempoScadChiusFirme) {
        this.tiTempoScadChiusFirme = tiTempoScadChiusFirme;
    }

    /**
     * Gets the value for tiTempoScadChiusFirme
     */
    @Required
    @MaxLength(20)
    public String getTiTempoScadChiusFirme() {
        return tiTempoScadChiusFirme;
    }

    /**
     * Sets the value for niTempoScadChiusFirme
     */
    public void setNiTempoScadChiusFirme(Long niTempoScadChiusFirme) {
        this.niTempoScadChiusFirme = niTempoScadChiusFirme;
    }

    /**
     * Gets the value for niTempoScadChiusFirme
     */
    @Required
    public Long getNiTempoScadChiusFirme() {
        return niTempoScadChiusFirme;
    }

    /**
     * Sets the value for flAbilitaContrCrittogVers
     */
    public void setFlAbilitaContrCrittogVers(String flAbilitaContrCrittogVers) {
        this.flAbilitaContrCrittogVers = flAbilitaContrCrittogVers;
    }

    /**
     * Gets the value for flAbilitaContrCrittogVers
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaContrCrittogVers() {
        return flAbilitaContrCrittogVers;
    }

    /**
     * Sets the value for flAbilitaContrTrustVers
     */
    public void setFlAbilitaContrTrustVers(String flAbilitaContrTrustVers) {
        this.flAbilitaContrTrustVers = flAbilitaContrTrustVers;
    }

    /**
     * Gets the value for flAbilitaContrTrustVers
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaContrTrustVers() {
        return flAbilitaContrTrustVers;
    }

    /**
     * Sets the value for flAbilitaContrCertifVers
     */
    public void setFlAbilitaContrCertifVers(String flAbilitaContrCertifVers) {
        this.flAbilitaContrCertifVers = flAbilitaContrCertifVers;
    }

    /**
     * Gets the value for flAbilitaContrCertifVers
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaContrCertifVers() {
        return flAbilitaContrCertifVers;
    }

    /**
     * Sets the value for flAbilitaContrCrlVers
     */
    public void setFlAbilitaContrCrlVers(String flAbilitaContrCrlVers) {
        this.flAbilitaContrCrlVers = flAbilitaContrCrlVers;
    }

    /**
     * Gets the value for flAbilitaContrCrlVers
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaContrCrlVers() {
        return flAbilitaContrCrlVers;
    }

    /**
     * Sets the value for flAccettaFirmaNoconos
     */
    public void setFlAccettaFirmaNoconos(String flAccettaFirmaNoconos) {
        this.flAccettaFirmaNoconos = flAccettaFirmaNoconos;
    }

    /**
     * Gets the value for flAccettaFirmaNoconos
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaFirmaNoconos() {
        return flAccettaFirmaNoconos;
    }

    /**
     * Sets the value for flAccettaFirmaNoconf
     */
    public void setFlAccettaFirmaNoconf(String flAccettaFirmaNoconf) {
        this.flAccettaFirmaNoconf = flAccettaFirmaNoconf;
    }

    /**
     * Gets the value for flAccettaFirmaNoconf
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaFirmaNoconf() {
        return flAccettaFirmaNoconf;
    }

    /**
     * Sets the value for flAccettaFirmaGiugno2011
     */
    public void setFlAccettaFirmaGiugno2011(String flAccettaFirmaGiugno2011) {
        this.flAccettaFirmaGiugno2011 = flAccettaFirmaGiugno2011;
    }

    /**
     * Gets the value for flAccettaFirmaGiugno2011
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaFirmaGiugno2011() {
        return flAccettaFirmaGiugno2011;
    }

    /**
     * Sets the value for flAccettaContrCrittogNeg
     */
    public void setFlAccettaContrCrittogNeg(String flAccettaContrCrittogNeg) {
        this.flAccettaContrCrittogNeg = flAccettaContrCrittogNeg;
    }

    /**
     * Gets the value for flAccettaContrCrittogNeg
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCrittogNeg() {
        return flAccettaContrCrittogNeg;
    }

    /**
     * Sets the value for flAccettaContrTrustNeg
     */
    public void setFlAccettaContrTrustNeg(String flAccettaContrTrustNeg) {
        this.flAccettaContrTrustNeg = flAccettaContrTrustNeg;
    }

    /**
     * Gets the value for flAccettaContrTrustNeg
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrTrustNeg() {
        return flAccettaContrTrustNeg;
    }

    /**
     * Sets the value for flAccettaContrCertifScad
     */
    public void setFlAccettaContrCertifScad(String flAccettaContrCertifScad) {
        this.flAccettaContrCertifScad = flAccettaContrCertifScad;
    }

    /**
     * Gets the value for flAccettaContrCertifScad
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCertifScad() {
        return flAccettaContrCertifScad;
    }

    /**
     * Sets the value for flAccettaContrCertifNoval
     */
    public void setFlAccettaContrCertifNoval(String flAccettaContrCertifNoval) {
        this.flAccettaContrCertifNoval = flAccettaContrCertifNoval;
    }

    /**
     * Gets the value for flAccettaContrCertifNoval
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCertifNoval() {
        return flAccettaContrCertifNoval;
    }

    /**
     * Sets the value for flAccettaContrCertifNocert
     */
    public void setFlAccettaContrCertifNocert(String flAccettaContrCertifNocert) {
        this.flAccettaContrCertifNocert = flAccettaContrCertifNocert;
    }

    /**
     * Gets the value for flAccettaContrCertifNocert
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCertifNocert() {
        return flAccettaContrCertifNocert;
    }

    /**
     * Sets the value for flAccettaContrCrlNeg
     */
    public void setFlAccettaContrCrlNeg(String flAccettaContrCrlNeg) {
        this.flAccettaContrCrlNeg = flAccettaContrCrlNeg;
    }

    /**
     * Gets the value for flAccettaContrCrlNeg
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCrlNeg() {
        return flAccettaContrCrlNeg;
    }

    /**
     * Sets the value for flAccettaContrCrlScad
     */
    public void setFlAccettaContrCrlScad(String flAccettaContrCrlScad) {
        this.flAccettaContrCrlScad = flAccettaContrCrlScad;
    }

    /**
     * Gets the value for flAccettaContrCrlScad
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCrlScad() {
        return flAccettaContrCrlScad;
    }

    /**
     * Sets the value for flAccettaContrCrlNoval
     */
    public void setFlAccettaContrCrlNoval(String flAccettaContrCrlNoval) {
        this.flAccettaContrCrlNoval = flAccettaContrCrlNoval;
    }

    /**
     * Gets the value for flAccettaContrCrlNoval
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCrlNoval() {
        return flAccettaContrCrlNoval;
    }

    /**
     * Sets the value for flAccettaContrCrlNoscar
     */
    public void setFlAccettaContrCrlNoscar(String flAccettaContrCrlNoscar) {
        this.flAccettaContrCrlNoscar = flAccettaContrCrlNoscar;
    }

    /**
     * Gets the value for flAccettaContrCrlNoscar
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrCrlNoscar() {
        return flAccettaContrCrlNoscar;
    }

    /**
     * Sets the value for flAbilitaServModifica
     */
    public void setFlAbilitaServModifica(String flAbilitaServModifica) {
        this.flAbilitaServModifica = flAbilitaServModifica;
    }

    /**
     * Gets the value for flAbilitaServModifica
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaServModifica() {
        return flAbilitaServModifica;
    }

    /**
     * Sets the value for flAbilitaServIntegr
     */
    public void setFlAbilitaServIntegr(String flAbilitaServIntegr) {
        this.flAbilitaServIntegr = flAbilitaServIntegr;
    }

    /**
     * Gets the value for flAbilitaServIntegr
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaServIntegr() {
        return flAbilitaServIntegr;
    }

    /**
     * Sets the value for flAbilitaVersCompMeta
     */
    public void setFlAbilitaVersCompMeta(String flAbilitaVersCompMeta) {
        this.flAbilitaVersCompMeta = flAbilitaVersCompMeta;
    }

    /**
     * Gets the value for flAbilitaVersCompMeta
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaVersCompMeta() {
        return flAbilitaVersCompMeta;
    }

    /**
     * Sets the value for flAbilitaContrFmt
     */
    public void setFlAbilitaContrFmt(String flAbilitaContrFmt) {
        this.flAbilitaContrFmt = flAbilitaContrFmt;
    }

    /**
     * Gets the value for flAbilitaContrFmt
     */
    @Required
    @MaxLength(1)
    public String getFlAbilitaContrFmt() {
        return flAbilitaContrFmt;
    }

    /**
     * Sets the value for flAccettaMarcaNoconos
     */
    public void setFlAccettaMarcaNoconos(String flAccettaMarcaNoconos) {
        this.flAccettaMarcaNoconos = flAccettaMarcaNoconos;
    }

    /**
     * Gets the value for flAccettaMarcaNoconos
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaMarcaNoconos() {
        return flAccettaMarcaNoconos;
    }

    /**
     * Sets the value for flAccettaContrFmtNeg
     */
    public void setFlAccettaContrFmtNeg(String flAccettaContrFmtNeg) {
        this.flAccettaContrFmtNeg = flAccettaContrFmtNeg;
    }

    /**
     * Gets the value for flAccettaContrFmtNeg
     */
    @Required
    @MaxLength(1)
    public String getFlAccettaContrFmtNeg() {
        return flAccettaContrFmtNeg;
    }

    public String toString() {
        StringBuffer str = new StringBuffer();
        str.append(super.toString());
        try {
            java.lang.reflect.Field[] fields = getClass().getDeclaredFields();
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                Object fieldValue = fields[i].get(this);
                String line = "\n" + fieldName + ": " + fieldValue;
                str.append(line);
            }
            return str.toString();
        } catch (Exception e) {
            return str.toString();
        }
    }

    @Required
    @MaxLength(20)
    @Password
    public String getPasswordVersatore() {
        return passwordVersatore;
    }

    public void setPasswordVersatore(String passwordVersatore) {
        this.passwordVersatore = passwordVersatore;
    }
}

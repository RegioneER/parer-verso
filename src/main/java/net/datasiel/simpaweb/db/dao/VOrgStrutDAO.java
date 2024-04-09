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

package net.datasiel.simpaweb.db.dao;

/**
 * VOrgStrut
 *
 * WARNING! Automatically generated file! Do not edit!
 */

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import net.datasiel.simpaweb.db.pojo.VOrgStrut;

public class VOrgStrutDAO extends VOrgStrut {

    private final Logger log = LoggerFactory.getLogger(VOrgStrutDAO.class);

    private static final long serialVersionUID = 0L;
    public static String[] fieldNames = null;

    static {
        fieldNames = new String[] { "idStrut", "idEnte", "nmStrut", "dsStrut", "tiScadChiusVolume", "tiTempoScadChius",
                "niTempoScadChius", "tiTempoScadChiusFirme", "niTempoScadChiusFirme", "flAbilitaContrCrittogVers",
                "flAbilitaContrTrustVers", "flAbilitaContrCertifVers", "flAbilitaContrCrlVers", "flAccettaFirmaNoconos",
                "flAccettaFirmaNoconf", "flAccettaFirmaGiugno2011", "flAccettaContrCrittogNeg",
                "flAccettaContrTrustNeg", "flAccettaContrCertifScad", "flAccettaContrCertifNoval",
                "flAccettaContrCertifNocert", "flAccettaContrCrlNeg", "flAccettaContrCrlScad", "flAccettaContrCrlNoval",
                "flAccettaContrCrlNoscar", "flAbilitaServModifica", "flAbilitaServIntegr", "flAbilitaVersCompMeta",
                "flAbilitaContrFmt", "flAccettaMarcaNoconos", "flAccettaContrFmtNeg" };
    }

    public VOrgStrutDAO() {
        super();
    }

    /**
     * Updates the object from a retrieved ResultSet.
     */
    public void getFromResultSet(VOrgStrut obj, ResultSet r) throws SQLException {
        if (r.getObject("ID_STRUT") == null) {
            obj.setIdStrut(null);
        } else {
            obj.setIdStrut(r.getLong("ID_STRUT"));
        }
        if (r.getObject("ID_ENTE") == null) {
            obj.setIdEnte(null);
        } else {
            obj.setIdEnte(r.getLong("ID_ENTE"));
        }
        obj.setNmStrut(r.getString("NM_STRUT"));
        obj.setDsStrut(r.getString("DS_STRUT"));
        obj.setTiScadChiusVolume(r.getString("TI_SCAD_CHIUS_VOLUME"));
        obj.setTiTempoScadChius(r.getString("TI_TEMPO_SCAD_CHIUS"));
        if (r.getObject("NI_TEMPO_SCAD_CHIUS") == null) {
            obj.setNiTempoScadChius(null);
        } else {
            obj.setNiTempoScadChius(r.getLong("NI_TEMPO_SCAD_CHIUS"));
        }
        obj.setTiTempoScadChiusFirme(r.getString("TI_TEMPO_SCAD_CHIUS_FIRME"));
        if (r.getObject("NI_TEMPO_SCAD_CHIUS_FIRME") == null) {
            obj.setNiTempoScadChiusFirme(null);
        } else {
            obj.setNiTempoScadChiusFirme(r.getLong("NI_TEMPO_SCAD_CHIUS_FIRME"));
        }
        obj.setFlAbilitaContrCrittogVers(r.getString("FL_ABILITA_CONTR_CRITTOG_VERS"));
        obj.setFlAbilitaContrTrustVers(r.getString("FL_ABILITA_CONTR_TRUST_VERS"));
        obj.setFlAbilitaContrCertifVers(r.getString("FL_ABILITA_CONTR_CERTIF_VERS"));
        obj.setFlAbilitaContrCrlVers(r.getString("FL_ABILITA_CONTR_CRL_VERS"));
        obj.setFlAccettaFirmaNoconos(r.getString("FL_ACCETTA_FIRMA_NOCONOS"));
        obj.setFlAccettaFirmaNoconf(r.getString("FL_ACCETTA_FIRMA_NOCONF"));
        obj.setFlAccettaFirmaGiugno2011(r.getString("FL_ACCETTA_FIRMA_GIUGNO_2011"));
        obj.setFlAccettaContrCrittogNeg(r.getString("FL_ACCETTA_CONTR_CRITTOG_NEG"));
        obj.setFlAccettaContrTrustNeg(r.getString("FL_ACCETTA_CONTR_TRUST_NEG"));
        obj.setFlAccettaContrCertifScad(r.getString("FL_ACCETTA_CONTR_CERTIF_SCAD"));
        obj.setFlAccettaContrCertifNoval(r.getString("FL_ACCETTA_CONTR_CERTIF_NOVAL"));
        obj.setFlAccettaContrCertifNocert(r.getString("FL_ACCETTA_CONTR_CERTIF_NOCERT"));
        obj.setFlAccettaContrCrlNeg(r.getString("FL_ACCETTA_CONTR_CRL_NEG"));
        obj.setFlAccettaContrCrlScad(r.getString("FL_ACCETTA_CONTR_CRL_SCAD"));
        obj.setFlAccettaContrCrlNoval(r.getString("FL_ACCETTA_CONTR_CRL_NOVAL"));
        obj.setFlAccettaContrCrlNoscar(r.getString("FL_ACCETTA_CONTR_CRL_NOSCAR"));
        obj.setFlAbilitaServModifica(r.getString("FL_ABILITA_SERV_MODIFICA"));
        obj.setFlAbilitaServIntegr(r.getString("FL_ABILITA_SERV_INTEGR"));
        obj.setFlAbilitaVersCompMeta(r.getString("FL_ABILITA_VERS_COMP_META"));
        obj.setFlAbilitaContrFmt(r.getString("FL_ABILITA_CONTR_FMT"));
        obj.setFlAccettaMarcaNoconos(r.getString("FL_ACCETTA_MARCA_NOCONOS"));
        obj.setFlAccettaContrFmtNeg(r.getString("FL_ACCETTA_CONTR_FMT_NEG"));
    }

    /**
     * Inserts the current object values into the database.
     */
    public int insertPrepared(VOrgStrut obj, Connection con) throws SQLException {
        int indice = 1;
        String prepQuery = "insert into V_ORG_STRUT ( ID_STRUT,ID_ENTE,NM_STRUT,DS_STRUT,TI_SCAD_CHIUS_VOLUME,TI_TEMPO_SCAD_CHIUS,NI_TEMPO_SCAD_CHIUS,TI_TEMPO_SCAD_CHIUS_FIRME,NI_TEMPO_SCAD_CHIUS_FIRME,FL_ABILITA_CONTR_CRITTOG_VERS,FL_ABILITA_CONTR_TRUST_VERS,FL_ABILITA_CONTR_CERTIF_VERS,FL_ABILITA_CONTR_CRL_VERS,FL_ACCETTA_FIRMA_NOCONOS,FL_ACCETTA_FIRMA_NOCONF,FL_ACCETTA_FIRMA_GIUGNO_2011,FL_ACCETTA_CONTR_CRITTOG_NEG,FL_ACCETTA_CONTR_TRUST_NEG,FL_ACCETTA_CONTR_CERTIF_SCAD,FL_ACCETTA_CONTR_CERTIF_NOVAL,FL_ACCETTA_CONTR_CERTIF_NOCERT,FL_ACCETTA_CONTR_CRL_NEG,FL_ACCETTA_CONTR_CRL_SCAD,FL_ACCETTA_CONTR_CRL_NOVAL,FL_ACCETTA_CONTR_CRL_NOSCAR,FL_ABILITA_SERV_MODIFICA,FL_ABILITA_SERV_INTEGR,FL_ABILITA_VERS_COMP_META,FL_ABILITA_CONTR_FMT,FL_ACCETTA_MARCA_NOCONOS,FL_ACCETTA_CONTR_FMT_NEG ) values (? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,? ,?   )";
        java.sql.PreparedStatement pst = con.prepareStatement(prepQuery);
        if (obj.getIdStrut() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdStrut());
        }
        if (obj.getIdEnte() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getIdEnte());
        }
        pst.setString(indice++, obj.getNmStrut());
        pst.setString(indice++, obj.getDsStrut());
        pst.setString(indice++, obj.getTiScadChiusVolume());
        pst.setString(indice++, obj.getTiTempoScadChius());
        if (obj.getNiTempoScadChius() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getNiTempoScadChius());
        }
        pst.setString(indice++, obj.getTiTempoScadChiusFirme());
        if (obj.getNiTempoScadChiusFirme() == null) {
            pst.setNull(indice++, 3);
        } else {
            pst.setLong(indice++, obj.getNiTempoScadChiusFirme());
        }
        pst.setString(indice++, obj.getFlAbilitaContrCrittogVers());
        pst.setString(indice++, obj.getFlAbilitaContrTrustVers());
        pst.setString(indice++, obj.getFlAbilitaContrCertifVers());
        pst.setString(indice++, obj.getFlAbilitaContrCrlVers());
        pst.setString(indice++, obj.getFlAccettaFirmaNoconos());
        pst.setString(indice++, obj.getFlAccettaFirmaNoconf());
        pst.setString(indice++, obj.getFlAccettaFirmaGiugno2011());
        pst.setString(indice++, obj.getFlAccettaContrCrittogNeg());
        pst.setString(indice++, obj.getFlAccettaContrTrustNeg());
        pst.setString(indice++, obj.getFlAccettaContrCertifScad());
        pst.setString(indice++, obj.getFlAccettaContrCertifNoval());
        pst.setString(indice++, obj.getFlAccettaContrCertifNocert());
        pst.setString(indice++, obj.getFlAccettaContrCrlNeg());
        pst.setString(indice++, obj.getFlAccettaContrCrlScad());
        pst.setString(indice++, obj.getFlAccettaContrCrlNoval());
        pst.setString(indice++, obj.getFlAccettaContrCrlNoscar());
        pst.setString(indice++, obj.getFlAbilitaServModifica());
        pst.setString(indice++, obj.getFlAbilitaServIntegr());
        pst.setString(indice++, obj.getFlAbilitaVersCompMeta());
        pst.setString(indice++, obj.getFlAbilitaContrFmt());
        pst.setString(indice++, obj.getFlAccettaMarcaNoconos());
        pst.setString(indice++, obj.getFlAccettaContrFmtNeg());

        try {
            log.debug(prepQuery);
            int updates = pst.executeUpdate();
            return updates;
        } catch (SQLException e) {
            log.error("Failed query:" + prepQuery);
            throw e;
        } finally {
            if (pst != null) {
                pst.close();
            }
        }
    }

}

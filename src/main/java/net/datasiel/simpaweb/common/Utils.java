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

package net.datasiel.simpaweb.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import net.datasiel.par.jaxb.esito.ECEsitoExtType;
import net.datasiel.simpaweb.db.EnumStatoUD;

import org.apache.commons.lang.StringUtils;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.slf4j.Logger;

import com.manydesigns.elements.messages.SessionMessages;

public class Utils {

    private static final String PATH_NODO_ESITO = "/EsitoVersamento/EsitoGenerale";
    private static final String PATH_NODO_ERRORI_ULTERIORI = "/EsitoVersamento/ErroriUlteriori";
    //
    private static final String NODO_CODICE_ESITO = "CodiceEsito";
    private static final String NODO_MESSAGGIO_ERRORE = "MessaggioErrore";
    private static final String CODICE_ERRORE = "CodiceErrore";
    // CodiceErrore
    private static final String CODICE_ERRORE_CHIAVE_DOPPIA = "UD-002-001";

    public static String getLabelEnteStruttura(Long idStrut, Connection con) throws SQLException {
        String label = "";
        StringBuffer prepQuery = new StringBuffer();

        prepQuery.append("select distinct e.NM_AMBIENTE || ' - ' || e.NM_ENTE || ' - ' || e.NM_STRUT label ");
        prepQuery.append(" from V_USR_IAM e ");
        prepQuery.append(" where e.ID_STRUT = ? ");

        ResultSet rs = null;
        try (PreparedStatement pst = con.prepareStatement(prepQuery.toString())) {
            
            pst.setLong(1, idStrut);
            rs = pst.executeQuery();
            if (rs.next()) {
                label = rs.getString("label");
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
        }
        return label;
    }

    /**
     * @param docEsito
     *            (xml di cui effettuare il parsing) verifica ("true" se si tratta di una verifica, false se si tratta
     *            di un versamento) log
     */
    public static EnumStatoUD getNuovoStato(Document docEsito, boolean verifica, Logger log) {

        ECEsitoExtType esito = null;
        EnumStatoUD codice = null;
        String messaggio = "";

        XPathExpression<org.jdom2.Element> xpathEsito = XPathFactory.instance().compile(PATH_NODO_ESITO,
                Filters.element());
        org.jdom2.Element emtEsito = xpathEsito.evaluateFirst(docEsito);
        if (emtEsito != null) {
            org.jdom2.Element codiceEl = emtEsito.getChild(NODO_CODICE_ESITO);
            org.jdom2.Element messaggioEl = emtEsito.getChild(NODO_MESSAGGIO_ERRORE);
            System.out.println("XPath has result: " + emtEsito.getName());
            esito = ECEsitoExtType.fromValue(codiceEl.getTextTrim());

            switch (esito) {

            case NEGATIVO:
                codice = EnumStatoUD.VERIFICATAKO;
                messaggio = messaggioEl.getTextTrim();
                log.debug("Esito negativo.");
                SessionMessages.addErrorMessage(messaggio);
                break;

            case POSITIVO:
                if (verifica) {
                    messaggio = "Verifica corretta.";
                    codice = EnumStatoUD.VERIFICATAOK;
                } else {
                    messaggio = "Versamento corretto.";
                    codice = EnumStatoUD.VERSATA;
                }
                log.debug("Esito positivo.");
                SessionMessages.addInfoMessage(messaggio);
                break;

            case WARNING:
                if (verifica) {
                    codice = EnumStatoUD.VERIFICATAOK;
                } else {
                    codice = EnumStatoUD.VERSATA;
                }
                messaggio = messaggioEl.getTextTrim();
                log.debug("Esito positivo con warning.");
                SessionMessages.addInfoMessage(messaggio);
                break;

            default:
                break;

            }

        }

        return codice;
    }

    /**
     * Effettua il parsing del Document contenente l'xml di risposta da cui estrarre le info da restituire sotto forma
     * di un oggetto di tipo InfoEsito contenente - l'esito (POSITIVO, NEGATIVO o WARNING) - il codice rappresentante lo
     * stato successivo all'operazione svolta (VERIFICATAKO, VERIFICATAOK o VERSATA) - un messaggio (rappresentato da
     * una stringa)
     *
     * @param docEsito
     *            (xml di cui effettuare il parsing) verifica ("true" se si tratta di una verifica, "false" se si tratta
     *            di un versamento)
     *
     * @return infoEsito
     */
    public static InfoEsito getInfoEsito(Document docEsito, EnumOperazione operazione, Logger log) {

        InfoEsito infoEsito = new InfoEsito();

        ECEsitoExtType esito = null;
        EnumStatoUD codice = null;
        String messaggio = "";
        String errorCode = "";
        XPathExpression<org.jdom2.Element> xpathEsito = XPathFactory.instance().compile(PATH_NODO_ESITO,
                Filters.element());
        org.jdom2.Element emtEsito = xpathEsito.evaluateFirst(docEsito);
        log.info("Esito :" + emtEsito);
        if (emtEsito != null) {
            org.jdom2.Element codiceEl = emtEsito.getChild(NODO_CODICE_ESITO);
            org.jdom2.Element messaggioEl = emtEsito.getChild(NODO_MESSAGGIO_ERRORE);
            org.jdom2.Element codiceErroreEl = emtEsito.getChild(CODICE_ERRORE);
            errorCode = codiceErroreEl.getTextTrim();
            errorCode = StringUtils.isNotBlank(errorCode) ? "(" + errorCode + ") " : "";
            // CodiceErrore
            esito = ECEsitoExtType.fromValue(codiceEl.getTextTrim());
            codice = EnumStatoUD.VERIFICATAKO;
            infoEsito.setEsito(codiceEl.getTextTrim());

            switch (esito) {

            case NEGATIVO:
                codice = EnumStatoUD.VERIFICATAKO;
                messaggio = errorCode + messaggioEl.getTextTrim();
                log.info("Esito negativo.");
                break;

            case POSITIVO:
                if (operazione == EnumOperazione.VERIFICA) {
                    messaggio = "Verifica corretta.";
                    codice = EnumStatoUD.VERIFICATAOK;
                } else {
                    messaggio = "Versamento corretto.";
                    codice = EnumStatoUD.VERSATA;
                }
                log.info("Esito positivo.");
                break;

            case WARNING:
                if (operazione == EnumOperazione.VERIFICA) {
                    codice = EnumStatoUD.VERIFICATAOK;
                } else {
                    codice = EnumStatoUD.VERSATA;
                }
                messaggio = errorCode + messaggioEl.getTextTrim();
                log.info("Esito positivo con warning.");
                break;

            default:
                break;

            }

        }
        /*
         * <ErroriUlteriori> <Errore> <CodiceErrore>string</CodiceErrore> <MessaggioErrore>string</MessaggioErrore>
         * </Errore> </ErroriUlteriori>
         */

        XPathExpression<org.jdom2.Element> xpathErrorUlteriori = XPathFactory.instance()
                .compile(PATH_NODO_ERRORI_ULTERIORI, Filters.element());
        org.jdom2.Element emtErroriUlteriori = xpathErrorUlteriori.evaluateFirst(docEsito);
        StringBuilder sErroriUlteriori = new StringBuilder();
        if (emtErroriUlteriori != null) {

            List<Element> errori = emtErroriUlteriori.getChildren();
            for (Element errore : errori) {
                org.jdom2.Element messaggioEl = errore.getChild(NODO_MESSAGGIO_ERRORE);
                org.jdom2.Element codiceErroreEl = errore.getChild(CODICE_ERRORE);
                errorCode = codiceErroreEl.getTextTrim();
                errorCode = StringUtils.isNotBlank(errorCode) ? "(" + errorCode + ") " : "";
                sErroriUlteriori.append("<br/>");
                sErroriUlteriori.append(errorCode + messaggioEl.getTextTrim());

            }

        }

        infoEsito.setMessaggio(messaggio);
        if (codice != null) {
            infoEsito.setStato(codice.ordinal());
        }
        return infoEsito;
    }

    public static void VisualizzaMessaggioEsito(ECEsitoExtType esito, String messaggio, EnumOperazione operazione) {
        String opVal = "Esito " + StringUtils.capitalize(operazione.name());

        switch (esito) {

        case NEGATIVO:
            SessionMessages.addErrorMessage(opVal + " : NEGATIVO - " + messaggio);
            break;

        case POSITIVO:
            messaggio = "Verifica corretta.";
            SessionMessages.addInfoMessage(opVal + " : POSITIVO");
            break;

        case WARNING:
            SessionMessages.addInfoMessage(opVal + " : WARNING - " + messaggio);
            break;

        default:
            break;

        }
    }

    public static boolean isChiaveDoppia(InfoEsito esito) {
        boolean result;

        if (esito.getMessaggio().contains(CODICE_ERRORE_CHIAVE_DOPPIA)) {
            result = true;
        } else {
            result = false;
        }

        return result;
    }
}

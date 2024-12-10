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

package net.datasiel.par.service;

import static it.eng.spagoCore.configuration.ConfigProperties.StandardProperty.URI_VERSMENTO_SYNC;

import java.io.StringReader;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.Document;
import org.jdom2.input.SAXBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.manydesigns.elements.messages.SessionMessages;

import it.eng.exceptions.ErrorCategory.VersoErrorCategory;
import it.eng.exceptions.VersoException;
import it.eng.parer.simparer.security.ClientUser;
import it.eng.spagoCore.configuration.ConfigSingleton;
import it.eng.spagoLite.SessionManager;
import net.datasiel.par.beans.DatiUnitaDocumentaria;
import net.datasiel.par.xml.PARHelper;
import net.datasiel.simpaweb.common.EnumOperazione;
import net.datasiel.simpaweb.common.InfoEsito;
import net.datasiel.simpaweb.common.Utils;
import net.datasiel.simpaweb.db.dao.ParConfigurazioneDAO;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.simpaweb.helpers.GatewayHelper;

public class VerificaVersamentoUD {

    private static final Logger log = LoggerFactory.getLogger(VerificaVersamentoUD.class);

    private VerificaVersamentoUD() {
        throw new IllegalStateException("Utility class");
    }

    @SuppressWarnings("unchecked")
    public static InfoEsito esegui(EnumOperazione operazione, Long idUnitaDoc, Connection con,
            HttpServletRequest request) throws SQLException {
        InfoEsito infoEsito = new InfoEsito();
        HttpSession session = request.getSession();
        String xmlRichiesta = null;
        ParConfigurazioneDAO configDao = new ParConfigurazioneDAO();
        String automaUsername = configDao.retrieveByChiave("AUTOMA_WS_USER", con);
        String automaPassword = configDao.retrieveByChiave("AUTOMA_WS_PWD", con);
        boolean isComunicazione = false;

        if (StringUtils.isBlank(automaPassword)) {
            infoEsito.setStato(1);
            infoEsito.setEsito("NEGATIVO");
            infoEsito.setMessaggio("La password dell'automa di versamento non è stata configurata.");
        } else if (StringUtils.isBlank(automaUsername)) {
            infoEsito.setStato(1);
            infoEsito.setEsito("NEGATIVO");
            infoEsito.setMessaggio("Lo username  dell'automa di versamento non è stato configurato.");
        } else {
            try {
                ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
                ParUnitadoc parUnitaDoc = parUnitadocVO.retrieveByKey(idUnitaDoc, con);

                GatewayHelper sacerHelper = new GatewayHelper();
                boolean simulaDb = true;
                if (operazione == EnumOperazione.VERSAMENTO || operazione == EnumOperazione.COMUNICAZIONE) {
                    simulaDb = false;
                }
                if (operazione == EnumOperazione.COMUNICAZIONE) {
                    operazione = EnumOperazione.VERIFICA;
                    isComunicazione = true;
                }

                DatiUnitaDocumentaria udDaTestare = sacerHelper.preparaUd(parUnitaDoc, simulaDb, con, automaUsername,
                        ((ClientUser) SessionManager.getUser(session)).getUsername());
                if (udDaTestare != null) {
                    PARHelper parHelper = new PARHelper("UTF-8");
                    String esitoInvocazione = "";
                    try {
                        URI uploadUrl = ConfigSingleton.getInstance().getUriValue(URI_VERSMENTO_SYNC.name());
                        xmlRichiesta = new PARHelper().creaRequestVersamento(udDaTestare);
                        esitoInvocazione = parHelper.invocaVersamentoSync(automaUsername, automaPassword, udDaTestare,
                                uploadUrl,
                                (Vector<Map<String, String>>) request.getSession().getAttribute("listaCookie"),
                                xmlRichiesta);
                    } catch (Exception e) {
                        // Tentativo di versamento fallito per eccezione senza che
                        // sia restituito xml di risposta.
                        ParUnitadocVO udDAO = new ParUnitadocVO();
                        udDAO.aggiornaUnitaDoc(-6, xmlRichiesta, null, idUnitaDoc,
                                simulaDb || isComunicazione ? null : infoEsito.getEsito(), con);

                        Throwable rootCause = ExceptionUtils.getRootCause(e);
                        log.error("Generic error {}", rootCause, e);
                        infoEsito.setStato(1);
                        infoEsito.setEsito("NEGATIVO");

                        String opDesc = "Versamento fallito";
                        if (operazione == EnumOperazione.VERIFICA) {
                            opDesc = "Verifica fallita";
                        }
                        infoEsito.setMessaggio(String.format("%s a causa di un errore generico %s", opDesc,
                                ExceptionUtils.getRootCauseMessage(e)));
                        return infoEsito;
                    }

                    // Leggere esito
                    SAXBuilder builder = new SAXBuilder();
                    ParUnitadocVO parUnitaDocVO = new ParUnitadocVO();
                    Document docEsito = builder.build(new StringReader(esitoInvocazione));

                    infoEsito = Utils.getInfoEsito(docEsito, operazione, log);

                    int statoDopoVerifica = infoEsito.getStato();
                    statoDopoVerifica = isComunicazione ? 0 : statoDopoVerifica;
                    if (statoDopoVerifica == 0 && isComunicazione) {
                        statoDopoVerifica = -5;
                    }

                    if (infoEsito.getStato() == 1) {
                        if (Utils.isChiaveDoppia(infoEsito)) {
                            if (parUnitaDocVO.getStato(idUnitaDoc, con) == 4) {
                                log.debug("UD {} già versata in Sacer!", idUnitaDoc);
                            } else {
                                parUnitaDocVO.aggiornaUnitaDoc(statoDopoVerifica, xmlRichiesta, esitoInvocazione,
                                        idUnitaDoc, simulaDb || isComunicazione ? null : infoEsito.getEsito(), con);
                            }
                        } else {
                            parUnitaDocVO.aggiornaUnitaDoc(statoDopoVerifica, xmlRichiesta, esitoInvocazione,
                                    idUnitaDoc, simulaDb || isComunicazione ? null : infoEsito.getEsito(), con);
                        }
                    } else {
                        parUnitaDocVO.aggiornaUnitaDoc(statoDopoVerifica, xmlRichiesta, esitoInvocazione, idUnitaDoc,
                                simulaDb || isComunicazione ? null : infoEsito.getEsito(), con);
                    }
                } else {
                    ParUnitadocVO udDAO = new ParUnitadocVO();
                    int statoPerVerificaOVersa = isComunicazione ? 0 : simulaDb ? 1 : -2;
                    if (statoPerVerificaOVersa == 1 && udDaTestare == null) {
                        statoPerVerificaOVersa = -6; // setto il nuovo stato per verifica locale fallita
                    }
                    udDAO.aggiornaUnitaDoc(statoPerVerificaOVersa, xmlRichiesta, null, idUnitaDoc,
                            simulaDb || isComunicazione ? null : infoEsito.getEsito(), con);
                    infoEsito.setStato(1);
                    infoEsito.setEsito("NEGATIVO");
                    infoEsito.setMessaggio("L'unità documentaria è priva di un documento principale.");
                }
            } catch (Exception e) {
                Throwable rootCause = ExceptionUtils.getRootCause(e);
                log.error("Errore " + operazione + " unità documentaria " + idUnitaDoc + ": " + rootCause, e);
                infoEsito.setStato(1);
                infoEsito.setEsito("NEGATIVO");

                String messaggio = "Errore generico, Contattare l'assistenza";
                SessionMessages.addErrorMessage(
                        "Errore " + operazione + " unità documentaria " + idUnitaDoc + ": " + messaggio);
            }
        }
        return infoEsito;
    }

    public static boolean isVersata(Long idUnitaDoc, Connection conn) throws VersoException {
        ParUnitadocVO parUnitaDocVO = new ParUnitadocVO();
        boolean result;

        try {
            if (parUnitaDocVO.getStato(idUnitaDoc, conn) == 4) {
                result = true;
            } else {
                result = false;
            }
        } catch (SQLException e) {
            throw new VersoException(e, VersoErrorCategory.INTERNAL_ERROR);
        }

        return result;
    }
}

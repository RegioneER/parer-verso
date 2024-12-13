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

package net.datasiel.simpaweb.actionbeans;

import com.manydesigns.elements.messages.SessionMessages;
import it.eng.parer.simparer.common.AppServerInstance;
import it.eng.parer.simparer.security.ClientUser;
import it.eng.parer.simparer.security.SimparerAuthenticator;
import it.eng.parer.simparer.security.SimparerLoginLog;
import it.eng.spagoCore.error.EMFError;
import it.eng.spagoLite.SessionManager;
import it.eng.spagoLite.security.IUser;
import it.eng.spagoLite.security.User;
import it.eng.spagoLite.security.auth.PwdUtil;
import it.eng.util.EncryptionUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import net.datasiel.simpaweb.beans.StrutturaInfo;
import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.common.UtenteStrutture;
import net.datasiel.simpaweb.common.shiro.CustomRealm;
import net.datasiel.simpaweb.db.dao.ParConfigurazioneDAO;
import net.datasiel.webapp.BaseAction;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.HandlesEvent;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.dbutils.DbUtils;
import org.springframework.security.authentication.AuthenticationServiceException;

/**
 * @author reisoli
 *
 */
@UrlBinding("/pro/{$event}")
public class Login extends BaseAction {

    @SpringBean("dataSource")
    private DataSource dataSource;
    private String userName;

    @SpringBean("simparerAuthenticator")
    private SimparerAuthenticator authenticator;

    @SpringBean("simparerLoginLog")
    private SimparerLoginLog loginLog;

    private static final String DS_EVENTO_BAD_CF_SPID = "SPID - Codice fiscale non trovato nella base dati";

    @DefaultHandler
    @HandlesEvent("login")
    public Resolution doLogin() {
        log.debug("risponde /pro/login");
        HttpSession session = getContext().getRequest().getSession();
        RedirectResolution resolution = new RedirectResolution(HomePrivata.class);
        Connection conn = null;
        try {
            ClientUser utente = getAndSetUserInSession(session);
            if (utente == null) {
                SessionMessages.addErrorMessage("Utente non autorizzato all'applicazione.");
                resolution = new RedirectResolution(HomePubblica.class);
                return resolution;
            }
            if (utente.isUtenteDaAssociare()) {
                gestisciUtenteDaAssociare(utente);
            } else {
                userName = utente.getUsername();
                byte[] password = utente.getPassword();
                session.setAttribute(Constants.USERNAME_ATTR, userName);
                session.setAttribute(Constants.PASSWORD_ATTR, password);
                conn = dataSource.getConnection();
                CustomRealm customRealm = new CustomRealm();
                // Determina se un utente è entrato con lo SPID Federa
                // MAC#25318 - Correzione della gestione delle utenze non attive mediante autenticazione SPID
                boolean isUtenteSpid = false;
                if (utente.getUserType() != null) {
                    isUtenteSpid = utente.getUserType().equals(IUser.UserType.SPID_FEDERA);
                }
                UtenteStrutture struttureUtente = customRealm.retrieveUtenteStrutture(userName, conn, isUtenteSpid);
                if (struttureUtente == null || struttureUtente.getStrutture() == null
                        || struttureUtente.getStrutture().isEmpty()) {
                    SessionMessages.addErrorMessage("L'utente non risulta associato a nessuna struttura.");
                    resolution = new RedirectResolution(HomePubblica.class);
                } else {
                    /*
                     * Logica per generalizzare il funzionamento dell'applicativo IDP: Un IDP che non conosce il DB di
                     * IAM non è a conoscenza dell'ID dell'utente. La logica seguente recupera l'ID dell'utente dal db
                     * di IAM attraverso lo userID e lo setta in sessione
                     */
                    utente.setUtenteStrutture(struttureUtente);
                    // ricarico l'ID dell'utente, poichè il dato che arriva dall'IDP potrebbe essere privo di senso
                    utente.setIdUtente(struttureUtente.getUtente().getIdUser());
                    Set<String> stringPermissions = new HashSet<String>();
                    for (StrutturaInfo struttura : struttureUtente.getStrutture()) {
                        Long idStrut = struttura.getIdStrut();
                        String permesso = String.format(CustomRealm.PERMESSO_PER_STRUTTURA_LEGGI_D, idStrut);
                        stringPermissions.add(permesso);
                        // Se ci saranno delle condizioni per inibire l'inserimento di nuove ud su
                        // una certa struttura inserire qui il controllo e non aggiungere il permesso.
                        permesso = String.format(CustomRealm.PERMESSO_PER_STRUTTURA_NUOVAUD_D, idStrut);
                        stringPermissions.add(permesso);
                    }
                    utente.setStringPermissions(stringPermissions);
                    Long idStruttura = struttureUtente.getStrutture().get(0).getIdStrut();
                    resolution = new RedirectResolution(HomePrivata.class);
                    resolution.addParameter("idStrut", idStruttura);
                    SessionManager.setUser(session, utente);
                }
                session.setAttribute(Constants.USERNAME_ATTR, userName);
            }
        } catch (Exception e) {
            log.error("Errore nella fase di login ", e);
            throw new AuthenticationServiceException(e.getMessage(), e);
        } finally {
            DbUtils.closeQuietly(conn);
        }
        return resolution;

    }

    @HandlesEvent("backFromAssociation")
    public Resolution backFromAssociation() {
        log.debug("risponde /pro/backFromAssociation");
        RedirectResolution resolution = null;
        SimparerLoginLog sl = new SimparerLoginLog();
        User user = (User) SessionManager.currentUserDetails();
        if (user.getCodiceFiscale() != null && !user.getCodiceFiscale().isEmpty()) {
            List<SimparerLoginLog.UsrUser> l = sl.findUtenteByCf(user.getCodiceFiscale());
            if (l.size() == 1) {
                SimparerLoginLog.UsrUser us = l.iterator().next();
                user.setUtenteDaAssociare(false);
                user.setUsername(us.getUsername());
                user.setIdUtente(us.getIdUser());
                resolution = (RedirectResolution) doLogin();
                resolution.addParameter("msgUtenteRicondotto",
                        "L'utente loggato e' stato ricondotto con successo all'utenza Parer.");
                return resolution;
            }
        }
        /* Per sicurezza se qualcuno forza l'accesso con la URL senza provenire da IAM lo butto fuori! */
        log.error("Chiamata al metodo beckFromAssociation non autorizzata! Effettuo il logout forzato!");
        resolution = new RedirectResolution(Logout.class);
        return resolution;
    }

    // MEV#23905 - Associazione utente SPID con anagrafica utenti
    private void gestisciUtenteDaAssociare(User utente) throws AuthenticationServiceException {
        HttpSession session = getContext().getRequest().getSession();
        String username = "NON_PRESENTE";
        /*
         * MEV#22913 - Logging accessi SPID non autorizzati In caso di utente SPID lo username non c'è ancora perché
         * deve essere ancora associato Quindi si prende il suo codice fiscale se presente, altrimenti una stringa fissa
         * come username
         */
        if (utente.getCodiceFiscale() != null && !utente.getCodiceFiscale().isEmpty()) {
            username = utente.getCodiceFiscale().toUpperCase();
        }

        /* MEV#22913 - Logging accessi SPID non autorizzati */
        String ipClient = (String) session.getAttribute(Constants.SESS_PARAM_WEB_CLIENT_IP_ADDRESS);
        String serverName = (String) session.getAttribute(Constants.SESS_PARAM_WEB_SERVER_ADDRESS);
        SimparerLoginLog sl = new SimparerLoginLog();
        sl.insertEventoLoginUser(username, ipClient, serverName, new Date(), "BAD_CF",
                "VERSO - " + DS_EVENTO_BAD_CF_SPID, utente.getCognome(), utente.getNome(), utente.getCodiceFiscale(),
                utente.getExternalId(), utente.getEmail());

        ParConfigurazioneDAO configDao = new ParConfigurazioneDAO();
        String salt = Base64.encodeBase64URLSafeString(PwdUtil.generateSalt());
        byte[] cfCriptato = EncryptionUtil.aesCrypt(utente.getCodiceFiscale(), EncryptionUtil.Aes.BIT_256);
        String f = Base64.encodeBase64URLSafeString(cfCriptato);
        byte[] cogCriptato = EncryptionUtil.aesCrypt(utente.getCognome(), EncryptionUtil.Aes.BIT_256);
        String c = Base64.encodeBase64URLSafeString(cogCriptato);
        byte[] nomeCriptato = EncryptionUtil.aesCrypt(utente.getNome(), EncryptionUtil.Aes.BIT_256);
        String n = Base64.encodeBase64URLSafeString(nomeCriptato);
        try {
            String urlIam = configDao.retrieveByChiave("URL_ASSOCIAZIONE_UTENTE_CF", getConnection());
            String retURL = configDao.retrieveByChiave("URL_BACK_ASSOCIAZIONE_UTENTE_CF", getConnection());
            String hmac = EncryptionUtil.getHMAC(retURL + ":" + utente.getCodiceFiscale() + ":" + salt);
            getContext().getResponse().sendRedirect(urlIam + "?r=" + Base64.encodeBase64URLSafeString(retURL.getBytes())
                    + "&h=" + hmac + "&s=" + salt + "&f=" + f + "&c=" + c + "&n=" + n);
        } catch (IOException | EMFError | SQLException ex) {
            log.error("Errore nella redirect verso IAM", ex);
            throw new AuthenticationServiceException("Errore nella gestione della redirect verso Iam", ex);
        }

    }

    public ClientUser getAndSetUserInSession(HttpSession httpSession) {
        User user = (User) SessionManager.currentUserDetails();
        ClientUser utente = null;
        if (user != null) {
            // Il controllo sull'authorized lo fa solo se l'utente non è da associare. Se è uno SPID bypassa questo
            // controllo
            if (!user.isUtenteDaAssociare() && !isAutorized(user)) {
                return null;
            }
            utente = new ClientUser(user);

            HttpServletRequest request = getContext().getRequest();
            String ipVers = request.getHeader("X-FORWARDED-FOR");
            if (ipVers == null || ipVers.isEmpty()) {
                ipVers = request.getRemoteAddr();
            }
            log.debug("Indirizzo da cui l'utente si connette: " + ipVers);
            String serverName = new AppServerInstance().getAddress();
            log.debug("Indirizzo del server che riceve la connessione: " + serverName);
            if (!user.isUtenteDaAssociare()) {
                loginLog.writeLogEvento(user, ipVers, serverName, SimparerLoginLog.TipiEvento.LOGIN);
            }
            SessionManager.setUser(httpSession, utente);
            httpSession.setAttribute(Constants.SESS_PARAM_WEB_CLIENT_IP_ADDRESS, ipVers);
            httpSession.setAttribute(Constants.SESS_PARAM_WEB_SERVER_ADDRESS, serverName);
        }
        return utente;
    }

    /**
     * Metodo per la verifica dell'autorizzazione ad eseguire l'applicazione corrente Il test deve essere del tipo:
     *
     *
     * @param utente
     *
     * @return
     */
    public boolean isAutorized(User utente) {
        boolean result = authenticator.isAuthorized(utente.getUsername());
        return result;

    }

    private String getAppName() {
        return authenticator.getAppName();
    }

}

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
import it.eng.parer.simparer.security.ClientUser;
import it.eng.spagoLite.SessionManager;
import java.sql.Connection;
import java.sql.SQLException;

import net.datasiel.simpaweb.common.Constants;
import net.datasiel.simpaweb.db.dao.VUsrIAMUserDAO;
import net.sourceforge.stripes.action.DefaultHandler;
import net.sourceforge.stripes.action.ForwardResolution;
import net.sourceforge.stripes.action.RedirectResolution;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.action.UrlBinding;
import net.sourceforge.stripes.integration.spring.SpringBean;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.security.web.csrf.CsrfTokenRepository;

@UrlBinding("/pro/password/{idStrut}")
public class GestionePassword extends HomePrivata {
    private String passwordVersatore = null;
    private String azione = null;
    private Integer comunica = null;
    @SpringBean
    private CsrfTokenRepository tokenRepos;

    public GestionePassword() {
        // TODO Auto-generated constructor stub
    }

    @Override
    @DefaultHandler
    public Resolution executeDefault() throws NumberFormatException, SQLException {

        setTitoloPagina("Dati Versatore");
        context.getRequest().getSession().setAttribute(Constants.PASSWORD_ATTR, "true");
        return handleRedirect();
        // return new ForwardResolution("/pages/datiVersatore.jsp");
    }

    private Resolution handleRedirect() {
        Resolution result = null;
        RedirectResolution redirectResolution;
        if (StringUtils.isNotBlank(azione) && azione.contains("gestione")) {
            // ForwardResolution resolution = new ForwardResolution(
            // HomePrivata.class);
            redirectResolution = new RedirectResolution("/pro/strut/" + idStrut);
            // resolution.addParameter("idStrut", idStrut);//pro/strut/
            result = redirectResolution;
        } else {

            String redirectUrl = "/pro/editud/" + idrecord + "?daHome=1&verifica=";
            if (StringUtils.isNotBlank(azione) && azione.contains("versa")) {
                redirectUrl = "/pro/editud/" + idrecord + "?daHome=1&versa=";
            }
            redirectResolution = new RedirectResolution(redirectUrl);
            redirectResolution.addParameter("idrecord", idrecord);
            redirectResolution.addParameter("idStrut", idStrut);
            if (comunica != null) {
                redirectResolution.addParameter("comunica", comunica);
            }
            // Ricarico il csrfToken in quanto con redirect lo perderei
            CsrfToken csrfToken = tokenRepos.loadToken(getContext().getRequest());
            redirectResolution.addParameter(csrfToken.getParameterName(), csrfToken.getToken());
            result = redirectResolution;
        }
        return result;
    }

    public Resolution doConfermaPassword() {
        context.getRequest().getSession().removeAttribute(Constants.PASSWORD_ATTR);

        Resolution result = handleRedirect();
        /*
         * Resolution result = null; RedirectResolution redirectResolution; if (StringUtils.isNotBlank(azione) &&
         * azione.contains("gestione")) { // ForwardResolution resolution = new ForwardResolution( //
         * HomePrivata.class); redirectResolution = new RedirectResolution("/pro/strut/" + idStrut); //
         * resolution.addParameter("idStrut", idStrut);//pro/strut/ result = redirectResolution; } else {
         * 
         * String redirectUrl = "/pro/editud/" + idrecord + "?daHome=1&verifica="; if (StringUtils.isNotBlank(azione) &&
         * azione.contains("versa")) { redirectUrl = "/pro/editud/" + idrecord + "?daHome=1&versa="; }
         * redirectResolution = new RedirectResolution(redirectUrl); redirectResolution.addParameter("idrecord",
         * idrecord); redirectResolution.addParameter("idStrut", idStrut); if (comunica != null) {
         * redirectResolution.addParameter("comunica", comunica); } // Ricarico il csrfToken in quanto con redirect lo
         * perderei CsrfToken csrfToken = tokenRepos.loadToken(getContext().getRequest());
         * redirectResolution.addParameter(csrfToken.getParameterName(), csrfToken.getToken()); result =
         * redirectResolution; }
         */

        if (passwordVersatore == null || "".equals(passwordVersatore)) {
            SessionMessages.addErrorMessage("Inserire la password di versatore");
            ForwardResolution resolution = new ForwardResolution("/pages/datiVersatore.jsp");
            resolution.addParameter("idrecord", idrecord);
            resolution.addParameter("idStrut", idStrut);
            resolution.addParameter("azione", azione);
            if (comunica != null) {
                resolution.addParameter("comunica", comunica);

            }
            result = resolution;

        } else {

            ClientUser user = (ClientUser) SessionManager.getUser(context.getRequest().getSession());
            try {
                // recupero cdPwd da IAMUSER
                Connection con = getConnection();
                VUsrIAMUserDAO viamUsr = new VUsrIAMUserDAO();
                String password = viamUsr.isUserAuthenticated(user.getUsername(), passwordVersatore, con);
                if (StringUtils.isNotBlank(password)) {
                    user.setPassword(password.getBytes());
                    context.getRequest().getSession().setAttribute(Constants.PASSWORD_ATTR, "true");
                } else {
                    SessionMessages.addErrorMessage("Password errata!!");
                    ForwardResolution resolution = new ForwardResolution("/pages/datiVersatore.jsp");
                    resolution.addParameter("idrecord", idrecord);
                    resolution.addParameter("idStrut", idStrut);
                    resolution.addParameter("azione", azione);
                    if (comunica != null) {
                        resolution.addParameter("comunica", comunica);

                    }
                    result = resolution;

                }
                // PwdUtil.encodePBKDF2Password(PwdUtil.decodeUFT8Base64String(salt),
                // password).equals(dbPwd)
            } catch (Exception e) {
                log.error("Errore durante la cifratura della password ", e);
                SessionMessages.addErrorMessage("Errore durante la cifratura della password ");
            }

        }

        return result;
    }

    public Resolution doEliminaPassword() {
        ForwardResolution resolution = new ForwardResolution(HomePrivata.class);
        if (idStrut != null && idStrut > 0) {
            resolution.addParameter("idStrut", idStrut);
        }

        ClientUser user = (ClientUser) SessionManager.getUser(context.getRequest().getSession());
        user.setPassword(null);
        context.getRequest().getSession().removeAttribute(Constants.PASSWORD_ATTR);
        return resolution;
    }

    public String getIdutente() {
        return idutente;
    }

    public void setIdutente(String idutente) {
        this.idutente = idutente;
    }

    public Long getIdStrut() {
        return idStrut;
    }

    public void setIdStrut(Long idStrut) {
        this.idStrut = idStrut;
    }

    public String getPasswordVersatore() {
        return passwordVersatore;
    }

    public void setPasswordVersatore(String passwordVersatore) {
        this.passwordVersatore = passwordVersatore;
    }

    public String getAzione() {
        return azione;
    }

    public void setAzione(String azione) {
        this.azione = azione;
    }

    public Integer getComunica() {
        return comunica;
    }

    public void setComunica(Integer comunicazione) {
        this.comunica = comunicazione;
    }

}

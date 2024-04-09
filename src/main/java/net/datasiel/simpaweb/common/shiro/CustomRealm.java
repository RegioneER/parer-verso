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

/**
 * 
 */
package net.datasiel.simpaweb.common.shiro;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import it.eng.parer.simparer.security.ClientUser;
import it.eng.spagoLite.SessionManager;
import net.datasiel.simpaweb.beans.StrutturaInfo;
import net.datasiel.simpaweb.common.UtenteStrutture;
import net.datasiel.simpaweb.db.EnumStatoUD;
import net.datasiel.simpaweb.db.dao.VUsrIAMUserDAO;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.simpaweb.db.pojo.VUsrVRicUser;
import net.datasiel.simpaweb.db.vo.ParUnitadocVO;
import net.datasiel.webapp.AuthorizationException;

/**
 * @author reisoli
 * 
 */
public class CustomRealm {
    private static final Logger log = LoggerFactory.getLogger(CustomRealm.class);

    public static final String PERMESSO_PER_STRUTTURA_LEGGI_D = "struttura:leggi:%d";
    public static final String PERMESSO_PER_STRUTTURA_NUOVAUD_D = "struttura:nuovaud:%d";
    public static final String PERMESSO_PER_UD_LEGGI_D = "ud:leggi:%d";
    public static final Pattern UD_LEGGI_PATTERN = Pattern.compile("ud:leggi:(\\d+)");
    DataSource dataSource;
    public static final String CUSTOM_FORBIDDEN_MESSAGE = "Dati non accessibili dall'utente.";

    public UtenteStrutture retrieveUtenteStrutture(String strCodFiscale, Connection con, boolean isUtenteSpid)
            throws SQLException {

        VUsrVRicUser utente = null;
        List<StrutturaInfo> strutture = new ArrayList<StrutturaInfo>();

        StringBuffer query = new StringBuffer();
        query.append("select * ");
        query.append("from V_USR_IAM ");
        query.append("where upper(NM_USERID) = upper(?) ");
        /*
         * Nel caso di utente entrato con lo SPID bypassa il controllo sul FLAG_ATTIVO e quindi anche se un utente non è
         * attivo entra o stesso, altrimenti si comporta alla vecchia maniera controlando che l'utente sia attivo.
         */
        if (!isUtenteSpid) {
            query.append("and FL_ATTIVO = '1' ");
        }
        query.append("order by upper(NM_AMBIENTE), upper(NM_ENTE), upper(NM_STRUT)");
        log.info("username = " + strCodFiscale);

        PreparedStatement pst = con.prepareStatement(query.toString());
        ResultSet r = null;
        pst.setString(1, strCodFiscale.toUpperCase());
        try {
            log.debug("{}", query);
            r = pst.executeQuery();
            VUsrIAMUserDAO dao = new VUsrIAMUserDAO();
            log.info("utente trovato. Recupero le strutture associate ");
            int i = 0;
            while (r.next()) {
                utente = new VUsrVRicUser();
                dao.getFromResultSet(utente, r);
                Long idStrut = null;
                if (r.getObject("ID_STRUT") != null) {
                    idStrut = r.getLong("ID_STRUT");
                }
                StrutturaInfo struttura = new StrutturaInfo(
                        utente.getNmAmbiente() + " - " + utente.getNmEnte() + " - " + utente.getNmStrut(), idStrut);
                strutture.add(struttura);
                i++;
            }
            log.info("Recuperate " + i + " strutture associate all'utente");
            return new UtenteStrutture(utente, strutture);
        } catch (Exception e) {
            log.error("Errore nel recupero delle strutture associate all'utente.", e);
        } finally {
            if (r != null)
                r.close();
            if (pst != null)
                pst.close();
        }
        return null;
    }

    public static boolean isPermitted(String permission, Long idStrut, HttpSession session, Connection con)
            throws AuthorizationException {
        ClientUser u = (ClientUser) SessionManager.getUser(session);
        if (u == null) {
            throw new AuthorizationException("Utente non autorizzato");
        }
        if (u.getStringPermissions().contains(permission)) {
            log.debug(String.format("Permesso %s accordato a %s", permission, u.getIdUtente()));
            return true;
        }
        Matcher matcher = UD_LEGGI_PATTERN.matcher(permission);
        if (!matcher.matches()) {
            log.error(String.format("Pattern del permesso %s non gestito: Permesso negato", permission));
            return false;
        }
        String strIdUnitaDoc = matcher.group(1);
        Long idUnitaDoc = Long.parseLong(strIdUnitaDoc);
        Long idUtente = u.getIdUtente();
        try {
            log.debug(String.format("Utente %d - struttura %d chiede permesso %s", idUtente, idStrut, permission));
            // in caso di UD già versata l'accesso è consentito anche agli altri utenti della stessa struttura
            boolean udVisibileUtentiStruttura = false;
            ParUnitadocVO parUnitadocVO = new ParUnitadocVO();
            ParUnitadoc ud = parUnitadocVO.retrieveByKey(idUnitaDoc, con);
            if (ud.getStato() == EnumStatoUD.VERSATA.getValore()) {
                String strutPermission = String.format(CustomRealm.PERMESSO_PER_STRUTTURA_LEGGI_D, ud.getIdStrut());
                if (u.getStringPermissions().contains(strutPermission)) {
                    udVisibileUtentiStruttura = true;
                }
            }
            boolean possiedeUd = parUnitadocVO.userPossiedeUd(idUtente, idUnitaDoc, con);
            return possiedeUd || udVisibileUtentiStruttura;
        } catch (SQLException e) {
            throw new Error(ExceptionUtils.getRootCauseMessage(e), e);
        }
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    /**
     * Contiene le informazioni dell'utente e delle sue strutture di appartenenza.
     * 
     * @author reisoli
     * 
     */

}

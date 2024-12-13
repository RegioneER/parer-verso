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

package it.eng.parer.simparer.security;

import it.eng.parer.simparer.common.SpringContext;
import it.eng.spagoLite.security.IUser;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.sql.DataSource;
import net.datasiel.simpaweb.db.dao.DbUtil;
import net.datasiel.simpaweb.db.pojo.SLLogEventoLoginUser;
import net.datasiel.simpaweb.db.pojo.SLLogLoginUser;
import net.datasiel.simpaweb.db.vo.SIAplApplicVO;
import net.datasiel.simpaweb.db.vo.SLLogEventoLoginUserVO;
import net.datasiel.simpaweb.db.vo.SLLogLoginUserVO;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author fioravanti_f
 */
public class SimparerLoginLog {

    public final Logger logger = LoggerFactory.getLogger(SimparerLoginLog.class);

    public enum TipiEvento {
        LOGIN, LOGOUT
    }

    public void writeLogEvento(IUser<?> user, String indIpClient, String localServerName, TipiEvento tipoEvento) {
        long idApplic;
        Connection conn = null;
        DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);

        if (datasource != null) {
            try {
                conn = datasource.getConnection();
                idApplic = SIAplApplicVO.getIdApplicazione("SACER_VERSO", conn);
                long idDaInserire = DbUtil.getSequenceValue("sacer_log.slog_login_user", conn);
                SLLogLoginUser tmpLoginUser = new SLLogLoginUser();
                tmpLoginUser.setIdLoginUser(idDaInserire);
                tmpLoginUser.setIdApplic(idApplic);
                tmpLoginUser.setNmUserid(user.getUsername());
                tmpLoginUser.setCdIndIpClient(indIpClient);
                tmpLoginUser.setCdIndServer(localServerName);
                tmpLoginUser.setTipoEvento(tipoEvento.name());
                // Modifica per lo SPID
                if (user.getUserType() != null) {
                    tmpLoginUser.setTipoUtenteAuth(user.getUserType().name());
                    tmpLoginUser.setCdIdEsterno(user.getExternalId());
                }
                // dtEvento viene calcolato in fase di inserimento
                // dtVirtualEvento è una colonna virtuale del DB, non deve *mai* essere scritta
                SLLogLoginUserVO tmpVO = new SLLogLoginUserVO();
                tmpVO.insertPrepared(tmpLoginUser, conn);
            } catch (SQLException e) {
                logger.error("Errore nella registrazione dell'evento di log: ", e);
                throw new RuntimeException("impossibile registrare l'evento di login/logout: " + e.getMessage());
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }
    }

    public void insertEventoLoginUser(String nmUserid, String cdIndIpClient, String nomeServer, Date dtEvento,
            String tipoEvento, String dsEvento, String cognomeUser, String nomeUser, String cfUser, String cdIdEsterno,
            String emailUser) {
        Connection conn = null;
        DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);
        if (datasource != null) {
            try {
                conn = datasource.getConnection();
                long idDaInserire = DbUtil.getSequenceValue("sacer_log.slog_evento_login_user", conn);
                SLLogEventoLoginUser logEventoLoginUser = new SLLogEventoLoginUser();
                logEventoLoginUser.setIdEventoLoginUser(idDaInserire);
                logEventoLoginUser.setNmUserid(nmUserid);
                logEventoLoginUser.setCdIndIpClient(cdIndIpClient);
                logEventoLoginUser.setCdIndServer(nomeServer);
                logEventoLoginUser.setDtEvento(dtEvento);
                logEventoLoginUser.setTipoEvento(tipoEvento);
                logEventoLoginUser.setDsEvento(dsEvento);
                logEventoLoginUser.setNmCognomeUser(cognomeUser);
                logEventoLoginUser.setNmNomeUser(nomeUser);
                logEventoLoginUser.setCdFiscUser(cfUser);
                logEventoLoginUser.setCdIDEsterno(cdIdEsterno);
                logEventoLoginUser.setDsEmailUser(emailUser);
                if (cdIdEsterno != null) {
                    logEventoLoginUser.setCdIDEsterno(cdIdEsterno);
                }
                SLLogEventoLoginUserVO tmpVO = new SLLogEventoLoginUserVO();
                tmpVO.insertPrepared(logEventoLoginUser, conn);
            } catch (SQLException e) {
                logger.error("Errore nella registrazione dell'evento di log: ", e);
                throw new RuntimeException("impossibile registrare l'evento di login/logout: " + e.getMessage());
            } finally {
                if (conn != null) {
                    try {
                        conn.close();
                    } catch (SQLException e) {
                    }
                }
            }
        }

    }

    /* Estrae utenti dalla USR_USER per codice fiscale */
    public List<UsrUser> findUtenteByCf(String cf) {
        String sql = "SELECT ID_USER_IAM, NM_USERID, DT_SCAD_PSW, CD_FISC FROM SACER_IAM.USR_USER WHERE (CD_FISC = ? OR CD_FISC = ?) AND FL_ATTIVO='1'";
        List<UsrUser> users = new ArrayList<>();
        DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);
        if (datasource != null) {
            try (Connection con = datasource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, cf.toUpperCase());
                ps.setString(2, cf.toLowerCase());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        users.add(new UsrUser(rs.getLong("ID_USER_IAM"), rs.getString("NM_USERID"),
                                rs.getDate("DT_SCAD_PSW"), rs.getString("CD_FISC")));
                    }
                }
            } catch (SQLException e) {
                logger.error("Generic error", e);
            }
        }
        return users;
    }

    /* Estrae utenti dalla USR_USER per username */
    public UsrUser findUtenteByUsername(String username) {
        String sql = "SELECT ID_USER_IAM, NM_USERID, DT_SCAD_PSW, CD_FISC FROM SACER_IAM.USR_USER WHERE NM_USERID = ?";
        List<UsrUser> users = new ArrayList<>();
        DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);
        if (datasource != null) {
            try (Connection con = datasource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        users.add(new UsrUser(rs.getLong("ID_USER_IAM"), rs.getString("NM_USERID"),
                                rs.getDate("DT_SCAD_PSW"), rs.getString("CD_FISC")));
                    }
                }
            } catch (SQLException e) {
                logger.error("Generic error", e);
            }
        }
        if (users.size() > 0) {
            return users.get(0);
        } else {
            return null;
        }
    }

    /*
     * Estrae utenti dalla USR_USER per username case insensitive
     *
     * Introdotto per l'itegrazione con SPID Puglia dove a fronte del codice fiscale arrivato da SPID andiamo a cercare
     * sulla usruser un utente avente come username il codice fiscale ignorando il case.
     */
    public List<UsrUser> findUtentiPerUsernameCaseInsensitive(String username) {
        String sql = "SELECT ID_USER_IAM, NM_USERID, DT_SCAD_PSW, CD_FISC FROM SACER_IAM.USR_USER WHERE lower(NM_USERID) = ? AND FL_ATTIVO='1'";
        List<UsrUser> users = new ArrayList<>();
        DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);
        if (datasource != null) {
            try (Connection con = datasource.getConnection(); PreparedStatement ps = con.prepareStatement(sql)) {
                ps.setString(1, username.toLowerCase());
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        users.add(new UsrUser(rs.getLong("ID_USER_IAM"), rs.getString("NM_USERID"),
                                rs.getDate("DT_SCAD_PSW"), rs.getString("CD_FISC")));
                    }
                }
            } catch (SQLException e) {
                logger.error("Generic error", e);
            }
        }
        return users;
    }

    public class UsrUser {

        private long idUser;
        private String username;
        private Date dtScadPwd;
        private String codiceFiscale;

        public UsrUser(long idUser, String username, Date dtScadPwd, String codiceFiscale) {
            this.idUser = idUser;
            this.username = username;
            this.dtScadPwd = dtScadPwd;
            this.codiceFiscale = codiceFiscale;
        }

        public long getIdUser() {
            return idUser;
        }

        public void setIdUser(long idUser) {
            this.idUser = idUser;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public Date getDtScadPwd() {
            return dtScadPwd;
        }

        public void setDtScadPwd(Date dtScadPwd) {
            this.dtScadPwd = dtScadPwd;
        }

        public String getCodiceFiscale() {
            return codiceFiscale;
        }

        public void setCodiceFiscale(String codiceFiscale) {
            this.codiceFiscale = codiceFiscale;
        }

    }

}

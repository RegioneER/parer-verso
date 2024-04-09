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

import java.util.ArrayList;
import java.util.List;

import it.eng.parer.simparer.security.SimparerLoginLog.UsrUser;
import it.eng.spagoLite.security.saml.SliteSAMLUserDetail;

/**
 *
 * @author MIacolucci
 */
public class VersoSAMLUserDetail extends SliteSAMLUserDetail {

    @Override
    protected List<UtenteDb> findUtentiPerCodiceFiscale(String codiceFiscale) {
        SimparerLoginLog sl = new SimparerLoginLog();
        ArrayList<UtenteDb> al = new ArrayList<>();
        List<UsrUser> l = sl.findUtenteByCf(codiceFiscale);
        for (UsrUser usrUser : l) {
            UtenteDb u = new UtenteDb();
            u.setId(usrUser.getIdUser());
            u.setCodiceFiscale(usrUser.getCodiceFiscale());
            u.setDataScadenzaPassword(u.getDataScadenzaPassword());
            u.setUsername(usrUser.getUsername());
            al.add(u);
        }
        return al;
    }

    @Override
    protected UtenteDb getUtentePerUsername(String username) {
        SimparerLoginLog sl = new SimparerLoginLog();
        UsrUser usrUser = sl.findUtenteByUsername(username);
        UtenteDb u = new UtenteDb();
        u.setId(usrUser.getIdUser());
        u.setCodiceFiscale(usrUser.getCodiceFiscale());
        u.setDataScadenzaPassword(u.getDataScadenzaPassword());
        u.setUsername(usrUser.getUsername());
        return u;
    }

    @Override
    protected List<UtenteDb> findUtentiPerUsernameCaseInsensitive(String username) {
        SimparerLoginLog sl = new SimparerLoginLog();
        ArrayList<UtenteDb> al = new ArrayList<>();
        List<UsrUser> l = sl.findUtentiPerUsernameCaseInsensitive(username);
        for (UsrUser usrUser : l) {
            UtenteDb u = new UtenteDb();
            u.setId(usrUser.getIdUser());
            u.setCodiceFiscale(usrUser.getCodiceFiscale());
            u.setDataScadenzaPassword(u.getDataScadenzaPassword());
            u.setUsername(usrUser.getUsername());
            al.add(u);
        }
        return al;
    }

}

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
package it.eng.parer.simparer.security;

import java.util.Set;

import net.datasiel.simpaweb.common.UtenteStrutture;
import it.eng.spagoLite.security.User;

/**
 * @author Parucci_M
 *
 */
public class ClientUser extends User {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private UtenteStrutture utenteStrutture;
    private byte[] password = null;
    private String encodedPassword = null;
    private Set<String> stringPermissions = null;

    public UtenteStrutture getUtenteStrutture() {
        return utenteStrutture;
    }

    public void setUtenteStrutture(UtenteStrutture utenteStrutture) {
        this.utenteStrutture = utenteStrutture;
    }

    /**
     * 
     */
    public ClientUser() {
        // TODO Auto-generated constructor stub
    }

    public ClientUser(User user) {
        this.setApplicazioni(user.getApplicazioni());
        this.setAttivo(user.isAttivo());
        this.setCognome(user.getCognome());
        this.setConfigurazione(user.getConfigurazione());
        this.setIdApplicazione(user.getIdApplicazione());
        this.setIdOrganizzazioneFoglia(user.getIdOrganizzazioneFoglia());
        this.setIdUtente(user.getIdUtente());
        this.setMenu(user.getMenu());
        this.setNome(user.getNome());
        this.setNomeStruttura(user.getNomeStruttura());
        this.setOrganizzazioneMap(user.getOrganizzazioneMap());
        this.setProfile(user.getProfile());
        this.setScadenzaPwd(user.getScadenzaPwd());
        this.setUsername(user.getUsername());
        this.setExternalId(user.getExternalId());
        this.setUserType(user.getUserType());
        this.setUtenteDaAssociare(user.isUtenteDaAssociare());
        this.setCodiceFiscale(user.getCodiceFiscale());
        this.setEmail(user.getEmail());
    }

    public byte[] getPassword() {
        return password;
    }

    public void setPassword(byte[] password) {
        this.password = password;
    }

    public String getEncodedPassword() {
        return encodedPassword;
    }

    public void setEncodedPassword(String encodedPassword) {
        this.encodedPassword = encodedPassword;
    }

    public Set<String> getStringPermissions() {
        return stringPermissions;
    }

    public void setStringPermissions(Set<String> stringPermissions) {
        this.stringPermissions = stringPermissions;
    }

}

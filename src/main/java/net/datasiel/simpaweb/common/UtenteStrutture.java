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

import java.util.List;

import net.datasiel.simpaweb.beans.StrutturaInfo;
import net.datasiel.simpaweb.db.pojo.VUsrVRicUser;

public class UtenteStrutture {

    /**
     * Dati dell'utente.
     */
    VUsrVRicUser utente;
    /**
     * Elenco delle strutture a cui l'utente appartiene.
     */
    List<StrutturaInfo> strutture;

    public UtenteStrutture(VUsrVRicUser utente, List<StrutturaInfo> strutture) {
        super();
        this.utente = utente;
        this.strutture = strutture;
    }

    public VUsrVRicUser getUtente() {
        return utente;
    }

    public void setUtente(VUsrVRicUser utente) {
        this.utente = utente;
    }

    public List<StrutturaInfo> getStrutture() {
        return strutture;
    }

    public void setStrutture(List<StrutturaInfo> strutture) {
        this.strutture = strutture;
    }

}

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

package net.datasiel.simpaweb.beans;

import java.io.Serializable;

public class StrutturaInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    public StrutturaInfo(String nomeStruttura, Long idStrut) {
        super();
        this.nomeStruttura = nomeStruttura;
        this.idStrut = idStrut;
    }

    /**
     * Concatenazione del nome ente e del nome struttura.
     */
    final String nomeStruttura;
    /**
     * Identificativo della struttura.
     */
    final Long idStrut;

    public String getNomeStruttura() {
        return nomeStruttura;
    }

    public Long getIdStrut() {
        return idStrut;
    }
}

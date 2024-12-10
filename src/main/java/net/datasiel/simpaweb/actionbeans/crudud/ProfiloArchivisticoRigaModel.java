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

package net.datasiel.simpaweb.actionbeans.crudud;

import java.util.List;

import net.datasiel.simpaweb.db.pojo.ParFascicolo;
import net.datasiel.webapp.crud.RigaModel;

/**
 *
 * @author reisoli
 *
 */
public class ProfiloArchivisticoRigaModel implements RigaModel {
    public final ParFascicolo riga;

    public ProfiloArchivisticoRigaModel(ParFascicolo riga) {
        super();
        this.riga = riga;
    }

    @Override
    public Object getRiga() {
        return riga;
    }

    @Override
    public List<Object> getDettagli1() {
        // Non ci sono dettagli
        return null;
    }

    @Override
    public List<Object> getDettagli2() {
        // Non ci sono dettagli
        return null;
    }

}

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
package net.datasiel.simpaweb.actionbeans.crudud;

import java.util.ArrayList;
import java.util.List;

import net.datasiel.simpaweb.db.pojo.ParFascicolo;
import net.datasiel.simpaweb.db.pojo.ParUnitadoc;
import net.datasiel.webapp.crud.CrudModel;
import net.datasiel.webapp.crud.RigaModel;

/**
 * Sarà composto così: intestazioneRO: intestazione (cambiando solo il tipo da FormUnitaDoc a quello vero
 * intestazioneRW: oggetto corrispondente a fascicolo principale (ParFascicolo) righe: lista di fascicoli secondari
 * (List<RigaModel>).
 * 
 * @author reisoli
 *
 */
public class ProfiloArchivisticoCrudModel implements CrudModel {
    public ParUnitadoc intestazione;
    public ParFascicolo fascicoloPrincipale;
    public final List<RigaModel> righe = new ArrayList<RigaModel>();

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.CrudModel#getIntestazioneRO()
     */
    @Override
    public Object getIntestazioneRO() {
        return intestazione;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.CrudModel#getIntestazioneRW()
     */
    @Override
    public Object getIntestazioneRW() {
        return fascicoloPrincipale;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.CrudModel#getRighe()
     */
    @Override
    public List<RigaModel> getRighe() {
        return righe;
    }

}

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

import net.datasiel.simpaweb.db.pojo.ParCollegamento;
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.webapp.crud.RigaModel;

/**
 * @author saba
 *
 */
public class CollegamentiRigaModel implements RigaModel {
    public final ParCollegamento riga;

    public CollegamentiRigaModel(ParCollegamento riga, List<ParComponente> fileAllegati, List<String> codiciAllegati) {
        super();
        this.riga = riga;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.RigaModel#getRiga()
     */
    @Override
    public Object getRiga() {
        return riga;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.RigaModel#getDettagli1()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List getDettagli1() {
        // Non ci sono dettagli 1
        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @see net.datasiel.webapp.crud.RigaModel#getDettagli2()
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public List getDettagli2() {
        // Non ci sono dettagli 2
        return null;
    }

    public List<String> getListaCodiciAllegati() {
        return null;
    }

}

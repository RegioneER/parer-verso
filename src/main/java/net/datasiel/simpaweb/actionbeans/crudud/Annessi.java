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

import net.datasiel.simpaweb.beans.EnumEntitaDatiSpecifici;
import net.datasiel.webapp.InfoToolbar;
import net.sourceforge.stripes.action.UrlBinding;

/**
 * @author reisoli
 *
 */
@UrlBinding("/pro/annessi/{idrecord}")
@InfoToolbar(titolo = "Annessi", accelerator = "N", breadcrumbs = "Home")
public class Annessi extends Documenti {

    public Annessi() {
        super();
        this.pgm = getClass().getSimpleName();
    }

    @Override
    protected String getTipoTab() {
        return "ANNE";
    }

    @Override
    public int getMaxRighe() {
        return 0;
    }

    @Override
    public String getTitoloFieldsetRiga() {
        return "Annesso";
    }

    @Override
    protected EnumEntitaDatiSpecifici getEntitaDatiSpecifici() {
        // TODO Auto-generated method stub
        return EnumEntitaDatiSpecifici.DOC;
    }

    @Override
    public String getTitoloPagina() {
        // TODO Auto-generated method stub
        return "Verso: Annessi";
    }

}

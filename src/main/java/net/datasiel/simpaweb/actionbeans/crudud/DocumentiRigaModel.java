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

import java.util.List;

import org.apache.commons.lang.StringUtils;

import net.datasiel.simpaweb.beans.DatoSpecifico;
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.datasiel.simpaweb.db.pojo.ParDocumento;
import net.datasiel.webapp.crud.RigaModel;

/**
 * @author reisoli
 *
 */
public class DocumentiRigaModel implements RigaModel {
    public final ParDocumento riga;
    public final List<ParComponente> fileAllegati;
    public final List<String> listaCodiciAllegati;
    private final List<DatoSpecifico> listaDatiSpecifici;
    private boolean isPrincipale = false;

    public DocumentiRigaModel(ParDocumento riga, List<ParComponente> fileAllegati, List<String> codiciAllegati,
            List<DatoSpecifico> listaDatiSpecifici, boolean isPrincipale) {
        super();
        this.riga = riga;
        this.fileAllegati = fileAllegati;
        this.listaCodiciAllegati = codiciAllegati;
        this.listaDatiSpecifici = listaDatiSpecifici;
        this.isPrincipale = isPrincipale;
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
        return fileAllegati;
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
        if (isPrincipale && isListaDatiSpecificiValorizzata(listaDatiSpecifici))
            return listaDatiSpecifici;
        else
            return null;
    }

    public List<String> getListaCodiciAllegati() {
        return listaCodiciAllegati;
    }

    private boolean isListaDatiSpecificiValorizzata(List<DatoSpecifico> listaDatiSpecifici) {
        boolean result = false;
        for (DatoSpecifico datoSpecifico : listaDatiSpecifici) {
            if (!StringUtils.isBlank(datoSpecifico.getValore())) {
                result = true;
            }
        }
        return result;
    }
}

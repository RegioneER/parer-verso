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
package net.datasiel.simpaweb.elements;

import com.manydesigns.elements.Mode;
import com.manydesigns.elements.composites.Sequence;
import com.manydesigns.elements.forms.Form;
import com.manydesigns.elements.forms.FormBuilder;
import com.manydesigns.elements.options.DefaultSelectionProvider;

import net.datasiel.simpaweb.db.pojo.ParDocumento;

/**
 * @author reisoli
 *
 */
public class RigaDocumentoForm extends Sequence {
    public Form elfoHidden;
    public Form elfoDocumento;

    public static String[] listaCampiHidden = { "tipologia", "iddocumento" };
    public static String[] listaCampiForm = { "idTipoDoc", "profiloautoredoc", "profilodescrizionedoc" };
    public static String[] listaCampiFormPrinc = { "idTipoDoc", "profiloautoredoc", "profilodescrizionedoc",
            "cdVersioneXSD" };

    /**
     *
     */
    public RigaDocumentoForm(DefaultSelectionProvider selTipoDoc, DefaultSelectionProvider selCDVersione,
            DefaultSelectionProvider selTipoStrutDoc, String nomeFieldset, Mode mode, String prefix,
            boolean isPrincipale) {
        elfoHidden = new FormBuilder(ParDocumento.class).configFields(listaCampiHidden).configMode(Mode.HIDDEN)
                .configPrefix(prefix).build();
        if (isPrincipale)
            elfoDocumento = new FormBuilder(ParDocumento.class).configFields(listaCampiFormPrinc)
                    .configFieldSetNames(nomeFieldset).configMode(mode).configPrefix(prefix)
                    .configSelectionProvider(selTipoDoc, "idTipoDoc")
                    .configSelectionProvider(selCDVersione, "cdVersioneXSD").build();
        else
            elfoDocumento = new FormBuilder(ParDocumento.class).configFields(listaCampiForm)
                    .configFieldSetNames(nomeFieldset).configMode(mode).configPrefix(prefix)
                    .configSelectionProvider(selTipoDoc, "idTipoDoc").build();
    }

}

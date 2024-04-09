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

package net.datasiel.simpaweb.db;

/**
 * Elenca le possibiili tipologie di documenti con la relativa dicitura leggibile per le diverse ed il relativo valore
 * inserito in DB per individuare le tipologie documentarie ammesse.
 * 
 * @author reisoli
 *
 */
public enum EnumTipoDocumento {
    PRINC("documento principale", "PRINCIPALE"), ALLE("allegato", "ALLEGATO"), ANNE("annesso", "ANNESSO"),
    ANNO("annotazione", "ANNOTAZIONE");

    private EnumTipoDocumento(String valoreLeggibile, String valoreInDb) {
        this.valoreLeggibile = valoreLeggibile;
        this.valoreInDb = valoreInDb;
    }

    final String valoreLeggibile;

    /**
     * @return the valoreLeggibile
     */
    public String getValoreLeggibile() {
        return valoreLeggibile;
    }

    final String valoreInDb;

    /**
     * @return the valoreInDb
     */
    public String getValoreInDb() {
        return valoreInDb;
    }
}

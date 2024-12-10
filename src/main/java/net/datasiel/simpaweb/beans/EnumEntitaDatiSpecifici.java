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

/**
 * Elenco possibili valori per TI_ENTITA_SACER e relativo nome colonna in PAR_DATISPECIFICI.
 *
 * @author reisoli
 *
 */
public enum EnumEntitaDatiSpecifici {
    /*
     * ID_TIPO_UNITA_DOC ID_TIPO_DOC ID_TIPO_COMP_DOC
     */
    UNI_DOC("idunitadoc", "ID_TIPO_UNITA_DOC"), DOC("iddocumento", "ID_TIPO_DOC"),
    COMP("idcomponente", "ID_TIPO_COMP_DOC"), SUB_COMP("idcomponente", "ID_TIPO_COMP_DOC");

    final String columnNameForXsd;
    final String columnNameForAttrib;

    private EnumEntitaDatiSpecifici(String columnNameForXsd, String columnNameForAttrib) {
        this.columnNameForXsd = columnNameForXsd;
        this.columnNameForAttrib = columnNameForAttrib;
    }

    public String getColumnNameForXsd() {
        return columnNameForXsd;
    }

    public String getColumnNameForAttrib() {
        return columnNameForAttrib;
    }

}

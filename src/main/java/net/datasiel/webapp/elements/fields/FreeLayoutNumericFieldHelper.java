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
package net.datasiel.webapp.elements.fields;

import net.datasiel.elements.annotations.SingleValueSearch;

import com.manydesigns.elements.Mode;
import com.manydesigns.elements.fields.Field;
import com.manydesigns.elements.fields.NumericField;
import com.manydesigns.elements.fields.helpers.FieldHelper;
import com.manydesigns.elements.fields.search.SearchField;
import com.manydesigns.elements.reflection.ClassAccessor;
import com.manydesigns.elements.reflection.PropertyAccessor;
import com.manydesigns.elements.util.Util;

/**
 * @author reisoli
 * 
 */
public class FreeLayoutNumericFieldHelper implements FieldHelper {

    /*
     * (non-Javadoc)
     * 
     * @see com.manydesigns.elements.fields.helpers.FieldHelper#tryToInstantiateField
     * (com.manydesigns.elements.reflection.ClassAccessor, com.manydesigns.elements.reflection.PropertyAccessor,
     * com.manydesigns.elements.Mode, java.lang.String)
     */
    public Field tryToInstantiateField(ClassAccessor classAccessor, PropertyAccessor propertyAccessor, Mode mode,
            String prefix) {
        Class<?> type = propertyAccessor.getType();
        if (Util.isNumericType(type)) {
            return new NumericField(propertyAccessor, mode, prefix);
        }

        return null;
    }

    /*
     * (non-Javadoc)
     * 
     * @seecom.manydesigns.elements.fields.helpers.FieldHelper# tryToInstantiateSearchField
     * (com.manydesigns.elements.reflection.ClassAccessor, com.manydesigns.elements.reflection.PropertyAccessor,
     * java.lang.String)
     */
    public SearchField tryToInstantiateSearchField(ClassAccessor classAccessor, PropertyAccessor propertyAccessor,
            String prefix) {
        Class<?> type = propertyAccessor.getType();
        if (Util.isNumericType(type)) {
            if (propertyAccessor.isAnnotationPresent(SingleValueSearch.class)) {
                return new SingleValueNumericSearchField(propertyAccessor, prefix);
            } else {
                return new FreeLayoutRangeSearchField(propertyAccessor, prefix);
            }
        }
        return null;
    }

}

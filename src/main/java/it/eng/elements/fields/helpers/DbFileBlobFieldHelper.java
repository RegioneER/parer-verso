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

package it.eng.elements.fields.helpers;

import it.eng.elements.fields.BlobField;

import com.manydesigns.elements.Mode;
import com.manydesigns.elements.annotations.FileBlob;
import com.manydesigns.elements.fields.Field;
import com.manydesigns.elements.fields.helpers.FieldHelper;
import com.manydesigns.elements.fields.search.SearchField;
import com.manydesigns.elements.reflection.ClassAccessor;
import com.manydesigns.elements.reflection.PropertyAccessor;

public class DbFileBlobFieldHelper implements FieldHelper {
    public static final String copyright = "";

    public Field tryToInstantiateField(ClassAccessor classAccessor, PropertyAccessor propertyAccessor, Mode mode,
            String prefix) {
        if (String.class.isAssignableFrom(propertyAccessor.getType())
                && propertyAccessor.isAnnotationPresent(FileBlob.class)) {
            return new BlobField(propertyAccessor, mode, prefix);
        }
        return null;
    }

    public SearchField tryToInstantiateSearchField(ClassAccessor classAccessor, PropertyAccessor propertyAccessor,
            String prefix) {
        return null;
    }

}

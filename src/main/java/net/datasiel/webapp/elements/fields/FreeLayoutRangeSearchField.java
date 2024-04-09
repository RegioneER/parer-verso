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

package net.datasiel.webapp.elements.fields;

import org.apache.commons.lang.StringUtils;

import com.manydesigns.elements.fields.search.RangeSearchField;
import com.manydesigns.elements.reflection.PropertyAccessor;
import com.manydesigns.elements.xml.XhtmlBuffer;

public class FreeLayoutRangeSearchField extends RangeSearchField {

    public FreeLayoutRangeSearchField(PropertyAccessor accessor) {
        super(accessor);
        // TODO Auto-generated constructor stub
    }

    FreeLayoutRangeSearchField(PropertyAccessor accessor, String prefix) {
        super(accessor, prefix);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void rangeEndToXhtml(XhtmlBuffer xb, String id, String inputName, String stringValue, String label) {
        xb.openElement("span");
        xb.openElement("label");
        xb.addAttribute("for", id);
        xb.write(label);
        xb.closeElement("label");
        xb.writeInputText(id, inputName, stringValue, "text", null, null);
        xb.closeElement("span");
    }

    @Override
    public void toXhtml(XhtmlBuffer xb) {
        xb.openElement("fieldset");
        xb.writeLegend(StringUtils.capitalize(label), ATTR_NAME_HTML_CLASS);

        xb.openElement("div");
        xb.addAttribute("class", "range");

        rangeEndToXhtml(xb, minId, minInputName, minStringValue, getText("elements.search.range.from"));
        rangeEndToXhtml(xb, maxId, maxInputName, maxStringValue, getText("elements.search.range.to"));

        xb.closeElement("div");

        xb.closeElement("fieldset");
    }

}

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

import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import com.manydesigns.elements.fields.search.AbstractSearchField;
import com.manydesigns.elements.fields.search.Criteria;
import com.manydesigns.elements.ognl.OgnlUtils;
import com.manydesigns.elements.reflection.PropertyAccessor;
import com.manydesigns.elements.xml.XhtmlBuffer;

/**
 * @author reisoli
 * 
 */
public class SingleValueNumericSearchField extends AbstractSearchField {

    protected String stringValue;
    protected Integer maxLength = null;
    protected Object decimalValue;

    /**
     * Dimensione del campo di input.
     */
    private static final Integer FIELDSIZE = 18;

    /**
     * Costruttore standard.
     * 
     * @param accessor
     *            L'accessor del campo
     */
    public SingleValueNumericSearchField(final PropertyAccessor accessor) {
        super(accessor);
        // TODO Auto-generated constructor stub
    }

    /**
     * Costruttore con il prefisso per le tavole.
     * 
     * @param accessor
     *            L'accessor del campo
     * @param prefix
     *            Il prefisso delle tavole da aggiungere alla query
     */
    public SingleValueNumericSearchField(final PropertyAccessor accessor, final String prefix) {
        super(accessor, prefix);
        // TODO Auto-generated constructor stub
    }

    /**
     * Configura il criterio di ricerca per uguaglianza.
     * 
     * @see com.manydesigns.elements.fields.search.SearchField#configureCriteria(
     *      com.manydesigns.elements.fields.search.Criteria)
     * 
     * @param criteria
     *            L'oggetto criteria da configurare
     */
    public final void configureCriteria(final Criteria criteria) {
        if (decimalValue != null) {
            criteria.eq(accessor, decimalValue);
        }
    }

    /**
     * @see com.manydesigns.elements.fields.search.SearchField#toSearchString(java .lang.StringBuilder)
     * 
     * @param sb
     *            String buffer della query
     */
    public final void toSearchString(final StringBuilder sb) {
        if (stringValue != null) {
            appendToSearchString(sb, inputName, stringValue);
        }

    }

    /**
     * @seecom.manydesigns.elements.Element#readFromRequest(javax.servlet.http. HttpServletRequest)
     */
    public void readFromRequest(HttpServletRequest req) {
        stringValue = StringUtils.trimToNull(req.getParameter(inputName));
        try {
            decimalValue = OgnlUtils.convertValue(stringValue, accessor.getType());
        } catch (Exception t) {
            decimalValue = null;
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.manydesigns.elements.Element#validate()
     */
    public boolean validate() {
        // Non sono necessarie validazioni quindi...
        return true;
    }

    /**
     * Rendering Xhtml del campo.
     * 
     * @see com.manydesigns.elements.xml.XhtmlFragment#toXhtml( com.manydesigns.elements.xml.XhtmlBuffer)
     * 
     * @param xb
     *            XhtmlBuffer a cui aggiungere il campo
     */
    public void toXhtml(XhtmlBuffer xb) {
        xb.writeLabel(StringUtils.capitalize(label), id, ATTR_NAME_HTML_CLASS);
        xb.writeInputText(id, inputName, stringValue, "text", FIELDSIZE, maxLength);

    }

}

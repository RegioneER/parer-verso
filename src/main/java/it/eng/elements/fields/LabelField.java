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

package it.eng.elements.fields;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;

import com.manydesigns.elements.Mode;
import com.manydesigns.elements.annotations.HighlightLinks;
import com.manydesigns.elements.annotations.Multiline;
import com.manydesigns.elements.annotations.RichText;
import com.manydesigns.elements.annotations.Status;
import com.manydesigns.elements.fields.AbstractTextField;
import com.manydesigns.elements.reflection.PropertyAccessor;
import com.manydesigns.elements.util.Util;
import com.manydesigns.elements.xml.XhtmlBuffer;

/*
* @author Paolo Predonzani     - paolo.predonzani@manydesigns.com
* @author Angelo Lupo          - angelo.lupo@manydesigns.com
* @author Giampiero Granatella - giampiero.granatella@manydesigns.com
* @author Alessio Stalla       - alessio.stalla@manydesigns.com
*/
public class LabelField extends AbstractTextField {
    public static final String copyright = "Copyright (c) 2005-2014, ManyDesigns srl";

    protected boolean highlightLinks = false;
    protected boolean multiline = false;
    protected boolean richText = false;
    protected Integer textAreaWidth;
    protected int textAreaMinRows = 4;
    protected String[] red;
    protected String[] amber;
    protected String[] green;

    // **************************************************************************
    // Costruttori
    // **************************************************************************

    public LabelField(PropertyAccessor accessor, Mode mode) {
        this(accessor, mode, null);
    }

    public LabelField(PropertyAccessor accessor, Mode mode, String prefix, String labelValue) {
        super(accessor, mode, prefix);
        this.stringValue = labelValue;
    }

    public LabelField(PropertyAccessor accessor, Mode mode, String prefix) {
        super(accessor, mode, prefix);

        HighlightLinks highlightLinksAnnotation = accessor.getAnnotation(HighlightLinks.class);
        if (highlightLinksAnnotation != null) {
            highlightLinks = highlightLinksAnnotation.value();
            logger.debug("HighlightLinks annotation present with value: {}", highlightLinks);
        }

        Multiline multilineAnnotation = accessor.getAnnotation(Multiline.class);
        if (multilineAnnotation != null) {
            multiline = multilineAnnotation.value();
            logger.debug("Multiline annotation present with value: {}", multiline);
        }

        RichText richTextAnnotation = accessor.getAnnotation(RichText.class);
        if (richTextAnnotation != null) {
            multiline = richTextAnnotation.value();
            richText = richTextAnnotation.value();
            logger.debug("RichText annotation present with value: {}", multiline);
        }

        if (accessor.isAnnotationPresent(Status.class)) {
            Status annotation = accessor.getAnnotation(Status.class);
            red = annotation.red();
            amber = annotation.amber();
            green = annotation.green();
        }
    }

    // **************************************************************************
    // Implementazione/override di AbstractLabelField
    // **************************************************************************
    @Override
    public void readFromRequest(HttpServletRequest req) {
        // not used
    }

    @Override
    public void readFromObject(Object obj) {
        // not used
    }

    public void writeToObject(Object obj) {
        // not used
    }

    @Override
    protected void valueToXhtmlEdit(XhtmlBuffer xb) {
        valueToXhtmlView(xb);

    }

    @Override
    protected void valueToXhtmlPreview(XhtmlBuffer xb) {
        valueToXhtmlView(xb);

    }

    @Override
    protected void valueToXhtmlView(XhtmlBuffer xb) {
        xb.openElement("div");
        String cssClass = "value";
        if (ArrayUtils.contains(red, stringValue)) {
            cssClass += " status_red";
        } else if (ArrayUtils.contains(amber, stringValue)) {
            cssClass += " status_amber";
        } else if (ArrayUtils.contains(green, stringValue)) {
            cssClass += " status_green";
        }
        xb.addAttribute("class", cssClass);
        xb.addAttribute("id", id);
        if (href != null) {
            xb.openElement("a");
            xb.addAttribute("href", href);
            xb.addAttribute("alt", title);
        }
        if (richText) {
            xb.writeNoHtmlEscape(stringValue);
        } else {
            Util.writeFormattedText(xb, stringValue, href == null && highlightLinks);
        }
        if (href != null) {
            xb.closeElement("a");
        }
        xb.closeElement("div");
    }

    @Override
    public String getDisplayValue() {
        String escapedText;
        if (richText) {
            escapedText = stringValue;
        } else {
            escapedText = StringEscapeUtils.escapeHtml(stringValue);
        }
        return escapedText;
    }

    public String getValue() {
        return stringValue;
    }

    // **************************************************************************
    // Other methods
    // **************************************************************************
    @Deprecated(forRemoval = true)
    private int numRowTextArea(String stringValue, int cols) {
        if (stringValue == null)
            return textAreaMinRows;

        String[] dim = stringValue.split("\n");
        int rows = 0;
        for (String aDim : dim) {
            if (aDim.length() >= cols)
                rows += aDim.length() / cols;
        }
        rows += dim.length;
        if (rows < textAreaMinRows)
            rows = textAreaMinRows;
        return rows;
    }

    // **************************************************************************
    // Getters/setters
    // **************************************************************************
    public boolean isHighlightLinks() {
        return highlightLinks;
    }

    public void setHighlightLinks(boolean highlightLinks) {
        this.highlightLinks = highlightLinks;
    }

    public boolean isMultiline() {
        return multiline;
    }

    public void setMultiline(boolean multiline) {
        this.multiline = multiline;
    }

    public boolean isRichText() {
        return richText;
    }

    public void setRichText(boolean richText) {
        this.richText = richText;
    }

    public Integer getTextAreaWidth() {
        return textAreaWidth;
    }

    public void setTextAreaWidth(Integer textAreaWidth) {
        this.textAreaWidth = textAreaWidth;
    }

    public int getTextAreaMinRows() {
        return textAreaMinRows;
    }

    public void setTextAreaMinRows(int textAreaMinRows) {
        this.textAreaMinRows = textAreaMinRows;
    }

    @Override
    public void valueToXhtml(XhtmlBuffer xb) {
        if (mode.isView(insertable, updatable)) {
            valueToXhtmlView(xb);
        } else if (mode.isEdit()) {
            valueToXhtmlEdit(xb);
        } else if (mode.isPreview()) {
            valueToXhtmlPreview(xb);
        } else if (mode.isHidden()) {
            xb.writeInputHidden(id, inputName, stringValue);
        } else {
            throw new IllegalStateException("Unknown mode: " + mode);
        }
    }

}

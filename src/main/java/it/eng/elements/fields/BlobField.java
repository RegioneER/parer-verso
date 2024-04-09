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

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.apache.commons.lang.StringEscapeUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import com.manydesigns.elements.ElementsThreadLocals;
import com.manydesigns.elements.Mode;
import com.manydesigns.elements.blobs.Blob;
import com.manydesigns.elements.blobs.BlobManager;
import com.manydesigns.elements.fields.AbstractField;
import com.manydesigns.elements.fields.MultipartRequestField;
import com.manydesigns.elements.reflection.PropertyAccessor;
import com.manydesigns.elements.util.MemoryUtil;
import com.manydesigns.elements.util.RandomUtil;
import com.manydesigns.elements.xml.XhtmlBuffer;

import it.eng.parer.simparer.common.SpringContext;
import net.datasiel.simpaweb.db.dao.ParComponenteDAO;
import net.datasiel.simpaweb.db.pojo.ParComponente;
import net.sourceforge.stripes.action.FileBean;
import net.sourceforge.stripes.controller.StripesRequestWrapper;

public class BlobField extends AbstractField implements MultipartRequestField {
    public static final String copyright = "Copyright (c) 2005-2014, ManyDesigns srl";

    public static final String UPLOAD_KEEP = "_keep";
    public static final String UPLOAD_MODIFY = "_modify";
    public static final String UPLOAD_DELETE = "_delete";

    public static final String OPERATION_SUFFIX = "_operation";
    public static final String CODE_SUFFIX = "_code";
    public static final String INNER_SUFFIX = "_inner";

    protected String innerId;
    protected String operationInputName;
    protected String codeInputName;

    protected Blob blob;
    protected String blobError;

    // **************************************************************************
    // Costruttori
    // **************************************************************************
    public BlobField(PropertyAccessor accessor, Mode mode) {
        this(accessor, mode, null);
    }

    public BlobField(@NotNull PropertyAccessor accessor, @NotNull Mode mode, @Nullable String prefix) {
        super(accessor, mode, prefix);

        innerId = id + INNER_SUFFIX;
        operationInputName = inputName + OPERATION_SUFFIX;
        codeInputName = inputName + CODE_SUFFIX;
    }

    // **************************************************************************
    // AbstractField implementation
    // **************************************************************************
    public void valueToXhtml(XhtmlBuffer xb) {
        if (mode.isView(insertable, updatable)) {
            valueToXhtmlView(xb);
        } else if (mode.isEdit()) {
            valueToXhtmlEdit(xb);
        } else if (mode.isPreview()) {
            valueToXhtmlPreview(xb);
        } else if (mode.isHidden()) {
            valueToXhtmlHidden(xb);
        } else {
            throw new IllegalStateException("Unknown mode: " + mode);
        }
    }

    public String getStringValue() {
        if (blob == null) {
            return null;
        } else {
            return blob.getFilename();
        }
    }

    public void valueToXhtmlPreview(XhtmlBuffer xb) {
        valueToXhtmlView(xb);
        valueToXhtmlHidden(xb);
    }

    private void valueToXhtmlHidden(XhtmlBuffer xb) {
        xb.writeInputHidden(operationInputName, UPLOAD_KEEP);
        if (blob != null) {
            xb.writeInputHidden(codeInputName, blob.getCode());
        }
    }

    public void valueToXhtmlView(XhtmlBuffer xb) {
        xb.openElement("div");
        xb.addAttribute("id", id);
        xb.addAttribute("class", "value");
        if (blobError != null) {
            xb.openElement("div");
            xb.addAttribute("class", "blob-error");
            xb.write(blobError);
            xb.closeElement("div");
        } else if (blob != null) {
            writeBlobFilenameAndSize(xb);
        }
        xb.closeElement("div");
    }

    public void writeBlobFilenameAndSize(XhtmlBuffer xb) {
        if (href != null) {
            xb.openElement("a");
            xb.addAttribute("href", href);
        }
        xb.write(blob.getFilename());
        if (href != null) {
            xb.closeElement("a");
        }
        if (blob.getSize() > 0) {
            xb.write(" (");
            xb.write(MemoryUtil.bytesToHumanString(blob.getSize()));
            xb.write(")");
        }
    }

    private void valueToXhtmlEdit(XhtmlBuffer xb) {
        if (blob == null) {
            xb.writeInputHidden(operationInputName, UPLOAD_MODIFY);
            xb.writeInputFile(id, inputName, false);
        } else {
            xb.openElement("div");
            xb.addAttribute("class", "value");
            xb.addAttribute("id", id);

            xb.openElement("div");
            writeBlobFilenameAndSize(xb);
            xb.closeElement("div");

            String radioId = id + UPLOAD_KEEP;
            String script = "var inptxt = this.ownerDocument.getElementById('"
                    + StringEscapeUtils.escapeJavaScript(innerId) + "');" + "inptxt.disabled=true;inptxt.value='';";
            printRadio(xb, radioId, "elements.field.upload.keep", UPLOAD_KEEP, true, script);

            radioId = id + UPLOAD_MODIFY;
            script = "var inptxt = this.ownerDocument.getElementById('" + StringEscapeUtils.escapeJavaScript(innerId)
                    + "');" + "inptxt.disabled=false;inptxt.value='';";
            printRadio(xb, radioId, "elements.field.upload.update", UPLOAD_MODIFY, false, script);

            if (!isRequired()) {
                radioId = id + UPLOAD_DELETE;
                script = "var inptxt = this.ownerDocument.getElementById('"
                        + StringEscapeUtils.escapeJavaScript(innerId) + "');" + "inptxt.disabled=true;inptxt.value='';";
                printRadio(xb, radioId, "elements.field.upload.delete", UPLOAD_DELETE, false, script);
            }

            xb.writeInputFile(innerId, inputName, true);
            xb.writeInputHidden(codeInputName, blob.getCode());

            xb.closeElement("div");
        }
    }

    protected void printRadio(XhtmlBuffer xb, String radioId, String labelKey, String value, boolean checked,
            String script) {
        xb.openElement("label");
        xb.addAttribute("for", radioId);
        xb.addAttribute("class", "radio");
        xb.writeInputRadio(radioId, operationInputName, value, checked, false, script);
        xb.writeNbsp();
        xb.write(getText(labelKey));
        xb.closeElement("label");
    }

    // **************************************************************************
    // Element implementation
    // **************************************************************************
    public void readFromRequest(HttpServletRequest req) {
        super.readFromRequest(req);

        if (mode.isView(insertable, updatable)) {
            return;
        }

        String updateTypeStr = req.getParameter(operationInputName);
        if (UPLOAD_MODIFY.equals(updateTypeStr)) {
            saveUpload(req);
        } else if (UPLOAD_DELETE.equals(updateTypeStr)) {
            blob = null;
        } else {
            // in all other cases (updateTypeStr is UPLOAD_KEEP,
            // null, or other values) keep the existing blob
            String code = req.getParameter(codeInputName);
            safeLoadBlob(code);
        }
    }

    private void saveUpload(HttpServletRequest req) {
        BlobManager blobManager = ElementsThreadLocals.getBlobManager();
        if (blobManager == null) {
            logger.warn("No blob manager found. Cannot save upload.");
            throw new Error("No blob manager found. Cannot save upload.");
        }

        FileBean fileBean = null;
        try {
            StripesRequestWrapper stripesRequest = StripesRequestWrapper.findStripesWrapper(req);
            fileBean = stripesRequest.getFileParameterValue(inputName);

            if (fileBean != null) {
                String code = RandomUtil.createRandomId();
                blob = blobManager.saveBlob(code, fileBean.getInputStream(), fileBean.getFileName(),
                        fileBean.getContentType(), null);
            } else {
                logger.debug(
                        "An update of a blob was requested, but nothing was uploaded. The previous value will be kept.");
                String code = req.getParameter(codeInputName);
                safeLoadBlob(code);
            }
        } catch (Exception e) {
            logger.warn("Cannot save upload", e);
            throw new Error("Cannot save upload", e);
        } finally {
            if (fileBean != null) {
                try {
                    fileBean.delete();
                } catch (IOException e) {
                    logger.warn("Could not delete file bean", e);
                }
            }
        }
    }

    public boolean validate() {
        if (mode.isView(insertable, updatable) || (mode.isBulk() && !bulkChecked)) {
            return true;
        }

        boolean result = true;
        if (required && (blob == null)) {
            errors.add(getText("elements.error.field.required"));
            result = false;
        }
        return result;
    }

    public void readFromObject(Object obj) {
        super.readFromObject(obj);
        if (obj == null) {
            blob = null;
        } else {
            String code = (String) accessor.get(obj);
            safeLoadBlob(code);
        }
    }

    protected void safeLoadBlob(String code) {
        Connection conn = null;
        blob = null;
        if (code != null) {
            DataSource datasource = SpringContext.getApplicationContext().getBean("dataSource", DataSource.class);
            if (datasource != null) {
                try {
                    conn = datasource.getConnection();
                    ParComponenteDAO parCompDao = new ParComponenteDAO();
                    List<ParComponente> lista = parCompDao.retrieveByCodallegatoNoBlob(code, conn);
                    if (lista != null && !lista.isEmpty()) {

                        ParComponente comp = lista.get(0);
                        blob = new Blob(null, null);
                        blob.setCode(code);
                        blob.setFilename(comp.getNome());

                    }

                } catch (SQLException e) {
                    blob = null;
                    blobError = getText("elements.error.field.fileblob.cannotLoad");
                    logger.warn("Cannot load blob with code '{}'. Cause: {}", code, e.getMessage());
                } finally {
                    if (conn != null)
                        try {
                            conn.close();
                        } catch (SQLException e) {

                        }
                }

            }

        }
    }

    public void writeToObject(Object obj) {
        if (blob == null) {
            writeToObject(obj, null);
        } else {
            writeToObject(obj, blob.getCode());
        }
    }

    public Blob getValue() {
        return blob;
    }

    public void setValue(Blob blob) {
        this.blob = blob;
    }

    public String getCodeInputName() {
        return codeInputName;
    }

    public String getOperationInputName() {
        return operationInputName;
    }

    public void setOperationInputName(String operationInputName) {
        this.operationInputName = operationInputName;
    }

    public String getBlobError() {
        return blobError;
    }

    public void setBlobError(String blobError) {
        this.blobError = blobError;
    }
}

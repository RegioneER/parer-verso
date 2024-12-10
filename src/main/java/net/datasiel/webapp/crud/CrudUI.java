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
package net.datasiel.webapp.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.servlet.http.HttpServletRequest;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.xml.XhtmlBuffer;

/**
 * @author reisoli
 *
 */
public class CrudUI implements Element {

    public static final String ELIMINA_RIGHE_DETTAGLIO = "Elimina righe dettaglio ";
    public static final String CANCELLA_RIGA = "Cancella %s ";
    public static final String CREA_DETTAGLIO = "Crea riga dettaglio ";
    // ****************************************************
    // Fields / componenti costitutivi dell'interfaccia
    // ****************************************************
    Element intestazioneUiRO;
    Element intestazioneUiRW;
    final List<RigaUI> righeUI = new ArrayList<RigaUI>();

    // ****************************************************
    // Elementi di configurazione *
    // ****************************************************
    int maxRighe;
    String labelFieldset;

    public final boolean readOnly;

    /**
     * @param readOnly
     */
    public CrudUI(boolean readOnly) {
        super();
        this.readOnly = readOnly;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.manydesigns.elements.Element#readFromObject(java.lang.Object)
     */
    public void readFromObject(Object obj) {
        CrudModel crudModel = (CrudModel) obj;
        if (crudModel == null) {
            return;
        }
        if (intestazioneUiRO != null) {
            intestazioneUiRO.readFromObject(crudModel.getIntestazioneRO());
        }
        if (intestazioneUiRW != null) {
            intestazioneUiRW.readFromObject(crudModel.getIntestazioneRW());
        }
        int index = 0;
        for (RigaUI rigaUI : righeUI) {
            rigaUI.readFromObject(crudModel.getRighe().get(index));
            index++;
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.manydesigns.elements.Element#readFromRequest(javax.servlet.http.HttpServletRequest)
     */
    public void readFromRequest(HttpServletRequest req) {
        /**
         * La parte read only non viene riletta dal request
         */
        if (intestazioneUiRW != null) {
            intestazioneUiRW.readFromRequest(req);
        }

        for (RigaUI rigaUI : righeUI) {
            rigaUI.readFromRequest(req);
        }

    }

    /*
     * (non-Javadoc)
     *
     * @see com.manydesigns.elements.Element#validate()
     */
    public boolean validate() {
        // La parte RO non deve essere validata
        boolean result = true;
        if (intestazioneUiRW != null) {
            result = intestazioneUiRW.validate() && result;
        }

        for (RigaUI rigaUI : righeUI) {
            result = rigaUI.validate() && result;
        }
        return result;
    }

    /*
     * (non-Javadoc)
     *
     * @see com.manydesigns.elements.Element#writeToObject(java.lang.Object)
     */
    public void writeToObject(Object obj) {
        CrudModel crudModel = (CrudModel) obj;
        if (intestazioneUiRW != null) {
            intestazioneUiRW.writeToObject(crudModel.getIntestazioneRW());
        }
        int index = 0;
        for (RigaUI rigaUI : righeUI) {
            rigaUI.writeToObject(crudModel.getRighe().get(index));
            index++;
        }
    }

    /*
     * (non-Javadoc)
     *
     * @see com.manydesigns.elements.xml.XhtmlFragment#toXhtml(com.manydesigns.elements.xml.XhtmlBuffer)
     */
    public void toXhtml(XhtmlBuffer xb) {
        if (intestazioneUiRO != null) {
            intestazioneUiRO.toXhtml(xb);
        }
        if (intestazioneUiRW != null) {
            intestazioneUiRW.toXhtml(xb);
        }

        int index = 1;
        for (RigaUI rigaUI : righeUI) {
            if (Objects.nonNull(labelFieldset)) {
                xb.openElement("fieldset");
                xb.writeLegend(labelFieldset + index, null);
            }
            // Se deve essere gestita solo una riga questa non deve essere eliminata
            if (maxRighe == 1) {
                rigaUI.toXhtml(xb, index, readOnly, false, labelFieldset);
            } else {
                rigaUI.toXhtml(xb, index, readOnly, true, labelFieldset);
            }
            if (Objects.nonNull(labelFieldset)) {
                xb.closeElement("fieldset");
            }
            index++;
        }
    }

    // ****************************************************
    // Getters
    // ****************************************************

    /**
     * @return the intestazioneUiRO
     */
    public Element getIntestazioneUI() {
        return intestazioneUiRO;
    }

    /**
     * @return the righeUI
     */
    public List<RigaUI> getRigheUI() {
        return righeUI;
    }

    /**
     * @return the intestazioneUiRO
     */
    public Element getIntestazioneUiRO() {
        return intestazioneUiRO;
    }

    /**
     * @return the intestazioneUiRW
     */
    public Element getIntestazioneUiRW() {
        return intestazioneUiRW;
    }

}

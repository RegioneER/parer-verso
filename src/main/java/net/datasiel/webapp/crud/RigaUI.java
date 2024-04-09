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

package net.datasiel.webapp.crud;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;

import com.manydesigns.elements.Element;
import com.manydesigns.elements.forms.TableForm;
import com.manydesigns.elements.xml.XhtmlBuffer;

public class RigaUI implements Element {

    // ****************************************************
    // Fields / componenti costitutivi dell'interfaccia
    // ****************************************************

    /**
     * Immaginamo che siano sufficienti per gestire una riga considerando che gestiremo la chiave per via posizionale.
     */
    Element elRiga;
    TableForm eltafoDettaglio1;
    TableForm eltafoDettaglio2;

    public void readFromObject(Object obj) {
        RigaModel rigaModel = (RigaModel) obj;
        if (obj != null && elRiga == null) {
            throw new IllegalStateException("Ti sei dimenticato di implementare prepareElRiga di AbstractCrudAction?");
        }
        elRiga.readFromObject(rigaModel.getRiga());
        List<Object> dettagli1 = rigaModel.getDettagli1();
        if (CollectionUtils.isNotEmpty(dettagli1)) {
            if (eltafoDettaglio1 == null) {
                throw new IllegalStateException(
                        "Ti sei dimenticato di implementare prepareEltafoDettaglio1 di AbstractCrudAction?");
            } else {
                eltafoDettaglio1.readFromObject(dettagli1);
            }
        }

        List<Object> dettagli2 = rigaModel.getDettagli2();
        if (CollectionUtils.isNotEmpty(dettagli2)) {
            if (eltafoDettaglio2 == null) {
                throw new IllegalStateException(
                        "Ti sei dimenticato di implementare prepareEltafoDettaglio2 di AbstractCrudAction?");
            } else {
                eltafoDettaglio2.readFromObject(dettagli2);
            }
        }

    }

    public void readFromRequest(HttpServletRequest req) {
        elRiga.readFromRequest(req);
        /**
         * Blindatura più leggera rispetta a readFromObject perchè la AbstractCrudAction farà sempre prima la
         * readFromObject individuando subito i problemi dati da non implementazione dei metodi.
         */
        if (eltafoDettaglio1 != null) {
            eltafoDettaglio1.readFromRequest(req);
        }
        if (eltafoDettaglio2 != null) {
            eltafoDettaglio2.readFromRequest(req);
        }
    }

    public boolean validate() {
        boolean result = true;

        result = elRiga.validate() && result;
        if (eltafoDettaglio1 != null) {
            result = eltafoDettaglio1.validate() && result;
        }
        if (eltafoDettaglio2 != null) {
            result = eltafoDettaglio2.validate() && result;
        }
        return result;
    }

    public void writeToObject(Object obj) {
        RigaModel rigaModel = (RigaModel) obj;
        elRiga.writeToObject(rigaModel.getRiga());
        if (eltafoDettaglio1 != null) {
            eltafoDettaglio1.writeToObject(rigaModel.getDettagli1());
        }

        if (eltafoDettaglio2 != null && rigaModel.getDettagli2() != null) {
            eltafoDettaglio2.writeToObject(rigaModel.getDettagli2());
        }

    }

    public void toXhtml(XhtmlBuffer xb) {

    }

    public void toXhtml(XhtmlBuffer xb, int index, boolean readOnly, boolean deletable, String titoloRiga) {
        elRiga.toXhtml(xb);
        if (!readOnly && deletable) {
            xb.openElement("input");
            xb.addAttribute("type", "submit");
            xb.addAttribute("class", "btn btn-sacer btn-small deleteRiga");
            xb.addAttribute("name", "delete");
            String titoloPulsante;
            if (titoloRiga == null) {
                titoloPulsante = String.format(CrudUI.CANCELLA_RIGA, "riga") + index;
            } else {
                titoloPulsante = String.format(CrudUI.CANCELLA_RIGA, titoloRiga) + index;
            }
            xb.addAttribute("value", titoloPulsante);
            xb.closeElement("input");
        }

        if (eltafoDettaglio1 != null) {
            xb.writeBr();
            xb.writeBr();
            eltafoDettaglio1.toXhtml(xb);

            if (!readOnly) {
                xb.openElement("input");
                xb.addAttribute("type", "submit");
                xb.addAttribute("class", "btn btn-sacer btn-small createDettaglio dettaglio1");
                xb.addAttribute("name", "createDettaglio1");
                xb.addAttribute("value", CrudUI.CREA_DETTAGLIO + index);
                xb.closeElement("input");

                xb.openElement("input");
                xb.addAttribute("type", "submit");
                xb.addAttribute("class", "btn btn-warning btn-small deleteDettaglio dettaglio1");
                xb.addAttribute("name", "deleteDettaglio1");
                xb.addAttribute("value", CrudUI.ELIMINA_RIGHE_DETTAGLIO + index);
                xb.closeElement("input");
            }
        }
        if (eltafoDettaglio2 != null) {
            xb.writeBr();
            xb.writeBr();
            eltafoDettaglio2.toXhtml(xb);

            if (!readOnly) {
                xb.openElement("input");
                xb.addAttribute("type", "submit");
                xb.addAttribute("class", "btn btn-sacer btn-small createDettaglio dettaglio2");
                xb.addAttribute("name", "createDettaglio2");
                xb.addAttribute("value", CrudUI.CREA_DETTAGLIO + index);
                xb.closeElement("input");

                xb.openElement("input");
                xb.addAttribute("type", "submit");
                xb.addAttribute("class", "btn btn-warning btn-small deleteDettaglio dettaglio2");
                xb.addAttribute("name", "deleteDettaglio2");
                xb.addAttribute("value", CrudUI.ELIMINA_RIGHE_DETTAGLIO + index);
                xb.closeElement("input");
            }
        }

    }

    // ****************************************************
    // Getters
    // ****************************************************

    /**
     * @return the elRiga
     */
    public Element getElRiga() {
        return elRiga;
    }

    /**
     * @return the eltafoDettaglio1
     */
    public TableForm getEltafoDettaglio1() {
        return eltafoDettaglio1;
    }

    /**
     * @return the eltafoDettaglio2
     */
    public TableForm getEltafoDettaglio2() {
        return eltafoDettaglio2;
    }

}

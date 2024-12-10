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

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package it.eng.exceptions;

import it.eng.exceptions.ErrorCategory.VersoErrorCategory;

public class VersoException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = -9173356878746119329L;
    private final VersoErrorCategory category;

    public VersoException() {
        super();
        this.category = VersoErrorCategory.INTERNAL_ERROR; // default
    }

    public VersoException(VersoErrorCategory category) {
        super();
        this.category = category;
    }

    public VersoException(String message, Throwable cause, VersoErrorCategory category) {
        super(message, cause);
        this.category = category;
    }

    public VersoException(String message, VersoErrorCategory category) {
        super(message);
        this.category = category;
    }

    public VersoException(Throwable cause, VersoErrorCategory category) {
        super(cause);
        this.category = category;
    }

    public VersoErrorCategory getCategory() {
        return category;
    }

    @Override
    public String getLocalizedMessage() {
        return "[" + getCategory().toString() + "]" + "  " + super.getLocalizedMessage();
    }

    @Override
    public String getMessage() {
        return "[" + getCategory().toString() + "]" + "  " + super.getMessage();
    }

}

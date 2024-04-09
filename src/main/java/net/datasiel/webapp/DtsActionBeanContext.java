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

package net.datasiel.webapp;

import javax.sql.DataSource;

import net.sourceforge.stripes.action.ActionBeanContext;
import net.sourceforge.stripes.action.Resolution;
import net.sourceforge.stripes.exception.SourcePageNotFoundException;
import net.sourceforge.stripes.integration.spring.SpringBean;

public class DtsActionBeanContext extends ActionBeanContext {

    @SpringBean("dataSource")
    private DataSource dataSource;

    public DtsActionBeanContext() {
        super();
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Override
    public Resolution getSourcePageResolution() throws SourcePageNotFoundException {
        // TODO Auto-generated method stub
        return super.getSourcePageResolution();
    }
}

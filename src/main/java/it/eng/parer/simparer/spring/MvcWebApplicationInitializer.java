/*
 * Engineering Ingegneria Informatica S.p.A.
 *
 * Copyright (C) 2023 Regione Emilia-Romagna <p/> This program is free software: you can
 * redistribute it and/or modify it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the License, or (at your option)
 * any later version. <p/> This program is distributed in the hope that it will be useful, but
 * WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A
 * PARTICULAR PURPOSE. See the GNU Affero General Public License for more details. <p/> You should
 * have received a copy of the GNU Affero General Public License along with this program. If not,
 * see <https://www.gnu.org/licenses/>.
 */

package it.eng.parer.simparer.spring;

import javax.servlet.Filter;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

/**
 *
 * @author Marco Iacolucci
 */
@ComponentScan(basePackages = {
        "it.eng.parer.simparer.spring", "net.datasiel.simpaweb.actionbeans", "it.eng.spagoCore",
        "it.eng.spagoLite", "it.eng.spagoLite.actions" })
public class MvcWebApplicationInitializer
        extends AbstractAnnotationConfigDispatcherServletInitializer {

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {
                ApplicationConfiguration.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        // return new Class[] { ApplicationConfiguration.class, SecurityConfiguration.class };
        return new Class[] {
                SecurityConfiguration.class, WebMvcConfiguration.class };

    }

    @Override
    protected String[] getServletMappings() {
        return new String[] {
                "/" };
    }

    @Override
    protected Filter[] getServletFilters() {

        return null;
    }
}

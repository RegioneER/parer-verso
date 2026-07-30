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

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 *
 * @author Marco Iacolucci
 */
public class SecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    @Override
    protected boolean enableHttpSessionEventPublisher() {
        return true;
    }
    /*
     * @Override protected void beforeSpringSecurityFilterChain(ServletContext servletContext) {
     * super.afterSpringSecurityFilterChain(servletContext); // Configurazione del filtro
     * StripesFilter FilterRegistration.Dynamic stripesFilter =
     * servletContext.addFilter("StripesFilter", StripesFilter.class);
     * stripesFilter.addMappingForUrlPatterns(null, false, "/*");
     * stripesFilter.setInitParameters(getStripesFilterInitParams());
     *
     * // Configurazione del filtro DynamicMappingFilter FilterRegistration.Dynamic
     * dynamicMappingFilter = servletContext.addFilter("DynamicMappingFilter",
     * DynamicMappingFilter.class); dynamicMappingFilter.addMappingForUrlPatterns(null, false,
     * "/*");
     *
     * }
     *
     * private Map<String, String> getStripesFilterInitParams() { Map<String, String> initParams =
     * new HashMap<>(); initParams.put("ActionResolver.Packages",
     * "net.datasiel.simpaweb.actionbeans"); initParams.put("Interceptor.Classes", String.join(",",
     * "net.sourceforge.stripes.integration.spring.SpringInterceptor",
     * "net.sourceforge.stripes.controller.BeforeAfterMethodInterceptor",
     * "net.datasiel.webapp.ApplicationInterceptor", "net.datasiel.webapp.CleanUpInterceptor"));
     * initParams.put("ActionBeanContext.Class", "net.datasiel.webapp.DtsActionBeanContext");
     * initParams.put("ExceptionHandler.Class",
     * "it.eng.parer.simparer.security.VersoExceptionHandler");
     * initParams.put("FileUpload.MaximumPostSize", "1gb"); return initParams; }
     */

}

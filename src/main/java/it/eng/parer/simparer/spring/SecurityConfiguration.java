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

import it.eng.spagoLite.spring.ParerSecurityConfiguration;
import it.eng.spagoLite.spring.RefreshableRelyingPartyRegistrationRepository;
import java.util.List;
import javax.naming.NamingException;
import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import it.eng.parer.simparer.security.SimparerAuthenticator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.lookup.JndiDataSourceLookup;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.saml2.provider.service.metadata.OpenSamlMetadataResolver;
import org.springframework.security.saml2.provider.service.web.DefaultRelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.RelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.Saml2MetadataFilter;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;
import org.springframework.security.web.header.HeaderWriterFilter;

import it.eng.parer.simparer.security.SimparerLoginLog;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.transaction.jta.JtaTransactionManager;

/**
 *
 * @author Marco Iacolucci
 */
@EnableWebSecurity
@Configuration
@ComponentScan(basePackages = {
        "it.eng.parer.simparer.spring", "it.eng.spagoCore", "it.eng.spagoLite",
        "net.datasiel.simpaweb.actionbeans", "net.datasiel.simpaweb.common",
        "it.eng.spagoLite.actions" })
public class SecurityConfiguration extends ParerSecurityConfiguration {

    @Autowired
    public RefreshableRelyingPartyRegistrationRepository refreshableRelyingPartyRegistrationRepository;
    @Autowired
    private VersoSaml2AuthenticationSuccessHandler successHandler;

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityConfiguration.class);

    public SecurityConfiguration() {
        nomeApplicazione = "verso";
    }

    /*
     * Serve per parametrizzare l'applicazione specifica per esempio per caricare le variabili di
     * sistema che hanno come suffisso ad esempio "saceriam".
     */
    @Bean
    String nomeApplicazione() {
        return "verso";
    }

    /*
     * Impostazione del filtro di sicurezza Spring ed esposizione del metadata di Saceriam
     */
    @Bean
    SecurityFilterChain app(HttpSecurity http,
            RefreshableRelyingPartyRegistrationRepository relyingPartyRegistrationRepository)
            throws Exception {

        // ----- Questo per abilitare l'esposizione del metadata del service provider
        RelyingPartyRegistrationResolver reg = new DefaultRelyingPartyRegistrationResolver(
                relyingPartyRegistrationRepository);

        // Metadata dell'app reperibile in locale al seguente URL standard:
        // http://localhost:8080/sacerdips/saml2/service-provider-metadata/sacerdips
        Saml2MetadataFilter filter = new Saml2MetadataFilter(reg, new OpenSamlMetadataResolver());
        LOGGER.info("Oggetto HTTP {}", http);

        http.authorizeHttpRequests(authorize -> authorize.requestMatchers("/logout").permitAll()
                .requestMatchers("/font/**").permitAll().requestMatchers("/pages/Logout.jsp")
                .permitAll().requestMatchers("/pro/logout").permitAll().requestMatchers("/gfx/**")
                .permitAll().requestMatchers("/admin/**").permitAll()
                .requestMatchers("/Logout.html").permitAll()
                .requestMatchers("/AssociazioneUtente.html").permitAll()
                .requestMatchers("/ModificaPsw.html").permitAll()
                .requestMatchers("/saml/SingleLogout/alias/" + nomeApplicazione).permitAll()
                .requestMatchers("/rest/**").permitAll().requestMatchers("/saml/**").permitAll()
                .requestMatchers("/saml2/**").permitAll().requestMatchers("/pro/*").authenticated()
                .requestMatchers("/*.html").authenticated().requestMatchers("/*.jsp")
                .authenticated().requestMatchers("/*.json").authenticated().anyRequest()
                .permitAll()).addFilterBefore(filter, HeaderWriterFilter.class)
                // Il CSRF è abilitato di default !!
                .csrf(c -> c.requireCsrfProtectionMatcher((HttpServletRequest request) -> {
                    boolean metodiOk = "POST".equals(request.getMethod())
                            || "PUT".equals(request.getMethod())
                            || "DELETE".equals(request.getMethod());
                    boolean matchPro = AntPathRequestMatcher.antMatcher("/pro/**").matches(request);
                    // boolean matchJson =
                    // AntPathRequestMatcher.antMatcher("*.json").matches(request);
                    return metodiOk && matchPro;
                })).saml2Login(saml2 -> {
                    saml2.successHandler(successHandler);
                    saml2.loginPage("/discovery");
                    saml2.loginProcessingUrl("/saml/SSO/alias/{registrationId}");
                }).logout(logout -> logout.logoutSuccessUrl("/pages/Logout.jsp"))
                .saml2Logout(saml2 -> {
                    saml2.logoutResponse().logoutUrl("/saml/SingleLogout/alias/{registrationId}");
                    saml2.logoutUrl("/logout");
                });
        http.headers().contentSecurityPolicy(System.getProperty(
                "http.sec.header.content-security-policy",
                "'default-src 'self'; script-src 'self' 'unsafe-inline' 'unsafe-eval' 'report-sample' *; style-src 'self' 'report-sample' 'unsafe-inline' *; img-src 'self' data: *;"));
        http.headers().permissionsPolicy(
                pol -> pol.policy(System.getProperty("http.sec.header.permissions-policy",
                        "'cross-origin-isolated=*, vertical-scroll=*'")));
        SecurityFilterChain catena = http.build();

        List<Filter> filtri = catena.getFilters();
        LOGGER.info("Oggetto FILTRI {}", filtri);
        for (javax.servlet.Filter filter1 : filtri) {
            LOGGER.info("FILTRO configurato->>{} per l'applicazione {}",
                    filter1.getClass().getName(), nomeApplicazione);
        }
        return catena;
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        return new HttpSessionCsrfTokenRepository();
    }

    /*
     * Serve per parametrizzare l'applicazione specifica per esempio per caricare le variabili di
     * sistema che hanno come suffisso ad esempio "saceriam".
     */

    @Bean(name = "dataSource")
    public DataSource dataSource() throws NamingException {

        JndiDataSourceLookup j = new JndiDataSourceLookup();
        DataSource ds = j.getDataSource("jboss/datasources/ClientVersDs");
        // ds.setResourceRef(true);
        return ds;
    }

    /*
     * @Bean(name = "transactionManager") public JtaTransactionManager transactionManager() {
     * JtaTransactionManager jta = new JtaTransactionManager(); return jta; }
     */
    @Bean(name = "simparerAuthenticator")
    public SimparerAuthenticator simparerAuthenticator() {
        SimparerAuthenticator a = new SimparerAuthenticator();
        a.setAppName("SACER_VERSO");
        return a;
    }

    @Bean(name = "simparerLoginLog")
    public SimparerLoginLog simparerLoginLog() {
        SimparerLoginLog a = new SimparerLoginLog();
        return a;
    }

}

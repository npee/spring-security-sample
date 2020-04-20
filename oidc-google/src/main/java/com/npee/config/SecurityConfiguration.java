package com.npee.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.client.oidc.web.logout.OidcClientInitiatedLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import java.net.URI;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private String logoutStringUrl = "http://localhost:8080/logoutPage";
    private URI logoutUrl = URI.create(logoutStringUrl);

    ClientRegistrationRepository clientRegistrationRepository = registrationId -> null;

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
            new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);
        oidcLogoutSuccessHandler.setDefaultTargetUrl(logoutStringUrl);

        return oidcLogoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        OidcUserService googleUserService = new OidcUserService();
        http
                .authorizeRequests(
                        authorizeRequests -> authorizeRequests.antMatchers("/", "/logoutPage").permitAll()
                                .anyRequest().authenticated())
                .oauth2Login(
                        oauth2Login -> oauth2Login.userInfoEndpoint().oidcUserService(googleUserService));
        http
                .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()));

    }
}


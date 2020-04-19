package com.npee.config;

import org.springframework.beans.factory.annotation.Autowired;
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


    // Field Injection (Constructor based Injection으로 변경 예정)
    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    private URI logoutUrl = URI.create("http://localhost:8080/logoutPage");


    /*
    // Setter based Injection 테스트 중
    private ClientRegistrationRepository clientRegistrationRepository;

    public void setClientRegistrationRepository(ClientRegistrationRepository clientRegistrationRepository) {
        this.clientRegistrationRepository = clientRegistrationRepository;
    }
    */

    private LogoutSuccessHandler oidcLogoutSuccessHandler() {
        OidcClientInitiatedLogoutSuccessHandler oidcLogoutSuccessHandler =
            new OidcClientInitiatedLogoutSuccessHandler(this.clientRegistrationRepository);

        // 리다이렉트 세팅 작동 안함
        // oidcLogoutSuccessHandler.setPostLogoutRedirectUri(logoutUrl);

        // Default 값을 변경
        oidcLogoutSuccessHandler.setDefaultTargetUrl("http://localhost:8080/logoutPage");

        return oidcLogoutSuccessHandler;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        OidcUserService googleUserService = new OidcUserService();
        http
                .authorizeRequests().antMatchers("/", "/logoutPage").permitAll()
                .and()
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .oauth2Login().userInfoEndpoint().oidcUserService(googleUserService);
        http
                .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler()));

    }
}

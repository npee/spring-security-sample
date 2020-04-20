package com.npee.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
                .withUser("npee").password("{noop}1234").roles("ADMIN")
                .and()
                .withUser("noob").password("{noop}1234").roles("USER");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http    .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/").hasAnyRole())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/me").hasRole("ADMIN"))
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .anyRequest().authenticated())
                .formLogin().successHandler(authenticationSuccessHandler);
    }

    AuthenticationSuccessHandler authenticationSuccessHandler = new AuthenticationSuccessHandler() {

        private String targetUrl = "http://localhost:8080/me";

        @Override
        public void onAuthenticationSuccess(
                HttpServletRequest request, HttpServletResponse response, Authentication authentication
        ) throws IOException {
            setRedirectUrl(request, response);
        }

        protected void setRedirectUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
            redirectStrategy.sendRedirect(request, response, targetUrl);
        }
    };
}

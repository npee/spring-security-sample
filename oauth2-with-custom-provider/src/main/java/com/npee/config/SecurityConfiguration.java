package com.npee.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;


@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/").permitAll())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/me").hasRole("USER").anyRequest().authenticated());
        http
                .oauth2Login()
                .defaultSuccessUrl("/me")
                .userInfoEndpoint().userService(new DefaultOAuth2UserService());
    }
}

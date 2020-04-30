package com.npee.config;

import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.oauth2.client.CommonOAuth2Provider;
import org.springframework.security.oauth2.client.registration.ClientRegistration;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.registration.InMemoryClientRegistrationRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserService;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    // private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    private final Environment environment;
    private final static String CLIENT_CONFIGURATION_KEY = "spring.security.oauth2.client.registration.";
    private static List<String> providers;
    private final String socialType = "google/facebook/github";
    private final String targetUrl = "http://localhost:8080/me";
    private final OAuth2UserService<OAuth2UserRequest, OAuth2User> oAuth2UserService = new DefaultOAuth2UserService();

    public SecurityConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/").permitAll())
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .antMatchers("/me").hasRole("USER"))
                .authorizeRequests(authorizeRequests -> authorizeRequests
                        .anyRequest().authenticated());

        http
                .oauth2Login().clientRegistrationRepository(clientRegistrationRepository())
                .defaultSuccessUrl(targetUrl)
                .userInfoEndpoint().userService(oAuth2UserService);

        http
                .logout().logoutUrl("/");

    }

    @Bean
    public ClientRegistrationRepository clientRegistrationRepository() {
        providers = Arrays.asList(socialType.split("/"));

        List<ClientRegistration> registrations = providers.stream().map(this::getRegistration)
                .filter(Objects::nonNull).collect(Collectors.toList());

        return new InMemoryClientRegistrationRepository(registrations);
    }

    private ClientRegistration getRegistration(String client) {

        String clientId = clientConfigurationValue(client, "client-id");
        String clientSecret = clientConfigurationValue(client, "client-secret");
        if (clientId == null) return null;

        for (String provider: providers) {
            if (provider.equals(client)) {
                return CommonOAuth2Provider.valueOf(provider.toUpperCase())
                        .getBuilder(client)
                        .clientId(clientId)
                        .clientSecret(clientSecret)
                        .build();
            }
        }
        return null;
    }

    private String clientConfigurationValue(String clientKey, String clientValue) {
        return environment.getProperty(CLIENT_CONFIGURATION_KEY + clientKey + "." + clientValue);
    }

//    @Bean
//    public OAuth2AuthorizedClientService authorizedClientService() {
//        return new InMemoryOAuth2AuthorizedClientService(clientRegistrationRepository());
//    }

//    AuthenticationSuccessHandler authenticationSuccessHandler = new AuthenticationSuccessHandler() {
//
//        @Override
//        public void onAuthenticationSuccess(
//                HttpServletRequest request, HttpServletResponse response, Authentication authentication
//        ) throws IOException {
//            setRedirectUrl(request, response);
//        }
//
//        protected void setRedirectUrl(HttpServletRequest request, HttpServletResponse response) throws IOException {
//            redirectStrategy.sendRedirect(request, response, targetUrl);
//        }
//    };

}

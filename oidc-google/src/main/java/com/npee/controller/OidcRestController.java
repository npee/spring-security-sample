package com.npee.controller;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class OidcRestController {

    @GetMapping("/me/google")
    public OidcUser getGoogleProfile(@AuthenticationPrincipal OidcUser user) {
        return user;
    }

    @GetMapping("/logoutPage")
    public String logout() {
        return "Logout Page";
    }
}
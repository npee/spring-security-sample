package com.npee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/login/auth")
public class OAuth2RestController {

    @GetMapping("/{provider}")
    public String loginWithProvider(@PathVariable String provider, Principal principal) {
        return provider;
    }
}

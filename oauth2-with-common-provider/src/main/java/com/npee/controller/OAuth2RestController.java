package com.npee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class OAuth2RestController {

    @GetMapping("/me")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }
}

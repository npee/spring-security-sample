package com.npee.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    public String index() {
        return "main page";
    }

    @GetMapping("/me")
    public Principal getPrincipal(Principal principal) {
        return principal;
    }
}

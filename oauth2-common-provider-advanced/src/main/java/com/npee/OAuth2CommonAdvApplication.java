package com.npee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OAuth2CommonAdvApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OAuth2CommonAdvApplication.class).run(args);
    }
}

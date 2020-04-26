package com.npee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OAuth2AdvApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OAuth2AdvApplication.class).run(args);
    }
}

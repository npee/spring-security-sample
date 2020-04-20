package com.npee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OAuth2Application {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OAuth2Application.class).run(args);
    }
}

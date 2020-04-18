package com.npee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OidcApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OidcApplication.class).run(args);
    }
}

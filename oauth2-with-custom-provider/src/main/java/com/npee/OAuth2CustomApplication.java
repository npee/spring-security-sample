package com.npee;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class OAuth2CustomApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(OAuth2CustomApplication.class).run(args);
    }
}

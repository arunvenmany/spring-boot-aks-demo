package com.spring.handson.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration("WebConfig")
public class WebConfig extends WebMvcConfigurerAdapter {

    public static final String ALLOWED_ORIGINS = "*";

    @Override
    public void addCorsMappings(final CorsRegistry registry) {
        registry.addMapping("/users/**")
                .allowedOrigins(ALLOWED_ORIGINS)
                .allowedMethods("HEAD", "GET", "PUT", "POST", "DELETE", "PATCH");
    }
}

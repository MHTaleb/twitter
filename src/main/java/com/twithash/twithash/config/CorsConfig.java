package com.twithash.twithash.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.CorsRegistry;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.config.WebFluxConfigurer;

/**
 * cors config to enable receiving request from angular app
 * this rules say to accept all requests from lh:4200
 * */
@Configuration
@EnableWebFlux
public class CorsConfig implements WebFluxConfigurer {
 
    @Override
    public void addCorsMappings(CorsRegistry corsRegistry) {
        corsRegistry.addMapping("/**")
          .allowedOrigins("http://localhost:4200")
          .allowedMethods("*")
          .allowedHeaders("*")
          .allowCredentials(true)
          .maxAge(3600);
    }
}
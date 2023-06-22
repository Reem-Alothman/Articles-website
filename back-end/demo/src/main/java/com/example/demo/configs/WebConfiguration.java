package com.example.demo.configs;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration implements WebMvcConfigurer {
    private String[] allowDomains = new String[3];

    @Override
    public void addCorsMappings(CorsRegistry registry){

        allowDomains[0] = "http://localhost:8080";
        allowDomains[1] = "http://localhost:4200";

        registry.addMapping("/**")
                .allowedOrigins(allowDomains)
                .allowedMethods("PUT", "DELETE", "POST", "GET")
                .exposedHeaders("Authorization");
    }
}

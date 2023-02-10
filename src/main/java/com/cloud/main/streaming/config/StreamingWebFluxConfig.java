package com.cloud.main.streaming.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.reactive.config.WebFluxConfigurer;

import com.cloud.main.streaming.properties.CorsProperties;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class StreamingWebFluxConfig implements WebFluxConfigurer {
    @Autowired
    private CorsProperties     corsProperties;

    @Bean
    public CorsWebFilter corsWebFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(false);
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        corsConfiguration.setAllowedOrigins(corsProperties.getAllowedOrigin());

        corsConfiguration.setMaxAge(3600L);

        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration);

        log.info("CORS: allowed to {}", corsProperties.getAllowedOrigin());
        return new CorsWebFilter(corsConfigurationSource);
    }
}
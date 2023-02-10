package com.cloud.main.streaming.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;


@Configuration
@EnableWebFluxSecurity
public class StreamingSecurityConfig {
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {

        http
        	.authorizeExchange().pathMatchers("/**").permitAll();

        http
	        .csrf().disable()
	        .formLogin().disable()
	        .httpBasic().disable();

        final SecurityWebFilterChain build = http.build();

        return build;
    }
}
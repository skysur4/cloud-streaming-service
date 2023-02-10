package com.cloud.main.streaming.properties;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@Data
@ConfigurationProperties(prefix = "cloud.cors")
public class CorsProperties {

    private List<String> allowedOrigin;
}

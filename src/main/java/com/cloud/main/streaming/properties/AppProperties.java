package com.cloud.main.streaming.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Data;

@Data
@Configuration
@ConfigurationProperties(prefix = "application")
public class AppProperties {

    /** Locale resolver (cookie or session) */
    private String localeResolver = "cookie";

    /** name */
    private String name;

    /** code */
    private String code;

    /** Contact name */
    private String contactName;

    /** Contact url */
    private String   contactUrl;

    /** Contact email */
    private String   contactEmail;

}

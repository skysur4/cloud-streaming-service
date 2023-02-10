package com.cloud.main.streaming.config;

import org.springdoc.core.GroupedOpenApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloud.main.streaming.properties.SwaggerProperties;

import io.swagger.v3.oas.models.OpenAPI;

@Configuration
public class StreamingSwaggerConfig {
	@Autowired
	SwaggerProperties swaggerProperties;

	@Bean
	GroupedOpenApi consoleApi() {
		return GroupedOpenApi.builder()
				.group("console")
	            .pathsToMatch("/console/**")
	            .build();
	  }

	@Bean
	GroupedOpenApi internalApi() {
		return GroupedOpenApi.builder()
			.group("internal")
			.pathsToMatch("/internal/**")
			//.addOpenApiMethodFilter(method -> method.isAnnotationPresent(Admin))
			.build();
	  }

	  @Bean
	  OpenAPI springGatewayOpenAPI() {
		  return swaggerProperties.getApiInfo();
	  }
}
package com.cloud.main.streaming.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@ConfigurationProperties(prefix = "springdoc.api-info")
public class SwaggerProperties {
	private String title;
	private String description;
	private String version;
	private License license;
	private Contact contact;

	public OpenAPI getApiInfo() {
		return new OpenAPI()
		        .info(new Info()
		        		.title(title)
		        		.description(description)
		        		.version(version)
		        		.license(license)
//		        ).externalDocs(new ExternalDocumentation()
//		        		.description("SpringShop Wiki Documentation")
//		        		.url("https://springshop.wiki.github.org/docs")
		        );
	}
}
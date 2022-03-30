package com.weather.prediction.config;
import org.springframework.context.annotation.Configuration;


import org.springframework.context.annotation.Bean;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {                                    
	@Bean
	public Docket api() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.withClassAnnotation(EnableSwaggerDoc.class))
				.paths(PathSelectors.any())
				.build().apiInfo(getApiInfo());
	}
	
	private ApiInfo getApiInfo() { 
		return new ApiInfoBuilder()
				.contact(new Contact("Weather Forecast", "https://www.weatherForecast.com", "ravin.kaur24@gmail.com"))
				.description("Documentation for Weather Forecast related APIs to call from Web/Mobile")
				.license("MIT Licence").licenseUrl("https://opensource.org/licenses/MIT")
				.title("Weather Forecast Enrollment REST API").version("1.0")
				.termsOfServiceUrl("Terms of service").build();
	}
}

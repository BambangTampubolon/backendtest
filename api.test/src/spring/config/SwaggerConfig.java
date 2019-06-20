package spring.config;

import org.springframework.context.annotation.Configuration;

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
	public Docket api(){
		return new Docket(DocumentationType.SWAGGER_2).select().apis(
				RequestHandlerSelectors.any()).paths(PathSelectors.any()).build().apiInfo(metaData());
	}
	
	private ApiInfo metaData(){
		ApiInfo apiInfo = new ApiInfo("TEST", "REST API FOR TEST", "1.0", "TERMS OF SERVICE", 
				new Contact("BAMBANG", "BAMBANG", "BAMBANG@BAMBANG.COM"), "Apache License Version 2.0", "https://www.apache.org/licenses/LICENSE-2.0");
		return apiInfo;
	}
}

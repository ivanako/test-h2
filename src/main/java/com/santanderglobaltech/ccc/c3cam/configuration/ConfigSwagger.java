package com.santanderglobaltech.ccc.c3cam.configuration;

import static springfox.documentation.schema.AlternateTypeRules.newRule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.async.DeferredResult;

import com.fasterxml.classmate.TypeResolver;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.WildcardType;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Tag;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class ConfigSwagger {
	
	public static final String TAG_BROKERS = "Brokers";
	public static final String TAG_SMS_REQUESTS = "SMS Requests";
//	public static final String TAG_AUTHORISATION = "Authorisation";
//	public static final String TAG_ENTITIES = "Entities";
	
	@Autowired
    private TypeResolver typeResolver;
	

	@Value("${spring.application.version}")
	private String appVersion;

	@Value("${spring.application.description}")
	private String appDesc;
	
	@Autowired
	private BuildProperties props;
	
	@Bean
	public Docket petApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.santanderglobaltech.ccc.c3cam.web"))
				.paths(PathSelectors.any())
				.build().pathMapping("/").genericModelSubstitutes(ResponseEntity.class)
				.alternateTypeRules(newRule(
						typeResolver.resolve(DeferredResult.class,
								typeResolver.resolve(ResponseEntity.class, WildcardType.class)),
						typeResolver.resolve(WildcardType.class)))
				.useDefaultResponseMessages(false)
//				.globalResponseMessage(RequestMethod.GET,
//						newArrayList(new ResponseMessageBuilder().code(500).message("500 message")
//								.responseModel(new ModelRef("Error")).build()))
				.enableUrlTemplating(false)
				.tags(new Tag(TAG_BROKERS, "Manage Brokers"))
				.tags(new Tag(TAG_SMS_REQUESTS, "Manage SMS Requests "))
//				.tags(new Tag(TAG_AUTHORISATION, "Manage system authorisation"))
//				.tags(new Tag(TAG_ENTITIES, "Manage Entities and related objects"))
				.apiInfo(apiInfo());
	}

	private ApiInfo apiInfo() {
		Contact cccSaaS = new Contact("CCC SaaS", "", "saas@santandernet.onmicrosoft.com");
		
		return new ApiInfoBuilder()
	            .title("C3CAM POC API documentation")
//	            .description(appDesc)
	            .description(props.get("app.desc"))
	            .termsOfServiceUrl("urn:tos")
	            .contact(cccSaaS)
	            .license("Apache License Version 2.0")
	            .licenseUrl("https://www.apache.org/licenses/LICENSE-2.0")
//	            .version(appVersion)
	            .version(props.getVersion())
	            .build();
	}
}


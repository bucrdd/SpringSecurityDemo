package com.example.config;

import com.github.xiaoymin.knife4j.spring.annotations.EnableKnife4j;
import com.github.xiaoymin.knife4j.spring.extension.ApiOrderExtension;
import com.google.common.collect.Lists;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import springfox.bean.validators.configuration.BeanValidatorPluginsConfiguration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.service.SecurityScheme;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
@EnableKnife4j
@Import(BeanValidatorPluginsConfiguration.class)
public class SwaggerConfig {

  @Bean
  public Docket api() {
    /*
    ParameterBuilder parameterBuilder = new ParameterBuilder();
    List<Parameter> parameters = Lists.newArrayList();
    parameterBuilder.name("token").description("token令牌").modelRef(new ModelRef("String"))
        .parameterType("header")
        .required(true).build();
    parameters.add(parameterBuilder.build());
    */

    Docket docket = new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(apiInfo())
        .select()
        //.apis(RequestHandlerSelectors.any())
        .apis(RequestHandlerSelectors.basePackage("com.example"))
        .paths(PathSelectors.any())
        .build()//.extensions(Lists.newArrayList(new ApiOrderExtension(1))).globalOperationParameters(parameters)
        .securityContexts(Lists.newArrayList(securityContext()))
        .securitySchemes(Lists.<SecurityScheme>newArrayList(apiKey()));
    return docket;

  }

  private ApiInfo apiInfo() {
    return new ApiInfoBuilder()
        .title("swagger-bootstrap-ui RESTFUL APIs")
        .description("swagger-bootstrap-ui")
        .termsOfServiceUrl("http://localhost:18900/")
        .contact(new Contact("bucr", "no url", "no email"))
        .version("1.0")
        .build();
  }


  private ApiKey apiKey() {
    return new ApiKey("BearerToken", "Authorization", "header");
  }

  private SecurityContext securityContext() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth())
        .forPaths(PathSelectors.regex("/.*"))
        .build();
  }

  List<SecurityReference> defaultAuth() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Lists.newArrayList(new SecurityReference("BearerToken", authorizationScopes));
  }

/*
  private ApiKey apiKey1() {
    return new ApiKey("BearerToken1", "Authorization-x", "header");
  }

  private SecurityContext securityContext1() {
    return SecurityContext.builder()
        .securityReferences(defaultAuth1())
        .forPaths(PathSelectors.regex("/.*"))
        .build();
  }

  List<SecurityReference> defaultAuth1() {
    AuthorizationScope authorizationScope = new AuthorizationScope("global", "accessEverything");
    AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
    authorizationScopes[0] = authorizationScope;
    return Lists.newArrayList(new SecurityReference("BearerToken1", authorizationScopes));
  }
  */

}

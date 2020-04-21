package com.istio.demo;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * The Class SwaggerConfig. Swagger is configured in application context init in this class.
 */
@Configuration
@EnableSwagger2
public class SwaggerAPIConfig {

  /** The Constant logger. */
  private static final Logger logger = LoggerFactory.getLogger(SwaggerAPIConfig.class);

  /**
   * Application Version property
   */
  @Value("${spring.application.version}")
  private String applicationVersion;

  /**
   * Instantiates a new swagger config.
   */
  public SwaggerAPIConfig() {
    logger.info("-> SwaggerConfig");
  }

  /**
   * Custom Docket implementation.
   *
   * @return the Docket
   */

  @Bean
  public Docket productApi() {

    List<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>();
    responseMessages.add(new ResponseMessage(200, "Success", null, null, null));
    responseMessages.add(new ResponseMessage(400, "Bad Request", null, null, null));
    responseMessages.add(new ResponseMessage(401,
        "Unauthorized (Not logged in or invalid session or resource cannot be accessed)", null, null, null));
    responseMessages.add(new ResponseMessage(404, "Requested resource is not found", null, null, null));
    responseMessages.add(new ResponseMessage(500, "Internal Server Error", null, null, null));
    return new Docket(DocumentationType.SWAGGER_2).select().paths(PathSelectors.any()).build().apiInfo(apiInfo());
  }


  /**
   * Api info.
   *
   * @return the api info
   */
  private ApiInfo apiInfo() {
    logger.trace("-> apiInfo");
    return new ApiInfoBuilder().title("Role").description("REST API contracts")
        .version(applicationVersion).build();
  }
}

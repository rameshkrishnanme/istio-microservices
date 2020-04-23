package com.istio.demo.feign.error;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import feign.FeignException;
import feign.Response;
import feign.codec.ErrorDecoder;

/**
 * 
 * FeignClientErrorDecoder
 *
 */
public class FeignClientErrorDecoder implements ErrorDecoder {

  /** The Constant logger. */
  private static final Logger logger = LoggerFactory.getLogger(FeignClientErrorDecoder.class);


  @Override
  public Exception decode(String methodKey, Response response) {

    if (HttpStatus.UNAUTHORIZED.value() == response.status()) {
      logger.error("Feign Auth (401) detected. Possibly stale token - clearing auth token from context");

    }
    if (HttpStatus.REQUEST_TIMEOUT.value() == response.status()) {
      logger.error("Feign Timeout (408) detected. Retrying the request");

    }
    return FeignException.errorStatus(methodKey, response);
  }
}

package org.beizix.core.configuration.adapter.web.rest;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/** Rest API 예외 처리 DTO */
@Builder
@Data
public class RestExceptionDto {
  private String message;
  private HttpStatus httpStatus;
}

package org.beizix.core.config.adapter.web.advice;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

/** Rest API 예외 처리 DTO */
@Builder
@Data
public class GlobalRestExceptionVO {
  private String message;
  private HttpStatus httpStatus;
}

package app.module.core.config.adapter.web.advice;

import lombok.*;
import org.springframework.http.HttpStatus;

/** Rest API 예외 처리 DTO */
@Getter
@Setter
@AllArgsConstructor
public class GlobalRestExceptionVO {
  private String exception;
  private String message;
}

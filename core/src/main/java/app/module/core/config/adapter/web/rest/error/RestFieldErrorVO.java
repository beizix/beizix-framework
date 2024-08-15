package app.module.core.config.adapter.web.rest.error;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Rest API Validation 실패 처리 DTO */
@Data
@AllArgsConstructor
public class RestFieldErrorVO {
  private String field;
  private String message;
}

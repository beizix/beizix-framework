package org.beizix.core.config.adapter.web.rest;

import lombok.AllArgsConstructor;
import lombok.Data;

/** Rest API Validation 실패 처리 DTO */
@Data
@AllArgsConstructor
public class RestFieldErrorDto {
  private String field;
  private String message;
}

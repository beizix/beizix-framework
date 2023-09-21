package org.beizix.core.config.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/** 파일 업로드 시 Validation 에 위배될 때 발생하는 커스텀 RuntimeException */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnAcceptableFileException extends RuntimeException {
  private String message;
  private String field;

  public UnAcceptableFileException(String message) {
    super(message);
    this.message = message;
  }
}

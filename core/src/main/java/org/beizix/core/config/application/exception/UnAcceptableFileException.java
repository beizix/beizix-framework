package org.beizix.core.config.application.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.beizix.core.config.application.enums.FileUploadType;

/** 파일 업로드 시 Validation 에 위배될 때 발생하는 커스텀 RuntimeException */
@EqualsAndHashCode(callSuper = true)
@Data
public class UnAcceptableFileException extends RuntimeException {
  private String message;
  private FileUploadType fileUploadType;

  public UnAcceptableFileException(FileUploadType fileUploadType, String message) {
    super(message);
    this.fileUploadType = fileUploadType;
    this.message = message;
  }
}

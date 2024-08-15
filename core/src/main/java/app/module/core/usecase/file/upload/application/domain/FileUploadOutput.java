package app.module.core.usecase.file.upload.application.domain;

import lombok.*;
import app.module.core.config.application.enums.FileUploadType;

@Getter
@Setter
@AllArgsConstructor
public class FileUploadOutput {
  private FileUploadType type;
  private String path;
  private String name;
  private String originName;
  private Long fileLength;
}

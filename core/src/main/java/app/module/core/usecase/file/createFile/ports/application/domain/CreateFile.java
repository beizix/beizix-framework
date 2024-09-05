package app.module.core.usecase.file.createFile.ports.application.domain;

import app.module.core.config.application.enums.FileUploadType;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateFile {
  private Long id;
  private FileUploadType type;
  private String path;
  private String name;
  private String originName;
  private Long fileLength;
}

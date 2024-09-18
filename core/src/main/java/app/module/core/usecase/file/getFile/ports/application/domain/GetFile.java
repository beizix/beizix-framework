package app.module.core.usecase.file.getFile.ports.application.domain;

import app.module.core.config.application.enums.FileUploadType;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetFile {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private Long id;
  private FileUploadType type;
  private String path;
  private String name;
  private String originName;
  private Long fileLength;
}

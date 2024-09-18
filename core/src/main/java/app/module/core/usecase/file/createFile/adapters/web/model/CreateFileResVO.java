package app.module.core.usecase.file.createFile.adapters.web.model;

import app.module.core.config.application.enums.FileUploadType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateFileResVO {
  private Long id;
  private FileUploadType type;
  private String path;
  private String name;
  private String originName;
  private Long fileLength;
  private String referURL;
}

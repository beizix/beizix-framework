package app.module.core.usecase.file.createFile.adapters.web.model;

import app.module.core.config.application.enums.FileUploadType;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class CreateFileReqVO {
  private FileUploadType fileUploadType;
}

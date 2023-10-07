package org.beizix.core.application.domain.fileupload.model;

import lombok.*;
import org.beizix.core.config.enums.FileUploadType;

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

package org.beizix.core.application.domain.fileupload.model;

import lombok.*;
import org.beizix.core.config.enums.FileUploadType;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadInfo {
  private FileUploadType type;

  private String path;
  private String name;
  private String originName;

  private Long fileLength;
}

package org.beizix.core.feature.fileUpload.persistence.model;

import lombok.*;
import org.beizix.core.config.enums.FileUploadType;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Comment;

@Embeddable
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FileUploadInfoEmbeddable {
  @Enumerated(EnumType.STRING)
  @Comment("파일타입")
  private FileUploadType type;

  @Comment("파일경로")
  private String path;

  @Comment("파일명")
  private String name;

  @Comment("원본파일명")
  private String originName;

  @Comment("파일크기")
  private Long fileLength;
}

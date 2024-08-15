package app.module.core.config.adapter.persistence.component;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import app.module.core.config.application.enums.FileUploadType;
import lombok.*;
import org.hibernate.annotations.Comment;

/** 파일 업로드 정보를 담는 공용 엔티티 속성. 다른 엔티티에 탑재(embed)되어 사용된다. */
@Embeddable
@Getter
@Setter
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

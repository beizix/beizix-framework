package app.module.core.config.adapter.persistence.entity;

import app.module.core.config.adapter.persistence.component.AuditEntity;
import app.module.core.config.adapter.persistence.component.FileUploadInfoEmbeddable;
import javax.persistence.*;

import app.module.core.config.application.enums.FileUploadType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;

@Entity
@Table(name = "upload_file")
@org.hibernate.annotations.Table(appliesTo = "upload_file", comment = "공용 첨부파일 테이블")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UploadFile extends AuditEntity {
  @Id
  @GeneratedValue
  @Comment("첨부 파일 아이디")
  private Long id;

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

  public UploadFile(Long id) {
    this.id = id;
  }
}

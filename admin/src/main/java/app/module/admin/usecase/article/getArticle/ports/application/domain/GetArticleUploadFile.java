package app.module.admin.usecase.article.getArticle.ports.application.domain;

import app.module.core.config.application.enums.FileUploadType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/** DTO for {@link app.module.core.config.adapter.persistence.entity.UploadFile} */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetArticleUploadFile implements Serializable {
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

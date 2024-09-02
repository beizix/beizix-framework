package app.module.admin.usecase.article.createArticle.ports.application.domain;

import app.module.core.config.adapter.persistence.entity.UploadFile;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateArticleCmd {
  private String title;
  private String content;
  private Boolean visible;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  Integer orderNo;
  private List<UploadFile> uploadFiles;
}

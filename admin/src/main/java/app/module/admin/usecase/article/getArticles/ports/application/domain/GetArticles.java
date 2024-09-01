package app.module.admin.usecase.article.getArticles.ports.application.domain;

import app.module.core.config.adapter.persistence.entity.UploadFile;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetArticles {
  private Long id;
  private String title;
  private String content;
  private Boolean visible;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  Integer orderNo;
  private List<GetArticleFiles> uploadFiles;
}

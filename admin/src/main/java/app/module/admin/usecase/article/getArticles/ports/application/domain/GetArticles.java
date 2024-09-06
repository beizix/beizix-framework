package app.module.admin.usecase.article.getArticles.ports.application.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetArticles {
  private Long id;
  private String updatedBy;
  private LocalDateTime updatedAt;
  private String title;
  private String content;
  private Boolean visible;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  Integer orderNo;
  private GetArticleFiles uploadFile;
}

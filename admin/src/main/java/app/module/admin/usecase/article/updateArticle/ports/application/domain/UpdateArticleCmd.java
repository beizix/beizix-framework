package app.module.admin.usecase.article.updateArticle.ports.application.domain;

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
public class UpdateArticleCmd {
  private Long id;
  private String title;
  private String content;
  private Boolean visible;
  private Integer orderNo;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private List<String> fileMappingId;
}

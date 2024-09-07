package app.module.admin.usecase.article.getArticle.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetArticle {
  private LocalDateTime createdAt;
  private String createdBy;
  private LocalDateTime updatedAt;
  private String updatedBy;
  private Long id;
  private String title;
  private String content;
  private Boolean visible;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Integer orderNo;
  private List<GetArticleUploadFile> uploadFiles;
}

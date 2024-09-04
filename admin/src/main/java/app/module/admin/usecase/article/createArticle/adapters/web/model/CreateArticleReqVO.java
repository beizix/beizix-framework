package app.module.admin.usecase.article.createArticle.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class CreateArticleReqVO {
  @NotBlank(message = "{valid.common.required}")
  private String title;

  @NotBlank(message = "{valid.common.required}")
  private String content;

  private Boolean visible;

  @NotNull(message = "{valid.common.required}")
  private LocalDateTime startDate;

  @NotNull(message = "{valid.common.required}")
  private LocalDateTime endDate;
}

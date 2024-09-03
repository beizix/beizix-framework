package app.module.admin.usecase.article.sortArticles.adapters.web.model;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class SortArticlesReqVO {
  @NotNull(message = "{valid.common.required}")
  private Long id;

  @NotNull(message = "{valid.common.required}")
  private Integer orderNo;
}

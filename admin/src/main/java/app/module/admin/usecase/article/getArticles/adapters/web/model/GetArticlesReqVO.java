package app.module.admin.usecase.article.getArticles.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetArticlesReqVO {
  private String searchField;
  private String searchValue;
  private String searchOpen;
}

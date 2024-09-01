package app.module.admin.usecase.article.getArticles.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class GetArticlesCmd {
  private String searchField;
  private String searchValue;
  private String searchOpen;
}

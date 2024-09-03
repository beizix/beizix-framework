package app.module.admin.usecase.article.sortArticles.ports.application.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class SortArticlesCmd {
  private Long id;
  private Integer orderNo;
}

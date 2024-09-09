package app.module.admin.usecase.article.removeArticles.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class RemoveArticlesReqVO {
  private Set<Long> selectIds;
}

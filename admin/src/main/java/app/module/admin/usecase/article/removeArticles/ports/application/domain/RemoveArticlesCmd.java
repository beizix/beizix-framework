package app.module.admin.usecase.article.removeArticles.ports.application.domain;

import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class RemoveArticlesCmd {
  private Set<Long> removeIds;
}

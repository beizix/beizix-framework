package app.module.admin.usecase.article.getArticle.ports;

import java.util.Optional;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticle;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticleCmd;

public interface GetArticlePortIn {
  Optional<GetArticle> operate(GetArticleCmd command);
}
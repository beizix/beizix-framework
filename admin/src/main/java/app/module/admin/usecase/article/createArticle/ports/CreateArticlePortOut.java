package app.module.admin.usecase.article.createArticle.ports;

import java.util.Optional;
import app.module.admin.usecase.article.createArticle.ports.application.domain.CreateArticle;
import app.module.admin.usecase.article.createArticle.ports.application.domain.CreateArticleCmd;

public interface CreateArticlePortOut {
  Optional<CreateArticle> operate(CreateArticleCmd command);
}
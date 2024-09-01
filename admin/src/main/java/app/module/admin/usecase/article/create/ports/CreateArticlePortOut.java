package app.module.admin.usecase.article.create.ports;

import java.util.Optional;
import app.module.admin.usecase.article.create.ports.application.domain.CreateArticle;
import app.module.admin.usecase.article.create.ports.application.domain.CreateArticleCmd;

public interface CreateArticlePortOut {
  Optional<CreateArticle> operate(CreateArticleCmd command);
}
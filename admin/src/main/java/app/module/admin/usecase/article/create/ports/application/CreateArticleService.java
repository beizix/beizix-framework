package app.module.admin.usecase.article.create.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.create.ports.application.domain.CreateArticle;
import app.module.admin.usecase.article.create.ports.application.domain.CreateArticleCmd;
import app.module.admin.usecase.article.create.ports.CreateArticlePortIn;
import app.module.admin.usecase.article.create.ports.CreateArticlePortOut;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateArticleService implements CreateArticlePortIn {
  private final CreateArticlePortOut portOut;

  @Override
  public Optional<CreateArticle> operate(CreateArticleCmd command) {
    return portOut.operate(command);
  }
}
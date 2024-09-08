package app.module.admin.usecase.article.updateArticle.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.updateArticle.ports.application.domain.UpdateArticleCmd;
import app.module.admin.usecase.article.updateArticle.ports.UpdateArticlePortIn;
import app.module.admin.usecase.article.updateArticle.ports.UpdateArticlePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class UpdateArticleService implements UpdateArticlePortIn {
  private final UpdateArticlePortOut portOut;

  @Override
  public void operate(UpdateArticleCmd command) {
    portOut.operate(command);
  }
}
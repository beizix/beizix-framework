package app.module.admin.usecase.article.removeArticles.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.removeArticles.ports.application.domain.RemoveArticlesCmd;
import app.module.admin.usecase.article.removeArticles.ports.RemoveArticlesPortIn;
import app.module.admin.usecase.article.removeArticles.ports.RemoveArticlesPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class RemoveArticlesService implements RemoveArticlesPortIn {
  private final RemoveArticlesPortOut portOut;

  @Override
  public void operate(RemoveArticlesCmd command) {
    portOut.operate(command);
  }
}
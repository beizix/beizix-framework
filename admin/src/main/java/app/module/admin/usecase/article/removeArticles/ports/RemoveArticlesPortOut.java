package app.module.admin.usecase.article.removeArticles.ports;

import app.module.admin.usecase.article.removeArticles.ports.application.domain.RemoveArticlesCmd;

public interface RemoveArticlesPortOut {
  void operate(RemoveArticlesCmd command);
}
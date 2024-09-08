package app.module.admin.usecase.article.updateArticle.ports;

import app.module.admin.usecase.article.updateArticle.ports.application.domain.UpdateArticleCmd;

public interface UpdateArticlePortOut {
  void operate(UpdateArticleCmd command);
}
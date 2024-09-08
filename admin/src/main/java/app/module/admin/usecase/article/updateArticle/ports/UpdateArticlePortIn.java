package app.module.admin.usecase.article.updateArticle.ports;

import app.module.admin.usecase.article.updateArticle.ports.application.domain.UpdateArticleCmd;

public interface UpdateArticlePortIn {
  void operate(UpdateArticleCmd command);
}
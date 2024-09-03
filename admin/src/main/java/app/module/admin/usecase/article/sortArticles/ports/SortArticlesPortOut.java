package app.module.admin.usecase.article.sortArticles.ports;

import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;

public interface SortArticlesPortOut {
  void operate(SortArticlesCmd command);
}
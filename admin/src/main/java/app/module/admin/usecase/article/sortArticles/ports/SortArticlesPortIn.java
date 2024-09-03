package app.module.admin.usecase.article.sortArticles.ports;

import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;

import java.util.List;

public interface SortArticlesPortIn {
  void operate(List<SortArticlesCmd> command);
}
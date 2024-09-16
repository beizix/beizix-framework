package app.module.admin.usecase.article.sortArticles.ports;

import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;

import java.util.List;

public interface SortArticlesPortOut {
  void operate(List<SortArticlesCmd> command);
}
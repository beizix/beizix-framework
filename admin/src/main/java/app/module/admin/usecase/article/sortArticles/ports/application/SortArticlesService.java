package app.module.admin.usecase.article.sortArticles.ports.application;

import app.module.admin.usecase.article.sortArticles.ports.SortArticlesPortIn;
import app.module.admin.usecase.article.sortArticles.ports.SortArticlesPortOut;
import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class SortArticlesService implements SortArticlesPortIn {
  private final SortArticlesPortOut portOut;

  @Override
  public void operate(List<SortArticlesCmd> command) {
    portOut.operate(command);
  }
}

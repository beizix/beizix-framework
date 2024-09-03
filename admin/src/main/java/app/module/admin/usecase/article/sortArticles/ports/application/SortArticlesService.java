package app.module.admin.usecase.article.sortArticles.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;
import app.module.admin.usecase.article.sortArticles.ports.SortArticlesPortIn;
import app.module.admin.usecase.article.sortArticles.ports.SortArticlesPortOut;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
class SortArticlesService implements SortArticlesPortIn {
  private final SortArticlesPortOut portOut;

  @Override
  @Transactional
  public void operate(List<SortArticlesCmd> command) {
    command.forEach(sortArticlesCmd -> portOut.operate(sortArticlesCmd));
  }
}

package app.module.admin.usecase.article.sortArticles.adapters.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;
import app.module.admin.usecase.article.sortArticles.ports.SortArticlesPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class SortArticlesDao implements SortArticlesPortOut {
  private final SortArticlesRepo sortArticlesRepo;

  @Override
  public void operate(SortArticlesCmd command) {
    sortArticlesRepo.operate(command.getId(), command.getOrderNo());
  }
}

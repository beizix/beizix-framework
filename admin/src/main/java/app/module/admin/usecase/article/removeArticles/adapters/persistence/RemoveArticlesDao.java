package app.module.admin.usecase.article.removeArticles.adapters.persistence;

import app.module.admin.usecase.article.removeArticles.ports.RemoveArticlesPortOut;
import app.module.admin.usecase.article.removeArticles.ports.application.domain.RemoveArticlesCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class RemoveArticlesDao implements RemoveArticlesPortOut {
  private final RemoveArticlesRepo removeArticlesRepo;

  @Override
  public void operate(RemoveArticlesCmd command) {
    removeArticlesRepo.deleteAllById(command.getRemoveIds());
  }
}

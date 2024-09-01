package app.module.admin.usecase.article.create.adapters.persistence;

import app.module.admin.usecase.article.create.ports.CreateArticlePortOut;
import app.module.admin.usecase.article.create.ports.application.domain.CreateArticle;
import app.module.admin.usecase.article.create.ports.application.domain.CreateArticleCmd;
import app.module.core.config.adapter.persistence.entity.Article;
import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class CreateArticleDao implements CreateArticlePortOut {
  private final CreateArticleRepo createArticleRepo;

  @Override
  @Transactional
  public Optional<CreateArticle> operate(CreateArticleCmd command) {
    Article article =
        createArticleRepo.save(
            new Article(
                null,
                command.getTitle(),
                command.getContent(),
                command.getVisible(),
                command.getStartDate(),
                command.getEndDate(),
                command.getOrderNo(),
                command.getUploadFiles()));

    return Optional.of(
        new CreateArticle(
            article.getId(),
            article.getTitle(),
            article.getContent(),
            article.getVisible(),
            article.getStartDate(),
            article.getEndDate(),
            article.getOrderNo(),
            article.getUploadFiles()));
  }
}

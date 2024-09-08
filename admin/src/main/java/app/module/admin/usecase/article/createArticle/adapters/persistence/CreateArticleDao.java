package app.module.admin.usecase.article.createArticle.adapters.persistence;

import app.module.admin.usecase.article.createArticle.ports.CreateArticlePortOut;
import app.module.admin.usecase.article.createArticle.ports.application.domain.CreateArticle;
import app.module.admin.usecase.article.createArticle.ports.application.domain.CreateArticleCmd;
import app.module.core.config.adapter.persistence.entity.Article;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.transaction.Transactional;

import app.module.core.config.adapter.persistence.entity.UploadFile;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
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
                createArticleRepo.getMaxOrderNo().orElse(-1) + 1,
                CollectionUtils.emptyIfNull(command.getFileMappingId()).stream()
                    .map(mappingId -> new UploadFile(Long.parseLong(mappingId)))
                    .collect(Collectors.toList())));

    return Optional.of(new CreateArticle(article.getId(), article.getOrderNo()));
  }
}

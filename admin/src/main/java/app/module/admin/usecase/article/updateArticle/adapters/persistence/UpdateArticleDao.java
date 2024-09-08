package app.module.admin.usecase.article.updateArticle.adapters.persistence;

import app.module.core.config.adapter.persistence.entity.Article;
import app.module.core.config.adapter.persistence.entity.UploadFile;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.article.updateArticle.ports.application.domain.UpdateArticleCmd;
import app.module.admin.usecase.article.updateArticle.ports.UpdateArticlePortOut;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.stereotype.Repository;

import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
class UpdateArticleDao implements UpdateArticlePortOut {
  private final UpdateArticleRepo updateArticleRepo;

  @Override
  public void operate(UpdateArticleCmd command) {
    updateArticleRepo.save(
        new Article(
            command.getId(),
            command.getTitle(),
            command.getContent(),
            command.getVisible(),
            command.getStartDate(),
            command.getEndDate(),
            command.getOrderNo(),
            CollectionUtils.emptyIfNull(command.getFileMappingId()).stream()
                .map(mappingId -> new UploadFile(Long.parseLong(mappingId)))
                .collect(Collectors.toList())));
  }
}

package app.module.admin.usecase.article.getArticle.adapters.persistence;

import app.module.admin.usecase.article.getArticle.ports.GetArticlePortOut;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticle;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticleCmd;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticleUploadFile;
import java.util.Optional;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
class GetArticleDao implements GetArticlePortOut {
  private final GetArticleRepo getArticleRepo;

  @Override
  @Transactional
  public Optional<GetArticle> operate(GetArticleCmd command) {

    return getArticleRepo
        .findById(command.getId())
        .map(
            article ->
                new GetArticle(
                    article.getCreatedAt(),
                    article.getCreatedBy(),
                    article.getUpdatedAt(),
                    article.getUpdatedBy(),
                    article.getId(),
                    article.getTitle(),
                    article.getContent(),
                    article.getVisible(),
                    article.getStartDate(),
                    article.getEndDate(),
                    article.getOrderNo(),
                    article.getUploadFiles().stream()
                        .map(
                            uploadFile ->
                                new GetArticleUploadFile(
                                    uploadFile.getCreatedAt(),
                                    uploadFile.getCreatedBy(),
                                    uploadFile.getUpdatedAt(),
                                    uploadFile.getUpdatedBy(),
                                    uploadFile.getId(),
                                    uploadFile.getType(),
                                    uploadFile.getPath(),
                                    uploadFile.getName(),
                                    uploadFile.getOriginName(),
                                    uploadFile.getFileLength()))
                        .collect(Collectors.toList())));
  }
}

package app.module.admin.usecase.article.getArticles.adapters.persistence;

import app.module.admin.usecase.article.getArticles.ports.GetArticlesPortOut;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticleFiles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticlesCmd;
import app.module.core.config.adapter.persistence.entity.Article;
import app.module.core.config.application.enums.FileUploadType;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import javax.transaction.Transactional;

@Repository
@RequiredArgsConstructor
class GetArticlesDao implements GetArticlesPortOut {
  private final GetArticlesRepo getArticlesRepo;

  @Override
  @Transactional
  public Page<GetArticles> operate(Pageable pageable, GetArticlesCmd command) {
    // 검색조건 초기화
    Specification<Article> spec = (root, query, criteriaBuilder) -> null;

    // 검색어 - like 검색
    if (!StringUtils.isEmpty(command.getSearchField())) {
      spec =
          spec.and(
              (root, query, builder) ->
                  builder.like(
                      root.get(command.getSearchField()), "%" + command.getSearchValue() + "%"));
    }

    // 공개여부 검색
    if (!StringUtils.isEmpty(command.getSearchOpen())) {
      spec =
          spec.and(
              ((root, query, criteriaBuilder) ->
                  criteriaBuilder.equal(
                      root.get("visible"), Boolean.valueOf(command.getSearchOpen()))));
    }

    Page<Article> result = getArticlesRepo.findAll(spec, pageable);

    return new PageImpl<>(
        result.getContent().stream()
            .map(
                article ->
                    new GetArticles(
                        article.getId(),
                        article.getTitle(),
                        article.getContent(),
                        article.getVisible(),
                        article.getStartDate(),
                        article.getEndDate(),
                        article.getOrderNo(),
                        article.getUploadFiles().stream()
                            // 목록에 보여줄 대표이미지만 담는다
                            .filter(
                                uploadFile ->
                                    uploadFile.getType().equals(FileUploadType.EXAMPLE_REP))
                            .map(
                                uploadFile ->
                                    new GetArticleFiles(
                                        uploadFile.getId(),
                                        uploadFile.getType(),
                                        uploadFile.getPath(),
                                        uploadFile.getName(),
                                        uploadFile.getOriginName(),
                                        uploadFile.getFileLength()))
                            .collect(Collectors.toList())))
            .collect(Collectors.toList()),
        pageable,
        result.getTotalElements());
  }
}

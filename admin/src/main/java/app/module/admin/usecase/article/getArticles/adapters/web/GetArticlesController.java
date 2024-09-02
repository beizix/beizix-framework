package app.module.admin.usecase.article.getArticles.adapters.web;

import app.module.admin.usecase.article.getArticles.adapters.web.model.GetArticlesReqVO;
import app.module.admin.usecase.article.getArticles.ports.GetArticlesPortIn;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticlesCmd;
import app.module.core.config.adapter.persistence.entity.Article_;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GetArticlesController {
  private final GetArticlesPortIn getArticlesPortIn;

  @GetMapping(path = "/api/board/articles")
  ResponseEntity<?> operate(
      @PageableDefault(sort = Article_.ORDER_NO, direction = Sort.Direction.DESC) Pageable pageable,
      GetArticlesReqVO reqVO) {
    Page<GetArticles> articles =
        getArticlesPortIn.operate(
            pageable,
            new GetArticlesCmd(
                reqVO.getSearchField(), reqVO.getSearchValue(), reqVO.getSearchOpen()));

    return ResponseEntity.status(HttpStatus.OK).body(articles);
  }
}

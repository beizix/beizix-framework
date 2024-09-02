package app.module.admin.usecase.article.getArticles.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import app.module.admin.usecase.article.getArticles.adapters.web.model.GetArticlesReqVO;

@RestController
@RequiredArgsConstructor
class GetArticlesController {

  @GetMapping(path = "/api/board/articles")
  ResponseEntity<?> operate(
    @PageableDefault Pageable pageable,
    GetArticlesReqVO reqVO) {

    return ResponseEntity.status(HttpStatus.OK)
        .body(reqVO);
  }
}
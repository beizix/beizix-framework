package app.module.admin.usecase.article.getArticle.adapters.web;

import app.module.admin.usecase.article.getArticle.ports.GetArticlePortIn;
import app.module.admin.usecase.article.getArticle.ports.application.domain.GetArticleCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GetArticleController {
  private final GetArticlePortIn getArticlePortIn;

  @GetMapping(path = "/api/board/articles/{id}")
  ResponseEntity<?> operate(@PathVariable Long id) {
    return ResponseEntity.status(HttpStatus.OK)
        .body(getArticlePortIn.operate(new GetArticleCmd(id)).orElseThrow());
  }
}

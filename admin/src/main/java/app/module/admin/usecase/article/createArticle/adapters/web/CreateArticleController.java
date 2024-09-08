package app.module.admin.usecase.article.createArticle.adapters.web;

import app.module.admin.usecase.article.createArticle.adapters.web.model.CreateArticleReqVO;
import app.module.admin.usecase.article.createArticle.ports.CreateArticlePortIn;
import app.module.admin.usecase.article.createArticle.ports.application.domain.CreateArticle;
import app.module.admin.usecase.article.createArticle.ports.application.domain.CreateArticleCmd;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class CreateArticleController {
  private final CreateArticlePortIn createArticlePortIn;

  @PostMapping(path = "/api/board/articles/create")
  ResponseEntity<?> operate(@Valid CreateArticleReqVO reqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(bindingResult.getFieldErrors());
    }

    CreateArticle article =
        createArticlePortIn
            .operate(
                new CreateArticleCmd(
                    reqVO.getTitle(),
                    reqVO.getContent(),
                    reqVO.getVisible(),
                    reqVO.getStartDate(),
                    reqVO.getEndDate(),
                    reqVO.getFileMappingId()))
            .orElseThrow();

    return ResponseEntity.status(HttpStatus.OK).body(article);
  }
}

package app.module.admin.usecase.article.createArticle.adapters.web;

import javax.validation.Valid;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import app.module.admin.usecase.article.createArticle.adapters.web.model.CreateArticleReqVO;

@RestController
@RequiredArgsConstructor
class CreateArticleController {
  @PostMapping(path = "/api/board/articles/create")
  ResponseEntity<?> operate(@Valid CreateArticleReqVO reqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
        .body(bindingResult.getFieldErrors());
    }

    return ResponseEntity.status(HttpStatus.OK)
        .body(reqVO);
  }
}
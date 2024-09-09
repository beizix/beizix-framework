package app.module.admin.usecase.article.removeArticles.adapters.web;

import javax.validation.Valid;

import app.module.admin.usecase.article.removeArticles.ports.RemoveArticlesPortIn;
import app.module.admin.usecase.article.removeArticles.ports.application.domain.RemoveArticlesCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.validation.BindingResult;
import app.module.admin.usecase.article.removeArticles.adapters.web.model.RemoveArticlesReqVO;

@RestController
@RequiredArgsConstructor
class RemoveArticlesController {
  private final RemoveArticlesPortIn removeArticlesPortIn;

  @DeleteMapping(path = "/api/board/articles/remove")
  ResponseEntity<?> operate(@Valid RemoveArticlesReqVO reqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(bindingResult.getFieldErrors());
    }

    removeArticlesPortIn.operate(new RemoveArticlesCmd(reqVO.getSelectIds()));

    return ResponseEntity.status(HttpStatus.OK).body(reqVO);
  }
}

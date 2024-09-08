package app.module.admin.usecase.article.updateArticle.adapters.web;

import app.module.admin.usecase.article.updateArticle.adapters.web.model.UpdateArticleReqVO;
import app.module.admin.usecase.article.updateArticle.ports.UpdateArticlePortIn;
import app.module.admin.usecase.article.updateArticle.ports.application.domain.UpdateArticleCmd;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class UpdateArticleController {
  private final UpdateArticlePortIn updateArticlePortIn;

  @PutMapping(path = "/api/board/articles/update")
  ResponseEntity<?> operate(@Valid UpdateArticleReqVO reqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
          .body(bindingResult.getFieldErrors());
    }

    updateArticlePortIn.operate(
        new UpdateArticleCmd(
            reqVO.getId(),
            reqVO.getTitle(),
            reqVO.getContent(),
            reqVO.getVisible(),
            reqVO.getOrderNo(),
            reqVO.getStartDate(),
            reqVO.getEndDate(),
            reqVO.getFileMappingId()));

    return ResponseEntity.status(HttpStatus.OK).body(reqVO);
  }
}

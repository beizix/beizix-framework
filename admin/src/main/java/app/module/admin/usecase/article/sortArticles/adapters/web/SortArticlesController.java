package app.module.admin.usecase.article.sortArticles.adapters.web;

import app.module.admin.usecase.article.sortArticles.adapters.web.model.SortArticlesReqVO;
import app.module.admin.usecase.article.sortArticles.ports.SortArticlesPortIn;
import app.module.admin.usecase.article.sortArticles.ports.application.domain.SortArticlesCmd;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class SortArticlesController {
  private final SortArticlesPortIn sortArticlesPortIn;

  @PatchMapping(path = "/api/board/articles/sort")
  ResponseEntity<?> operate(
      @Valid @RequestBody List<SortArticlesReqVO> reqVO, BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(bindingResult);
    }

    sortArticlesPortIn.operate(
        reqVO.stream()
            .map(
                sortArticlesReqVO ->
                    new SortArticlesCmd(sortArticlesReqVO.getId(), sortArticlesReqVO.getOrderNo()))
            .collect(Collectors.toList()));

    return ResponseEntity.status(HttpStatus.OK).body(reqVO);
  }
}

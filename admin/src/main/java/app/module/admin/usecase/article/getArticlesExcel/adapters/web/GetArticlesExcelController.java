package app.module.admin.usecase.article.getArticlesExcel.adapters.web;

import app.module.admin.usecase.article.getArticles.ports.GetArticlesPortIn;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticlesCmd;
import app.module.admin.usecase.article.getArticlesExcel.adapters.web.model.GetArticlesExcelReqVO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GetArticlesExcelController {
  private final GetArticlesPortIn getArticlesPortIn;

  @GetMapping(path = "/api/board/articles/excel")
  ResponseEntity<?> operate(
      @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable, GetArticlesExcelReqVO reqVO) {
    Page<GetArticles> output =
        getArticlesPortIn.operate(
            pageable,
            new GetArticlesCmd(
                reqVO.getSearchField(), reqVO.getSearchValue(), reqVO.getSearchOpen()));



    return ResponseEntity.status(HttpStatus.OK).body(reqVO);
  }
}

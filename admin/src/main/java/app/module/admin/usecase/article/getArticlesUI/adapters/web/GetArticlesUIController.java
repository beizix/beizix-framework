package app.module.admin.usecase.article.getArticlesUI.adapters.web;

import app.module.admin.usecase.article.getArticles.ports.GetArticlesPortIn;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticlesCmd;
import app.module.admin.usecase.article.getArticlesUI.adapters.web.model.GetArticlesUIReqVO;
import app.module.core.config.adapter.persistence.entity.Article_;
import app.module.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class GetArticlesUIController {
  private final UICodeListPortIn uiCodeListPortIn;

  @GetMapping(path = "/board/articles")
  String operate(
      @PageableDefault(sort = Article_.ORDER_NO, direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("reqVO") GetArticlesUIReqVO reqVO,
      Model model) {

    // pageable 을 통해 조회된 정렬값은 실제 요청 값과 다르다. 실제 요청값으로 변경해준다.
    reqVO.setSort(pageable.getSort().toString().replace(": ", ","));
    reqVO.setPage(pageable.getPageNumber());

    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "board/articles";
  }
}

package app.module.admin.usecase.article.getArticlesUI.adapters.web;

import app.module.admin.usecase.article.getArticlesUI.adapters.web.model.GetArticlesUIReqVO;
import app.module.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class GetArticlesUIController {
  private final UICodeListPortIn uiCodeListPortIn;

  @GetMapping(path = "/board/articles")
  String operate(Model model, @ModelAttribute("reqVO") GetArticlesUIReqVO reqVO) {
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "board/articles";
  }
}

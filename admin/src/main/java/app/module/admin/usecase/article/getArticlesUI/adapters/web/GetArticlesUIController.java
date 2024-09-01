package app.module.admin.usecase.article.getArticlesUI.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import app.module.admin.usecase.article.getArticlesUI.adapters.web.model.GetArticlesUIReqVO;

@Controller
@RequiredArgsConstructor
class GetArticlesUIController {

  @GetMapping(path = "/board/articles")
  String operate(
    Model model,
    @ModelAttribute("reqVO") GetArticlesUIReqVO reqVO) {

    return "board/articles";
  }
}
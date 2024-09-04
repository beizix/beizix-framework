package app.module.admin.usecase.article.getArticleCreateUI.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import app.module.admin.usecase.article.getArticleCreateUI.adapters.web.model.CreateArticleUIReqVO;

@Controller
@RequiredArgsConstructor
class CreateArticleUIController {

  @GetMapping(path = "/board/articles/create")
  String operate(
    Model model,
    @ModelAttribute("reqVO") CreateArticleUIReqVO reqVO) {

    return "board/articles/create";
  }
}
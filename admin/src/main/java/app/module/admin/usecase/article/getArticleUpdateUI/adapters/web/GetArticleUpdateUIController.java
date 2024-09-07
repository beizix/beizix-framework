package app.module.admin.usecase.article.getArticleUpdateUI.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import app.module.admin.usecase.article.getArticleUpdateUI.adapters.web.model.GetArticleUpdateUIReqVO;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
class GetArticleUpdateUIController {

  @GetMapping(path = "/board/articles/{id}")
  String operate(
      Model model, @PathVariable Long id, @ModelAttribute("reqVO") GetArticleUpdateUIReqVO reqVO) {

    return "board/articles/{id}";
  }
}

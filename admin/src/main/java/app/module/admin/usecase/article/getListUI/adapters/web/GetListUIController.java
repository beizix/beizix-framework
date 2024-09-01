package app.module.admin.usecase.article.getListUI.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import app.module.admin.usecase.article.getListUI.adapters.web.model.GetListUIReqVO;

@Controller
@RequiredArgsConstructor
class GetListUIController {

  @GetMapping(path = "/board/article")
  String operate(
    Model model,
    @ModelAttribute("reqVO") GetListUIReqVO reqVO) {

    return "board/article";
  }
}
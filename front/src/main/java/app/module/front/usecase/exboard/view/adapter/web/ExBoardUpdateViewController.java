package app.module.front.usecase.exboard.view.adapter.web;

import lombok.RequiredArgsConstructor;
import app.module.core.usecase.exboard.view.application.domain.ExBoardView;
import app.module.core.usecase.exboard.view.application.port.in.ExBoardViewPortIn;
import app.module.front.usecase.exboard.list.adapter.web.ExBoardListFilterVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class ExBoardUpdateViewController {
  private final ExBoardViewPortIn<ExBoardView> exBoardViewPortIn;

  @GetMapping(path = "/board/exampleBoard/update/{id}")
  public String updateGet(
      Model model,
      @PathVariable long id,
      @ModelAttribute("paramDto") ExBoardListFilterVO paramDto) {
    ExBoardView exBoardView = exBoardViewPortIn.connect(id);
    model.addAttribute("formDto", exBoardView);

    return "board/exampleBoardForm";
  }
}

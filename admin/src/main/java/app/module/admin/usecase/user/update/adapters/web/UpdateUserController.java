package app.module.admin.usecase.user.update.adapters.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import app.module.admin.usecase.user.update.adapters.web.model.UpdateUserReqVO;

@Controller
@RequiredArgsConstructor
class UpdateUserController {

  @GetMapping(path = "/settings/users/update/{id}")
  String operate(
    Model model,
    @ModelAttribute UpdateUserReqVO reqVO) {

    return "settings/users/update/{id}";
  }
}
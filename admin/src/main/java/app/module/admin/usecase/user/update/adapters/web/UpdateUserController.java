package app.module.admin.usecase.user.update.adapters.web;

import app.module.admin.usecase.user.role.list.ports.GetRolesPortIn;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRolesCmd;
import app.module.admin.usecase.user.update.adapters.web.model.UpdateUserReqVO;
import app.module.core.usecase.user.findUser.ports.FindUserPortIn;
import app.module.core.usecase.user.findUser.ports.application.domain.FindUser;
import app.module.core.usecase.user.findUser.ports.application.domain.FindUserCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
class UpdateUserController {
  private final FindUserPortIn findUserPortIn;

  @GetMapping(path = "/settings/users/update/{id}")
  String operate(
      Model model, @PathVariable String id, @ModelAttribute("reqVO") UpdateUserReqVO reqVO) {
    FindUser output = findUserPortIn.operate(new FindUserCmd(id)).orElseThrow();

    model.addAttribute("output", output);

    return "settings/users/update/{id}";
  }
}

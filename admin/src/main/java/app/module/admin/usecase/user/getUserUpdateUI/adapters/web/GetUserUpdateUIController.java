package app.module.admin.usecase.user.getUserUpdateUI.adapters.web;

import app.module.admin.usecase.user.getUserUpdateUI.adapters.web.model.GetUserUpdateUIReqVO;
import app.module.admin.usecase.user.role.list.ports.GetRolesPortIn;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRolesCmd;
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
class GetUserUpdateUIController {
  private final FindUserPortIn findUserPortIn;
  private final GetRolesPortIn getRolesPortIn;

  @GetMapping(path = "/settings/users/{id}")
  String operate(
      Model model,
      @PathVariable @ModelAttribute("id") String id,
      @ModelAttribute("reqVO") GetUserUpdateUIReqVO reqVO) {

    FindUser findUser = findUserPortIn.operate(new FindUserCmd(id)).orElseThrow();
    model.addAttribute("user", findUser);

    model.addAttribute("roles", getRolesPortIn.operate(new GetRolesCmd()));

    return "settings/users/{id}";
  }
}

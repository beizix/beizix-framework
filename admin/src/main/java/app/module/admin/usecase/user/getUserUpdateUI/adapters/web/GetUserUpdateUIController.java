package app.module.admin.usecase.user.getUserUpdateUI.adapters.web;

import app.module.admin.usecase.user.getUserUpdateUI.adapters.web.model.GetUserUpdateUIReqVO;
import app.module.admin.usecase.user.getRoles.ports.GetRolesPortIn;
import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRolesCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
class GetUserUpdateUIController {
  private final GetRolesPortIn getRolesPortIn;

  @GetMapping(path = "/settings/users/{id}")
  String operate(
      Model model,
      @PathVariable @ModelAttribute("id") String id,
      @ModelAttribute("reqVO") GetUserUpdateUIReqVO reqVO) {

    model.addAttribute("roles", getRolesPortIn.operate(new GetRolesCmd()));

    return "settings/users/{id}";
  }
}

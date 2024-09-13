package app.module.admin.usecase.user.getUsersUI.adapters.web;

import app.module.admin.usecase.user.getUsersUI.adapters.web.model.GetUsersUIReqVO;
import app.module.admin.usecase.user.getRoles.ports.GetRolesPortIn;
import app.module.admin.usecase.user.getRoles.ports.application.domain.GetRolesCmd;
import app.module.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class GetUsersUIController {
  private final UICodeListPortIn uiCodeListPortIn;
  private final GetRolesPortIn getRolesPortIn;

  @GetMapping(path = "/settings/users")
  String operate(Model model, @ModelAttribute("reqVO") GetUsersUIReqVO reqVO) {

    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));
    model.addAttribute("roles", getRolesPortIn.operate(new GetRolesCmd()));

    return "settings/users";
  }
}

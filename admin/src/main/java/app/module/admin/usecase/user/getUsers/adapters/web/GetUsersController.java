package app.module.admin.usecase.user.getUsers.adapters.web;

import app.module.admin.usecase.user.getUsers.adapters.web.model.GetUsersReqVO;
import app.module.admin.usecase.user.getUsers.ports.GetUserListPortIn;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsersCmd;
import app.module.admin.usecase.user.role.list.ports.GetRolesPortIn;
import app.module.admin.usecase.user.role.list.ports.application.domain.GetRolesCmd;
import app.module.core.config.adapter.persistence.entity.FrontUser_;
import app.module.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class GetUsersController {
  private final GetUserListPortIn getUserListPortIn;
  private final UICodeListPortIn uiCodeListPortIn;
  private final GetRolesPortIn getRolesPortIn;

  @GetMapping(path = "/settings/users")
  String operate(
      @PageableDefault(sort = FrontUser_.CREATED_AT) Pageable pageable,
      Model model,
      @ModelAttribute("reqVO") GetUsersReqVO reqVO) {

    Page<GetUsers> result =
        getUserListPortIn.operate(
            pageable,
            new GetUsersCmd(reqVO.getSearchField(), reqVO.getSearchField(), reqVO.getSearchRole()));

    model.addAttribute("output", result);

    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));
    model.addAttribute("roles", getRolesPortIn.operate(new GetRolesCmd()));

    return "settings/users";
  }
}

package app.module.admin.usecase.admin.list.adapters.web;

import lombok.RequiredArgsConstructor;
import app.module.admin.config.adapter.persistence.entity.Admin_;
import app.module.admin.usecase.admin.list.adapters.web.model.AdminListFilterVO;
import app.module.admin.usecase.admin.list.ports.application.domain.AdminElement;
import app.module.admin.usecase.admin.list.ports.application.domain.AdminListCmd;
import app.module.admin.usecase.admin.list.ports.AdminListPortIn;
import app.module.admin.usecase.role.list.ports.application.domain.RoleElement;
import app.module.admin.usecase.role.list.ports.RoleListPortIn;
import app.module.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class AdminListController {
  private final AdminListPortIn adminListPortIn;
  private final RoleListPortIn<RoleElement> roleListPortIn;
  private final UICodeListPortIn uiCodeListPortIn;

  @GetMapping(path = "/settings/admins")
  String operate(
      Model model,
      @PageableDefault(sort = Admin_.CREATED_AT) Pageable pageable,
      @ModelAttribute("listStatus") AdminListFilterVO adminListFilterVO) {

    Page<AdminElement> listOutput =
        adminListPortIn.connect(
            pageable,
            new AdminListCmd(
                adminListFilterVO.getSearchField(),
                adminListFilterVO.getSearchValue(),
                adminListFilterVO.getSearchRole()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("roles", roleListPortIn.connect());
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "admin/adminList";
  }
}

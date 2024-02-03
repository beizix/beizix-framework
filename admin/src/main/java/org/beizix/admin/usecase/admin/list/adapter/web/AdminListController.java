package org.beizix.admin.usecase.admin.list.adapter.web;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.adapter.persistence.entity.Admin_;
import org.beizix.admin.usecase.admin.list.application.domain.AdminElement;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.port.in.AdminListPortIn;
import org.beizix.admin.usecase.role.list.application.domain.RoleElement;
import org.beizix.admin.usecase.role.list.application.port.in.RoleListPortIn;
import org.beizix.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
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
            new AdminListFilterCommand(
                adminListFilterVO.getSearchField(),
                adminListFilterVO.getSearchValue(),
                adminListFilterVO.getSearchRole()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("roles", roleListPortIn.connect());
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "admin/adminList";
  }
}

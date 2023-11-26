package org.beizix.admin.usecase.admin.list.adapter.web;

import lombok.RequiredArgsConstructor;
import org.beizix.core.configuration.application.aop.PageDefault;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.configuration.application.enums.OrderDir;
import org.beizix.security.adapter.persistence.admin.model.Admin_;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.AdminPageableList;
import org.beizix.security.application.domain.role.model.list.RoleOutput;
import org.beizix.admin.usecase.admin.list.application.port.in.AdminListPortIn;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
class AdminListController {
  private final AdminListPortIn adminListPortIn;
  private final RoleListPortIn<RoleOutput> roleListPortIn;

  @GetMapping(path = "/settings/admins")
  String operate(
      Model model,
      @PageDefault(orderBy = Admin_.CREATED_AT, orderDir = OrderDir.DESC)
          PageableInput pageableInput,
      @ModelAttribute("listStatus") AdminListFilterVO adminListFilterVO) {

    AdminPageableList listOutput =
        adminListPortIn.connect(
            pageableInput,
            new AdminListFilterCommand(
                adminListFilterVO.getSearchField(),
                adminListFilterVO.getSearchValue(),
                adminListFilterVO.getSearchRole()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("roles", roleListPortIn.connect());

    return "admin/adminList";
  }
}

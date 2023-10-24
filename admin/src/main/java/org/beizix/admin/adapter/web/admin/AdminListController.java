package org.beizix.admin.adapter.web.admin;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.admin.model.filter.AdminListStatusVO;
import org.beizix.admin.config.aop.PageDefault;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.config.enums.OrderDir;
import org.beizix.security.adapter.persistence.admin.model.Admin_;
import org.beizix.security.application.domain.admin.model.filter.AdminListStatus;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;
import org.beizix.security.application.domain.role.model.list.RoleOutput;
import org.beizix.security.application.port.in.admin.AdminListPortIn;
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
      @ModelAttribute("listStatus") AdminListStatusVO adminListStatusVO) {

    AdminListOutput listOutput =
        adminListPortIn.connect(
            pageableInput,
            new AdminListStatus(
                adminListStatusVO.getSearchField(),
                adminListStatusVO.getSearchValue(),
                adminListStatusVO.getSearchRole()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("roles", roleListPortIn.connect());

    return "admin/adminList";
  }
}

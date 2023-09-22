package org.beizix.admin.adapter.web.admin;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.beizix.admin.adapter.web.admin.model.query.AdminListReqParam;
import org.beizix.security.application.port.in.role.RoleListPortIn;
import org.beizix.security.application.domain.admin.model.query.AdminListInput;
import org.beizix.security.application.port.in.admin.AdminListPortIn;

@Controller
@RequiredArgsConstructor
class AdminListController {
  private final AdminListPortIn adminListPortIn;
  private final ModelMapper modelMapper;
  private final RoleListPortIn roleListPortIn;

  @GetMapping(path = "/settings/admins")
  String operate(
      Model model,
      @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") AdminListReqParam adminListReqParam) {

    adminListReqParam.setSize(pageable.getPageSize());

    model.addAttribute(
        "items",
        adminListPortIn.connect(pageable, modelMapper.map(adminListReqParam, AdminListInput.class)));
    model.addAttribute("roles", roleListPortIn.connect());

    return "admin/adminList";
  }
}

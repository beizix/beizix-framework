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
import org.beizix.security.application.port.in.role.RoleListService;
import org.beizix.security.application.domain.admin.model.query.AdminListReq;
import org.beizix.security.application.port.in.admin.AdminListService;

@Controller
@RequiredArgsConstructor
class AdminListController {
  private final AdminListService adminListService;
  private final ModelMapper modelMapper;
  private final RoleListService roleListService;

  @GetMapping(path = "/settings/admins")
  String operate(
      Model model,
      @PageableDefault(sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") AdminListReqParam paramDto) {

    paramDto.setSize(pageable.getPageSize());

    model.addAttribute(
        "items",
        adminListService.operate(
            pageable, modelMapper.map(paramDto, AdminListReq.class)));
    model.addAttribute("roles", roleListService.operate());

    return "admin/adminList";
  }
}

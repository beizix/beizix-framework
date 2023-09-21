package org.beizix.admin.adapter.web.admin;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.beizix.admin.adapter.web.admin.model.query.AdminListReqParam;
import org.beizix.admin.adapter.web.admin.validator.AdminRemoveValidator;
import org.beizix.security.application.domain.admin.model.query.AdminListReq;
import org.beizix.security.application.port.in.admin.AdminRemoveService;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.MessageUtil;

@Controller
@RequiredArgsConstructor
class AdminRemoveController {
  private final AdminRemoveValidator adminRemoveValidator;
  private final CommonUtil commonUtil;
  private final MessageUtil messageUtil;
  private final AdminRemoveService adminRemoveService;
  private final ModelMapper modelMapper;

  @PostMapping(path = "/settings/admins/delete")
  String operate(
      RedirectAttributes redirectAttributes,
      @ModelAttribute("paramDto") AdminListReqParam paramDto,
      BindingResult bindingResult) {

    adminRemoveValidator.validate(paramDto, bindingResult);
    if (bindingResult.hasErrors()) {
      commonUtil.setValidationFailRedirectAttributes(redirectAttributes, bindingResult);
    } else {
      adminRemoveService.operate(modelMapper.map(paramDto, AdminListReq.class));
      redirectAttributes.addFlashAttribute(
          "operationMessage", messageUtil.getMessage("operation.settings.admin.removed"));
    }

    return "redirect:/settings/admins";
  }
}

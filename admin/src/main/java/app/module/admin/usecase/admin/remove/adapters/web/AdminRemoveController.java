package app.module.admin.usecase.admin.remove.adapters.web;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import app.module.admin.usecase.admin.remove.ports.AdminRemovePortIn;
import app.module.core.config.application.util.MessageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequiredArgsConstructor
class AdminRemoveController {
  private final MessageUtil messageUtil;
  private final AdminRemovePortIn adminRemovePortIn;

  @PostMapping(path = "/settings/admins/delete")
  String operate(RedirectAttributes redirectAttributes, @RequestParam List<String> checkedIds) {

    if (CollectionUtils.isNotEmpty(checkedIds)) {
      adminRemovePortIn.connect(checkedIds);
      redirectAttributes.addFlashAttribute(
          "operationMessage", messageUtil.getMessage("operation.settings.admin.removed"));
    }

    return "redirect:/settings/admins";
  }
}

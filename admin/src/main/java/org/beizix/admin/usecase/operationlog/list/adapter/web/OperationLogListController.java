package org.beizix.admin.usecase.operationlog.list.adapter.web;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.adapter.persistence.entity.Admin_;
import org.beizix.core.config.application.component.PageableInput;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogPageableList;
import org.beizix.core.usecase.operationlog.list.application.port.in.OperationLogListPortIn;
import org.beizix.core.config.application.aop.PageDefault;
import org.beizix.core.config.application.enums.OrderDir;
import org.beizix.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class OperationLogListController {
  private final OperationLogListPortIn operationLogListPortIn;
  private final UICodeListPortIn uiCodeListPortIn;

  @GetMapping("/analysis/operationlog")
  String operate(
      Model model,
      @PageDefault(orderBy = Admin_.CREATED_AT, orderDir = OrderDir.DESC) PageableInput pageable,
      @ModelAttribute("paramDto") OperationLogListFilterVO statusVO) {

    OperationLogPageableList listOutput =
        operationLogListPortIn.connect(
            pageable,
            new OperationLogListFilterCommand(
                statusVO.getSearchField(),
                statusVO.getSearchValue(),
                statusVO.getSearchAppType(),
                statusVO.getSearchOperationType()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "operationLog/operationLogList";
  }
}

package org.beizix.admin.adapter.web.operation;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.operation.model.filter.OperationLogListStatusVO;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListStatus;
import org.beizix.core.application.domain.operationlog.model.list.OperationLogListOutput;
import org.beizix.core.application.port.in.operationlog.OperationLogListPortIn;
import org.beizix.core.configuration.application.aop.PageDefault;
import org.beizix.core.configuration.application.enums.OrderDir;
import org.beizix.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import org.beizix.security.adapter.persistence.admin.model.Admin_;
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
      @ModelAttribute("paramDto") OperationLogListStatusVO statusVO) {

    OperationLogListOutput listOutput =
        operationLogListPortIn.connect(
            pageable,
            new OperationLogListStatus(
                statusVO.getSearchField(),
                statusVO.getSearchValue(),
                statusVO.getSearchAppType(),
                statusVO.getSearchOperationType()));

    model.addAttribute("listOutput", listOutput);
    model.addAttribute("pageRows", uiCodeListPortIn.connect("code.pageable.rows"));

    return "operationLog/operationLogList";
  }
}

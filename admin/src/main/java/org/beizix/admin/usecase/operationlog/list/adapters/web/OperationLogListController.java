package org.beizix.admin.usecase.operationlog.list.adapters.web;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.config.adapter.persistence.entity.Admin_;
import org.beizix.admin.usecase.operationlog.list.adapters.web.model.OperationLogListFilterVO;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogElement;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.beizix.core.usecase.operationlog.list.application.port.in.OperationLogListPortIn;
import org.beizix.core.usecase.uicode.list.application.port.in.UICodeListPortIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
      @PageableDefault(sort = Admin_.CREATED_AT, direction = Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") OperationLogListFilterVO statusVO) {

    Page<OperationLogElement> listOutput =
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

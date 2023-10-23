package org.beizix.admin.adapter.web.operation;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.adapter.web.operation.model.filter.OperationLogListStatusVO;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListInput;
import org.beizix.core.application.port.in.operationlog.OperationLogListPortIn;

@Controller
@RequiredArgsConstructor
public class OperationLogListController {
  private final OperationLogListPortIn operationLogListPortIn;
  private final ModelMapper modelMapper;

  @GetMapping("/analysis/operationlog")
  String operate(
      Model model,
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") OperationLogListStatusVO statusVO) {

    statusVO.setSize(pageable.getPageSize());

    model.addAttribute(
        "items",
        operationLogListPortIn.connect(
            pageable, modelMapper.map(statusVO, OperationLogListInput.class)));

    return "operationLog/operationLogList";
  }
}

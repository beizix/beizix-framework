package org.beizix.admin.feature.operation.web;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.beizix.core.feature.operationlog.application.model.OperationLogListCondition;
import org.beizix.core.feature.operationlog.application.service.OperationLogListService;

@Controller
@RequiredArgsConstructor
public class OperationLogListController {
  private final OperationLogListService operationLogListService;
  private final ModelMapper modelMapper;

  @GetMapping("/analysis/operationlog")
  String operate(
      Model model,
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") OperationLogListConditionDto paramDto) {

    paramDto.setSize(pageable.getPageSize());

    model.addAttribute(
        "items",
        operationLogListService.operate(
            pageable, modelMapper.map(paramDto, OperationLogListCondition.class)));

    return "operationLog/operationLogList";
  }
}

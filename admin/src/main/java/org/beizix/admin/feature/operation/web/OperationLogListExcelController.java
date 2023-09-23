package org.beizix.admin.feature.operation.web;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.beizix.utility.common.ExcelUtil;
import org.beizix.core.application.domain.operationlog.model.OperationLog;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListInput;
import org.beizix.core.application.port.in.operationlog.OperationLogListPortIn;

import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class OperationLogListExcelController {
  private final ExcelUtil excelUtil;
  private final ModelMapper modelMapper;
  private final OperationLogListPortIn operationLogListPortIn;

  @GetMapping("/analysis/operationlog/excel")
  public void operate(
      HttpServletResponse response,
      @PageableDefault(sort = "id", direction = Sort.Direction.DESC) Pageable pageable,
      @ModelAttribute("paramDto") OperationLogListConditionDto paramDto) {
    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("수행로그 목록");

    Page<OperationLog> items =
        operationLogListPortIn.connect(
            pageable, modelMapper.map(paramDto, OperationLogListInput.class));

    if (!items.isEmpty()) {
      // Header
      Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
      excelUtil.createCells(
          headerRow,
          "로그 ID",
          "App Type",
          "Operation Type",
          "IP",
          "수행일",
          "수행자",
          "피수행자",
          "수행 내용");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      items.forEach(
          item -> {
            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());

            excelUtil.createCells(
                row,
                item.getId(),
                item.getAppType().name(),
                item.getOperationLogType().name(),
                item.getIp(),
                Optional.ofNullable(item.getOperatedAt())
                    .map(localDateTime -> localDateTime.format(formatter))
                    .orElse(""),
                item.getOperatorId(),
                item.getTargetId(),
                item.getTaskDesc());
          });
    }

    excelUtil.generateWithPassword(response, wb, "operation-logs");
  }
}

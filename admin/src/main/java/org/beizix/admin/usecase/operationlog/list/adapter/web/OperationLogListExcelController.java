package org.beizix.admin.usecase.operationlog.list.adapter.web;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.beizix.core.config.adapter.persistence.entity.OperationLog_;
import org.beizix.core.config.application.aop.PageDefault;
import org.beizix.core.config.application.component.PageableInput;
import org.beizix.core.config.application.enums.OrderDir;
import org.beizix.core.config.application.util.ExcelUtil;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogListFilterCommand;
import org.beizix.core.usecase.operationlog.list.application.domain.OperationLogPageableList;
import org.beizix.core.usecase.operationlog.list.application.port.in.OperationLogListPortIn;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class OperationLogListExcelController {
  private final ExcelUtil excelUtil;
  private final OperationLogListPortIn operationLogListPortIn;

  @GetMapping("/analysis/operationlog/excel")
  public void operate(
      HttpServletResponse response,
      @PageDefault(orderBy = OperationLog_.ID, orderDir = OrderDir.DESC) PageableInput pageable,
      @ModelAttribute("paramDto") OperationLogListFilterVO paramDto) {
    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("수행로그 목록");

    OperationLogPageableList listOutput =
        operationLogListPortIn.connect(
            pageable,
            new OperationLogListFilterCommand(
                paramDto.getSearchField(),
                paramDto.getSearchValue(),
                paramDto.getSearchAppType(),
                paramDto.getSearchOperationType()));

    if (CollectionUtils.isNotEmpty(listOutput.getContents())) {
      // Header
      Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
      excelUtil.createCells(
          headerRow, "로그 ID", "App Type", "Operation Type", "IP", "수행일", "수행자", "피수행자", "수행 내용");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      listOutput
          .getContents()
          .forEach(
              item -> {
                Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());

                excelUtil.createCells(
                    row,
                    item.getId(),
                    item.getAppType().name(),
                    item.getOperationLogType().name(),
                    item.getIp(),
                    Optional.ofNullable(item.getCreatedAt())
                        .map(localDateTime -> localDateTime.format(formatter))
                        .orElse(""),
                    item.getCreatedBy(),
                    item.getTargetId(),
                    item.getTaskDesc());
              });
    }

    excelUtil.generateWithPassword(response, wb, "operation-logs");
  }
}

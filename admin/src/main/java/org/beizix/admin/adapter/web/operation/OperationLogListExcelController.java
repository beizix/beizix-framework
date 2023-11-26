package org.beizix.admin.adapter.web.operation;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.beizix.admin.adapter.web.operation.model.filter.OperationLogListStatusVO;
import org.beizix.core.configuration.application.aop.PageDefault;
import org.beizix.core.adapter.persistence.operationlog.model.OperationLog_;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.operationlog.model.filter.OperationLogListStatus;
import org.beizix.core.application.domain.operationlog.model.list.OperationLogListOutput;
import org.beizix.core.application.port.in.operationlog.OperationLogListPortIn;
import org.beizix.core.configuration.application.enums.OrderDir;
import org.beizix.utility.common.ExcelUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
@RequiredArgsConstructor
public class OperationLogListExcelController {
  private final ExcelUtil excelUtil;
  private final ModelMapper modelMapper;
  private final OperationLogListPortIn operationLogListPortIn;

  @GetMapping("/analysis/operationlog/excel")
  public void operate(
      HttpServletResponse response,
      @PageDefault(orderBy = OperationLog_.ID, orderDir = OrderDir.DESC) PageableInput pageable,
      @ModelAttribute("paramDto") OperationLogListStatusVO paramDto) {
    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("수행로그 목록");

    OperationLogListOutput listOutput =
        operationLogListPortIn.connect(
            pageable, modelMapper.map(paramDto, OperationLogListStatus.class));

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

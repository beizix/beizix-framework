package org.beizix.admin.usecase.exboard.excel.adapter.web;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.beizix.admin.usecase.exboard.list.adapter.web.ExBoardListFilterVO;
import org.beizix.core.config.application.aop.PageDefault;
import org.beizix.core.config.application.component.PageableInput;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardPageableList;
import org.beizix.core.usecase.exboard.list.application.port.in.ExBoardListPortIn;
import org.beizix.core.config.application.enums.OrderDir;
import org.beizix.core.config.application.util.ExcelUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class ExBoardExcelController {
  private final ExcelUtil excelUtil;
  private final ExBoardListPortIn exBoardListPortIn;

  @GetMapping("/board/exampleBoard/excel")
  public void excelDownload(
      HttpServletResponse response,
      @PageDefault(orderBy = "orderNo", orderDir = OrderDir.DESC) PageableInput pageableInput,
      ExBoardListFilterVO exBoardListFilterVO) {

    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("예제 게시판 목록");

    ExBoardPageableList listOutput =
        exBoardListPortIn.connect(
            pageableInput,
            new ExBoardListFilterCommand(
                exBoardListFilterVO.getSearchField(),
                exBoardListFilterVO.getSearchValue(),
                exBoardListFilterVO.getSearchOpen()));

    if (!listOutput.getContents().isEmpty()) {
      // Header
      Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
      excelUtil.createCells(headerRow, "번호", "제목", "내용", "공개여부", "게시일", "종료일", "수정자", "최근 수정일");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      listOutput
          .getContents()
          .forEach(
              item -> {
                Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());

                excelUtil.createCells(
                    row,
                    item.getId(),
                    item.getTitle(),
                    item.getContent(),
                    item.getVisible(),
                    Optional.ofNullable(item.getBoardStartDate())
                        .map(localDateTime -> localDateTime.format(formatter))
                        .orElse(""),
                    Optional.ofNullable(item.getBoardEndDate())
                        .map(localDateTime -> localDateTime.format(formatter))
                        .orElse(""),
                    item.getUpdatedBy(),
                    Optional.ofNullable(item.getUpdatedAt())
                        .map(localDateTime -> localDateTime.format(formatter))
                        .orElse(""));
              });
    }

    excelUtil.generateWithPassword(response, wb, "example-board");
  }
}

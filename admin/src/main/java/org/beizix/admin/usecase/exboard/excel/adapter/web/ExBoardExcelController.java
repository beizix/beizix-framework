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
import org.beizix.core.config.adapter.persistence.entity.ExBoard_;
import org.beizix.core.config.application.util.ExcelUtil;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardElement;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.port.in.ExBoardListPortIn;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
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
      @PageableDefault(sort = ExBoard_.ORDER_NO, direction = Direction.DESC) Pageable pageable,
      ExBoardListFilterVO filterVO) {

    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("예제 게시판 목록");

    Page<ExBoardElement> listOutput =
        exBoardListPortIn.connect(
            pageable,
            new ExBoardListFilterCommand(
                filterVO.getSearchField(), filterVO.getSearchValue(), filterVO.getSearchOpen()));

    if (!listOutput.getContent().isEmpty()) {
      // Header
      Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
      excelUtil.createCells(headerRow, "번호", "제목", "내용", "공개여부", "게시일", "종료일", "수정자", "최근 수정일");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      listOutput
          .getContent()
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

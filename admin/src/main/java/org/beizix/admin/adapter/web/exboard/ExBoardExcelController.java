package org.beizix.admin.adapter.web.exboard;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.admin.config.aop.PageDefault;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.beizix.core.config.enums.OrderDir;
import org.beizix.utility.common.ExcelUtil;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
class ExBoardExcelController {
  private final ExcelUtil excelUtil;
  private final ExBoardListPortIn<ExBoardListOutput> exBoardListPortIn;

  @GetMapping("/board/exampleBoard/excel")
  public void excelDownload(
      HttpServletResponse response,
      @PageDefault(orderBy = "orderNo", orderDir = OrderDir.DESC) PageableInput pageableInput,
      ExBoardListFilterReqVO exBoardListFilterReqVO) {

    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("예제 게시판 목록");

    ExBoardListOutput listOutput =
        exBoardListPortIn.connect(
            pageableInput,
            new ExBoardListFilterInput(
                exBoardListFilterReqVO.getSearchField(),
                exBoardListFilterReqVO.getSearchValue(),
                exBoardListFilterReqVO.getSearchOpen()));

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

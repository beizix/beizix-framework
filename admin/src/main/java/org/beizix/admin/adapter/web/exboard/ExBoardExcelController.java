package org.beizix.admin.adapter.web.exboard;

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
import org.beizix.admin.adapter.web.exboard.model.filter.ExBoardListFilterReqVO;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListInput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.beizix.utility.common.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
class ExBoardExcelController {
  private final ExcelUtil excelUtil;
  private final ExBoardListPortIn exBoardListPortIn;
  private final ModelMapper modelMapper;

  @GetMapping("/board/exampleBoard/excel")
  public void excelDownload(
      HttpServletResponse response,
      @PageableDefault(size = 5, sort = "updatedAt", direction = Sort.Direction.DESC)
          Pageable pageable,
      ExBoardListFilterReqVO exBoardListFilterReqVO) {

    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("예제 게시판 목록");

    Page<ExBoardInput> items =
        exBoardListPortIn.connect(pageable, modelMapper.map(exBoardListFilterReqVO, ExBoardListInput.class));
    if (!items.isEmpty()) {
      // Header
      Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
      excelUtil.createCells(headerRow, "번호", "제목", "내용", "공개여부", "게시일", "종료일", "수정자", "최근 수정일");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      items.forEach(
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

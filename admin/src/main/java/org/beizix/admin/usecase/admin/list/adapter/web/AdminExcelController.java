package org.beizix.admin.usecase.admin.list.adapter.web;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.beizix.admin.config.adapter.persistence.entity.Admin_;
import org.beizix.admin.usecase.admin.list.application.domain.AdminElement;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.RoleElement;
import org.beizix.admin.usecase.admin.list.application.port.in.AdminListPortIn;
import org.beizix.core.config.application.util.ExcelUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
class AdminExcelController {
  private final AdminListPortIn adminListPortIn;
  private final ExcelUtil excelUtil;

  @PostMapping(path = "/settings/admins/excel")
  void excelDownload(
      HttpServletResponse response,
      @PageableDefault(sort = Admin_.CREATED_AT) Pageable pageable,
      @ModelAttribute("paramDto") AdminListFilterVO adminListFilterVO) {

    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("예제 관리자 목록");

    Page<AdminElement> listOutput =
        adminListPortIn.connect(
            pageable,
            new AdminListFilterCommand(
                adminListFilterVO.getSearchField(),
                adminListFilterVO.getSearchValue(),
                adminListFilterVO.getSearchRole()));

    if (CollectionUtils.isNotEmpty(listOutput.getContent())) {
      // Header
      Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
      excelUtil.createCells(headerRow, "번호", "이메일", "권한", "등록일", "최근 수정일");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      listOutput
          .getContent()
          .forEach(
              item -> {
                Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
                excelUtil.createCells(
                    row,
                    item.getId(),
                    item.getEmail(),
                    item.getRoles().stream()
                        .map(RoleElement::getId)
                        .collect(Collectors.joining(", ")),
                    Optional.ofNullable(item.getCreatedAt())
                        .map(localDateTime -> localDateTime.format(formatter))
                        .orElse(""),
                    Optional.ofNullable(item.getUpdatedAt())
                        .map(localDateTime -> localDateTime.format(formatter))
                        .orElse(""));
              });
    }

    excelUtil.generateWithPassword(response, wb, "admin-list");
  }
}

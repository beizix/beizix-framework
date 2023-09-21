package org.beizix.admin.adapter.web.admin;

import java.time.format.DateTimeFormatter;
import java.util.Optional;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletResponse;
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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.beizix.admin.adapter.web.admin.model.query.AdminListReqParam;
import org.beizix.security.application.domain.admin.model.list.AdminListResp;
import org.beizix.security.application.domain.admin.model.query.AdminListReq;
import org.beizix.security.application.port.in.admin.AdminListService;
import org.beizix.utility.common.ExcelUtil;

@Controller
@RequiredArgsConstructor
class AdminExcelController {
  private final AdminListService adminListService;
  private final ModelMapper modelMapper;
  private final ExcelUtil excelUtil;

  @PostMapping(path = "/settings/admins/excel")
  void excelDownload(
      HttpServletResponse response,
      @PageableDefault(size = 5, sort = "createdAt", direction = Sort.Direction.DESC)
          Pageable pageable,
      @ModelAttribute("paramDto") AdminListReqParam paramDto) {

    Workbook wb = new XSSFWorkbook();
    Sheet sheet = wb.createSheet("예제 관리자 목록");

    Page<AdminListResp> items =
        adminListService.operate(
            pageable, modelMapper.map(paramDto, AdminListReq.class));

    if (!items.isEmpty()) {
      // Header
      Row headerRow = sheet.createRow(sheet.getPhysicalNumberOfRows());
      excelUtil.createCells(headerRow, "번호", "이메일", "권한", "등록일", "최근 수정일");

      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

      items.forEach(
          item -> {
            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
            excelUtil.createCells(
                row,
                item.getId(),
                item.getEmail(),
                item.getWithRoles().stream()
                    .map(adminUserWithRole -> adminUserWithRole.getRole().getId())
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

package app.module.admin.usecase.article.getArticlesExcel.adapters.web;

import app.module.admin.usecase.article.getArticles.ports.GetArticlesPortIn;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticles;
import app.module.admin.usecase.article.getArticles.ports.application.domain.GetArticlesCmd;
import app.module.admin.usecase.article.getArticlesExcel.adapters.web.model.GetArticlesExcelReqVO;
import app.module.core.usecase.excel.createCell.ports.CreateCellPortIn;
import app.module.core.usecase.excel.createCell.ports.application.model.CellConfig;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
class GetArticlesExcelController {
  private final GetArticlesPortIn getArticlesPortIn;
  private final CreateCellPortIn createCellPortIn;

  @GetMapping(path = "/api/board/articles/excel")
  void operate(
      @PageableDefault(size = Integer.MAX_VALUE) Pageable pageable,
      GetArticlesExcelReqVO reqVO,
      HttpServletResponse response)
      throws IOException {

    String title = "예제 게시판 목록";

    Page<GetArticles> output =
        getArticlesPortIn.operate(
            pageable,
            new GetArticlesCmd(
                reqVO.getSearchField(), reqVO.getSearchValue(), reqVO.getSearchOpen()));

    if (!output.getContent().isEmpty()) {

      // 1000 개 로우가 넘으면 디스크에 기록한다.
      try (SXSSFWorkbook wb = new SXSSFWorkbook(1000)) {
        Sheet sheet = wb.createSheet(title);

        AtomicInteger colNo = new AtomicInteger(0);

        // 상단 Header 생성 및 필터 적용
        createCellPortIn.operate(
            // 상단 Header 의 Row 목록 - 2개 이상이면 병합이 가능해진다.
            List.of(sheet.createRow(sheet.getPhysicalNumberOfRows())),
            // 상단 Header Row 를 구성하는 col 목록
            List.of(
                new CellConfig(
                    0,
                    colNo.getAndIncrement(),
                    null,
                    CellType.STRING,
                    "제목",
                    createCellPortIn.getDefaultHeaderStyle(wb, HorizontalAlignment.CENTER),
                    null),
                new CellConfig(
                    0,
                    colNo.getAndIncrement(),
                    null,
                    CellType.STRING,
                    "공개여부",
                    createCellPortIn.getDefaultHeaderStyle(wb, HorizontalAlignment.CENTER),
                    null),
                new CellConfig(
                    0,
                    colNo.getAndIncrement(),
                    null,
                    CellType.STRING,
                    "게시일",
                    createCellPortIn.getDefaultHeaderStyle(wb, HorizontalAlignment.CENTER),
                    null),
                new CellConfig(
                    0,
                    colNo.getAndIncrement(),
                    null,
                    CellType.STRING,
                    "종료일",
                    createCellPortIn.getDefaultHeaderStyle(wb, HorizontalAlignment.CENTER),
                    null),
                new CellConfig(
                    0,
                    colNo.getAndIncrement(),
                    null,
                    CellType.STRING,
                    "최근 수정자",
                    createCellPortIn.getDefaultHeaderStyle(wb, HorizontalAlignment.CENTER),
                    null),
                new CellConfig(
                    0,
                    colNo.getAndIncrement(),
                    null,
                    CellType.STRING,
                    "최근 수정일",
                    createCellPortIn.getDefaultHeaderStyle(wb, HorizontalAlignment.CENTER),
                    null)));
        sheet.setAutoFilter(new CellRangeAddress(0, 0, 0, colNo.get() - 1));

        // 본문 데이터
        output
            .getContent()
            .forEach(
                item -> {
                  colNo.set(0);
                  createCellPortIn.operate(
                      List.of(sheet.createRow(sheet.getPhysicalNumberOfRows())),
                      List.of(
                          new CellConfig(
                              0,
                              colNo.getAndIncrement(),
                              null,
                              CellType.STRING,
                              item.getTitle(),
                              createCellPortIn.getDefaultDataStyle(wb, HorizontalAlignment.CENTER),
                              null),
                          new CellConfig(
                              0,
                              colNo.getAndIncrement(),
                              null,
                              CellType.STRING,
                              item.getVisible() ? "Y" : "N",
                              createCellPortIn.getDefaultDataStyle(wb, HorizontalAlignment.CENTER),
                              null),
                          new CellConfig(
                              0,
                              colNo.getAndIncrement(),
                              null,
                              CellType.STRING,
                              item.getStartDate()
                                  .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                              createCellPortIn.getDefaultDataStyle(wb, HorizontalAlignment.CENTER),
                              null),
                          new CellConfig(
                              0,
                              colNo.getAndIncrement(),
                              null,
                              CellType.STRING,
                              item.getEndDate()
                                  .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                              createCellPortIn.getDefaultDataStyle(wb, HorizontalAlignment.CENTER),
                              null),
                          new CellConfig(
                              0,
                              colNo.getAndIncrement(),
                              null,
                              CellType.STRING,
                              item.getUpdatedBy(),
                              createCellPortIn.getDefaultDataStyle(wb, HorizontalAlignment.CENTER),
                              null),
                          new CellConfig(
                              0,
                              colNo.getAndIncrement(),
                              null,
                              CellType.STRING,
                              item.getUpdatedAt()
                                  .format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
                              createCellPortIn.getDefaultDataStyle(wb, HorizontalAlignment.CENTER),
                              null)));
                });

        // 한글 파일명 처리를 위해 URLEncoder 필요
        String filename =
            URLEncoder.encode(
                String.format(
                    "%s-%s.xlsx",
                    title,
                    new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime())),
                StandardCharsets.UTF_8);

        response.setHeader(
            HttpHeaders.CONTENT_DISPOSITION,
            String.format("attachment; filename=\"%s\"", filename));

        wb.write(response.getOutputStream());

        // 디스크에 기록한 임시 파일 제거
        wb.dispose();
      }
    }
  }
}

package app.module.core.usecase.excel.createCell.ports;

import app.module.core.usecase.excel.createCell.ports.application.model.CellConfig;
import java.awt.Color;
import java.util.List;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;

public interface CreateCellPortIn {
  void operate(List<Row> rows, List<CellConfig> cellConfigs);

  default XSSFCellStyle getDefaultStyle(Workbook wb) {
    XSSFCellStyle style = (XSSFCellStyle) wb.createCellStyle();

    // cell 테두리 설정
    style.setBorderTop(BorderStyle.THIN);
    style.setBorderLeft(BorderStyle.THIN);
    style.setBorderRight(BorderStyle.THIN);
    style.setBorderBottom(BorderStyle.THIN);

    // 선 색
    byte[] rgb = {(byte) 217, (byte) 217, (byte) 217};
    style.setBottomBorderColor(new XSSFColor(rgb));
    style.setLeftBorderColor(new XSSFColor(rgb));
    style.setRightBorderColor(new XSSFColor(rgb));
    style.setTopBorderColor(new XSSFColor(rgb));

    // 색 채우기
    style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
    style.setFillForegroundColor(new XSSFColor(Color.WHITE));

    // 정렬 기본값.
    style.setAlignment(HorizontalAlignment.CENTER);
    style.setVerticalAlignment(VerticalAlignment.CENTER);

    // 폰트
    XSSFFont font = (XSSFFont) wb.createFont();
    font.setFontName("맑은 고딕");

    // 폰트 색
    XSSFColor color = new XSSFColor(Color.BLACK);
    font.setColor(color);
    style.setFont(font);

    return style;
  }

  default XSSFCellStyle getDefaultHeaderStyle(Workbook wb, HorizontalAlignment alignment) {
    XSSFCellStyle headerStyle = getDefaultStyle(wb);

    headerStyle.setAlignment(alignment);
    headerStyle.setFillForegroundColor(new XSSFColor(Color.YELLOW));
    headerStyle.setRightBorderColor(new XSSFColor(Color.BLACK));
    headerStyle.setBottomBorderColor(new XSSFColor(Color.BLACK));

    // 줄바꿈 처리
    headerStyle.setWrapText(true);

    // 폰트크기 헤더용으로 재설정
    headerStyle.getFont().setFontHeightInPoints((short) 12);

    return headerStyle;
  }

  default XSSFCellStyle getDefaultDataStyle(Workbook wb, HorizontalAlignment alignment) {
    XSSFCellStyle style = getDefaultStyle(wb);

    // 정렬 재지정
    style.setAlignment(alignment);

    return style;
  }
}

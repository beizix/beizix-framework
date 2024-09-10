package app.module.core.usecase.excel.createCell.ports.application;

import app.module.core.usecase.excel.createCell.ports.CreateCellPortIn;
import app.module.core.usecase.excel.createCell.ports.application.model.CellConfig;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.util.RegionUtil;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCellService implements CreateCellPortIn {
  @Override
  public void operate(List<Row> rows, List<CellConfig> cellConfigs) {

    cellConfigs.forEach(
        cellConfig -> {
          Row targetRow = rows.get(cellConfig.getTargetRowNo());
          Sheet sheet = targetRow.getSheet();
          Cell cell = targetRow.createCell(cellConfig.getTargetColNo());

          cell.setCellType(cellConfig.getCellType());

          Object cellValue = cellConfig.getCellValue();
          if (cellValue == null) {
            cell.setCellValue("");
          } else {
            switch (cellConfig.getCellType()) {
              case NUMERIC:
                cell.setCellValue((Integer) cellValue);
                break;
              case FORMULA:
                cell.setCellFormula((String) cellValue);
                break;
              default:
                cell.setCellValue((String) cellValue);
                break;
            }
          }

          // 컬럼 넓이
          Integer width = cellConfig.getCellWidth();
          if (width != null) {
            if (width > 0) {
              sheet.setColumnWidth(cellConfig.getTargetColNo(), width * 500);
            } else {
              // 자동 열 너비 맞춤
              sheet.autoSizeColumn(cellConfig.getTargetColNo());
            }
          }

          cell.setCellStyle(cell.getCellStyle());

          // MERGE 병합
          if (cellConfig.getCellRangeAddress() != null) {
            sheet.addMergedRegion(cellConfig.getCellRangeAddress());
            setMergedCellBorderStyle(
                cellConfig.getCellStyle(), cellConfig.getCellRangeAddress(), sheet);
          }
        });
  }

  private void setMergedCellBorderStyle(
      XSSFCellStyle cellStyle, CellRangeAddress cellRangeAddress, Sheet sheet) {
    RegionUtil.setBorderTop(cellStyle.getBorderTopEnum(), cellRangeAddress, sheet);
    RegionUtil.setBorderBottom(cellStyle.getBorderBottomEnum(), cellRangeAddress, sheet);
    RegionUtil.setBorderLeft(cellStyle.getBorderLeftEnum(), cellRangeAddress, sheet);
    RegionUtil.setBorderRight(cellStyle.getBorderRightEnum(), cellRangeAddress, sheet);
  }
}

package app.module.core.usecase.excel.createCell.ports.application.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

@Getter
@Setter
@AllArgsConstructor
public class CellConfig {
  private Integer targetRowNo;
  private Integer targetColNo;
  private Integer cellWidth;
  private CellType cellType;
  private Object cellValue;
  private XSSFCellStyle cellStyle;
  private CellRangeAddress cellRangeAddress;
}

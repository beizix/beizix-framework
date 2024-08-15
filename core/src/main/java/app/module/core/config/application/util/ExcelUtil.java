package app.module.core.config.application.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.security.GeneralSecurityException;
import java.util.Arrays;
import java.util.Date;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.poifs.crypt.EncryptionInfo;
import org.apache.poi.poifs.crypt.EncryptionMode;
import org.apache.poi.poifs.crypt.Encryptor;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExcelUtil {

  @Value("${excel.password}")
  private String excelPassword;

  public void createCells(Row row, Object... objects) {
    Arrays.stream(objects)
        .forEach(
            obj -> {
              Cell cell = row.createCell(row.getPhysicalNumberOfCells());
              String simpleName = obj == null ? "" : obj.getClass().getSimpleName();
              log.debug(String.format("check object's simple name: %s", simpleName));

              switch (simpleName) {
                case "String":
                  cell.setCellValue((String) obj);
                  break;
                case "Integer":
                  cell.setCellValue((Integer) obj);
                  break;
                case "Long":
                  cell.setCellValue((Long) obj);
                  break;
                case "Double":
                  cell.setCellValue((Double) obj);
                  break;
                case "Float":
                  cell.setCellValue((Float) obj);
                  break;
                case "Boolean":
                  cell.setCellValue((Boolean) obj);
                  break;
                case "Date":
                case "LocalDateTime":
                  cell.setCellValue((Date) obj);
                  break;
              }
            });
  }

  public void generateWithPassword(HttpServletResponse response, Workbook wb, String filename) {
    response.setContentType("application/vnd.ms-excel");
    response.setHeader(
        "Content-Disposition", String.format("attachment;filename=%s.xlsx", filename));

    try (ByteArrayOutputStream workbookOutputStream = new ByteArrayOutputStream();
        OutputStream responseOutputStream = response.getOutputStream()) {
      wb.write(workbookOutputStream);
      POIFSFileSystem fs = new POIFSFileSystem();

      Encryptor enc = new EncryptionInfo(EncryptionMode.agile).getEncryptor();
      enc.confirmPassword(excelPassword);

      OPCPackage opc =
          OPCPackage.open(new ByteArrayInputStream(workbookOutputStream.toByteArray()));
      opc.save(enc.getDataStream(fs));
      opc.close();

      fs.writeFilesystem(responseOutputStream);
      wb.close();
    } catch (IOException | InvalidFormatException | GeneralSecurityException e) {
      throw new RuntimeException(e.getMessage(), e);
    }
  }

  public void generateWithoutPassword(HttpServletResponse response, Workbook wb, String filename) {
    response.setContentType("application/vnd.ms-excel");
    response.setHeader(
        "Content-Disposition", String.format("attachment;filename=%s.xlsx", filename));

    try (OutputStream responseOutputStream = response.getOutputStream()) {
      wb.write(responseOutputStream);
      wb.close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}

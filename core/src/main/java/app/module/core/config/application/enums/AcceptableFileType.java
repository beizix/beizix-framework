package app.module.core.config.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum AcceptableFileType {
  // 이미지 파일
  IMAGE(
      Set.of("jpg", "jpeg", "png", "gif", "bmp"),
      Set.of(
          "image/jpeg",
          "image/pjpeg",
          "image/png",
          "image/gif",
          "image/bmp",
          "image/x-windows-bmp")),
  // Office - Excel 파일
  EXCEL(
      Set.of("xlsx", "xlsm", "xlsb", "xltx", "xltm", "xls", "xlt", "xlam", "xla", "xlw", "xlr"),
      Set.of(
          "application/excel",
          "application/vnd.ms-excel",
          "application/x-excel",
          "application/x-tika-ooxml",
          "application/x-tika-msoffice",
          "application/x-msexcel")),
  // Office - Power Point
  PPT(
      Set.of("ppt", "pptx"),
      Set.of(
          "application/mspowerpoint",
          "application/powerpoint",
          "application/vnd.ms-powerpoint",
          "application/x-tika-ooxml",
          "application/x-mspowerpoint")),
  // Office - Word
  WORD(
      Set.of("doc", "docx"),
      Set.of(
          "application/msword",
          "application/vnd.ms-word.document.macroEnabled.12",
          "application/x-tika-ooxml",
          "application/vnd.openxmlformats-officedocument.wordprocessingml.document")),
  // 한글
  HWP(
      Set.of("hwp", "hwpx"),
      Set.of(
          "application/x-hwp",
          "application/haansofthwp",
          "application/vnd.hancom.hwp",
          "application/x-tika-msoffice",
          "application/vnd.hancom.hwpx")),
  // TXT
  TXT(Set.of("txt"), Set.of("text/plain")),
  // CSV
  CSV(Set.of("csv"), Set.of("text/csv", "text/plain")),
  // PDF
  PDF(Set.of("pdf"), Set.of("application/pdf")),
  // ZIP
  ZIP(
      Set.of("zip", "gzip", "gz"),
      Set.of(
          "application/x-compressed",
          "application/x-zip-compressed",
          "application/zip",
          "multipart/x-zip",
          "application/x-gzip",
          "multipart/x-gzip"));

  private final Set<String> extensions;
  private final Set<String> mimeTypes;
}

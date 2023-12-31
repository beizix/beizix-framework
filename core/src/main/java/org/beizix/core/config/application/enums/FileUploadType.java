package org.beizix.core.config.application.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Set;

@Getter
@RequiredArgsConstructor
public enum FileUploadType {
  // URI og:image 용 이미지 파일
  OG_IMAGE(FileStorageType.LOCAL, true, "/ogImage", Set.of(AcceptableFileType.IMAGE)),
  // WEB Editor 이미지 파일
  EDITOR_IMAGE(FileStorageType.LOCAL, true, "/editorImage", Set.of(AcceptableFileType.IMAGE)),
  // 예제 게시판 대표 이미지
  EXAMPLE_REP(FileStorageType.LOCAL, true, "/exampleBoard", Set.of(AcceptableFileType.IMAGE)),
  // 예제 게시판 PUBLIC 다건 파일 업로드
  EXAMPLE_PUBLIC(
      FileStorageType.LOCAL,
      true,
      "/exampleBoard",
      Set.of(
          AcceptableFileType.IMAGE,
          AcceptableFileType.EXCEL,
          AcceptableFileType.PPT,
          AcceptableFileType.HWP,
          AcceptableFileType.WORD,
          AcceptableFileType.CSV,
          AcceptableFileType.TXT,
          AcceptableFileType.PDF,
          AcceptableFileType.ZIP)),
  // 예제 게시판 PRIVATE 다건 파일 업로드
  EXAMPLE_PRIVATE(
      FileStorageType.LOCAL,
      false,
      "/exampleBoard",
      Set.of(
          AcceptableFileType.IMAGE,
          AcceptableFileType.EXCEL,
          AcceptableFileType.PPT,
          AcceptableFileType.HWP,
          AcceptableFileType.WORD,
          AcceptableFileType.CSV,
          AcceptableFileType.TXT,
          AcceptableFileType.PDF,
          AcceptableFileType.ZIP));

  private final FileStorageType fileStorageType;
  private final boolean pubic;
  private final String subPath;
  private final Set<AcceptableFileType> acceptableFileTypes;
}

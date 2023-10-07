package org.beizix.core.application.port.in.exboard;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ExBoardSavePortIn {

  /**
   * 예제 게시판 저장 인터페이스
   *
   * @param exBoard 게시물 정보
   * @param representImgFile 대표 이미지 파일
   * @param privateAttachment 비공개 첨부 파일
   * @param publicAttachments 공개 첨부 파일 목록
   * @return ExBoardSaveOutput
   * @throws IOException 파일 업로드 수행 중 발생한 오류
   */
  Long connect(
      Long id,
      String title,
      String content,
      Boolean visible,
      LocalDateTime boardStartDate,
      LocalDateTime boardEndDate,
      String repImgAlt,
      Integer orderNo,
      List<Long> removeAttachmentIds,
      MultipartFile representImgFile,
      MultipartFile privateAttachment,
      List<MultipartFile> publicAttachments)
      throws IOException;
}

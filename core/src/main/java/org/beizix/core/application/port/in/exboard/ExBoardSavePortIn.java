package org.beizix.core.application.port.in.exboard;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.multipart.MultipartFile;

public interface ExBoardSavePortIn {
  /**
   * 예제 게시판 저장 인터페이스
   *
   * @param id 아이디
   * @param title 제목
   * @param content 내용
   * @param visible 공개여부
   * @param boardStartDate 공개 시작일
   * @param boardEndDate 공개 종료일
   * @param repImgAlt 이미지 alt
   * @param orderNo 정렬번호
   * @param removeAttachmentIds 공개 첨부 파일 삭제 아이디 목록
   * @param representImgFile 대표 이미지 파일
   * @param privateAttachment 비공개 첨부 파일
   * @param publicAttachments 공개 첨부 파일 목록
   * @return 생성/수정된 아이템 아이디
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

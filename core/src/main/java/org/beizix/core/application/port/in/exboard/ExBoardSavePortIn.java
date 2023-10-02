package org.beizix.core.application.port.in.exboard;

import java.io.IOException;
import java.util.List;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveOutput;
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
  ExBoardSaveOutput connect(
      ExBoardSaveInput exBoard,
      MultipartFile representImgFile,
      MultipartFile privateAttachment,
      List<MultipartFile> publicAttachments)
      throws IOException;
}

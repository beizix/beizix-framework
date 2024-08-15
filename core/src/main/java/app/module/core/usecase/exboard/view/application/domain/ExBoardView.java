package app.module.core.usecase.exboard.view.application.domain;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import app.module.core.config.application.component.AuditOutput;
import app.module.core.usecase.file.upload.application.domain.FileUploadOutput;

@Getter
@AllArgsConstructor
public class ExBoardView implements AuditOutput {
  private final String createdBy;
  private final LocalDateTime createdAt;
  private final String updatedBy;
  private final LocalDateTime updatedAt;
  private final Long id;
  private final String title;
  private final String content;
  private final Boolean visible;
  private final LocalDateTime boardStartDate;
  private final LocalDateTime boardEndDate;
  // 대표 이미지 - 조회용
  private final FileUploadOutput representImage;
  // 대표 이미지 - 대체 텍스트
  private final String repImgAlt;
  // 다건 첨부 - 조회용
  private final List<ExBoardViewAttach> attachments;
  // Private 다건 첨부 - 조회용
  private final FileUploadOutput privateAttachment;
  private final Integer orderNo;
}

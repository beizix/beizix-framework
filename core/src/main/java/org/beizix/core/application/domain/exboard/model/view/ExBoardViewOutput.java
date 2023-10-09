package org.beizix.core.application.domain.exboard.model.view;

import java.time.LocalDateTime;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

@Getter
@AllArgsConstructor
public class ExBoardViewOutput implements AuditOutput {
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
  private final List<ExBoardViewAttachOutput> attachments;
  // Private 다건 첨부 - 조회용
  private final FileUploadOutput privateAttachment;
  private final Integer orderNo;
}

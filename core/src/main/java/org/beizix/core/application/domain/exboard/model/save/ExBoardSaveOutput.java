package org.beizix.core.application.domain.exboard.model.save;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardSaveOutput implements AuditOutput {
  private String createdBy;
  private LocalDateTime createdAt;
  private String updatedBy;
  private LocalDateTime updatedAt;
  private Long id;
  private String title;
  private String content;
  private Boolean visible;
  private LocalDateTime boardStartDate;
  private LocalDateTime boardEndDate;
  // 대표 이미지 - 조회용
  private FileUploadOutput representImage;
  // 대표 이미지 - 대체 텍스트
  private String repImgAlt;
  // 다건 첨부 - 조회용
  private List<ExBoardSaveAttachOutput> attachments;
  // Private 다건 첨부 - 조회용
  private FileUploadOutput privateAttachment;
  private Integer orderNo;

  public ExBoardSaveOutput(Long id) {
    this.id = id;
  }
}

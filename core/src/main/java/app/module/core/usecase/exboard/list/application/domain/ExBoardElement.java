package app.module.core.usecase.exboard.list.application.domain;

import java.time.LocalDateTime;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import app.module.core.config.application.component.AuditOutput;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardElement implements AuditOutput {
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
  private SaveToStorage representImage;
  // 대표 이미지 - 대체 텍스트
  private String repImgAlt;
  private Integer orderNo;
}

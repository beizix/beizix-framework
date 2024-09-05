package app.module.admin.usecase.exboard.save.adapters.web.model;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import app.module.core.config.application.component.AuditOutput;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardUpdateBindingVO implements AuditOutput {
  private final String createdBy;
  private final LocalDateTime createdAt;
  private final String updatedBy;
  private final LocalDateTime updatedAt;

  private final Long id;
  private final Boolean visible;

  @NotBlank(message = "{valid.common.required}")
  private final String title;

  @NotBlank(message = "{valid.common.required}")
  private final String content;

  @NotNull(message = "{valid.common.required}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private final LocalDateTime boardStartDate;

  @NotNull(message = "{valid.common.required}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private final LocalDateTime boardEndDate;

  private SaveToStorage representImage; // 대표 이미지 - 조회정보
  private List<ExBoardUpdateAttachVO> attachments; // 다건 첨부 조회정보
  private SaveToStorage privateAttachment; // Private 첨부 - 조회정보
  private final List<Long> removeAttachmentIds;

  private final String repImgAlt; // 대표 이미지 - 대체 텍스트
  private final Integer orderNo;
}

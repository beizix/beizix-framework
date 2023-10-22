package org.beizix.admin.adapter.web.exboard.model.update;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardBindingVO implements AuditOutput {
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

  private FileUploadOutput representImage; // 대표 이미지 - 조회정보
  private List<ExBoardUpdateAttachVO> attachments; // 다건 첨부 조회정보
  private FileUploadOutput privateAttachment; // Private 첨부 - 조회정보
  private final List<Long> removeAttachmentIds;

  private final String repImgAlt; // 대표 이미지 - 대체 텍스트
  private final Integer orderNo;
}

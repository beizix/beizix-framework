package org.beizix.admin.adapter.web.exboard.model.update;

import java.time.LocalDateTime;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.beizix.core.application.domain.common.model.AuditBase;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.format.annotation.DateTimeFormat;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ExBoardUpdateReqVO extends AuditBase {

  private Long id;
  private Boolean visible;

  @NotBlank(message = "{valid.common.required}")
  private String title;

  @NotBlank(message = "{valid.common.required}")
  private String content;

  @NotNull(message = "{valid.common.required}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime boardStartDate;

  @NotNull(message = "{valid.common.required}")
  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime boardEndDate;

  private FileUploadOutput representImage; // 대표 이미지 - 조회정보
  private List<ExBoardUpdateAttachVO> attachments; // 다건 첨부 조회정보
  private FileUploadOutput privateAttachment; // Private 첨부 - 조회정보

  private String repImgAlt; // 대표 이미지 - 대체 텍스트
  private Integer orderNo;
}

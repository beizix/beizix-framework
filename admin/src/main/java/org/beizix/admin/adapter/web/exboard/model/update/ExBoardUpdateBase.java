package org.beizix.admin.adapter.web.exboard.model.update;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import org.beizix.core.adapter.web.common.model.AuditVO;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class ExBoardUpdateBase extends AuditVO {
  @NotBlank(message = "{valid.common.required}")
  private String title;

  @NotBlank(message = "{valid.common.required}")
  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @NotNull(message = "{valid.common.required}")
  private LocalDateTime boardStartDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  @NotNull(message = "{valid.common.required}")
  private LocalDateTime boardEndDate;

  private Long id;
  private Boolean visible;

  private MultipartFile representImgFile; // 대표 이미지 파일

  private FileUploadOutput representImage; // 대표 이미지 - 조회정보

  private String repImgAlt; // 대표 이미지 - 대체 텍스트

  private List<MultipartFile> multipartAttachments = Collections.emptyList(); // 다건 첨부 파일 목록

  private List<ExBoardAttachmentVO> attachments = Collections.emptyList(); // 다건 첨부 조회정보

  private List<Long> removeAttachmentIds = Collections.emptyList(); // 다건 첨부 - 삭제용

  private MultipartFile multipartPrivateAttachment; // Private 첨부 파일

  private FileUploadOutput privateAttachment; // Private 첨부 - 조회정보

  private Integer orderNo;
}

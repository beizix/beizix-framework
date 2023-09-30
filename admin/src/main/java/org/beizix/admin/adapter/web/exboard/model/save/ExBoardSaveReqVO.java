package org.beizix.admin.adapter.web.exboard.model.save;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.Accessors;
import org.beizix.core.adapter.web.common.model.AuditVO;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Accessors(chain = true)
public class ExBoardSaveReqVO extends AuditVO {
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

  // 대표 이미지 - 저장/수정용
  private MultipartFile representImgFile;
  // 대표 이미지 - 조회용
  private FileUploadOutput representImage;
  // 대표 이미지 - 대체 텍스트
  private String repImgAlt;

  // 다건 첨부 - 등록/수정용
  private List<MultipartFile> multipartAttachments = Collections.emptyList();
  // 다건 첨부 - 조회용
  private List<ExBoardSaveAttachRespVO> attachments = Collections.emptyList();
  // 다건 첨부 - 삭제용
  private List<Long> removeAttachmentIds = Collections.emptyList();

  // Private 첨부 - 등록/수정용
  private MultipartFile multipartPrivateAttachment;
  // Private 첨부 - 조회용
  private FileUploadOutput privateAttachment;

  // 비공개 파일 목록
  private List<MultipartFile> privateFiles = Collections.emptyList();

  // 삭제할 파일 seq 목록
  private List<Long> removePrivateFileSeq;

  private Integer orderNo;
}

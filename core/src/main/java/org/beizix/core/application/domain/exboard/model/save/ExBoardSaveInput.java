package org.beizix.core.application.domain.exboard.model.save;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import org.beizix.core.application.domain.common.model.AuditBase;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewAttachOutput;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExBoardSaveInput extends AuditBase {
  private Long id;
  private String title;
  private String content;
  private Boolean visible;
  private LocalDateTime boardStartDate;
  private LocalDateTime boardEndDate;

  // 대표 이미지 - 저장/수정용
  private MultipartFile representImgFile;
  // 대표 이미지 - 조회용
  private FileUploadInfo representImage;
  // 대표 이미지 - 대체 텍스트
  private String repImgAlt;

  // 다건 첨부 - 등록/수정용
  private List<MultipartFile> multipartAttachments = Collections.emptyList();
  // 다건 첨부 - 조회용
  private List<ExBoardSaveAttachInput> attachments = Collections.emptyList();
  // 다건 첨부 - 삭제용
  private List<Long> removeAttachmentIds = Collections.emptyList();

  // Private 다건 첨부 - 등록/수정용
  private MultipartFile multipartPrivateAttachment;
  // Private 다건 첨부 - 조회용
  private FileUploadInfo privateAttachment;

  // 업로드할 공개 파일 목록
  private List<MultipartFile> publicFiles;
  // 업로드할 비공개 파일 목록
  private List<MultipartFile> privateFiles;

  private Integer orderNo;
}

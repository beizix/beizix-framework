package org.beizix.core.application.domain.exboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExBoardInput {
  private Long id;
  private String title;
  private String content;
  private Boolean visible;
  private String createdBy;
  private String updatedBy;
  private LocalDateTime boardStartDate;
  private LocalDateTime boardEndDate;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;

  // 대표 이미지 - 저장/수정용
  private MultipartFile representImgFile;
  // 대표 이미지 - 조회용
  private FileUploadInfo representImage;
  // 대표 이미지 - 대체 텍스트
  private String repImgAlt;

  // 다건 첨부 - 등록/수정용
  private List<MultipartFile> multipartAttachments = Collections.emptyList();
  // 다건 첨부 - 조회용
  private List<ExBoardAttachment> attachments = Collections.emptyList();
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

  // 삭제할 파일 seq 목록
  private List<Long> removePublicFileSeq;
  private List<Long> removePrivateFileSeq;

  private Integer orderNo;
}

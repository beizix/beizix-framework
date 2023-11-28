package org.beizix.front.usecase.exboard.view.adapter.web;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ExBoardVO {
  @NotBlank(message = "{valid.common.required}")
  private String title;

  @NotBlank(message = "{valid.common.required}")
  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
  @NotNull(message = "{valid.common.required}")
  private LocalDateTime boardStartDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'hh:mm")
  @NotNull(message = "{valid.common.required}")
  private LocalDateTime boardEndDate;

  private boolean editMode;
  private int id;
  private Boolean visible;

  // 저장/수정시 신규 파일 업로드 목록
  private List<MultipartFile> multipartFiles = Collections.emptyList();

  // 비공개 파일 목록
  private List<MultipartFile> privateFiles = Collections.emptyList();

  // 삭제할 파일 seq 목록
  private List<Long> removePublicFileSeq;
  private List<Long> removePrivateFileSeq;

  private String createdBy;
  private String updatedBy;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}

package org.beizix.admin.adapter.web.exboard.model.create;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import lombok.*;
import lombok.experimental.Accessors;
import org.beizix.core.adapter.web.common.model.AuditVO;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ExBoardCreateBase extends AuditVO {
  private String title;
  private String content;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime boardStartDate;

  @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime boardEndDate;

  private Long id;
  private Boolean visible;
  private MultipartFile representImgFile; // 대표 이미지 파일
  private String repImgAlt; // 대표 이미지 - 대체 텍스트
  private List<MultipartFile> multipartAttachments = Collections.emptyList(); // 다건 첨부 파일 목록
  private MultipartFile multipartPrivateAttachment; // Private 첨부 파일
  private Integer orderNo;
}

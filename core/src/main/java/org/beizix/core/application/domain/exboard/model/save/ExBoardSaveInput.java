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
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

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
  // 대표 이미지 - 조회용
  private FileUploadOutput representImage;
  // 대표 이미지 - 대체 텍스트
  private String repImgAlt;
  // 다건 첨부 - 조회용
  private List<ExBoardSaveAttachInput> attachments = Collections.emptyList();
  // 다건 첨부 - 삭제용
  private List<Long> removeAttachmentIds = Collections.emptyList();
  // Private 다건 첨부 - 조회용
  private FileUploadOutput privateAttachment;
  private Integer orderNo;
}

package org.beizix.core.application.domain.exboard.model.save;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.AuditOutput;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardSaveAttachOutput implements AuditOutput {
  private String createdBy;
  private LocalDateTime createdAt;
  private String updatedBy;
  private LocalDateTime updatedAt;
  private Long id;
  private FileUploadOutput fileUploadOutput;
}

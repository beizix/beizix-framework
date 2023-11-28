package org.beizix.core.usecase.exboard.view.application.domain;

import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.config.application.component.AuditOutput;
import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardViewAttach implements AuditOutput {
  private String createdBy;
  private LocalDateTime createdAt;
  private String updatedBy;
  private LocalDateTime updatedAt;
  private Long id;
  private FileUploadOutput fileUploadOutput;
}

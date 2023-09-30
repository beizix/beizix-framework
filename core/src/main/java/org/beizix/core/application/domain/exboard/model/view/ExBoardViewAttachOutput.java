package org.beizix.core.application.domain.exboard.model.view;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.beizix.core.application.domain.common.model.AuditBase;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardViewAttachOutput extends AuditBase {
  private Long id;
  private FileUploadInfo fileUploadInfo;
  private ExBoardSaveInput exBoard;
}

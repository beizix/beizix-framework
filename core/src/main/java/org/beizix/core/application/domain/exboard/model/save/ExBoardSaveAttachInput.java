package org.beizix.core.application.domain.exboard.model.save;

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
public class ExBoardSaveAttachInput extends AuditBase {
  private FileUploadOutput fileUploadOutput;
  private ExBoardSaveOutput exBoard;
}

package org.beizix.core.application.domain.exboard.model.save;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

@Getter
@Setter
@AllArgsConstructor
public class ExBoardSaveAttachInput {
  private FileUploadOutput fileUploadOutput;
  private ExBoardSaveInput exBoard;
}

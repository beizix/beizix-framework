package org.beizix.core.application.domain.exboard.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.beizix.core.application.domain.exboard.model.save.ExBoardSaveInput;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

import javax.persistence.Embedded;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ExBoardAttachment {
  private Long id;
  @Embedded private FileUploadInfo fileUploadInfo;
  private ExBoardSaveInput exBoard;
}

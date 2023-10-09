package org.beizix.admin.adapter.web.exboard.model.update;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

@Getter
@AllArgsConstructor
public class ExBoardUpdateAttachVO {
  private final Long id;
  private final FileUploadOutput fileUploadOutput;
}

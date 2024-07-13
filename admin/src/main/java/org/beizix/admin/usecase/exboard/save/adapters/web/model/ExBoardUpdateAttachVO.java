package org.beizix.admin.usecase.exboard.save.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;

@Getter
@AllArgsConstructor
public class ExBoardUpdateAttachVO {
  private final Long id;
  private final FileUploadOutput fileUploadOutput;
}

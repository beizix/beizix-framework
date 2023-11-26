package org.beizix.admin.usecase.exboard.save.adapter.web;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.beizix.core.usecase.file.upload.domain.FileUploadOutput;

@Getter
@AllArgsConstructor
public class ExBoardUpdateAttachVO {
  private final Long id;
  private final FileUploadOutput fileUploadOutput;
}

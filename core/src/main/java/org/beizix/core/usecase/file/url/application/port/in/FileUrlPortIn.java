package org.beizix.core.usecase.file.url.application.port.in;

import org.beizix.core.configuration.application.enums.ContentDispositionType;
import org.beizix.core.usecase.file.upload.domain.FileUploadOutput;

public interface FileUrlPortIn {
  String connect(ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput);
  String connect(String contentDispositionTypeName, FileUploadOutput fileUploadOutput);
}

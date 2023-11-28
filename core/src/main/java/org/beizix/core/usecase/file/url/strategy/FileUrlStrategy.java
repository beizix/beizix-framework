package org.beizix.core.usecase.file.url.strategy;

import org.beizix.core.config.application.enums.ContentDispositionType;
import org.beizix.core.config.application.enums.FileStorageType;
import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;

public interface FileUrlStrategy {
  FileStorageType getStorageType();

  String operate(ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput);
}

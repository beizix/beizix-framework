package org.beizix.core.usecase.file.url.strategy;

import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.usecase.file.upload.domain.FileUploadOutput;

public interface FileUrlStrategy {
  FileStorageType getStorageType();

  String operate(ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput);
}

package org.beizix.core.application.domain.fileurl.strategy;

import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

public interface FileUrlStrategy {
  FileStorageType getStorageType();

  String operate(ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput);
}

package app.module.core.usecase.file.url.strategy;

import app.module.core.usecase.file.upload.application.domain.FileUploadOutput;
import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileStorageType;

public interface FileUrlStrategy {
  FileStorageType getStorageType();

  String operate(ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput);
}

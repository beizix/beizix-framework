package org.beizix.core.feature.fileUrl.application.strategy;

import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.feature.fileUpload.application.model.FileUploadInfo;

public interface FileUrlStrategy {
  FileStorageType getStorageType();

  String operate(ContentDispositionType contentDispositionType, FileUploadInfo fileUploadInfo);
}

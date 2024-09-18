package app.module.core.usecase.file.url.strategy;

import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileStorageType;

public interface FileUrlStrategy {
  FileStorageType getStorageType();

  String getInlineURL(String subPath, String filename);

  String getAttachmentURL(Long fileId);
}

package app.module.core.usecase.file.url.application.port.in;

import app.module.core.config.application.enums.FileStorageType;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import app.module.core.config.application.enums.ContentDispositionType;

public interface FileUrlPortIn {
  String getInline(SaveToStorage saveToStorage);
  String getAttachment(FileStorageType storageType, Long fileId);
}

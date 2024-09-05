package app.module.core.usecase.file.url.strategy;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileStorageType;

public interface FileUrlStrategy {
  FileStorageType getStorageType();

  String operate(ContentDispositionType contentDispositionType, SaveToStorage saveToStorage);
}

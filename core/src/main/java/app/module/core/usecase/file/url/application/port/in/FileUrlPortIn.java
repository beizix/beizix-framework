package app.module.core.usecase.file.url.application.port.in;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import app.module.core.config.application.enums.ContentDispositionType;

public interface FileUrlPortIn {
  String connect(ContentDispositionType contentDispositionType, SaveToStorage saveToStorage);
  String connect(String contentDispositionTypeName, SaveToStorage saveToStorage);
}

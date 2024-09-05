package app.module.core.usecase.file.url.strategy;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import org.springframework.stereotype.Service;
import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileStorageType;

@Service
public class LocalFileUrlStrategy implements FileUrlStrategy {
  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.LOCAL;
  }

  @Override
  public String operate(
      ContentDispositionType contentDispositionType, SaveToStorage saveToStorage) {
    String fileUrl = null;

    if (contentDispositionType.equals(ContentDispositionType.INLINE)) {
      fileUrl =
          String.format(
              "/content-disposition/inline/%s%s/%s",
              saveToStorage.getType().isPubic() ? "public" : "private",
              saveToStorage.getPath(),
              saveToStorage.getName());
    }

    if (contentDispositionType.equals(ContentDispositionType.ATTACHMENT)) {
      fileUrl =
          String.format(
              "/content-disposition/attachment/%s?path=%s&name=%s&originName=%s",
              saveToStorage.getType().isPubic() ? "public" : "private",
              saveToStorage.getPath(),
              saveToStorage.getName(),
              saveToStorage.getOriginName());
    }

    return fileUrl;
  }
}

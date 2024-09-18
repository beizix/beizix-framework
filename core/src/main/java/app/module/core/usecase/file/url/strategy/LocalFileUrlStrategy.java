package app.module.core.usecase.file.url.strategy;

import app.module.core.config.application.enums.FileStorageType;
import org.springframework.stereotype.Service;

@Service
public class LocalFileUrlStrategy implements FileUrlStrategy {
  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.LOCAL;
  }

  @Override
  public String getInlineURL(String subPath, String filename) {
    return String.format("/content-disposition/inline%s/%s", subPath, filename);
  }

  @Override
  public String getAttachmentURL(Long fileId) {
    return String.format("/content-disposition/attachment/%s", fileId);
  }
}

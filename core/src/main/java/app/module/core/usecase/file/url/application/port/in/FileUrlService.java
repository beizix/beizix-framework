package app.module.core.usecase.file.url.application.port.in;

import app.module.core.config.application.enums.FileStorageType;
import app.module.core.usecase.file.url.strategy.FileUrlStrategy;
import java.util.NoSuchElementException;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "fileUrlService")
@RequiredArgsConstructor
public class FileUrlService implements FileUrlPortIn {
  private final Set<FileUrlStrategy> fileUrlStrategies;

  @Override
  public String getInline(FileStorageType storageType, String subPath, String filename) {
    return getFileUrlStrategy(storageType).getInlineURL(subPath, filename);
  }

  @Override
  public String getAttachment(FileStorageType storageType, Long fileId) {
    return getFileUrlStrategy(storageType).getAttachmentURL(fileId);
  }

  private FileUrlStrategy getFileUrlStrategy(FileStorageType fileStorageType) {
    return fileUrlStrategies.stream()
        .filter(strategy -> strategy.getStorageType().equals(fileStorageType))
        .findFirst()
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    String.format(
                        "There is no URL strategy for FileStorageType(`%s`)",
                        fileStorageType.name())));
  }
}

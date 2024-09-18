package app.module.core.usecase.file.url.application.port.in;

import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileStorageType;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import app.module.core.usecase.file.url.strategy.FileUrlStrategy;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service(value = "fileUrlService")
@RequiredArgsConstructor
public class FileUrlService implements FileUrlPortIn {
  private final Set<FileUrlStrategy> fileUrlStrategies;

  @Override
  public String getInline(SaveToStorage saveToStorage) {
    return Optional.ofNullable(saveToStorage)
        .map(
            uploadInfo ->
                getFileUrlStrategy(uploadInfo.getType().getFileStorageType())
                    .getInlineURL(uploadInfo.getPath(), uploadInfo.getName()))
        .orElse(null);
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

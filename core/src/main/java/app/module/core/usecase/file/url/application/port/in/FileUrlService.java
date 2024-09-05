package app.module.core.usecase.file.url.application.port.in;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileStorageType;
import app.module.core.usecase.file.url.strategy.FileUrlStrategy;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service(value = "fileUrlService")
@RequiredArgsConstructor
public class FileUrlService implements FileUrlPortIn {
  private final Set<FileUrlStrategy> fileUrlStrategies;

  @Override
  public String connect(
      ContentDispositionType contentDispositionType, SaveToStorage saveToStorage) {
    return Optional.ofNullable(saveToStorage)
        .map(
            uploadInfo ->
                getFileUrlStrategy(uploadInfo.getType().getFileStorageType())
                    .operate(contentDispositionType, uploadInfo))
        .orElse(null);
  }

  @Override
  public String connect(String contentDispositionTypeName, SaveToStorage saveToStorage) {
    return this.connect(ContentDispositionType.valueOf(contentDispositionTypeName),
        saveToStorage);
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

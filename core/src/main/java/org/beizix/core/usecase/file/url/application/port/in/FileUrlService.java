package org.beizix.core.usecase.file.url.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;
import org.springframework.stereotype.Service;
import org.beizix.core.config.application.enums.ContentDispositionType;
import org.beizix.core.config.application.enums.FileStorageType;
import org.beizix.core.usecase.file.url.strategy.FileUrlStrategy;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service(value = "fileUrlService")
@RequiredArgsConstructor
public class FileUrlService implements FileUrlPortIn {
  private final Set<FileUrlStrategy> fileUrlStrategies;

  @Override
  public String connect(
      ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput) {
    return Optional.ofNullable(fileUploadOutput)
        .map(
            uploadInfo ->
                getFileUrlStrategy(uploadInfo.getType().getFileStorageType())
                    .operate(contentDispositionType, uploadInfo))
        .orElse(null);
  }

  @Override
  public String connect(String contentDispositionTypeName, FileUploadOutput fileUploadOutput) {
    return this.connect(ContentDispositionType.valueOf(contentDispositionTypeName),
        fileUploadOutput);
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

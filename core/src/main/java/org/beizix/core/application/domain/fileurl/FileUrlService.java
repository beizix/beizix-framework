package org.beizix.core.application.domain.fileurl;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.application.port.in.fileurl.FileUrlPortIn;
import org.beizix.core.application.domain.fileurl.strategy.FileUrlStrategy;

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

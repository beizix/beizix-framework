package org.beizix.core.feature.fileUrl.application.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;
import org.beizix.core.feature.fileUrl.application.service.FileUrlService;
import org.beizix.core.feature.fileUrl.application.strategy.FileUrlStrategy;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

@Service(value = "fileUrlService")
@RequiredArgsConstructor
public class FileUrlServiceImpl implements FileUrlService {
  private final Set<FileUrlStrategy> fileUrlStrategies;

  @Override
  public String operate(
      ContentDispositionType contentDispositionType, FileUploadInfo fileUploadInfo) {
    return Optional.ofNullable(fileUploadInfo)
        .map(
            uploadInfo ->
                getFileUrlStrategy(uploadInfo.getType().getFileStorageType())
                    .operate(contentDispositionType, uploadInfo))
        .orElse(null);
  }

  @Override
  public String operate(String contentDispositionTypeName, FileUploadInfo fileUploadInfo) {
    return this.operate(ContentDispositionType.valueOf(contentDispositionTypeName), fileUploadInfo);
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

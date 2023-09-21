package org.beizix.aws.feature.fileUrl.application.strategy.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.feature.fileUpload.application.model.FileUploadInfo;
import org.beizix.core.feature.fileUrl.application.strategy.FileUrlStrategy;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FileUrlStrategyImpl implements FileUrlStrategy {
  @Value("${recycle.aws.cloudfront.domain}")
  private String cloudFrontDomain;

  @Value("${recycle.aws.s3.bucketFolder}")
  private String bucketFolder;

  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.S3;
  }

  @Override
  public String operate(
      ContentDispositionType contentDispositionType, FileUploadInfo fileUploadInfo) {
    return Optional.ofNullable(fileUploadInfo)
        .map(
            uploadInfo ->
                "https://"
                    + cloudFrontDomain
                    + bucketFolder
                    + uploadInfo.getPath()
                    + "/"
                    + uploadInfo.getName())
        .orElse(null);
  }
}

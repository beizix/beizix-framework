package org.beizix.aws.application.domain.fileurl.strategy.s3;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.application.domain.fileurl.strategy.FileUrlStrategy;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class S3FileUrlStrategy implements FileUrlStrategy {
  @Value("${beizix.aws.cloudfront.domain}")
  private String cloudFrontDomain;

  @Value("${beizix.aws.s3.bucketFolder}")
  private String bucketFolder;

  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.S3;
  }

  @Override
  public String operate(
      ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput) {
    return Optional.ofNullable(fileUploadOutput)
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

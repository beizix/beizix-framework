package app.module.aws.usecase.file.url.strategy;

import app.module.core.config.application.enums.FileStorageType;
import app.module.core.usecase.file.url.strategy.FileUrlStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class S3FileUrlStrategy implements FileUrlStrategy {
  @Value("${app.aws.cloudfront.domain}")
  private String cloudFrontDomain;

  @Value("${app.aws.s3.bucketFolder}")
  private String bucketFolder;

  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.S3;
  }

  @Override
  public String getInlineURL(String subPath, String filename) {
    return "https://" + cloudFrontDomain + bucketFolder + subPath + "/" + filename;
  }

  @Override
  public String getAttachmentURL(Long fileId) {
    return String.format("/content-disposition/attachment/%s", fileId);
  }
}

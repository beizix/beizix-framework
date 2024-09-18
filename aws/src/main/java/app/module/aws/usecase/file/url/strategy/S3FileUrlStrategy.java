package app.module.aws.usecase.file.url.strategy;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import app.module.core.config.application.enums.ContentDispositionType;
import app.module.core.config.application.enums.FileStorageType;
import app.module.core.usecase.file.url.strategy.FileUrlStrategy;

import java.util.Optional;

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
  public String operate(
      ContentDispositionType contentDispositionType, SaveToStorage saveToStorage) {
    return Optional.ofNullable(saveToStorage)
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

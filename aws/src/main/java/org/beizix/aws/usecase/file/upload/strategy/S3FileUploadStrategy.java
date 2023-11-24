package org.beizix.aws.usecase.file.upload.strategy;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.usecase.file.upload.strategy.FileUploadStrategy;

import java.io.IOException;
import java.io.InputStream;

@Service
@RequiredArgsConstructor
public class S3FileUploadStrategy implements FileUploadStrategy {
  private final AmazonS3Client amazonS3Client;

  @Value("${beizix.aws.s3.bucketName}")
  private String bucket;

  @Value("${beizix.aws.s3.bucketFolder}")
  private String bucketFolder;

  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.S3;
  }

  @Override
  public void operate(
      MultipartFile multipartFile, Boolean isPublic, String createSubPath, String createFilename)
      throws IOException {
    if (multipartFile == null || multipartFile.isEmpty())
      throw new RuntimeException("Failed to store empty file.");

    if (!isPublic)
      throw new RuntimeException(
          "Argument `isPublic` is not true. It should be true to use S3FileUploadStrategy");

    String bucketPath = bucket + bucketFolder;
    String createPath = createSubPath + "/" + createFilename;
    if (createPath.startsWith("/")) createPath = createPath.replaceFirst("/", "");

    ObjectMetadata objMetadata = new ObjectMetadata();
    objMetadata.setContentType(multipartFile.getContentType());
    objMetadata.setContentLength(multipartFile.getSize());

    try (InputStream inputStream = multipartFile.getInputStream()) {
      amazonS3Client.putObject(
          new PutObjectRequest(bucketPath, createPath, inputStream, objMetadata)
              .withCannedAcl(CannedAccessControlList.PublicRead));
    }
  }
}

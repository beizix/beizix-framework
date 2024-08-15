package app.module.core.usecase.file.upload.strategy;

import org.springframework.web.multipart.MultipartFile;
import app.module.core.config.application.enums.FileStorageType;

import java.io.IOException;

public interface FileUploadStrategy {
  FileStorageType getStorageType();

  void operate(
      MultipartFile multipartFile, Boolean isPublic, String createSubPath, String createFilename) throws IOException;
}

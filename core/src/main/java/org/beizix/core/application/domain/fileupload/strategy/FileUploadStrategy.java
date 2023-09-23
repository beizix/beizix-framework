package org.beizix.core.application.domain.fileupload.strategy;

import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.config.enums.FileStorageType;

import java.io.IOException;

public interface FileUploadStrategy {
  FileStorageType getStorageType();

  void operate(
      MultipartFile multipartFile, Boolean isPublic, String createSubPath, String createFilename) throws IOException;
}

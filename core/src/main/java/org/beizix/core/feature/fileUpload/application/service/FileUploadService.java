package org.beizix.core.feature.fileUpload.application.service;

import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.config.enums.FileUploadType;
import org.beizix.core.feature.fileUpload.application.model.FileUploadInfo;

import java.io.IOException;
import java.util.Optional;

public interface FileUploadService {
  Optional<FileUploadInfo> operate(FileUploadType fileUploadType, MultipartFile multipartFile)
      throws IOException;
}

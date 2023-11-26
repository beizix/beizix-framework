package org.beizix.core.usecase.file.upload.application.port.in;

import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.configuration.application.enums.FileUploadType;

import java.io.IOException;
import java.util.Optional;

public interface FileUploadPortIn {
  Optional<FileUploadOutput> connect(FileUploadType fileUploadType, MultipartFile multipartFile)
      throws IOException;
}

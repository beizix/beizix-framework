package org.beizix.core.application.port.in.fileupload;

import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.config.enums.FileUploadType;

import java.io.IOException;
import java.util.Optional;

public interface FileUploadPortIn {
  Optional<FileUploadOutput> connect(FileUploadType fileUploadType, MultipartFile multipartFile)
      throws IOException;
}

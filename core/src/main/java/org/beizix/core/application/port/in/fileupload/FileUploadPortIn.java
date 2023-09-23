package org.beizix.core.application.port.in.fileupload;

import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.config.enums.FileUploadType;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

import java.io.IOException;
import java.util.Optional;

public interface FileUploadPortIn {
  Optional<FileUploadInfo> connect(FileUploadType fileUploadType, MultipartFile multipartFile)
      throws IOException;
}

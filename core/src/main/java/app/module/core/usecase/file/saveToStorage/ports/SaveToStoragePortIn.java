package app.module.core.usecase.file.saveToStorage.ports;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import org.springframework.web.multipart.MultipartFile;
import app.module.core.config.application.enums.FileUploadType;

import java.io.IOException;
import java.util.Optional;

public interface SaveToStoragePortIn {
  Optional<SaveToStorage> operate(FileUploadType fileUploadType, MultipartFile multipartFile)
      throws IOException;
}

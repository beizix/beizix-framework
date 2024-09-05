package app.module.core.usecase.image.crop.application.port.in;

import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageCropPortIn {
  void operate(
      SaveToStorage saveToStorage, MultipartFile multipartFile, int maxWidth, double xyRatio)
      throws IOException;
}

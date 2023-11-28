package org.beizix.core.usecase.image.crop.application.port.in;

import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;

import java.io.IOException;

public interface ImageCropPortIn {
  void operate(
      FileUploadOutput fileUploadOutput, MultipartFile multipartFile, int maxWidth, double xyRatio)
      throws IOException;
}

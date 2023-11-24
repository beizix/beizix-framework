package org.beizix.core.application.port.in.imagecrop;

import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.usecase.file.upload.domain.FileUploadOutput;

import java.io.IOException;

public interface ImageCropPortIn {
  void operate(
      FileUploadOutput fileUploadOutput, MultipartFile multipartFile, int maxWidth, double xyRatio)
      throws IOException;
}

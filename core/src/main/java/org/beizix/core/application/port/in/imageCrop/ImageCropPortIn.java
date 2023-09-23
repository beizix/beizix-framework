package org.beizix.core.application.port.in.imageCrop;

import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

import java.io.IOException;

public interface ImageCropPortIn {
  void operate(
      FileUploadInfo fileUploadInfo, MultipartFile multipartFile, int maxWidth, double xyRatio)
      throws IOException;
}

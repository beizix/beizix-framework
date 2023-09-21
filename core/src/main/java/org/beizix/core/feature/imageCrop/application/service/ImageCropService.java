package org.beizix.core.feature.imageCrop.application.service;

import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.feature.fileUpload.application.model.FileUploadInfo;

import java.io.IOException;

public interface ImageCropService {
  void operate(FileUploadInfo fileUploadInfo, MultipartFile multipartFile, int maxWidth, double xyRatio)
      throws IOException;
}

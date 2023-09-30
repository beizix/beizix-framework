package org.beizix.core.application.domain.imagecrop;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import javax.imageio.ImageIO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.beizix.core.application.port.in.imagecrop.ImageCropPortIn;
import org.beizix.utility.common.CommonUtil;
import org.beizix.utility.common.ImageUtil;

@Service
@RequiredArgsConstructor
class ImageCropService implements ImageCropPortIn {
  private final CommonUtil commonUtil;

  @Value("${path.upload.public}")
  private String publicPath;

  @Override
  public void operate(
          FileUploadOutput fileUploadOutput, MultipartFile multipartFile, int maxWidth, double xyRatio)
      throws IOException {
    if (fileUploadOutput == null) return;

    Path directoryPath = Paths.get(publicPath, fileUploadOutput.getPath(), "crop");
    Files.createDirectories(directoryPath);

    try (InputStream inputStream = multipartFile.getInputStream()) {
      // 이미지 크롭 및 리사이징
      BufferedImage croppedImage =
          ImageUtil.cropImageByMaxWidthAndRatio(inputStream, maxWidth, xyRatio);

      ImageIO.write(
          croppedImage,
          commonUtil.getFileExtension(fileUploadOutput.getName()),
          directoryPath.resolve(fileUploadOutput.getName()).toFile());
    }
  }
}

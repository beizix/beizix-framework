package app.module.core.usecase.image.crop.application.port.in;

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
import app.module.core.usecase.file.upload.application.domain.FileUploadOutput;
import app.module.core.config.application.util.CommonUtil;
import app.module.core.config.application.util.ImageUtil;

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

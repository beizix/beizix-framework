package org.beizix.core.usecase.file.url.strategy;

import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;
import org.springframework.stereotype.Service;
import org.beizix.core.config.application.enums.ContentDispositionType;
import org.beizix.core.config.application.enums.FileStorageType;

@Service
public class LocalFileUrlStrategy implements FileUrlStrategy {
  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.LOCAL;
  }

  @Override
  public String operate(
      ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput) {
    String fileUrl = null;

    if (contentDispositionType.equals(ContentDispositionType.INLINE)) {
      fileUrl =
          String.format(
              "/content-disposition/inline/%s%s/%s",
              fileUploadOutput.getType().isPubic() ? "public" : "private",
              fileUploadOutput.getPath(),
              fileUploadOutput.getName());
    }

    if (contentDispositionType.equals(ContentDispositionType.ATTACHMENT)) {
      fileUrl =
          String.format(
              "/content-disposition/attachment/%s?path=%s&name=%s&originName=%s",
              fileUploadOutput.getType().isPubic() ? "public" : "private",
              fileUploadOutput.getPath(),
              fileUploadOutput.getName(),
              fileUploadOutput.getOriginName());
    }

    return fileUrl;
  }
}

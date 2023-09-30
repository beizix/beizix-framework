package org.beizix.core.application.domain.fileurl.strategy.local;

import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;
import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.application.domain.fileurl.strategy.FileUrlStrategy;

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

package org.beizix.core.application.domain.fileurl.strategy.local;

import org.springframework.stereotype.Service;
import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.config.enums.FileStorageType;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;
import org.beizix.core.application.domain.fileurl.strategy.FileUrlStrategy;

@Service
public class LocalFileUrlStrategy implements FileUrlStrategy {
  @Override
  public FileStorageType getStorageType() {
    return FileStorageType.LOCAL;
  }

  @Override
  public String operate(
      ContentDispositionType contentDispositionType, FileUploadInfo fileUploadInfo) {
    String fileUrl = null;

    if (contentDispositionType.equals(ContentDispositionType.INLINE)) {
      fileUrl =
          String.format(
              "/content-disposition/inline/%s%s/%s",
              fileUploadInfo.getType().isPubic() ? "public" : "private",
              fileUploadInfo.getPath(),
              fileUploadInfo.getName());
    }

    if (contentDispositionType.equals(ContentDispositionType.ATTACHMENT)) {
      fileUrl =
          String.format(
              "/content-disposition/attachment/%s?path=%s&name=%s&originName=%s",
              fileUploadInfo.getType().isPubic() ? "public" : "private",
              fileUploadInfo.getPath(),
              fileUploadInfo.getName(),
              fileUploadInfo.getOriginName());
    }

    return fileUrl;
  }
}

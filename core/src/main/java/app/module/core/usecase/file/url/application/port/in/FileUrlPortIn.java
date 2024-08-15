package app.module.core.usecase.file.url.application.port.in;

import app.module.core.usecase.file.upload.application.domain.FileUploadOutput;
import app.module.core.config.application.enums.ContentDispositionType;

public interface FileUrlPortIn {
  String connect(ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput);
  String connect(String contentDispositionTypeName, FileUploadOutput fileUploadOutput);
}

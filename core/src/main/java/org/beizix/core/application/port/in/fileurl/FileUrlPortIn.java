package org.beizix.core.application.port.in.fileurl;

import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.application.domain.fileupload.model.FileUploadOutput;

public interface FileUrlPortIn {
  String connect(ContentDispositionType contentDispositionType, FileUploadOutput fileUploadOutput);
  String connect(String contentDispositionTypeName, FileUploadOutput fileUploadOutput);
}

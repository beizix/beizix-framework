package org.beizix.core.application.port.in.fileurl;

import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

public interface FileUrlPortIn {
  String connect(ContentDispositionType contentDispositionType, FileUploadInfo fileUploadInfo);
  String connect(String contentDispositionTypeName, FileUploadInfo fileUploadInfo);
}

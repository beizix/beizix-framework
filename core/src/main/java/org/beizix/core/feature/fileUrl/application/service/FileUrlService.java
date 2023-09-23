package org.beizix.core.feature.fileUrl.application.service;

import org.beizix.core.config.enums.ContentDispositionType;
import org.beizix.core.application.domain.fileupload.model.FileUploadInfo;

public interface FileUrlService {
  String operate(ContentDispositionType contentDispositionType, FileUploadInfo fileUploadInfo);
  String operate(String contentDispositionTypeName, FileUploadInfo fileUploadInfo);
}

package org.beizix.admin.usecase.exboard.save.application.port.out;

import java.time.LocalDateTime;
import java.util.List;
import org.beizix.core.usecase.file.upload.application.domain.FileUploadOutput;

public interface ExBoardSavePortOut {
  Long connect(
      Long id,
      String title,
      String content,
      Boolean visible,
      LocalDateTime boardStartDate,
      LocalDateTime boardEndDate,
      FileUploadOutput representImage,
      String repImgAlt,
      List<FileUploadOutput> attachments,
      FileUploadOutput privateAttachment,
      Integer orderNo);
}

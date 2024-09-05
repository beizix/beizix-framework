package app.module.admin.usecase.exboard.save.ports;

import java.time.LocalDateTime;
import java.util.List;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;

public interface ExBoardSavePortOut {
  Long connect(
      Long id,
      String title,
      String content,
      Boolean visible,
      LocalDateTime boardStartDate,
      LocalDateTime boardEndDate,
      SaveToStorage representImage,
      String repImgAlt,
      List<SaveToStorage> attachments,
      SaveToStorage privateAttachment,
      Integer orderNo);
}

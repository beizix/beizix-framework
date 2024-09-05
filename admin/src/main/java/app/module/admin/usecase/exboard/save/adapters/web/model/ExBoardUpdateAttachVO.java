package app.module.admin.usecase.exboard.save.adapters.web.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import app.module.core.usecase.file.saveToStorage.ports.application.domain.SaveToStorage;

@Getter
@AllArgsConstructor
public class ExBoardUpdateAttachVO {
  private final Long id;
  private final SaveToStorage saveToStorage;
}

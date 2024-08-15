package app.module.admin.usecase.exboard.save.adapters.persistence;

import app.module.admin.usecase.exboard.save.ports.ExBoardAttachmentRemovePortOut;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
class ExBoardAttachmentRemoveDao implements ExBoardAttachmentRemovePortOut {
  private final ExBoardSaveRepo saveRe;

  @Override
  public void connect(Long id) {
    saveRe.deleteById(id);
  }
}

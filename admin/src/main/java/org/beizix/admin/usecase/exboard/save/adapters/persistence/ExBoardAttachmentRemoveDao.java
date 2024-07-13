package org.beizix.admin.usecase.exboard.save.adapters.persistence;

import org.beizix.admin.usecase.exboard.save.ports.ExBoardAttachmentRemovePortOut;
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

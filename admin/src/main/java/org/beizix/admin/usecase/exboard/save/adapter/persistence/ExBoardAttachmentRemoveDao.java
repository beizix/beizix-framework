package org.beizix.admin.usecase.exboard.save.adapter.persistence;

import org.beizix.admin.usecase.exboard.save.application.port.out.ExBoardAttachmentRemovePortOut;
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

package org.beizix.core.adapter.persistence.exboard;

import org.beizix.core.adapter.persistence.exboard.repository.ExBoardAttachmentRepo;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.exboard.ExBoardAttachmentRemovePortOut;

@Repository
@RequiredArgsConstructor
class ExBoardAttachmentRemoveDao implements ExBoardAttachmentRemovePortOut {
  private final ExBoardAttachmentRepo exBoardAttachmentRepo;

  @Override
  public void connect(Long id) {
    exBoardAttachmentRepo.deleteById(id);
  }
}

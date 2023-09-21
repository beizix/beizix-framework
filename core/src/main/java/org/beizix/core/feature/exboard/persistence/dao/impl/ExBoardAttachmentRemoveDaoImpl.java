package org.beizix.core.feature.exboard.persistence.dao.impl;

import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.feature.exboard.persistence.dao.ExBoardAttachmentRemoveDao;

@Repository
@RequiredArgsConstructor
class ExBoardAttachmentRemoveDaoImpl implements ExBoardAttachmentRemoveDao {
  private final ExBoardAttachmentRepo exBoardAttachmentRepo;

  @Override
  public void operate(Long id) {
    exBoardAttachmentRepo.deleteById(id);
  }
}

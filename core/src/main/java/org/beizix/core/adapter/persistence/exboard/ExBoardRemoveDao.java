package org.beizix.core.adapter.persistence.exboard;

import java.util.List;

import org.beizix.core.adapter.persistence.exboard.repository.ExBoardRepo;
import org.springframework.stereotype.Repository;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.port.out.exboard.ExBoardRemovePortOut;

@Repository
@RequiredArgsConstructor
class ExBoardRemoveDao implements ExBoardRemovePortOut {
  private final ExBoardRepo exBoardRepo;

  @Override
  public void connect(List<Long> checkedIds) {
    exBoardRepo.deleteAllById(checkedIds);
  }
}

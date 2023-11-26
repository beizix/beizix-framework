package org.beizix.admin.usecase.exboard.remove.adapter.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.remove.application.port.out.ExBoardRemovePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
class ExBoardRemoveDao implements ExBoardRemovePortOut {
  private final ExBoardRemoveRepo exBoardRepo;

  @Override
  public void connect(List<Long> checkedIds) {
    exBoardRepo.deleteAllById(checkedIds);
  }
}

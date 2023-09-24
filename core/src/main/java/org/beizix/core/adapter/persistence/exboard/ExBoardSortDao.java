package org.beizix.core.adapter.persistence.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardSortRepo;
import org.springframework.stereotype.Repository;
import org.beizix.core.application.port.out.exboard.ExBoardSortPortOut;

@Repository
@RequiredArgsConstructor
public class ExBoardSortDao implements ExBoardSortPortOut {
  private final ExBoardSortRepo exBoardSortRepo;

  @Override
  public void connect(Long id, Integer orderNo) {
    exBoardSortRepo.operate(id, orderNo);
  }
}

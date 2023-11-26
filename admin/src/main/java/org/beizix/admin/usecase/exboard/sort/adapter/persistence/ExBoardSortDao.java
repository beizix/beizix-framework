package org.beizix.admin.usecase.exboard.sort.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.sort.application.port.out.ExBoardSortPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExBoardSortDao implements ExBoardSortPortOut {
  private final ExBoardSortRepo exBoardSortRepo;

  @Override
  public void connect(Long id, Integer orderNo) {
    exBoardSortRepo.operate(id, orderNo);
  }
}

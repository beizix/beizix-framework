package org.beizix.admin.usecase.exboard.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.save.application.port.out.ExBoardNextOrderNoPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class ExBoardNextOrderNoDao implements ExBoardNextOrderNoPortOut {
  private final ExBoardMaxOrderNoRepo exBoardMaxOrderNoRepo;

  @Override
  public Integer connect() {
    return exBoardMaxOrderNoRepo.operate().orElse(-1) + 1;
  }
}

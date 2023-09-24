package org.beizix.core.adapter.persistence.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.adapter.persistence.exboard.repository.ExBoardMaxOrderNoRepo;
import org.springframework.stereotype.Repository;
import org.beizix.core.application.port.out.exboard.ExBoardNextOrderNoPortOut;

@Repository
@RequiredArgsConstructor
public class ExBoardNextOrderNoDao implements ExBoardNextOrderNoPortOut {
  private final ExBoardMaxOrderNoRepo exBoardMaxOrderNoRepo;

  @Override
  public Integer connect() {
    return exBoardMaxOrderNoRepo.operate().orElse(-1) + 1;
  }
}

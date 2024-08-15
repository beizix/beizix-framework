package app.module.admin.usecase.exboard.save.adapters.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.exboard.save.ports.ExBoardNextOrderNoPortOut;
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

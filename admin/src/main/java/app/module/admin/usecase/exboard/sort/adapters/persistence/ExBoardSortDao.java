package app.module.admin.usecase.exboard.sort.adapters.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.exboard.sort.ports.ExBoardSortPortOut;
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

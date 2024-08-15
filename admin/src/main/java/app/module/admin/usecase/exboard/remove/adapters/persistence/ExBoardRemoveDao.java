package app.module.admin.usecase.exboard.remove.adapters.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.exboard.remove.ports.ExBoardRemovePortOut;
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

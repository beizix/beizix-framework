package app.module.admin.usecase.exboard.sort.ports.application;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.exboard.sort.ports.ExBoardSortPortIn;
import app.module.admin.usecase.exboard.sort.ports.ExBoardSortPortOut;
import app.module.admin.usecase.exboard.sort.ports.application.domain.ExBoardSortCmd;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExBoardSortService implements ExBoardSortPortIn {
  private final ExBoardSortPortOut exBoardSortPortOut;

  @Override
  @Transactional
  public void operate(List<ExBoardSortCmd> sortItems) {
    sortItems.forEach(item -> exBoardSortPortOut.connect(item.getId(), item.getOrderNo()));
  }
}

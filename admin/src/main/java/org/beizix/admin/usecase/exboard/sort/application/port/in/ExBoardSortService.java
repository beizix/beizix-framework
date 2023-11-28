package org.beizix.admin.usecase.exboard.sort.application.port.in;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.sort.application.port.out.ExBoardSortPortOut;
import org.beizix.admin.usecase.exboard.sort.application.domain.ExBoardSortCommand;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExBoardSortService implements ExBoardSortPortIn {
  private final ExBoardSortPortOut exBoardSortPortOut;

  @Override
  @Transactional
  public void operate(List<ExBoardSortCommand> sortItems) {
    sortItems.forEach(item -> exBoardSortPortOut.connect(item.getId(), item.getOrderNo()));
  }
}

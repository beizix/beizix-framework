package org.beizix.core.application.domain.exboard;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.sort.ExBoardSortInput;
import org.beizix.core.application.port.in.exboard.ExBoardSortPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardSortPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExBoardSortService implements ExBoardSortPortIn {
  private final ExBoardSortPortOut exBoardSortPortOut;

  @Override
  @Transactional
  public void operate(List<ExBoardSortInput> sortItems) {
    sortItems.forEach(item -> exBoardSortPortOut.connect(item.getId(), item.getOrderNo()));
  }
}

package org.beizix.core.application.domain.exboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.port.in.exboard.ExBoardSortPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardSortPortOut;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExBoardSortService implements ExBoardSortPortIn {
  private final ExBoardSortPortOut exBoardSortPortOut;

  @Override
  @Transactional
  public void operate(List<ExBoardInput> sortItems) {
    sortItems.forEach(item -> exBoardSortPortOut.connect(item.getId(), item.getOrderNo()));
  }
}

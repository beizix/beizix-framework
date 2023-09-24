package org.beizix.core.application.domain.exboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.core.application.port.in.exboard.ExBoardRemovePortIn;
import org.beizix.core.application.port.out.exboard.ExBoardRemovePortOut;

import java.util.List;

@Service
@RequiredArgsConstructor
class ExBoardRemoveService implements ExBoardRemovePortIn {
  private final ExBoardRemovePortOut exBoardRemovePortOut;

  @Override
  public void connect(List<Long> checkedIds) {
    exBoardRemovePortOut.connect(checkedIds);
  }
}

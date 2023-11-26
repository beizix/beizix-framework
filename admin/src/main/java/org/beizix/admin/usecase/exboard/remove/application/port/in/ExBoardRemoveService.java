package org.beizix.admin.usecase.exboard.remove.application.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.exboard.remove.application.port.out.ExBoardRemovePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExBoardRemoveService implements ExBoardRemovePortIn {
  private final ExBoardRemovePortOut exBoardRemovePortOut;

  @Override
  public void connect(List<Long> checkedIds) {
    exBoardRemovePortOut.connect(checkedIds);
  }
}

package org.beizix.core.usecase.exboard.list.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardElement;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.beizix.core.usecase.exboard.list.application.port.out.ExBoardListPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExBoardListService implements ExBoardListPortIn {
  private final ExBoardListPortOut exBoardListPortOut;

  @Override
  public Page<ExBoardElement> connect(Pageable pageable, ExBoardListFilterCommand command) {
    return exBoardListPortOut.connect(pageable, command);
  }
}

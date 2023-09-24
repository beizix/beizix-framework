package org.beizix.core.application.domain.exboard;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListInput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardListPortOut;

@Service
@RequiredArgsConstructor
class ExBoardListService implements ExBoardListPortIn {
  private final ExBoardListPortOut exBoardListPortOut;

  @Override
  public Page<ExBoardInput> connect(Pageable pageable, ExBoardListInput exBoardListInput) {
    return exBoardListPortOut.connect(pageable, exBoardListInput);
  }
}

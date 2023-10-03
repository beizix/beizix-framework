package org.beizix.core.application.domain.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExBoardListService implements ExBoardListPortIn<ExBoardListOutput> {
  private final ExBoardListPortOut<ExBoardListOutput> exBoardListPortOut;

  @Override
  public ExBoardListOutput connect(
      PageableInput pageable, ExBoardListFilterInput exBoardListFilterInput) {
    return exBoardListPortOut.connect(pageable, exBoardListFilterInput);
  }
}

package org.beizix.core.application.domain.exboard;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.beizix.core.application.port.in.exboard.ExBoardListPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardListPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExBoardListService implements ExBoardListPortIn {
  private final ExBoardListPortOut exBoardListPortOut;

  @Override
  public Page<ExBoardListOutput> connect(Pageable pageable, ExBoardListFilterInput exBoardListFilterInput) {
    return exBoardListPortOut.connect(pageable, exBoardListFilterInput);
  }
}

package org.beizix.core.usecase.exboard.list.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.usecase.exboard.list.domain.ExBoardPageableList;
import org.beizix.core.usecase.exboard.list.application.port.out.ExBoardListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExBoardListService implements ExBoardListPortIn {
  private final ExBoardListPortOut exBoardListPortOut;

  @Override
  public ExBoardPageableList connect(
      PageableInput pageable, ExBoardListFilterInput exBoardListFilterInput) {
    return exBoardListPortOut.connect(pageable, exBoardListFilterInput);
  }
}
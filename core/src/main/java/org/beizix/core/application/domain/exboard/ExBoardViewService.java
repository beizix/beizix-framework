package org.beizix.core.application.domain.exboard;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.utility.common.MessageUtil;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.port.in.exboard.ExBoardViewPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardViewPortOut;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
class ExBoardViewService implements ExBoardViewPortIn {
  private final ExBoardViewPortOut exBoardViewPortOut;
  private final MessageUtil messageUtil;

  @Override
  public ExBoardInput operate(Long id) {
    return exBoardViewPortOut
        .connect(id)
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    messageUtil.getMessage("exception.noSuchElement", id, "ID")));
  }
}

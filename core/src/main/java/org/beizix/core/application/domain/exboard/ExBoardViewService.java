package org.beizix.core.application.domain.exboard;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.beizix.core.application.domain.exboard.model.view.ExBoardViewOutput;
import org.beizix.core.application.port.in.exboard.ExBoardViewPortIn;
import org.beizix.core.application.port.out.exboard.ExBoardViewPortOut;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExBoardViewService implements ExBoardViewPortIn {
  private final ExBoardViewPortOut exBoardViewPortOut;
  private final MessageUtil messageUtil;

  @Override
  public ExBoardViewOutput operate(Long id) {
    return exBoardViewPortOut
        .connect(id)
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    messageUtil.getMessage("exception.noSuchElement", id, "ID")));
  }
}

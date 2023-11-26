package org.beizix.core.usecase.exboard.view.application.port.in;

import java.util.NoSuchElementException;
import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.exboard.view.application.domain.ExBoardView;
import org.beizix.core.usecase.exboard.view.application.port.out.ExBoardViewPortOut;
import org.beizix.utility.common.MessageUtil;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class ExBoardViewService implements ExBoardViewPortIn<ExBoardView> {
  private final ExBoardViewPortOut<ExBoardView> exBoardViewPortOut;
  private final MessageUtil messageUtil;

  @Override
  public ExBoardView connect(Long id) {
    return exBoardViewPortOut
        .connect(id)
        .orElseThrow(
            () ->
                new NoSuchElementException(
                    messageUtil.getMessage("exception.noSuchElement", id, "ID")));
  }
}

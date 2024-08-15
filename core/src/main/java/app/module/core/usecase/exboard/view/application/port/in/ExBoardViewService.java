package app.module.core.usecase.exboard.view.application.port.in;

import java.util.NoSuchElementException;

import app.module.core.usecase.exboard.view.application.domain.ExBoardView;
import app.module.core.usecase.exboard.view.application.port.out.ExBoardViewPortOut;
import lombok.RequiredArgsConstructor;
import app.module.core.config.application.util.MessageUtil;
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

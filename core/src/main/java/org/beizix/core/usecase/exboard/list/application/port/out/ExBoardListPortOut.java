package org.beizix.core.usecase.exboard.list.application.port.out;

import org.beizix.core.usecase.exboard.list.application.domain.ExBoardElement;
import org.beizix.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExBoardListPortOut {
  Page<ExBoardElement> connect(Pageable pageable, ExBoardListFilterCommand command);
}

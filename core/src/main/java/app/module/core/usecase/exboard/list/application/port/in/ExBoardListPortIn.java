package app.module.core.usecase.exboard.list.application.port.in;

import app.module.core.usecase.exboard.list.application.domain.ExBoardElement;
import app.module.core.usecase.exboard.list.application.domain.ExBoardListFilterCommand;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExBoardListPortIn {
  Page<ExBoardElement> connect(Pageable pageable, ExBoardListFilterCommand command);
}

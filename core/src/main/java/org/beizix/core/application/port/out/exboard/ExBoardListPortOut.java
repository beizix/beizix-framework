package org.beizix.core.application.port.out.exboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListInput;

public interface ExBoardListPortOut {
  Page<ExBoardInput> connect(Pageable pageable, ExBoardListInput exBoardListInput);
}

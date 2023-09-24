package org.beizix.core.application.port.in.exboard;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.beizix.core.application.domain.exboard.model.ExBoardInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListInput;

public interface ExBoardListPortIn {
  Page<ExBoardInput> connect(Pageable pageable, ExBoardListInput exBoardListInput);
}

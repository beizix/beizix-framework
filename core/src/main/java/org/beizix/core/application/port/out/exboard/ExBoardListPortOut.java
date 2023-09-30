package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExBoardListPortOut {
  Page<ExBoardListOutput> connect(Pageable pageable, ExBoardListFilterInput exBoardListFilterInput);
}

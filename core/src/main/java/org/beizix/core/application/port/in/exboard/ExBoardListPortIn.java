package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;

public interface ExBoardListPortIn {
  ExBoardListOutput connect(PageableInput pageable, ExBoardListFilterInput exBoardListFilterInput);
}

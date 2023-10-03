package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;

public interface ExBoardListPortOut {
  ExBoardListOutput connect(PageableInput pageableInput, ExBoardListFilterInput exBoardListFilterInput);
}

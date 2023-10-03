package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;

public interface ExBoardListPortIn<T extends PageableListOutput> {
  T connect(PageableInput pageable, ExBoardListFilterInput exBoardListFilterInput);
}

package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardOutput;

public interface ExBoardListPortIn<T extends PageableListOutput<ExBoardOutput>> {
  T connect(PageableInput pageable, ExBoardListFilterInput exBoardListFilterInput);
}

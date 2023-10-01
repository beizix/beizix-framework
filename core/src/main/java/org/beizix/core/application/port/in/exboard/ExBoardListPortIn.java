package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.common.model.PageableBase;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;
import org.beizix.core.application.domain.exboard.model.list.ExBoardListOutput;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExBoardListPortIn {
  ExBoardListOutput connect(PageableBase pageable, ExBoardListFilterInput exBoardListFilterInput);
}

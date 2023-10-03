package org.beizix.core.application.port.out.exboard;

import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.core.application.domain.common.model.PageableListOutput;
import org.beizix.core.application.domain.exboard.model.filter.ExBoardListFilterInput;

public interface ExBoardListPortOut<T extends PageableListOutput> {
  T connect(PageableInput pageableInput, ExBoardListFilterInput exBoardListFilterInput);
}

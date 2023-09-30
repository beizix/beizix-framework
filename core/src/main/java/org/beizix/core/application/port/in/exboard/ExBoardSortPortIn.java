package org.beizix.core.application.port.in.exboard;

import java.util.List;
import org.beizix.core.application.domain.exboard.model.sort.ExBoardSortInput;

public interface ExBoardSortPortIn {
  void operate(List<ExBoardSortInput> sortItems);
}

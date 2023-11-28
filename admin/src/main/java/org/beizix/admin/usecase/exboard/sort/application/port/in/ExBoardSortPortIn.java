package org.beizix.admin.usecase.exboard.sort.application.port.in;

import java.util.List;
import org.beizix.admin.usecase.exboard.sort.application.domain.ExBoardSortCommand;

public interface ExBoardSortPortIn {
  void operate(List<ExBoardSortCommand> sortItems);
}

package org.beizix.admin.usecase.exboard.sort.ports;

import java.util.List;
import org.beizix.admin.usecase.exboard.sort.ports.application.domain.ExBoardSortCmd;

public interface ExBoardSortPortIn {
  void operate(List<ExBoardSortCmd> sortItems);
}

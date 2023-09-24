package org.beizix.core.application.port.in.exboard;

import org.beizix.core.application.domain.exboard.model.ExBoardInput;

import java.util.List;

public interface ExBoardSortPortIn {
  void operate(List<ExBoardInput> sortItems);
}

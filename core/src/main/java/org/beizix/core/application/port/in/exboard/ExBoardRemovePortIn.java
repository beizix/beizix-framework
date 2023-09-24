package org.beizix.core.application.port.in.exboard;

import java.util.List;

public interface ExBoardRemovePortIn {
  void connect(List<Long> checkedIds);
}

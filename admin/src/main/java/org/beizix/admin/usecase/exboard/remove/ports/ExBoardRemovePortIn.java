package org.beizix.admin.usecase.exboard.remove.ports;

import java.util.List;

public interface ExBoardRemovePortIn {
  void connect(List<Long> checkedIds);
}

package app.module.admin.usecase.exboard.sort.ports;

import java.util.List;
import app.module.admin.usecase.exboard.sort.ports.application.domain.ExBoardSortCmd;

public interface ExBoardSortPortIn {
  void operate(List<ExBoardSortCmd> sortItems);
}

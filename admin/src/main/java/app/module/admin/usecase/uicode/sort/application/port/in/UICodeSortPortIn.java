package app.module.admin.usecase.uicode.sort.application.port.in;

import java.util.List;

public interface UICodeSortPortIn {
  void connect(List<UICodeSortCommand> uiCodeSortCommands);
}

package app.module.admin.usecase.role.sort.application.port.in;

import java.util.List;

import app.module.admin.usecase.role.sort.application.domain.RoleSortCommand;

public interface RoleSortPortIn {
  void connect(List<RoleSortCommand> sortReqs);
}

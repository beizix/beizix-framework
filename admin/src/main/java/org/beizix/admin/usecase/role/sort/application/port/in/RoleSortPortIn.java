package org.beizix.admin.usecase.role.sort.application.port.in;

import java.util.List;
import org.beizix.admin.usecase.role.sort.application.domain.RoleSortCommand;

public interface RoleSortPortIn {
  void connect(List<RoleSortCommand> sortReqs);
}

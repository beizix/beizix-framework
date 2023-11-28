package org.beizix.admin.usecase.privilege.sort.application.port.in;

import java.util.List;
import org.beizix.admin.usecase.privilege.sort.application.domain.PrivilegeSortCommand;

public interface PrivilegeSortPortIn {
  void connect(List<PrivilegeSortCommand> sortReqs);
}

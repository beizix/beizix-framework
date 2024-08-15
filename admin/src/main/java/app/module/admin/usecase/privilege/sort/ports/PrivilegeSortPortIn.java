package app.module.admin.usecase.privilege.sort.ports;

import java.util.List;
import app.module.admin.usecase.privilege.sort.ports.application.domain.PrivilegeSortCmd;

public interface PrivilegeSortPortIn {
  void connect(List<PrivilegeSortCmd> sortReqs);
}

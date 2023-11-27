package org.beizix.admin.usecase.role.sort.application.port.in;

import java.util.List;
import org.beizix.security.application.domain.role.model.sort.RoleSortInput;

public interface RoleSortPortIn {
  void connect(List<RoleSortInput> sortReqs);
}

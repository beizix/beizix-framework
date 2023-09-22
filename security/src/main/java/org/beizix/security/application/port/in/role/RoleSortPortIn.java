package org.beizix.security.application.port.in.role;

import java.util.List;
import org.beizix.security.application.domain.role.model.sort.RoleSortInput;

public interface RoleSortPortIn {
  void connect(List<RoleSortInput> sortReqs);
}

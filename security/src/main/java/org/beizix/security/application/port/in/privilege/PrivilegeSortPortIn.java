package org.beizix.security.application.port.in.privilege;

import java.util.List;
import org.beizix.security.application.domain.privilege.model.sort.PrivilegeSortInput;

public interface PrivilegeSortPortIn {
  void connect(List<PrivilegeSortInput> sortReqs);
}

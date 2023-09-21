package org.beizix.security.application.port.in.role;

import java.util.List;
import org.beizix.security.application.domain.role.model.sort.RoleSortReq;

public interface RoleSortService {
  void operate(List<RoleSortReq> sortReqs);
}

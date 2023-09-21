package org.beizix.security.application.port.in.role;

import java.util.List;
import org.beizix.security.application.domain.role.model.list.RoleListResp;

public interface RoleListService {
  List<RoleListResp> operate();
}

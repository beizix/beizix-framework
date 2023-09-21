package org.beizix.security.application.port.in.role;

import org.beizix.security.application.domain.role.model.view.RoleViewResp;

public interface RoleViewService {
  RoleViewResp operate(String role);
}

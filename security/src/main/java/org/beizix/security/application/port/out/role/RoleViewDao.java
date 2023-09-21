package org.beizix.security.application.port.out.role;

import org.beizix.security.application.domain.role.model.view.RoleViewResp;

public interface RoleViewDao {
  RoleViewResp operate(String role);
}

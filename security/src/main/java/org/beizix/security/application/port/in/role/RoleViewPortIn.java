package org.beizix.security.application.port.in.role;

import org.beizix.security.application.domain.role.model.view.RoleViewOutput;

public interface RoleViewPortIn {
  RoleViewOutput connect(String role);
}

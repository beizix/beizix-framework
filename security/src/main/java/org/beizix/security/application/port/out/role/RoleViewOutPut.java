package org.beizix.security.application.port.out.role;

import org.beizix.security.application.domain.role.model.view.RoleViewOutput;

public interface RoleViewOutPut {
  RoleViewOutput connect(String role);
}

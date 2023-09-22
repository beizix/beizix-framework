package org.beizix.security.application.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.role.model.view.RoleViewOutput;
import org.beizix.security.application.port.in.role.RoleViewPortIn;
import org.beizix.security.application.port.out.role.RoleViewOutPut;

@Service
@RequiredArgsConstructor
public class RoleViewService implements RoleViewPortIn {
  private final RoleViewOutPut roleViewOutPut;

  @Override
  public RoleViewOutput connect(String role) {
    return roleViewOutPut.connect(role);
  }
}

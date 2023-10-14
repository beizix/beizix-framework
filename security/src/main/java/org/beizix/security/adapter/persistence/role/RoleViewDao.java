package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.view.RoleViewOutput;
import org.beizix.security.application.port.out.role.RoleViewOutPut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleViewDao implements RoleViewOutPut {
  private final RoleRepo roleRepo;

  @Override
  public RoleViewOutput connect(String roleId) {
    return roleRepo
        .findById(roleId)
        .map(role -> new RoleViewOutput(role.getId(), role.getDescription(), role.getOrderNo(), null))
        .orElse(null);
  }
}

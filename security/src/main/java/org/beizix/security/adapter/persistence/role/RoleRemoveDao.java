package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.port.out.role.RoleRemovePortOut;

@Repository
@RequiredArgsConstructor
public class RoleRemoveDao implements RoleRemovePortOut {
  private final RoleRepo roleRepo;

  @Override
  public void connect(String role) {
    roleRepo.deleteById(role);
  }
}

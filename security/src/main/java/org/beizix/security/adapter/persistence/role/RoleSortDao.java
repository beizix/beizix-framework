package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.port.out.role.RoleSortOutput;

@Repository
@RequiredArgsConstructor
public class RoleSortDao implements RoleSortOutput {
  private final RoleRepo roleRepo;

  @Override
  public void connect(String role, Integer orderNo) {
    roleRepo.updateOrderNo(role, orderNo);
  }
}

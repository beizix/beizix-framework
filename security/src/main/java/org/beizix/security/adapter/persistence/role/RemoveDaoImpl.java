package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.port.out.role.RoleRemoveDao;

@Repository
@RequiredArgsConstructor
public class RemoveDaoImpl implements RoleRemoveDao {
  private final RoleRepo roleRepo;

  @Override
  public void operate(String role) {
    roleRepo.deleteById(role);
  }
}

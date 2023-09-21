package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.port.out.role.RoleNextOrderNoDao;

@Repository
@RequiredArgsConstructor
public class RoleNextOrderNoDaoImpl implements RoleNextOrderNoDao {
  private final RoleRepo roleRepo;

  @Override
  public Integer getNextOrderNo() {
    return roleRepo.getMaxOrderNo().orElse(-1) + 1;
  }
}

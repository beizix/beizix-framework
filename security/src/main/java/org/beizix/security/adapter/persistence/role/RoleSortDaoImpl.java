package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.port.out.role.RoleSortDao;

@Repository
@RequiredArgsConstructor
public class RoleSortDaoImpl implements RoleSortDao {
  private final RoleRepo roleRepo;

  @Override
  public void operate(String role, Integer orderNo) {
    roleRepo.updateOrderNo(role, orderNo);
  }
}

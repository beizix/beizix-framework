package org.beizix.security.adapter.persistence.admin_role;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.admin_role.repository.AdminWithRoleRepo;
import org.beizix.security.application.port.out.admin_role.AdminWithRolePortOut;

@Repository
@RequiredArgsConstructor
public class AdminWithRoleDao implements AdminWithRolePortOut {
  private final AdminWithRoleRepo adminWithRoleRepo;

  @Override
  public void connect(Set<Long> ids) {
    adminWithRoleRepo.deleteAllById(ids);
  }
}

package org.beizix.security.application.domain.admin_role;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.port.in.admin_role.AdminWithRoleRemovePortIn;
import org.beizix.security.adapter.persistence.admin_role.repository.AdminWithRoleRepo;

@Service
@RequiredArgsConstructor
public class AdminWithRoleRemoveService implements AdminWithRoleRemovePortIn {
  private final AdminWithRoleRepo adminWithRoleRepo;

  @Override
  public void connect(Set<Long> ids) {
    adminWithRoleRepo.deleteAllById(ids);
  }
}

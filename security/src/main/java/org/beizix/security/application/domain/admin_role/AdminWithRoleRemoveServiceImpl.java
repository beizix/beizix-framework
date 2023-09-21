package org.beizix.security.application.domain.admin_role;

import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.port.in.admin_role.AdminWithRoleRemoveService;
import org.beizix.security.adapter.persistence.admin_role.repository.AdminWithRoleRepo;

@Service
@RequiredArgsConstructor
public class AdminWithRoleRemoveServiceImpl implements AdminWithRoleRemoveService {
  private final AdminWithRoleRepo adminWithRoleRepo;

  @Override
  public void operateAll(Set<Long> ids) {
    adminWithRoleRepo.deleteAllById(ids);
  }
}

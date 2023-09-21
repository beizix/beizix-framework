package org.beizix.security.application.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.port.in.role.RoleRemoveService;
import org.beizix.security.application.port.out.role.RoleRemoveDao;

@Service
@RequiredArgsConstructor
public class RoleRemoveServiceImpl implements RoleRemoveService {
  private final RoleRemoveDao roleRemoveDao;

  @Override
  public void operate(String role) {
    roleRemoveDao.operate(role);
  }
}

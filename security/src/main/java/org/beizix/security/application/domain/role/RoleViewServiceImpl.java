package org.beizix.security.application.domain.role;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.role.model.view.RoleViewResp;
import org.beizix.security.application.port.in.role.RoleViewService;
import org.beizix.security.application.port.out.role.RoleViewDao;

@Service
@RequiredArgsConstructor
public class RoleViewServiceImpl implements RoleViewService {
  private final RoleViewDao roleViewDao;

  @Override
  public RoleViewResp operate(String role) {
    return roleViewDao.operate(role);
  }
}

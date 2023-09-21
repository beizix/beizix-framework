package org.beizix.security.application.domain.admin;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.admin.model.view.AdminViewResp;
import org.beizix.security.application.port.in.admin.AdminViewService;
import org.beizix.security.application.port.out.admin.AdminViewDao;

@Service
@RequiredArgsConstructor
class AdminViewServiceImpl implements AdminViewService {
  private final AdminViewDao adminViewDao;

  @Override
  public Optional<AdminViewResp> operate(String adminId) {
    return adminViewDao.operate(adminId);
  }
}

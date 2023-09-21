package org.beizix.security.application.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.admin.model.query.AdminListReq;
import org.beizix.security.application.port.in.admin.AdminRemoveService;
import org.beizix.security.application.port.out.admin.AdminRemoveDao;

@Service
@RequiredArgsConstructor
class AdminRemoveServiceImpl implements AdminRemoveService {
  private final AdminRemoveDao adminRemoveDao;

  @Override
  public void operate(AdminListReq adminListReq) {
    adminRemoveDao.operate(adminListReq);
  }
}

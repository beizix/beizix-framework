package org.beizix.security.adapter.persistence.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.domain.admin.model.query.AdminListReq;
import org.beizix.security.application.port.out.admin.AdminRemoveDao;

@Component
@RequiredArgsConstructor
class AdminRemoveDaoImpl implements AdminRemoveDao {
  private final AdminRepo adminRepo;

  @Override
  public void operate(AdminListReq adminListReq) {
    adminRepo.deleteAllById(adminListReq.getCheckedIds());
  }
}

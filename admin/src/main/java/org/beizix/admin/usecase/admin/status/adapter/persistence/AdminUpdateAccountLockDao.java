package org.beizix.admin.usecase.admin.status.adapter.persistence;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.status.application.port.out.AdminUpdateAccountLockPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminUpdateAccountLockDao implements AdminUpdateAccountLockPortOut {
  private final AdminStatusRepo adminRepo;

  @Override
  @Transactional
  public void connect(String id, boolean accountLocked) {
    adminRepo.updateAccountLocked(id, accountLocked);
  }
}

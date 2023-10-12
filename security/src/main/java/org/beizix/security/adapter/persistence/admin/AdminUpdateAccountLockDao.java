package org.beizix.security.adapter.persistence.admin;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.port.out.admin.AdminUpdateAccountLockPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminUpdateAccountLockDao implements AdminUpdateAccountLockPortOut {
  private final AdminRepo adminRepo;

  @Override
  public void connect(String id, boolean accountLocked) {
    adminRepo.updateAccountLocked(id, accountLocked);
  }
}
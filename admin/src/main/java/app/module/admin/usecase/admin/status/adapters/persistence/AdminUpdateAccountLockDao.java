package app.module.admin.usecase.admin.status.adapters.persistence;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.status.ports.AdminUpdateAccountLockPortOut;
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

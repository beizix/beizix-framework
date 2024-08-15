package app.module.admin.usecase.admin.status.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.status.ports.AdminUpdateAccountLockPortIn;
import app.module.admin.usecase.admin.status.ports.AdminUpdateAccountLockPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUpdateAccountLockService implements AdminUpdateAccountLockPortIn {
  private final AdminUpdateAccountLockPortOut portOut;

  @Override
  public void connect(String id, boolean accountLocked) {
    portOut.connect(id, accountLocked);
  }
}

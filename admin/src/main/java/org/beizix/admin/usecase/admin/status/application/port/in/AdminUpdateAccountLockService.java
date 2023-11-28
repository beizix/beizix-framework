package org.beizix.admin.usecase.admin.status.application.port.in;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.status.application.port.out.AdminUpdateAccountLockPortOut;
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

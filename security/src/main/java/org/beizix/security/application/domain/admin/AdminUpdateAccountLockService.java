package org.beizix.security.application.domain.admin;

import lombok.RequiredArgsConstructor;
import org.beizix.security.application.port.in.admin.AdminUpdateAccountLockPortIn;
import org.beizix.security.application.port.out.admin.AdminUpdateAccountLockPortOut;
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

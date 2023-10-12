package org.beizix.security.application.domain.admin;

import lombok.RequiredArgsConstructor;
import org.beizix.security.application.port.in.admin.AdminUpdateLoginFailPortIn;
import org.beizix.security.application.port.out.admin.AdminUpdateLoginFailPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AdminUpdateLoginFailService implements AdminUpdateLoginFailPortIn {
  private final AdminUpdateLoginFailPortOut portOut;

  @Override
  public void connect(String id, Integer failCnt) {
    portOut.connect(id, failCnt);
  }
}

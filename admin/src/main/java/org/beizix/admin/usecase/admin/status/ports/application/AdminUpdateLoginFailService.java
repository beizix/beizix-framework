package org.beizix.admin.usecase.admin.status.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.status.ports.AdminUpdateLoginFailPortIn;
import org.beizix.admin.usecase.admin.status.ports.AdminUpdateLoginFailPortOut;
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

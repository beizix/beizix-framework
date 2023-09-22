package org.beizix.security.application.domain.admin;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.admin.model.query.AdminListInput;
import org.beizix.security.application.port.in.admin.AdminRemovePortIn;
import org.beizix.security.application.port.out.admin.AdminRemovePortOut;

@Service
@RequiredArgsConstructor
class AdminRemoveService implements AdminRemovePortIn {
  private final AdminRemovePortOut adminRemovePortOut;

  @Override
  public void connect(AdminListInput inflow) {
    adminRemovePortOut.connect(inflow);
  }
}

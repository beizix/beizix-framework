package org.beizix.security.application.domain.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;
import org.beizix.security.application.domain.admin.model.query.AdminListInput;
import org.beizix.security.application.port.in.admin.AdminListPortIn;
import org.beizix.security.application.port.out.admin.AdminListPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class AdminListService implements AdminListPortIn {
  private final AdminListPortOut portOut;

  @Override
  public Page<AdminListOutput> connect(Pageable pageable, AdminListInput inflow) {
    return portOut.connect(pageable, inflow);
  }
}

package org.beizix.security.application.domain.admin;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.security.application.domain.admin.model.filter.AdminListStatus;
import org.beizix.security.application.domain.admin.model.list.AdminListOutput;
import org.beizix.security.application.port.in.admin.AdminListPortIn;
import org.beizix.security.application.port.out.admin.AdminListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class AdminListService implements AdminListPortIn {
  private final AdminListPortOut portOut;

  @Override
  public AdminListOutput connect(PageableInput pageable, AdminListStatus inflow) {
    return portOut.connect(pageable, inflow);
  }
}

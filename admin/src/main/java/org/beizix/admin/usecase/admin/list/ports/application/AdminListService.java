package org.beizix.admin.usecase.admin.list.ports.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.admin.usecase.admin.list.ports.AdminListPortIn;
import org.beizix.admin.usecase.admin.list.ports.application.domain.AdminElement;
import org.beizix.admin.usecase.admin.list.ports.application.domain.AdminListCmd;
import org.beizix.admin.usecase.admin.list.ports.AdminListPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class AdminListService implements AdminListPortIn {
  private final AdminListPortOut portOut;

  @Override
  public Page<AdminElement> connect(Pageable pageable, AdminListCmd listCmd) {
    return portOut.connect(pageable, listCmd);
  }
}

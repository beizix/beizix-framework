package org.beizix.admin.usecase.admin.list.application.port.in;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.beizix.admin.usecase.admin.list.application.domain.AdminListFilterCommand;
import org.beizix.admin.usecase.admin.list.application.domain.AdminPageableList;
import org.beizix.core.application.domain.common.model.PageableInput;
import org.beizix.admin.usecase.admin.list.application.port.out.AdminListPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
class AdminListService implements AdminListPortIn {
  private final AdminListPortOut portOut;

  @Override
  public AdminPageableList connect(PageableInput pageable, AdminListFilterCommand filterCommand) {
    return portOut.connect(pageable, filterCommand);
  }
}

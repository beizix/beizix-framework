package app.module.admin.usecase.user.list.ports.application;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.user.list.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.list.ports.application.domain.GetUsersCmd;
import app.module.admin.usecase.user.list.ports.GetUserListPortIn;
import app.module.admin.usecase.user.list.ports.GetUserListPortOut;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUserListService implements GetUserListPortIn {
  private final GetUserListPortOut portOut;

  @Override
  public Page<GetUsers> operate(Pageable pageable, GetUsersCmd command) {
    return portOut.operate(pageable, command);
  }
}
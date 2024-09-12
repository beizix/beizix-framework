package app.module.admin.usecase.user.getUsers.ports.application;

import app.module.admin.usecase.user.getUsers.ports.GetUsersPortIn;
import app.module.admin.usecase.user.getUsers.ports.GetUsersPortOut;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsersCmd;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class GetUsersService implements GetUsersPortIn {
  private final GetUsersPortOut portOut;

  @Override
  public Page<GetUsers> operate(Pageable pageable, GetUsersCmd command) {
    return portOut.operate(pageable, command);
  }
}

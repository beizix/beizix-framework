package app.module.admin.usecase.user.getUsers.ports;

import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.getUsers.ports.application.domain.GetUsersCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetUserListPortOut {
  Page<GetUsers> operate(Pageable pageable, GetUsersCmd command);
}
package app.module.admin.usecase.user.list.ports;

import app.module.admin.usecase.user.list.ports.application.domain.GetUsers;
import app.module.admin.usecase.user.list.ports.application.domain.GetUsersCmd;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface GetUserListPortIn {
  Page<GetUsers> operate(Pageable pageable, GetUsersCmd command);
}
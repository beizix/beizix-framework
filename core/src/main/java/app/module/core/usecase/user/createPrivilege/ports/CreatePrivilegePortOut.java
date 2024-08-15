package app.module.core.usecase.user.createPrivilege.ports;

import java.util.Optional;

import app.module.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilege;
import app.module.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilegeCmd;

public interface CreatePrivilegePortOut {
  Optional<CreatePrivilege> operate(CreatePrivilegeCmd command);
}
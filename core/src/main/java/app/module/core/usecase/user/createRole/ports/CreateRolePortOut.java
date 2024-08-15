package app.module.core.usecase.user.createRole.ports;

import java.util.Optional;

import app.module.core.usecase.user.createRole.ports.application.domain.CreateRole;
import app.module.core.usecase.user.createRole.ports.application.domain.CreateRoleCmd;

public interface CreateRolePortOut {
  Optional<CreateRole> operate(CreateRoleCmd command);
}
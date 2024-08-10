package org.beizix.core.usecase.user.createRole.ports;

import java.util.Optional;
import org.beizix.core.usecase.user.createRole.ports.application.domain.CreateRole;
import org.beizix.core.usecase.user.createRole.ports.application.domain.CreateRoleCmd;

public interface CreateRolePortIn {
  Optional<CreateRole> operate(CreateRoleCmd command);
}
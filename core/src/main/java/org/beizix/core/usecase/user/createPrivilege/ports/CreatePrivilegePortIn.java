package org.beizix.core.usecase.user.createPrivilege.ports;

import java.util.Optional;
import org.beizix.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilege;
import org.beizix.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilegeCmd;

public interface CreatePrivilegePortIn {
  Optional<CreatePrivilege> operate(CreatePrivilegeCmd command);
}
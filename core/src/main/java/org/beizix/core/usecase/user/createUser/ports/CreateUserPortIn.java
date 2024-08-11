package org.beizix.core.usecase.user.createUser.ports;

import java.util.Optional;
import org.beizix.core.usecase.user.createUser.ports.application.domain.CreateUser;
import org.beizix.core.usecase.user.createUser.ports.application.domain.CreateUserCmd;

public interface CreateUserPortIn {
  Optional<CreateUser> operate(CreateUserCmd command);
}
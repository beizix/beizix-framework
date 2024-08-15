package app.module.core.usecase.user.createUser.ports;

import java.util.Optional;

import app.module.core.usecase.user.createUser.ports.application.domain.CreateUser;
import app.module.core.usecase.user.createUser.ports.application.domain.CreateUserCmd;

public interface CreateUserPortIn {
  Optional<CreateUser> operate(CreateUserCmd command);
}
package app.module.core.usecase.user.createUser.ports.application;

import app.module.core.usecase.user.createUser.ports.application.domain.CreateUser;
import app.module.core.usecase.user.createUser.ports.application.domain.CreateUserCmd;
import lombok.RequiredArgsConstructor;
import app.module.core.usecase.user.createUser.ports.CreateUserPortIn;
import app.module.core.usecase.user.createUser.ports.CreateUserPortOut;
import java.util.Optional;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class CreateUserService implements CreateUserPortIn {
  private final CreateUserPortOut portOut;
  private final BCryptPasswordEncoder passwordEncoder;

  @Override
  public Optional<CreateUser> operate(CreateUserCmd command) {
    command.setPassword(passwordEncoder.encode(command.getPassword()));
    return portOut.operate(command);
  }
}

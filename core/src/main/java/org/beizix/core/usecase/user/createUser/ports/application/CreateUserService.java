package org.beizix.core.usecase.user.createUser.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.user.createUser.ports.application.domain.CreateUser;
import org.beizix.core.usecase.user.createUser.ports.application.domain.CreateUserCmd;
import org.beizix.core.usecase.user.createUser.ports.CreateUserPortIn;
import org.beizix.core.usecase.user.createUser.ports.CreateUserPortOut;
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

package app.module.core.usecase.user.findUser.ports.application;

import app.module.core.usecase.user.findUser.ports.FindUserPortIn;
import app.module.core.usecase.user.findUser.ports.FindUserPortOut;
import app.module.core.usecase.user.findUser.ports.application.domain.FindUser;
import app.module.core.usecase.user.findUser.ports.application.domain.FindUserCmd;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class FindUserService implements FindUserPortIn {
  private final FindUserPortOut portOut;

  @Override
  public Optional<FindUser> operate(FindUserCmd command) {
    return portOut.operate(command);
  }
}
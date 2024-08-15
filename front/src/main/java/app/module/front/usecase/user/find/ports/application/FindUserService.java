package app.module.front.usecase.user.find.ports.application;

import app.module.front.usecase.user.find.ports.FindUserPortIn;
import app.module.front.usecase.user.find.ports.FindUserPortOut;
import app.module.front.usecase.user.find.ports.application.domain.FindUser;
import app.module.front.usecase.user.find.ports.application.domain.FindUserCmd;
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
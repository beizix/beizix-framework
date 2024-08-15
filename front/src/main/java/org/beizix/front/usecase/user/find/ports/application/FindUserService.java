package org.beizix.front.usecase.user.find.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUser;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserCmd;
import org.beizix.front.usecase.user.find.ports.FindUserPortIn;
import org.beizix.front.usecase.user.find.ports.FindUserPortOut;
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
package org.beizix.front.usecase.user.find.ports;

import java.util.Optional;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUser;
import org.beizix.front.usecase.user.find.ports.application.domain.FindUserCmd;

public interface FindUserPortOut {
  Optional<FindUser> operate(FindUserCmd command);
}
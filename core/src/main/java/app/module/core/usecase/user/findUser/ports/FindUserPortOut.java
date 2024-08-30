package app.module.core.usecase.user.findUser.ports;

import java.util.Optional;

import app.module.core.usecase.user.findUser.ports.application.domain.FindUser;
import app.module.core.usecase.user.findUser.ports.application.domain.FindUserCmd;

public interface FindUserPortOut {
  Optional<FindUser> operate(FindUserCmd command);
}
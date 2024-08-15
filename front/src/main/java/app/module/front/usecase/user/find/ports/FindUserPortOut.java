package app.module.front.usecase.user.find.ports;

import java.util.Optional;

import app.module.front.usecase.user.find.ports.application.domain.FindUser;
import app.module.front.usecase.user.find.ports.application.domain.FindUserCmd;

public interface FindUserPortOut {
  Optional<FindUser> operate(FindUserCmd command);
}
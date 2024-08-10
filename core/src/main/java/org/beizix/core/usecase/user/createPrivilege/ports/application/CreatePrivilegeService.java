package org.beizix.core.usecase.user.createPrivilege.ports.application;

import lombok.RequiredArgsConstructor;
import org.beizix.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilege;
import org.beizix.core.usecase.user.createPrivilege.ports.application.domain.CreatePrivilegeCmd;
import org.beizix.core.usecase.user.createPrivilege.ports.CreatePrivilegePortIn;
import org.beizix.core.usecase.user.createPrivilege.ports.CreatePrivilegePortOut;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreatePrivilegeService implements CreatePrivilegePortIn {
  private final CreatePrivilegePortOut portOut;

  @Override
  public Optional<CreatePrivilege> operate(CreatePrivilegeCmd command) {
    return portOut.operate(command);
  }
}
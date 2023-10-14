package org.beizix.security.adapter.persistence.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.repository.PrivilegeRepo;
import org.beizix.security.application.port.out.privilege.PrivilegeNextOrderNoPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeNextOrderNoDao implements PrivilegeNextOrderNoPortOut {
  private final PrivilegeRepo privilegeRepo;

  @Override
  public Integer connect() {
    return privilegeRepo.getMaxOrderNo().orElse(-1) + 1;
  }
}

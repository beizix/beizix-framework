package org.beizix.admin.usecase.privilege.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.save.application.port.out.PrivilegeNextOrderNoPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeNextOrderNoDao implements PrivilegeNextOrderNoPortOut {
  private final PrivilegeSaveRepo privilegeRepo;

  @Override
  public Integer connect() {
    return privilegeRepo.getMaxOrderNo().orElse(-1) + 1;
  }
}

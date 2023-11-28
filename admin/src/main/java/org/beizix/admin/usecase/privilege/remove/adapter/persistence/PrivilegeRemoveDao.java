package org.beizix.admin.usecase.privilege.remove.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.remove.application.port.out.PrivilegeRemovePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeRemoveDao implements PrivilegeRemovePortOut {
  private final PrivilegeRemoveRepo privilegeRepo;

  @Override
  public void connect(String id) {
    privilegeRepo.deleteById(id);
  }
}

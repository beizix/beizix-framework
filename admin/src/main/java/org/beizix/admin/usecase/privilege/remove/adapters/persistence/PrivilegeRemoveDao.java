package org.beizix.admin.usecase.privilege.remove.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.remove.ports.PrivilegeRemovePortOut;
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

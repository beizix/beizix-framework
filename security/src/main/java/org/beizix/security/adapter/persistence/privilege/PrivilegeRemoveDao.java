package org.beizix.security.adapter.persistence.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.repository.PrivilegeRepo;
import org.beizix.security.application.port.out.privilege.PrivilegeRemovePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeRemoveDao implements PrivilegeRemovePortOut {
  private final PrivilegeRepo privilegeRepo;

  @Override
  public void connect(String id) {
    privilegeRepo.deleteById(id);
  }
}

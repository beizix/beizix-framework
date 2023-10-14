package org.beizix.security.adapter.persistence.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.repository.PrivilegeRepo;
import org.beizix.security.application.port.out.privilege.PrivilegeSortPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeSortDao implements PrivilegeSortPortOut {
  private final PrivilegeRepo privilegeRepo;

  @Override
  public void connect(String id, Integer orderNo) {
    privilegeRepo.updateOrderNo(id, orderNo);
  }
}

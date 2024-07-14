package org.beizix.admin.usecase.privilege.sort.adapters.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.sort.ports.PrivilegeSortPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeSortDao implements PrivilegeSortPortOut {
  private final PrivilegeSortRepo privilegeRepo;

  @Override
  public void connect(String id, Integer orderNo) {
    privilegeRepo.updateOrderNo(id, orderNo);
  }
}

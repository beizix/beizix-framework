package org.beizix.admin.usecase.privilege.sort.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.sort.application.port.out.PrivilegeSortPortOut;
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

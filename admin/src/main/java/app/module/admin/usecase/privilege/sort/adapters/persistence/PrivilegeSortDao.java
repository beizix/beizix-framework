package app.module.admin.usecase.privilege.sort.adapters.persistence;

import app.module.admin.usecase.privilege.sort.ports.PrivilegeSortPortOut;
import lombok.RequiredArgsConstructor;
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

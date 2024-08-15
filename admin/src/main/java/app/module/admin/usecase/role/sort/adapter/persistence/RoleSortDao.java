package app.module.admin.usecase.role.sort.adapter.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.role.sort.application.port.out.RoleSortPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleSortDao implements RoleSortPortOut {
  private final RoleSortRepo roleRepo;

  @Override
  public void connect(String role, Integer orderNo) {
    roleRepo.updateOrderNo(role, orderNo);
  }
}

package app.module.admin.usecase.role.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.role.save.application.port.out.RoleNextOrderNoPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleNextOrderNoDao implements RoleNextOrderNoPortOut {
  private final RoleNextOrderNoRepo roleRepo;

  @Override
  public Integer connect() {
    return roleRepo.getMaxOrderNo().orElse(-1) + 1;
  }
}

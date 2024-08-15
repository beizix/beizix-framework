package app.module.admin.usecase.role.remove.adapter.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.role.remove.application.port.out.RoleRemovePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class RoleRemoveDao implements RoleRemovePortOut {
  private final RoleRemoveRepo roleRepo;

  @Override
  public void connect(String role) {
    roleRepo.deleteById(role);
  }
}

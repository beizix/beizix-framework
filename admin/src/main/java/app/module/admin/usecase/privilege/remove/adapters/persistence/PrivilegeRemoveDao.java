package app.module.admin.usecase.privilege.remove.adapters.persistence;

import app.module.admin.usecase.privilege.remove.ports.PrivilegeRemovePortOut;
import lombok.RequiredArgsConstructor;
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

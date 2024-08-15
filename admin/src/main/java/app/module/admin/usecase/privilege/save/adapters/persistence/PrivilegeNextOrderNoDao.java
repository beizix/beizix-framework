package app.module.admin.usecase.privilege.save.adapters.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.save.ports.PrivilegeNextOrderNoPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeNextOrderNoDao implements PrivilegeNextOrderNoPortOut {
  private final PrivilegeSaveRepo privilegeRepo;

  @Override
  public Integer connect() {
    return privilegeRepo.getMaxOrderNo().orElse(-1) + 1;
  }
}

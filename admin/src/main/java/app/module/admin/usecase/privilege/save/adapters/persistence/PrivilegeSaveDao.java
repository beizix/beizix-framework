package app.module.admin.usecase.privilege.save.adapters.persistence;

import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.save.ports.PrivilegeSavePortOut;
import app.module.admin.config.adapter.persistence.entity.Privilege;
import app.module.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeSaveDao implements PrivilegeSavePortOut {
  private final PrivilegeSaveRepo privilegeRepo;

  @Override
  public String connect(PrivilegeSaveCommand saveInput) {
    return privilegeRepo
        .save(new Privilege(saveInput.getId(), saveInput.getDescription(), saveInput.getOrderNo()))
        .getId();
  }
}

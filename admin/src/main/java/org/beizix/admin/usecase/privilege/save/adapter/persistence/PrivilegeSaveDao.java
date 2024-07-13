package org.beizix.admin.usecase.privilege.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.save.application.port.out.PrivilegeSavePortOut;
import org.beizix.admin.config.adapter.persistence.entity.Privilege;
import org.beizix.admin.usecase.admin.save.ports.application.domain.PrivilegeSaveCommand;
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

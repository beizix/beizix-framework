package org.beizix.admin.usecase.privilege.save.adapter.persistence;

import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.save.application.port.out.PrivilegeSavePortOut;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeSaveDao implements PrivilegeSavePortOut {
  private final PrivilegeSaveRepo privilegeRepo;

  @Override
  public String connect(PrivilegeSaveInput saveInput) {
    return privilegeRepo
        .save(new Privilege(saveInput.getId(), saveInput.getDescription(), saveInput.getOrderNo()))
        .getId();
  }
}

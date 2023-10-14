package org.beizix.security.adapter.persistence.privilege;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.beizix.security.adapter.persistence.privilege.repository.PrivilegeRepo;
import org.beizix.security.application.domain.privilege.model.save.PrivilegeSaveInput;
import org.beizix.security.application.port.out.privilege.PrivilegeSavePortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class PrivilegeSaveDao implements PrivilegeSavePortOut {
  private final PrivilegeRepo privilegeRepo;

  @Override
  public String connect(PrivilegeSaveInput saveInput) {
    return privilegeRepo
        .save(new Privilege(saveInput.getId(), saveInput.getDescription(), saveInput.getOrderNo()))
        .getId();
  }
}

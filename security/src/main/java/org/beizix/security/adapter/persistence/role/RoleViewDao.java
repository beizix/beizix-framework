package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.view.RoleViewOutput;
import org.beizix.security.application.port.out.role.RoleViewOutPut;

@Repository
@RequiredArgsConstructor
public class RoleViewDao implements RoleViewOutPut {
  private final RoleRepo roleRepo;
  private final ModelMapper modelMapper;

  @Override
  public RoleViewOutput connect(String role) {
    return roleRepo
        .findById(role)
        .map(adminUserRoleEntity -> modelMapper.map(adminUserRoleEntity, RoleViewOutput.class))
        .orElse(null);
  }
}

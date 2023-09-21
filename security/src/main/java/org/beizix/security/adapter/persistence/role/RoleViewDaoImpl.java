package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.view.RoleViewResp;
import org.beizix.security.application.port.out.role.RoleViewDao;

@Repository
@RequiredArgsConstructor
public class RoleViewDaoImpl implements RoleViewDao {
  private final RoleRepo roleRepo;
  private final ModelMapper modelMapper;

  @Override
  public RoleViewResp operate(String role) {
    return roleRepo
        .findById(role)
        .map(adminUserRoleEntity -> modelMapper.map(adminUserRoleEntity, RoleViewResp.class))
        .orElse(null);
  }
}

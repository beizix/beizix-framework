package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.save.RoleSaveInput;
import org.beizix.security.application.port.out.role.RoleSavePortOut;

@Repository
@RequiredArgsConstructor
public class RoleSaveDao implements RoleSavePortOut {
  private final RoleRepo roleRepo;
  private final ModelMapper modelMapper;

  @Override
  public RoleSaveInput connect(RoleSaveInput saveReq) {
    return modelMapper.map(
        roleRepo.save(modelMapper.map(saveReq, Role.class)),
        RoleSaveInput.class);
  }
}

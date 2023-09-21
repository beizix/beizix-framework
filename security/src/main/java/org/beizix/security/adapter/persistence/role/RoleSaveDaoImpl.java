package org.beizix.security.adapter.persistence.role;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.save.RoleSaveReq;
import org.beizix.security.application.port.out.role.RoleSaveDao;

@Repository
@RequiredArgsConstructor
public class RoleSaveDaoImpl implements RoleSaveDao {
  private final RoleRepo roleRepo;
  private final ModelMapper modelMapper;

  @Override
  public RoleSaveReq operate(RoleSaveReq saveReq) {
    return modelMapper.map(
        roleRepo.save(modelMapper.map(saveReq, Role.class)),
        RoleSaveReq.class);
  }
}

package org.beizix.security.application.domain.role;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.list.RoleListOutput;
import org.beizix.security.application.port.in.role.RoleListPortIn;

@Service
@RequiredArgsConstructor
public class RoleListService implements RoleListPortIn {
  private final RoleRepo roleRepo;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public List<RoleListOutput> connect() {
    return roleRepo.findAll(Sort.by(Sort.Direction.ASC, "orderNo")).stream()
        .map(adminUserRoleEntity -> modelMapper.map(adminUserRoleEntity, RoleListOutput.class))
        .collect(Collectors.toList());
  }
}

package org.beizix.security.application.domain.role;

import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.beizix.security.adapter.persistence.role.repository.RoleRepo;
import org.beizix.security.application.domain.role.model.list.RoleListResp;
import org.beizix.security.application.port.in.role.RoleListService;

@Service
@RequiredArgsConstructor
public class RoleListServiceImpl implements RoleListService {
  private final RoleRepo roleRepo;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public List<RoleListResp> operate() {
    return roleRepo.findAll(Sort.by(Sort.Direction.ASC, "orderNo")).stream()
        .map(adminUserRoleEntity -> modelMapper.map(adminUserRoleEntity, RoleListResp.class))
        .collect(Collectors.toList());
  }
}

package org.beizix.security.adapter.persistence.admin;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.domain.admin.model.view.AdminViewResp;
import org.beizix.security.application.port.out.admin.AdminViewDao;

@Component
@RequiredArgsConstructor
class AdminViewDaoImpl implements AdminViewDao {
  private final AdminRepo adminRepo;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public Optional<AdminViewResp> operate(String id) {
    return adminRepo.findAdminUserById(id).stream()
        .map(v -> modelMapper.map(v, AdminViewResp.class))
        .findFirst();
  }
}

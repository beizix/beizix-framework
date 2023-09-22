package org.beizix.security.adapter.persistence.admin;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;
import org.beizix.security.application.port.out.admin.AdminViewPortOut;

@Component
@RequiredArgsConstructor
class AdminViewDao implements AdminViewPortOut {
  private final AdminRepo adminRepo;
  private final ModelMapper modelMapper;

  @Override
  @Transactional
  public Optional<AdminViewOutput> connect(String id) {
    return adminRepo.findAdminUserById(id).stream()
        .map(v -> modelMapper.map(v, AdminViewOutput.class))
        .findFirst();
  }
}

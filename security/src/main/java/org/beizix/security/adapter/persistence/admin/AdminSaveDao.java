package org.beizix.security.adapter.persistence.admin;

import java.util.Optional;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.domain.admin.model.save.AdminSaveInput;
import org.beizix.security.application.port.out.admin.AdminSavePortOut;

@Component
@RequiredArgsConstructor
class AdminSaveDao implements AdminSavePortOut {
  private final AdminRepo adminRepo;
  private final ModelMapper modelMapper;

  @Override
  public Optional<AdminSaveInput> connect(AdminSaveInput saveInflow) {
    adminRepo.save(modelMapper.map(saveInflow, Admin.class));
    return Optional.of(saveInflow);
  }

  @Override
  @Transactional
  public void updateLoginFailCnt(String id, Integer failCnt) {
    adminRepo.updateLoginFailCnt(id, failCnt);
  }

  @Override
  @Transactional
  public void updateAccountLocked(String id, boolean accountLocked) {
    adminRepo.updateAccountLocked(id, accountLocked);
  }
}

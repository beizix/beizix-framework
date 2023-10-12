package org.beizix.security.adapter.persistence.admin;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.port.out.admin.AdminRemovePortOut;

@Component
@RequiredArgsConstructor
class AdminRemoveDao implements AdminRemovePortOut {
  private final AdminRepo adminRepo;

  @Override
  public void connect(List<String> checkedIds) {
    adminRepo.deleteAllById(checkedIds);
  }
}

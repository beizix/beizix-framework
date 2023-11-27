package org.beizix.admin.usecase.admin.remove.adapter.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.remove.application.port.out.AdminRemovePortOut;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class AdminRemoveDao implements AdminRemovePortOut {
  private final AdminRemoveRepo adminRepo;

  @Override
  public void connect(List<String> checkedIds) {
    adminRepo.deleteAllById(checkedIds);
  }
}

package app.module.admin.usecase.admin.remove.adapters.persistence;

import java.util.List;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.remove.ports.AdminRemovePortOut;
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

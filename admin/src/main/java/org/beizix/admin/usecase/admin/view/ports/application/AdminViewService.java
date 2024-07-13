package org.beizix.admin.usecase.admin.view.ports.application;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.view.ports.AdminViewPortIn;
import org.beizix.admin.usecase.admin.view.ports.AdminViewPortOut;
import org.springframework.stereotype.Service;
import org.beizix.admin.usecase.admin.view.ports.application.domain.AdminView;

@Service
@RequiredArgsConstructor
class AdminViewService implements AdminViewPortIn {
  private final AdminViewPortOut adminViewPortOut;

  @Override
  public Optional<AdminView> connect(String adminId) {
    return adminViewPortOut.connect(adminId);
  }
}

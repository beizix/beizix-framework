package org.beizix.admin.usecase.admin.view.application.port.in;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.view.application.port.out.AdminViewPortOut;
import org.springframework.stereotype.Service;
import org.beizix.admin.usecase.admin.view.application.domain.AdminView;

@Service
@RequiredArgsConstructor
class AdminViewService implements AdminViewPortIn {
  private final AdminViewPortOut adminViewPortOut;

  @Override
  public Optional<AdminView> connect(String adminId) {
    return adminViewPortOut.connect(adminId);
  }
}

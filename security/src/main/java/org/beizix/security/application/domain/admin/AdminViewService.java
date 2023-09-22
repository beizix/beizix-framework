package org.beizix.security.application.domain.admin;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.admin.model.view.AdminViewOutput;
import org.beizix.security.application.port.in.admin.AdminViewPortIn;
import org.beizix.security.application.port.out.admin.AdminViewPortOut;

@Service
@RequiredArgsConstructor
class AdminViewService implements AdminViewPortIn {
  private final AdminViewPortOut adminViewPortOut;

  @Override
  public Optional<AdminViewOutput> connect(String adminId) {
    return adminViewPortOut.connect(adminId);
  }
}

package app.module.admin.usecase.admin.view.ports.application;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.view.ports.AdminViewPortIn;
import app.module.admin.usecase.admin.view.ports.AdminViewPortOut;
import org.springframework.stereotype.Service;
import app.module.admin.usecase.admin.view.ports.application.domain.AdminView;

@Service
@RequiredArgsConstructor
class AdminViewService implements AdminViewPortIn {
  private final AdminViewPortOut adminViewPortOut;

  @Override
  public Optional<AdminView> connect(String adminId) {
    return adminViewPortOut.connect(adminId);
  }
}

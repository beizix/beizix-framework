package app.module.admin.usecase.admin.remove.ports.application;

import java.util.List;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.remove.ports.AdminRemovePortIn;
import app.module.admin.usecase.admin.remove.ports.AdminRemovePortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class AdminRemoveService implements AdminRemovePortIn {
  private final AdminRemovePortOut adminRemovePortOut;

  @Override
  public void connect(List<String> checkedIds) {
    adminRemovePortOut.connect(checkedIds);
  }
}

package org.beizix.admin.usecase.admin.remove.application.port.in;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.remove.application.port.out.AdminRemovePortOut;
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

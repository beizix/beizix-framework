package app.module.admin.usecase.privilege.sort.ports.application;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.privilege.sort.ports.application.domain.PrivilegeSortCmd;
import app.module.admin.usecase.privilege.sort.ports.PrivilegeSortPortIn;
import app.module.admin.usecase.privilege.sort.ports.PrivilegeSortPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeSortService implements PrivilegeSortPortIn {
  private final PrivilegeSortPortOut portOut;

  @Override
  @Transactional
  public void connect(List<PrivilegeSortCmd> sortReqs) {
    for (PrivilegeSortCmd sortInput : sortReqs) {
      portOut.connect(sortInput.getId(), sortInput.getOrderNo());
    }
  }
}

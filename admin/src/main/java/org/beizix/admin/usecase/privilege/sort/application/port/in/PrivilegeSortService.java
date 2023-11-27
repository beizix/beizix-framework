package org.beizix.admin.usecase.privilege.sort.application.port.in;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.privilege.sort.application.domain.PrivilegeSortCommand;
import org.beizix.admin.usecase.privilege.sort.application.port.out.PrivilegeSortPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeSortService implements PrivilegeSortPortIn {
  private final PrivilegeSortPortOut portOut;

  @Override
  @Transactional
  public void connect(List<PrivilegeSortCommand> sortReqs) {
    for (PrivilegeSortCommand sortInput : sortReqs) {
      portOut.connect(sortInput.getId(), sortInput.getOrderNo());
    }
  }
}

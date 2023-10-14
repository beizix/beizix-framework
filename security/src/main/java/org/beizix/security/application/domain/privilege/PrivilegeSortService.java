package org.beizix.security.application.domain.privilege;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.security.application.domain.privilege.model.sort.PrivilegeSortInput;
import org.beizix.security.application.port.in.privilege.PrivilegeSortPortIn;
import org.beizix.security.application.port.out.privilege.PrivilegeSortPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PrivilegeSortService implements PrivilegeSortPortIn {
  private final PrivilegeSortPortOut portOut;

  @Override
  @Transactional
  public void connect(List<PrivilegeSortInput> sortReqs) {
    for (PrivilegeSortInput sortInput : sortReqs) {
      portOut.connect(sortInput.getId(), sortInput.getOrderNo());
    }
  }
}

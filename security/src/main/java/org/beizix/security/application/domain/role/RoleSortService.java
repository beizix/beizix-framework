package org.beizix.security.application.domain.role;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.role.model.sort.RoleSortInput;
import org.beizix.security.application.port.in.role.RoleSortPortIn;
import org.beizix.security.application.port.out.role.RoleSortPortOut;

@Service
@RequiredArgsConstructor
public class RoleSortService implements RoleSortPortIn {
  private final RoleSortPortOut roleSortPortOut;

  @Override
  @Transactional
  public void connect(List<RoleSortInput> sortReqs) {
    for (RoleSortInput sortReq : sortReqs) {
      roleSortPortOut.connect(sortReq.getId(), sortReq.getOrderNo());
    }
  }
}

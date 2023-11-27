package org.beizix.admin.usecase.role.sort.application.port.in;

import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.security.application.domain.role.model.sort.RoleSortInput;
import org.beizix.admin.usecase.role.sort.application.port.out.RoleSortPortOut;
import org.springframework.stereotype.Service;

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

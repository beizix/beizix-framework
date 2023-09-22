package org.beizix.security.application.domain.role;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.role.model.sort.RoleSortInput;
import org.beizix.security.application.port.in.role.RoleSortPortIn;
import org.beizix.security.application.port.out.role.RoleSortOutput;

@Service
@RequiredArgsConstructor
public class RoleSortService implements RoleSortPortIn {
  private final RoleSortOutput roleSortOutput;

  @Override
  public void connect(List<RoleSortInput> sortReqs) {
    for (RoleSortInput sortReq : sortReqs) {
      roleSortOutput.connect(sortReq.getId(), sortReq.getOrderNo());
    }
  }
}

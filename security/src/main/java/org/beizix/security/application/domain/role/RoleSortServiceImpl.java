package org.beizix.security.application.domain.role;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.beizix.security.application.domain.role.model.sort.RoleSortReq;
import org.beizix.security.application.port.in.role.RoleSortService;
import org.beizix.security.application.port.out.role.RoleSortDao;

@Service
@RequiredArgsConstructor
public class RoleSortServiceImpl implements RoleSortService {
  private final RoleSortDao roleSortDao;

  @Override
  public void operate(List<RoleSortReq> sortReqs) {
    for (RoleSortReq sortReq : sortReqs) {
      roleSortDao.operate(sortReq.getId(), sortReq.getOrderNo());
    }
  }
}

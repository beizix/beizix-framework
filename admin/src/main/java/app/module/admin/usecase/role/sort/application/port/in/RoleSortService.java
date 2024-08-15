package app.module.admin.usecase.role.sort.application.port.in;

import java.util.List;
import javax.transaction.Transactional;

import app.module.admin.usecase.role.sort.application.domain.RoleSortCommand;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.role.sort.application.port.out.RoleSortPortOut;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RoleSortService implements RoleSortPortIn {
  private final RoleSortPortOut roleSortPortOut;

  @Override
  @Transactional
  public void connect(List<RoleSortCommand> sortReqs) {
    for (RoleSortCommand sortReq : sortReqs) {
      roleSortPortOut.connect(sortReq.getId(), sortReq.getOrderNo());
    }
  }
}

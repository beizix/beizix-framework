package app.module.admin.usecase.admin.status.adapters.persistence;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import app.module.admin.usecase.admin.status.ports.AdminUpdateLoginFailPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminUpdateLoginFailDao implements AdminUpdateLoginFailPortOut {
  private final AdminStatusRepo adminRepo;

  @Override
  @Transactional
  public void connect(String id, Integer failCnt) {
    adminRepo.updateLoginFailCnt(id, failCnt);
  }
}

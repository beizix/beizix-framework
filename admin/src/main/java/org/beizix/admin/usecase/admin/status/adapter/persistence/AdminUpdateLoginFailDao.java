package org.beizix.admin.usecase.admin.status.adapter.persistence;

import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.beizix.admin.usecase.admin.status.application.port.out.AdminUpdateLoginFailPortOut;
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

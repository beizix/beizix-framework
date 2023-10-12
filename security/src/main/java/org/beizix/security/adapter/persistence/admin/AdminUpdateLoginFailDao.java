package org.beizix.security.adapter.persistence.admin;

import lombok.RequiredArgsConstructor;
import org.beizix.security.adapter.persistence.admin.repository.AdminRepo;
import org.beizix.security.application.port.out.admin.AdminUpdateLoginFailPortOut;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminUpdateLoginFailDao implements AdminUpdateLoginFailPortOut {
  private final AdminRepo adminRepo;

  @Override
  public void connect(String id, Integer failCnt) {
    adminRepo.updateLoginFailCnt(id, failCnt);
  }
}

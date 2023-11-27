package org.beizix.admin.usecase.admin.status.adapter.persistence;

import org.beizix.security.adapter.persistence.admin.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface AdminStatusRepo extends JpaRepository<Admin, String> {
  @Modifying
  @Query("update Admin e set e.loginFailCnt = :failCnt where e.id = :id")
  void updateLoginFailCnt(String id, Integer failCnt);

  @Modifying
  @Query("update Admin e set e.accountLocked = :accountLocked where e.id = :id")
  void updateAccountLocked(String id, boolean accountLocked);
}

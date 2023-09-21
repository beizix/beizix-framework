package org.beizix.security.adapter.persistence.admin.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;
import org.beizix.security.adapter.persistence.admin.model.Admin;

@Repository
public interface AdminRepo extends JpaRepository<Admin, String>, JpaSpecificationExecutor<Admin> {

  @EntityGraph("view_details_entity_graph")
  Optional<Admin> findAdminUserById(String id);

  @Modifying
  @Query("update Admin e set e.loginFailCnt = :failCnt where e.id = :id")
  void updateLoginFailCnt(String id, Integer failCnt);

  @Modifying
  @Query("update Admin e set e.accountLocked = :accountLocked where e.id = :id")
  void updateAccountLocked(String id, boolean accountLocked);
}

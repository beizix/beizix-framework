package org.beizix.security.adapter.persistence.privilege.repository;

import java.util.Optional;
import org.beizix.security.adapter.persistence.privilege.model.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrivilegeRepo extends JpaRepository<Privilege, String> {
  @Modifying
  @Query("update Privilege e set e.orderNo = :orderNo where e.id = :role")
  void updateOrderNo(@Param("role") String role, @Param("orderNo") Integer orderNo);


}

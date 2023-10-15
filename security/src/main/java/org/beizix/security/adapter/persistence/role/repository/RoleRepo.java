package org.beizix.security.adapter.persistence.role.repository;

import java.util.Optional;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleRepo extends JpaRepository<Role, String> {
  @Modifying
  @Query("update Role e set e.orderNo = :orderNo where e.id = :role")
  void updateOrderNo(@Param("role") String role, @Param("orderNo") Integer orderNo);

  @Query("select max(e.orderNo) from Role e")
  Optional<Integer> getMaxOrderNo();
}

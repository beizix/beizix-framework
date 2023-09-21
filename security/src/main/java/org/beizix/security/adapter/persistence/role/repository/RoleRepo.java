package org.beizix.security.adapter.persistence.role.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.beizix.security.adapter.persistence.role.model.Role;

public interface RoleRepo extends JpaRepository<Role, String> {
  /**
   * Role - 순서 변경
   *
   * @param role
   * @param orderNo
   */
  @Modifying
  @Query("update Role e set e.orderNo = :orderNo where e.id = :role")
  void updateOrderNo(@Param("role") String role, @Param("orderNo") Integer orderNo);

  @Query("select max(e.orderNo) from Role e")
  Optional<Integer> getMaxOrderNo();
}

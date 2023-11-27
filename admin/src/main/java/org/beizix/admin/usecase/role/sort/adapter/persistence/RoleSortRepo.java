package org.beizix.admin.usecase.role.sort.adapter.persistence;

import org.beizix.admin.configuration.adapter.persistence.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface RoleSortRepo extends JpaRepository<Role, String> {
  @Modifying
  @Query("update Role e set e.orderNo = :orderNo where e.id = :role")
  void updateOrderNo(@Param("role") String role, @Param("orderNo") Integer orderNo);
}

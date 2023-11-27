package org.beizix.admin.usecase.role.save.adapter.persistence;

import java.util.Optional;
import org.beizix.security.adapter.persistence.role.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleNextOrderNoRepo extends JpaRepository<Role, String> {
  @Query("select max(e.orderNo) from Role e")
  Optional<Integer> getMaxOrderNo();
}

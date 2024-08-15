package app.module.admin.usecase.privilege.sort.adapters.persistence;

import app.module.admin.config.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PrivilegeSortRepo extends JpaRepository<Privilege, String> {
  @Modifying
  @Query("update Privilege e set e.orderNo = :orderNo where e.id = :role")
  void updateOrderNo(@Param("role") String role, @Param("orderNo") Integer orderNo);
}

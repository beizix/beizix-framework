package org.beizix.admin.usecase.privilege.save.adapter.persistence;

import java.util.Optional;
import org.beizix.admin.configuration.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeSaveRepo extends JpaRepository<Privilege, String> {
  @Query("select max(e.orderNo) from Privilege e")
  Optional<Integer> getMaxOrderNo();
}

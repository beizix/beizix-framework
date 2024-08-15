package app.module.admin.usecase.privilege.save.adapters.persistence;

import java.util.Optional;
import app.module.admin.config.adapter.persistence.entity.Privilege;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PrivilegeSaveRepo extends JpaRepository<Privilege, String> {
  @Query("select max(e.orderNo) from Privilege e")
  Optional<Integer> getMaxOrderNo();
}
